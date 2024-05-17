package br.com.leaf.produtos.processor;

import br.com.leaf.produtos.dtos.ProdutosImportDTO;
import br.com.leaf.produtos.enums.DominioCategoria;
import br.com.leaf.produtos.models.Produtos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Configuration
public class ImportaProdutosProcessor {

    @Bean
    public ItemProcessor<ProdutosImportDTO, Produtos> importarProdutosProcessor() {
        return dto -> {
            var produto = new Produtos();

            produto.setId(Objects.nonNull(dto.getId()) && !dto.getId().isBlank() ? UUID.fromString(dto.getId()) : null);
            produto.setNome(Objects.nonNull(dto.getNome()) ? dto.getNome() : null);
            produto.setDescricao(Objects.nonNull(dto.getDescricao()) ? dto.getDescricao() : null);
            produto.setEspecificacaoTecnica(Objects.nonNull(dto.getEspecificacaoTecnica()) ? dto.getEspecificacaoTecnica() : null);

            produto.setCategoria(DominioCategoria.obterPorSigla(dto.getSituacao()));

            montarPrecoProdutos(dto, produto);
            montarQuantidadeProduto(dto, produto);
            montarSituacao(dto, produto);

            return produto;
        };
    }

    private void montarSituacao(ProdutosImportDTO dto, Produtos produto) {
        try {
            produto.setSituacao(Boolean.valueOf(dto.getSituacao()));
        } catch (Exception e) {
            log.error(e.getMessage());
            produto.setSituacao(Boolean.FALSE);
        }
    }

    private void montarQuantidadeProduto(ProdutosImportDTO dto, Produtos produto) {
        if (Objects.nonNull(dto.getQuantidade())) {
            try {
                var quantidade = BigInteger.valueOf(Long.parseLong(dto.getQuantidade()));
                if (quantidade.intValue() < 0) {
                    produto.setQuantidade(BigInteger.ZERO);
                } else {
                    produto.setQuantidade(quantidade);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                produto.setQuantidade(BigInteger.ZERO);
            }
        }
    }

    private void montarPrecoProdutos(ProdutosImportDTO dto, Produtos produto) {
        if (Objects.nonNull(dto.getPreco())) {
            try {
                var preco = BigDecimal.valueOf(Double.parseDouble(dto.getPreco()));
                if (preco.intValue() < 0) {
                    produto.setPreco(BigDecimal.ZERO);
                } else {
                    produto.setPreco(preco);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
                produto.setPreco(BigDecimal.ZERO);
            }
        }
    }

}
