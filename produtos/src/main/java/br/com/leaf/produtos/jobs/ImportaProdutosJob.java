package br.com.leaf.produtos.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImportaProdutosJob {


    @Bean
    public Job importarProdutos(JobRepository jobRepository, @Qualifier("importarProdutosStep") Step importarProdutosStep) {
        return new JobBuilder("importarProdutos", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(importarProdutosStep)
                .build();
    }

}
