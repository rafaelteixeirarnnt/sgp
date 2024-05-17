package br.com.leaf.clientes.dtos;

import br.com.leaf.clientes.DominioSituacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO implements Serializable {

    private UUID id;

    private String nome;

    private String cpf;

    private String email;

    private LocalDate dtNascimento;

    private DominioSituacao situacao;

}
