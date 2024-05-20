package br.com.leaf.pedidos.gateways;

import br.com.leaf.pedidos.dtos.response.ClienteResponseDTO;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.GatewayHeader;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.MessageHeaders;

@MessagingGateway
public interface ClienteGateway {

    @Gateway(requestChannel = "recuperarClientID", requestTimeout = 5000,
            headers = @GatewayHeader(name = MessageHeaders.REPLY_CHANNEL, expression = "@nullChannel"))
    void recuperarClientID(String id);

}
