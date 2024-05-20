package br.com.leaf.pedidos.gateways;

import br.com.leaf.pedidos.dtos.response.ClienteResponseDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.http.dsl.Http;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;

@Configuration
public class ClienteGatewayConfiguration {

    @Bean
    public MessageChannel recuperarClientID() {
        var directChannel = new DirectChannel();
        directChannel.setFailover(false);
        return directChannel;
    }

//    @Bean
//    public MessageChannel responseClientID() {
//        var directChannel = new DirectChannel();
//        directChannel.setFailover(false);
//        return directChannel;
//    }

    @Bean
    public IntegrationFlow recuperarClientIDFlow() {
        return IntegrationFlow.from("recuperarClientID")
                .handle(Http.outboundGateway("http://localhost:8081/api/clientes/{id}")
                        .uriVariable("id", Message::getPayload)
                        .httpMethod(HttpMethod.GET)
//                        .expectedResponseType(ClienteResponseDTO.class)
//                        .messageConverters(new MappingJackson2HttpMessageConverter())
                )
                .log()
                .get();
    }

//    @Bean
//    public IntegrationFlow processarRespostaClientIDFlow() {
//        return IntegrationFlow.from("recuperarClientID")
//                .handle(message -> {
//                    ClienteResponseDTO clienteResponseDTO = (ClienteResponseDTO) message.getPayload();
//                })
//                .get();
//    }

}
