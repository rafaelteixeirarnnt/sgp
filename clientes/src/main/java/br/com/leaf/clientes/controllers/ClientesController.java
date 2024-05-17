package br.com.leaf.clientes.controllers;

import br.com.leaf.clientes.dtos.AtualizaClienteRequestDTO;
import br.com.leaf.clientes.dtos.ClienteDTO;
import br.com.leaf.clientes.dtos.CriaClienteRequestDTO;
import br.com.leaf.clientes.services.ClientesServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/clientes")
@Tag(name = "1 - Clientes", description = "Serviços disponíveis para gerenciamento de Clientes.")
public class ClientesController {

    private final ClientesServices service;

    @PostMapping
    @Operation(summary = "Serviço responsável por cadastrar clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso", headers = {
                    @Header(name = "Location", description = "URI do recurso criado", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "400", description = "Falha no cadastro do cliente"),
    })
    public ResponseEntity<UUID> criar(@RequestBody @Valid CriaClienteRequestDTO dto) {
        var uuid = service.salvarCliente(dto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuid)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Serviço responsável por atualizar os clientes.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Falha ao atualizar cliente"),
    })
    public ResponseEntity<ClienteDTO> atualizarCliente(@NotNull @PathVariable String id, @RequestBody @Valid AtualizaClienteRequestDTO dto) {
        var clienteDTO = service.atualizarCliente(id, dto);
        return ResponseEntity.ok().body(clienteDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Serviço responsável por obter clientes por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente localizado com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Falha ao recuperar cliente"),
    })
    public ResponseEntity<ClienteDTO> obterCliente(@NotNull @PathVariable String id) {
        var clienteDTO = service.obterClienteId(id);
        return ResponseEntity.ok().body(clienteDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Serviço responsável por excluir cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente excluído com sucesso"),
            @ApiResponse(responseCode = "400", description = "Falha ao recuperar cliente"),
    })
    public ResponseEntity<ClienteDTO> deletarCliente(@NotNull @PathVariable String id) {
        service.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }


}
