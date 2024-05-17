package br.com.leaf.produtos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DominioSort {

    ASC("asc"),
    DESC("desc");

    private final String descricao;

}
