package br.com.leaf.produtos.services;

import br.com.leaf.produtos.dtos.AtualizaProdutoDTO;
import br.com.leaf.produtos.dtos.CadastraProdutoDTO;
import br.com.leaf.produtos.dtos.ProdutosDTO;
import br.com.leaf.produtos.enums.DominioCategoria;
import br.com.leaf.produtos.enums.DominioSort;
import br.com.leaf.produtos.exceptions.ProdutosNegocioException;
import br.com.leaf.produtos.mappers.ProdutosMapper;
import br.com.leaf.produtos.models.Produtos;
import br.com.leaf.produtos.repositories.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProdutosServices {

    private static final String CSV = ".csv";
    private static final String SEPARADOR = ";";
    private static final String TYPE = "text/csv";
    private static final String PRODUTOS_IMPORTACAO = "produtos-importacao";
    private static final String APPLICATION_VND_MS_EXCEL = "application/vnd.ms-excel";
    private static final String CABECALHO_CSV_PRODUTOS = "id;nome;descricao;preco;categoria;especificacaoTecnica;situacao;quantidade";

    private static final int INDICE_ID = 0;
    private static final int INDICE_NOME = 1;
    private static final int INDICE_DESCRICAO = 2;
    private static final int INDICE_PRECO = 3;
    private static final int INDICE_CATEGORIA = 4;
    private static final int INDICE_ESPECIFICACAO_TECNICA = 5;
    private static final int INDICE_SITUACAO = 6;
    private static final int INDICE_QUANTIDADE = 7;

    private final ProdutosMapper mapper;
    private final ProdutosRepository repository;

    @Transactional
    public boolean importarProdutos(MultipartFile arquivo) {
        if (this.validarArquivo(arquivo)) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()))) {
                var produtos = importarArquivosListaProdutos(reader);
                this.repository.saveAll(produtos);
                return true;
            } catch (IOException e) {
                log.error("Falha ao importar produtos", e);
            }
        }
        return false;
    }

    private List<Produtos> importarArquivosListaProdutos(BufferedReader reader) throws IOException {
        String line;
        reader.readLine(); // Pular cabeçalho
        List<Produtos> produtos = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(SEPARADOR);
            var produto = new Produtos();
            UUID id = montarID(data);
            if (Objects.isNull(id)) {
                criarProdutoSemRegistro(produto, id, data);
            } else {
                var produtoDB  = this.repository.findById(id).orElse(null);
                if (Objects.nonNull(produtoDB)) {
                    atualizarProdutoComRegistro(produtoDB, data);
                    produto = produtoDB;
                } else {
                    criarProdutoSemRegistro(produto, id, data);
                }
            }
            produtos.add(produto);
        }
        return produtos;
    }

    private void atualizarProdutoComRegistro(Produtos produtoDB, String[] data) {
        produtoDB.setNome(data[INDICE_NOME]);
        produtoDB.setDescricao(data[INDICE_DESCRICAO]);
        produtoDB.setPreco(BigDecimal.valueOf(Double.parseDouble(data[INDICE_PRECO])));
        produtoDB.setQuantidade(BigInteger.valueOf(Long.parseLong(data[INDICE_QUANTIDADE])));
        produtoDB.setEspecificacaoTecnica(data[INDICE_ESPECIFICACAO_TECNICA]);
        produtoDB.setCategoria(DominioCategoria.obterPorSigla(data[INDICE_CATEGORIA]));
        produtoDB.setSituacao(Boolean.valueOf(data[INDICE_SITUACAO]));
        produtoDB.setDtAtualizacao(LocalDateTime.now());
    }

    private void criarProdutoSemRegistro(Produtos produto, UUID id, String[] data) {
        produto.setId(id);
        produto.setNome(data[INDICE_NOME]);
        produto.setDescricao(data[INDICE_DESCRICAO]);
        produto.setPreco(BigDecimal.valueOf(Double.parseDouble(data[INDICE_PRECO])));
        produto.setQuantidade(BigInteger.valueOf(Long.parseLong(data[INDICE_QUANTIDADE])));
        produto.setEspecificacaoTecnica(data[INDICE_ESPECIFICACAO_TECNICA]);
        produto.setCategoria(DominioCategoria.obterPorSigla(data[INDICE_CATEGORIA]));
        produto.setSituacao(Boolean.valueOf(data[INDICE_SITUACAO]));
        produto.setDtCadastro(LocalDateTime.now());
    }

    private UUID montarID(String[] data) {
        try {
            var id = data[INDICE_ID];
            return Objects.nonNull(id) ? UUID.fromString(id) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public boolean validarArquivo(MultipartFile arquivo) {
        if (Objects.nonNull(arquivo) && Objects.nonNull(arquivo.getContentType()) && Objects.nonNull(arquivo.getOriginalFilename())) {
            var isCsvType = arquivo.getContentType().equals(TYPE) || arquivo.getContentType().equals(APPLICATION_VND_MS_EXCEL);
            if (!isCsvType) {
                throw new ProdutosNegocioException("Tipo do arquivo é inválido.");
            }
            var isCsvExtension = arquivo.getOriginalFilename().toLowerCase().endsWith(CSV);
            if (!isCsvExtension) {
                throw new ProdutosNegocioException("Extensão inválida do arquivo.");
            }
            return true;
        }
        return false;
    }

    public Path downloadArquivoCsv() throws IOException {
        var arquivoTemp = Files.createTempFile(PRODUTOS_IMPORTACAO, CSV);
        return Files.write(arquivoTemp, CABECALHO_CSV_PRODUTOS.getBytes());
    }

    public Page<ProdutosDTO> obterTodosPaginados(int page, int size, String sortField, DominioSort typeSort) {
        var sort = Sort.by(Sort.Direction.fromString(typeSort.getDescricao()), sortField);
        var pageable = PageRequest.of(page, size, sort);
        Page<Produtos> produtosPage;

        try {
            produtosPage = this.repository.findAll(pageable);
        } catch (PropertyReferenceException e) {
            throw new ProdutosNegocioException("Propriedade não encontrada. Por favor, informo um novo parâmetro de ordenação");
        }

        var produtosDTOList = produtosPage.getContent().stream()
                .map(this.mapper::criarProdutosToProdutosDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(produtosDTOList, pageable, produtosPage.getTotalElements());
    }

    public ProdutosDTO obterPorId(String id) {
        var uuid = getUuid(id);

        return this.mapper.criarProdutosToProdutosDTO(this.repository.findById(uuid).orElseThrow(() -> new ProdutosNegocioException("Produto não localizado")));
    }

    private UUID getUuid(String id) {
        if (Objects.isNull(id) || id.isBlank()) {
            throw new ProdutosNegocioException("Informar o ID do produto");
        }
        return UUID.fromString(id);
    }

    public ProdutosDTO atualizarProduto(String id, AtualizaProdutoDTO dto) {
        var uuid = getUuid(id);
        var produto = this.repository.findById(uuid).orElseThrow(() -> new ProdutosNegocioException("Produto não localizado"));

        produto.setNome(dto.nome());
        produto.setPreco(dto.preco());
        produto.setDescricao(dto.descricao());
        produto.setCategoria(DominioCategoria.obterPorSigla(dto.categoria()));
        produto.setQuantidade(dto.quantidade());
        produto.setEspecificacaoTecnica(dto.especificacaoTecnica());
        produto.setSituacao(dto.situacao());
        produto.setDtAtualizacao(LocalDateTime.now());

        var produtoDB = this.repository.save(produto);

        return this.mapper.criarProdutosToProdutosDTO(produtoDB);
    }

    public UUID cadastrarProduto(CadastraProdutoDTO dto) {
        var produto = this.mapper.criarCadastraProdutoDTOToProdutos(dto);
        produto.setSituacao(Boolean.TRUE);
        produto.setDtCadastro(LocalDateTime.now());
        return this.repository.save(produto).getId();
    }
}
