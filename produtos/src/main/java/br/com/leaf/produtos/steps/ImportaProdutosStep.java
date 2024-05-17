package br.com.leaf.produtos.steps;

import br.com.leaf.produtos.dtos.ProdutosImportDTO;
import br.com.leaf.produtos.models.Produtos;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ImportaProdutosStep {

    @Bean
    public Step importarProdutosStep(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager,
                                     @Qualifier("importarProdutosReader") ItemReader<ProdutosImportDTO> importarProdutosReader,
                                     @Qualifier("importarProdutosProcessor") ItemProcessor<ProdutosImportDTO, Produtos> importarProdutosProcessor,
                                     @Qualifier("importarProdutosWriter") ItemWriter<Produtos> importarProdutosWriter) {
        return new StepBuilder("importarProdutosStep", jobRepository)
                .<ProdutosImportDTO, Produtos>chunk(100, platformTransactionManager)
                .reader(importarProdutosReader)
                .processor(importarProdutosProcessor)
                .writer(importarProdutosWriter)
                .build();
    }

}
