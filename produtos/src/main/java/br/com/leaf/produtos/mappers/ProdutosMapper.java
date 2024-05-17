package br.com.leaf.produtos.mappers;

import br.com.leaf.produtos.dtos.CadastraProdutoDTO;
import br.com.leaf.produtos.dtos.ProdutosDTO;
import br.com.leaf.produtos.enums.DominioCategoria;
import br.com.leaf.produtos.models.Produtos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProdutosMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "preco", source = "preco")
    @Mapping(target = "quantidade", source = "quantidade")
    @Mapping(target = "especificacaoTecnica", source = "especificacaoTecnica")
    @Mapping(target = "categoria", expression = "java(montarCategoriaDominio(p.getCategoria()))")
    @Mapping(target = "situacao", expression = "java(montarSituacao(p.getSituacao()))")
    ProdutosDTO criarProdutosToProdutosDTO(Produtos p);

    @Mapping(target = "nome", source = "nome")
    @Mapping(target = "descricao", source = "descricao")
    @Mapping(target = "preco", source = "preco")
    @Mapping(target = "quantidade", source = "quantidade")
    @Mapping(target = "especificacaoTecnica", source = "especificacaoTecnica")
    Produtos criarCadastraProdutoDTOToProdutos(CadastraProdutoDTO dto);


    default String montarCategoriaDominio(DominioCategoria categoria) {
        return categoria.getDescricao();
    }

    default String montarSituacao(Boolean situacao) {
        return situacao? "Ativo" : "Inativo";
    }

}
