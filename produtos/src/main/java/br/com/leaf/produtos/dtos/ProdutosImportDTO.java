package br.com.leaf.produtos.dtos;

import lombok.Data;

@Data
public class ProdutosImportDTO {

    private String id;
    private String nome;
    private String descricao;
    private String preco;
    private String categoria;
    private String especificacaoTecnica;
    private String situacao;
    private String quantidade;

}
