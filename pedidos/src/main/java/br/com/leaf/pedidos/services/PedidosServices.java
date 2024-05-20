package br.com.leaf.pedidos.services;

import br.com.leaf.pedidos.dtos.CriaPedidoRequestDTO;
import br.com.leaf.pedidos.dtos.PedidoDTO;
import br.com.leaf.pedidos.dtos.request.AtualizaEstoqueRequest;
import br.com.leaf.pedidos.enums.DominioStatusPagamento;
import br.com.leaf.pedidos.enums.DominioStatusPedido;
import br.com.leaf.pedidos.exceptions.PedidoNegocioException;
import br.com.leaf.pedidos.gateways.ClienteGateway;
import br.com.leaf.pedidos.gateways.ProdutoGateway;
import br.com.leaf.pedidos.mappers.PedidosMapper;
import br.com.leaf.pedidos.models.Pedidos;
import br.com.leaf.pedidos.repositories.PedidosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;
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

//        var clienteResponse = clienteGateway.recuperarClientID(dto.getIdCliente().toString());
        clienteGateway.recuperarClientID(dto.getIdCliente().toString());

//        if (Objects.isNull(clienteResponse)) {
//            throw new PedidoNegocioException("Cliente não localizado", 404);
//        }

        var requestProduto = new AtualizaEstoqueRequest(dto.getIdProduto().toString(), dto.getQuantidade());

        produtoGateway.atualizarEstoque(new GenericMessage<>(requestProduto));

        pedido.setStatusPagamento(DominioStatusPagamento.EM_ANALISE);
        pedido.setStatusPedido(DominioStatusPedido.AGUARDANDO_PAGAMENTO);
        pedido.setDtCadastro(LocalDateTime.now());
        pedido.setPreco(dto.getPreco());

        var pedidoDB = this.repository.save(pedido);

        return pedidoDB.getId();
    }

    public PedidoDTO obterPedidoPorId(String id) {
        Pedidos pedido;
        UUID uuid;

        if (Objects.nonNull(id) && !id.isBlank()) {
            uuid = gerarUuid(id);
            var pedidoDb = this.repository.findById(uuid).orElseThrow(() -> new PedidoNegocioException("Pedido não localizado", 404));
            return this.mapper.pedidoTOPedidoDTO(pedidoDb);
        }
        throw new PedidoNegocioException("ID inválido", 400);
    }

    private UUID gerarUuid(String id) {
        UUID uuid;
        try {
             uuid = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new PedidoNegocioException("ID inválido", 400);
        }
        return uuid;
    }
}
