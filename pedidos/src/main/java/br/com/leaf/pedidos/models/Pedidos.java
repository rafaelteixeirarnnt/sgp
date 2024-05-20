package br.com.leaf.pedidos.models;

import br.com.leaf.pedidos.enums.DominioStatusPagamento;
import br.com.leaf.pedidos.enums.DominioStatusPedido;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PEDIDOS", schema = "pedidos")
public class Pedidos {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ID_PRODUTO", nullable = false)
    private UUID idProduto;

    @Column(name = "ID_CLIENTE", nullable = false)
    private UUID idCliente;

    @Column(name = "VL_PRECO", scale = 4, nullable = false)
    private BigDecimal preco;

    @Column(name = "NR_QTD", nullable = false)
    private BigInteger quantidade;

    @Enumerated(EnumType.STRING)
    @Column(name = "TX_SITUACAO_PEDIDO", length = 2, nullable = false)
    private DominioStatusPedido statusPedido;

    @Enumerated(EnumType.STRING)
    @Column(name = "TX_SITUACAO_PAGAMENTO", length = 2, nullable = false)
    private DominioStatusPagamento statusPagamento;

    @Column(name = "DT_CADASTRO", nullable = false)
    private LocalDateTime dtCadastro;

    @Column(name = "DT_ATUALIZACAO")
    private LocalDateTime dtAtualizacao;

}
