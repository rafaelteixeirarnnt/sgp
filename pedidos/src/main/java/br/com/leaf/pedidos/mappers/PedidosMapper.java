package br.com.leaf.pedidos.mappers;

import br.com.leaf.pedidos.dtos.CriaPedidoRequestDTO;
import br.com.leaf.pedidos.dtos.PedidoDTO;
import br.com.leaf.pedidos.enums.DominioStatusPagamento;
import br.com.leaf.pedidos.enums.DominioStatusPedido;
import br.com.leaf.pedidos.models.Pedidos;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PedidosMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "idProduto", source = "idProduto")
    @Mapping(target = "idCliente", source = "idCliente")
    @Mapping(target = "preco", source = "preco")
    @Mapping(target = "quantidade", source = "quantidade")
    @Mapping(target = "dtCadastro", source = "dtCadastro")
    @Mapping(target = "dtAtualizacao", source = "dtAtualizacao")
    @Mapping(target = "statusPedido", expression = "java(montarDominioStatusPedido(p.getStatusPedido()))")
    @Mapping(target = "statusPagamento", expression = "java(montarDominioStatusPagamento(p.getStatusPagamento()))")
    PedidoDTO pedidoTOPedidoDTO(Pedidos p);

    @Mapping(target = "idProduto", source = "idProduto")
    @Mapping(target = "idCliente", source = "idCliente")
    @Mapping(target = "quantidade", source = "quantidade")
    @Mapping(target = "preco", source = "preco")
    Pedidos criarCadastraProdutoDTOToProdutos(CriaPedidoRequestDTO dto);

    default String montarDominioStatusPedido(DominioStatusPedido statusPedido) {
        return statusPedido.getDescricao();
    }

    default String montarDominioStatusPagamento(DominioStatusPagamento statusPagamento) {
        return statusPagamento.getDescricao();
    }

}
