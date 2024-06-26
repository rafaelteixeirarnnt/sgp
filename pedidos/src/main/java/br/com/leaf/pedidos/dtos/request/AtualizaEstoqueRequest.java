package br.com.leaf.pedidos.dtos.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizaEstoqueRequest implements Serializable {

    @NotNull
    private String idProduto;

    @NotNull
    @Size(min = 0)
    private BigInteger quantidade;

}
