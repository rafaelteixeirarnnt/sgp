package br.com.leaf.pedidos.mappers;

import br.com.leaf.pedidos.dtos.CriaPedidoRequestDTO;
import br.com.leaf.pedidos.models.Pedidos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidosMapper {

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "nome", source = "nome")
//    @Mapping(target = "descricao", source = "descricao")
//    @Mapping(target = "preco", source = "preco")
//    @Mapping(target = "quantidade", source = "quantidade")
//    @Mapping(target = "especificacaoTecnica", source = "especificacaoTecnica")
//    @Mapping(target = "categoria", expression = "java(montarCategoriaDominio(p.getCategoria()))")
//    @Mapping(target = "situacao", expression = "java(montarSituacao(p.getSituacao()))")
//    ProdutosDTO criarProdutosToProdutosDTO(Pedidos p);

    @Mapping(target = "idProduto", source = "idProduto")
    @Mapping(target = "idCliente", source = "idCliente")
    @Mapping(target = "quantidade", source = "quantidade")
    @Mapping(target = "preco", source = "preco")
    Pedidos criarCadastraProdutoDTOToProdutos(CriaPedidoRequestDTO dto);

//    default String montarCategoriaDominio(DominioCategoria categoria) {
//        return categoria.getDescricao();
//    }
//
//    default String montarSituacao(Boolean situacao) {
//        return situacao? "Ativo" : "Inativo";
//    }

}
