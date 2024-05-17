package br.com.leaf.produtos.writes;

import br.com.leaf.produtos.dtos.ProdutosImportDTO;
import br.com.leaf.produtos.models.Produtos;
import br.com.leaf.produtos.repositories.ProdutosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ImportaProdutosWriter {

    private final ProdutosRepository repository;

    @Bean
    public ItemWriter<ProdutosImportDTO> importarProdutosWriter() {
        return produtos -> {
            //                this.repository.save(produto);
            produtos.getItems().forEach(System.out::println);
        };
    }

}
