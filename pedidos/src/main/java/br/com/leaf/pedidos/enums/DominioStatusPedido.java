package br.com.leaf.pedidos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DominioStatusPedido {

    EM_ANALISE("Em análise", "EM"),
    AGUARDANDO_PAGAMENTO("Aguardando Pagamento", "AG"),
    CANCELADO("Cancelado", "CA"),
    APROVADO("Aprovado", "AP");

    private final String descricao;
    private final String sigla;

}
