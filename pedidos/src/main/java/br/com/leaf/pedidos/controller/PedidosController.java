package br.com.leaf.pedidos.controller;

import br.com.leaf.pedidos.dtos.CriaPedidoRequestDTO;
import br.com.leaf.pedidos.dtos.PedidoDTO;
import br.com.leaf.pedidos.services.PedidosServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/pedidos")
@Tag(name = "1 - Pedidos", description = "Serviços disponíveis para gerenciamento de Pedidos.")
public class PedidosController {

    private final PedidosServices service;

    @PostMapping
    @Operation(summary = "Serviço responsável por registrar os pedidos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso", headers = {
                    @Header(name = "Location", description = "URI do recurso criado", schema = @Schema(implementation = String.class))
            }),
            @ApiResponse(responseCode = "400", description = "Falha no cadastro do cliente"),
    })
    public ResponseEntity<UUID> salvarPedido(@Valid @RequestBody CriaPedidoRequestDTO dto) {
        var uuid = service.salvarPedido(dto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(uuid)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Serviço responsável por devolver os detalhes do pedido.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pedido localizado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoDTO.class))),
            @ApiResponse(responseCode = "400", description = "Falha ao recuperar pedido"),
            @ApiResponse(responseCode = "404", description = "Pedido não localizado"),
    })
    public ResponseEntity<PedidoDTO> obterPedidoPorId(@PathVariable("id") String id) {
        var dto = service.obterPedidoPorId(id);
        return ResponseEntity.ok().body(dto);
    }

}
