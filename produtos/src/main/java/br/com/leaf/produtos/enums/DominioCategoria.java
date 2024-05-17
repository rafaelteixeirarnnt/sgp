package br.com.leaf.produtos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DominioCategoria {

    EL("Eletrônicos", "EL"),
    MV("Moda e Vestuário", "MV"),
    AB("Alimentos e Bebidas", "AB"),
    EF("Esportes e Fitness", "EF");

    private final String descricao;
    private final String sigla;

    public static DominioCategoria obterPorSigla(String sigla) {
        return Arrays.stream(DominioCategoria.values()).filter(c -> c.getSigla().equalsIgnoreCase(sigla)).findFirst().get();
    }

}
