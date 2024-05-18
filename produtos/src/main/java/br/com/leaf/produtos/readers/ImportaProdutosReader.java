package br.com.leaf.produtos.readers;

import br.com.leaf.produtos.dtos.ProdutosImportDTO;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class ImportaProdutosReader {

    @Bean
    public ItemReader<ProdutosImportDTO> importarProdutosReader() {
        FlatFileItemReader<ProdutosImportDTO> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("carga_inicial.csv"));
        reader.setLinesToSkip(1);

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter(";");
        tokenizer.setNames("id", "nome", "descricao", "preco", "categoria", "especificacaoTecnica", "situacao", "quantidade");

        BeanWrapperFieldSetMapper<ProdutosImportDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(ProdutosImportDTO.class);

        reader.setLineMapper(new DefaultLineMapper<>() {
            {
                setLineTokenizer(tokenizer);
                setFieldSetMapper(fieldSetMapper);
            }
        });

        return reader;
    }

}
