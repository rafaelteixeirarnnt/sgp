package br.com.leaf.pedidos.gateways;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
                .handle(Http.outboundGateway("http://localhost:8082/api/produtos/atualiza-estoque")
                        .httpMethod(HttpMethod.POST))
                .log()
                .get();
    }

}
