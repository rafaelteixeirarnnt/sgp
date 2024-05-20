package br.com.leaf.pedidos.services;

import br.com.leaf.pedidos.dtos.CriaPedidoRequestDTO;
import br.com.leaf.pedidos.gateways.ClienteGateway;
import br.com.leaf.pedidos.gateways.ProdutoGateway;
import br.com.leaf.pedidos.mappers.PedidosMapper;
import br.com.leaf.pedidos.repositories.PedidosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PedidosServices {

    private final PedidosMapper mapper;
    private final PedidosRepository repository;
    private final ClienteGateway clienteGateway;
    private final ProdutoGateway produtoGateway;

    public UUID salvarPedido(CriaPedidoRequestDTO dto) {
        var pedido = this.mapper.criarCadastraProdutoDTOToProdutos(dto);

        var clienteResponse = clienteGateway.recuperarClientID(dto.getIdCliente().toString());

//        var requestProduto = new AtualizaEstoqueRequest(dto.getIdProduto(), dto.getQuantidade());
//        var produtoResponse = produtoGateway.atualizarEstoque(new GenericMessage<>(requestProduto));

//        this.repository.save(pedido);

        return null;
    }
}
