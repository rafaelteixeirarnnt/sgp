package br.com.leaf.pedidos.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtualizaEstoqueRequest {

    private UUID idProduto;
    private BigInteger quantidade;

}
