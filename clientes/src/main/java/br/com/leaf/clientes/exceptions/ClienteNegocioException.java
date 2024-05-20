package br.com.leaf.clientes.exceptions;

import lombok.Getter;

public class ClienteNegocioException extends RuntimeException {

    @Getter
    private int code;

    public ClienteNegocioException(String mensagem) {
        super(mensagem);
    }

    public ClienteNegocioException(String mensagem, int code) {
        super(mensagem);
        this.code = code;
    }

    public ClienteNegocioException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
