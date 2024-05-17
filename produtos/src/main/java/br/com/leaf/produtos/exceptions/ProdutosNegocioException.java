package br.com.leaf.produtos.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class ProdutosNegocioException extends RuntimeException {

    public ProdutosNegocioException (String mensagem) {
        super(mensagem);
    }

    public ProdutosNegocioException (String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
