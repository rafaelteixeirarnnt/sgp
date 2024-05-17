package br.com.leaf.clientes.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriaClienteRequestDTO implements Serializable {

    @NotNull
    @Size(min = 2, max = 100)
    private String nome;

    @CPF
    @NotNull
    private String cpf;

    @Email
    @NotNull
    private String email;

    @Past
    @NotNull
    private LocalDate dtNascimento;

}
