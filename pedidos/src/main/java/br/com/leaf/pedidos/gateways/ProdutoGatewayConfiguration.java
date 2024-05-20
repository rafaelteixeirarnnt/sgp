package br.com.leaf.pedidos.gateways;

import br.com.leaf.pedidos.dtos.response.EstoqueAtulizadoResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ProdutoGatewayConfiguration {

    @Bean
    public MessageChannel atualizarEstoque() {
        var directChannel = new DirectChannel();
        directChannel.setFailover(false);
        return directChannel;
    }

    @Bean
    public IntegrationFlow atualizarEstoqueFlow() {
        return IntegrationFlow.from("atualizarEstoque")
                .handle(Http.outboundGateway("http://localhost:8082/api/produtos")
                        .httpMethod(HttpMethod.POST)
                        .expectedResponseType(EstoqueAtulizadoResponseDTO.class)
                        .messageConverters(new MappingJackson2HttpMessageConverter()))
                .log()
                .get();
    }

    @Bean
    public IntegrationFlow processarRespostaProdutoFlow() {
        return IntegrationFlow.from("atualizarEstoque")
                .handle(message -> {
                    var estoqueAtulizadoResponseDTO = (EstoqueAtulizadoResponseDTO) message.getPayload();
                })
                .get();
    }

}
