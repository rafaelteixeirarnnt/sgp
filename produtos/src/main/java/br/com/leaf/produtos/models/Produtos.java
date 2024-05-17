package br.com.leaf.produtos.models;

import br.com.leaf.produtos.enums.DominioCategoria;
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
@Table(name = "TB_PRODUTOS", schema = "produtos")
public class Produtos {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "TX_NOME", length = 100, nullable = false)
    private String nome;

    @Column(name = "TX_DESCRICAO", length = 200, nullable = false)
    private String descricao;

    @Column(name = "VL_PRECO", scale = 4, nullable = false)
    private BigDecimal preco;

    @Column(name = "NR_QTD", nullable = false)
    private BigInteger quantidade;

    @Column(name = "TX_ESPECIFICACAO_TECNICA", length = 500, nullable = false)
    private String especificacaoTecnica;

    @Enumerated(EnumType.STRING)
    @Column(name = "TX_CATEGORIA", length = 2, nullable = false)
    private DominioCategoria categoria;

    @Column(name = "DT_CADASTRO", nullable = false)
    private LocalDateTime dtCadastro;

    @Column(name = "DT_ATUALIZACAO")
    private LocalDateTime dtAtualizacao;

    @Column(name = "IND_SITUACAO", nullable = false)
    private Boolean situacao;

}
