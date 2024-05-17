package br.com.leaf.clientes;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DominioSituacao {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private final String descricao;

}
