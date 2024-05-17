package br.com.leaf.produtos.config;

import br.com.leaf.produtos.exceptions.ErrorResponse;
import br.com.leaf.produtos.exceptions.ProdutosNegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProdutosNegocioException.class)
    public ResponseEntity<ErrorResponse> handleProdutoException(ProdutosNegocioException ex, WebRequest request) {
        var error = new ErrorResponse(BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false),
                getStackTrace(ex.getCause()));
        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    public String getStackTrace(Throwable throwable) {
        var sw = new StringWriter();
        var pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        return sw.toString();
    }

}
