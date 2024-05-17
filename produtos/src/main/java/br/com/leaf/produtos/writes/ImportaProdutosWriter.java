package br.com.leaf.produtos.writes;

import br.com.leaf.produtos.models.Produtos;
import br.com.leaf.produtos.repositories.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Objects;

@Configuration
@RequiredArgsConstructor
public class ImportaProdutosWriter {

    private final ProdutosRepository repository;

    @Bean
    public ItemWriter<Produtos> importarProdutosWriter() {
        return produtos -> produtos.getItems().forEach(produto -> {
            if (Objects.nonNull(produto.getId())) {
                var produtoDB = this.repository.findById(produto.getId()).orElse(null);
                if (Objects.isNull(produtoDB)) {
                    salvarProdutoSemID(produto);
                } else {
                    produtoDB.setQuantidade(produto.getQuantidade());
                    produtoDB.setSituacao(produto.getSituacao());
                    produtoDB.setPreco(produto.getPreco());
                    produtoDB.setNome(produto.getNome());
                    produtoDB.setDescricao(produto.getDescricao());
                    produtoDB.setEspecificacaoTecnica(produto.getEspecificacaoTecnica());
                    produtoDB.setCategoria(produto.getCategoria());
                    produtoDB.setDtAtualizacao(LocalDateTime.now());
                }
            } else {
                salvarProdutoSemID(produto);
            }
        });
    }

    private void salvarProdutoSemID(Produtos produto) {
        produto.setDtCadastro(LocalDateTime.now());
        this.repository.save(produto);
    }

}
