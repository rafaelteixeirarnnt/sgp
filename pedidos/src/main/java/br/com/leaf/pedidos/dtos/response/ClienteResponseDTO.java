package br.com.leaf.pedidos.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteResponseDTO implements Serializable {

    private String id;

    private String nome;

    private String cpf;

    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dtNascimento;

    private String situacao;

}
