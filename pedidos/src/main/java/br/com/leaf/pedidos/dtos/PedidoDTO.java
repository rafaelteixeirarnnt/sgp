package br.com.leaf.pedidos.dtos;

import br.com.leaf.pedidos.enums.DominioStatusPagamento;
import br.com.leaf.pedidos.enums.DominioStatusPedido;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO {

    private UUID id;
    private UUID idProduto;
    private UUID idCliente;
    private BigDecimal preco;
    private BigInteger quantidade;
    private String statusPedido;
    private String statusPagamento;
    private LocalDateTime dtCadastro;
    private LocalDateTime dtAtualizacao;;

}
