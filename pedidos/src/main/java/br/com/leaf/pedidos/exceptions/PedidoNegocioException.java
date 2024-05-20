package br.com.leaf.pedidos.exceptions;

import lombok.Getter;

public class PedidoNegocioException extends RuntimeException {

    @Getter
    private int code;

    public PedidoNegocioException(String mensagem) {
        super(mensagem);
    }

    public PedidoNegocioException(String mensagem, int code) {
        super(mensagem);
        this.code = code;
    }

    public PedidoNegocioException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
