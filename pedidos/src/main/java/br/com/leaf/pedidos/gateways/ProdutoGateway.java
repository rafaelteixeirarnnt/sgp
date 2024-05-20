package br.com.leaf.pedidos.gateways;

import br.com.leaf.pedidos.dtos.request.AtualizaEstoqueRequest;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

@MessagingGateway
public interface ProdutoGateway {

    @Gateway(requestChannel = "atualizarEstoque", requestTimeout = 5000,
            headers = @GatewayHeader(name = MessageHeaders.REPLY_CHANNEL, expression = "@nullChannel"))
    void atualizarEstoque(Message<AtualizaEstoqueRequest> string);
}
