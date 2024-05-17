package br.com.leaf.produtos.dtos;

import br.com.leaf.produtos.enums.DominioSort;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

import static br.com.leaf.produtos.enums.DominioSort.ASC;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginatorDTO {

    private Integer page = 0;
    private DominioSort sort = ASC;
    private Integer linesPerPage = 10;
    private Map<String, Object> params;

}
