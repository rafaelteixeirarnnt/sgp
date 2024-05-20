package br.com.leaf.pedidos.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriaPedidoRequestDTO {

    @NotNull
    private UUID idProduto;

    @NotNull
    private UUID idCliente;

    @NotNull
    private BigInteger quantidade;

    @NotNull
    private BigDecimal preco;

}