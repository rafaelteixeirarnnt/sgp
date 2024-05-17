package br.com.leaf.produtos.controllers;

import br.com.leaf.produtos.dtos.AtualizaProdutoDTO;
import br.com.leaf.produtos.dtos.CadastraProdutoDTO;
import br.com.leaf.produtos.dtos.ProdutosDTO;
import br.com.leaf.produtos.enums.DominioSort;
import br.com.leaf.produtos.services.ProdutosServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/produtos")
@Tag(name = "1 - Produtos", description = "Serviços disponíveis para gerenciamento de produtos.")
public class ProdutosController {

    private final ProdutosServices services;

    @PostMapping(value = "/importar-manualmente-produtos", produces = "application/json", consumes = "multipart/form-data")
    @Operation(summary = "Serviço responsável por importar produtos manualmente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo importado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Falha ao importar arquivo"),
    })
    public ResponseEntity<?> importarProdutos(@Valid @NotNull @RequestPart MultipartFile arquivo) {
        if (this.services.importarProdutos(arquivo)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping(value = "/export-csv", produces = "text/csv")
    @Operation(summary = "Serviço responsável por exportar o arquivo padrão csv para incluir os produtos iniciais.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo csv disponível para download"),
            @ApiResponse(responseCode = "400", description = "Falha ao gerar arquivo"),
    })
    public ResponseEntity<Resource> downloadArquivoCsv() {
        try {
            var arquivoCsv = this.services.downloadArquivoCsv();
            var resource = new UrlResource(arquivoCsv.toUri());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("text/csv"))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"produtos-importacao.csv\"")
                    .body(resource);
        } catch (IOException e) {
            log.error("Falha ao gerar arquivo csv", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping(produces = "application/json")
    @Operation(summary = "Serviço responsável por retornar todos os produtos paginados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha ao obter produtos"),
    })
    public ResponseEntity<Page<ProdutosDTO>> obterTodosPaginados(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size,
                                                        @RequestParam(defaultValue = "nome") String sortField,
                                                        @RequestParam DominioSort typeSort) {

        return ResponseEntity.ok().body(this.services.obterTodosPaginados(page, size, sortField, typeSort));
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @Operation(summary = "Serviço responsável por retornar o produto por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produtos retornados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha ao obter produto"),
    })
    public ResponseEntity<ProdutosDTO> obterPorId(@RequestParam String id) {

        return ResponseEntity.ok().body(this.services.obterPorId(id));
    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    @Operation(summary = "Serviço responsável por atualizar os produtos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha ao atualizar produto"),
    })
    public ResponseEntity<ProdutosDTO> atualizarProduto(@RequestParam String id, @Valid @RequestBody AtualizaProdutoDTO produto) {
        return ResponseEntity.ok().body(this.services.atualizarProduto(id, produto));
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    @Operation(summary = "Serviço responsável por cadastrar produto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produto cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha ao cadastrar produto"),
    })
    public ResponseEntity<ProdutosDTO> cadastrarProduto(@Valid @RequestBody CadastraProdutoDTO produto) {
        var uuid = this.services.cadastrarProduto(produto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuid)
                .toUri();
        return ResponseEntity.created(location).build();
    }

}
