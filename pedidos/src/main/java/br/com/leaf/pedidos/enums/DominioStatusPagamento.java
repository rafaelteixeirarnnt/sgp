package br.com.leaf.pedidos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DominioStatusPagamento {

    EM_ANALISE("Em análise", "EM"),
    APROVADO("Aprovado", "AP"),
    RECUSADO("Recusado", "RE");

    private final String descricao;
    private final String sigla;

}
