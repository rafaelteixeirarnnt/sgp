package br.com.leaf.produtos.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.BigInteger;

public record CadastraProdutoDTO(@NotNull String nome,
                                 @NotNull String descricao,
                                 @Min(value = 0, message = "O preço não pode ser menor que 0")
                                 @NotNull BigDecimal preco,
                                 @Min(value = 0, message = "O quantidade não pode ser menor que 0")
                                 @NotNull BigInteger quantidade,
                                 @NotNull String especificacaoTecnica,
                                 @Schema(description = "Categorias que existem na aplicação ", allowableValues = {"EL", "MV", "AB", "EF"},
                                         example = "EL, MV, AB, EF")
                                 @NotNull String categoria) {
}
