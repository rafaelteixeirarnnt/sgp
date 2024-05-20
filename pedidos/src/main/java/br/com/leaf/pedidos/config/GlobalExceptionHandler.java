package br.com.leaf.pedidos.config;

import br.com.leaf.pedidos.exceptions.ErrorResponse;
import br.com.leaf.pedidos.exceptions.PedidoNegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PedidoNegocioException.class)
    public ResponseEntity<ErrorResponse> handleProdutoException(PedidoNegocioException ex, WebRequest request) {
        ErrorResponse error;
        switch (ex.getCode()) {
            case 400:
                error = new ErrorResponse(BAD_REQUEST.value(),
                        ex.getMessage(),
                        request.getDescription(false),
                        getStackTrace(ex.getCause()));
                return new ResponseEntity<>(error, BAD_REQUEST);
            case 404:
                error = new ErrorResponse(NOT_FOUND.value(),
                        ex.getMessage(),
                        request.getDescription(false),
                        getStackTrace(ex.getCause()));
                return new ResponseEntity<>(error, NOT_FOUND);
        }
        error = new ErrorResponse(BAD_REQUEST.value(),
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
