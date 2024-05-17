package br.com.leaf.produtos.dtos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProdutosDTO(UUID id, String nome,
                          String descricao, BigDecimal preco,
                          BigInteger quantidade, String especificacaoTecnica,
                          String categoria,
                          LocalDateTime dtCadastro,
                          LocalDateTime dtAtualizacao,
                          String situacao) {
}
