package br.com.leaf.produtos.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizaEstoqueDTO {

    @NotNull
    private String idProduto;

    @NotNull
    private BigInteger quantidade;

}
