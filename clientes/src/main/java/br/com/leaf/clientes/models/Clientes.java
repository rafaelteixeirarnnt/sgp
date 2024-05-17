package br.com.leaf.clientes.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CLIENTES", schema = "clientes")
public class Clientes implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "TX_NOME", length = 100, nullable = false)
    private String nome;

    @Length(min = 11, max = 11)
    @Column(name = "TX_CPF", length = 11, nullable = false)
    private String cpf;

    @Email
    @Column(name = "TX_EMAIL", nullable = false, length = 50)
    private String email;

    @Column(name = "DT_NASCIMENTO", nullable = false)
    private LocalDate dtNascimento;

    @Column(name = "IND_SITUACAO", nullable = false)
    private Boolean situacao;

}
