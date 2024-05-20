package br.com.leaf.pedidos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DominioSituacao {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

}

