package br.com.leaf.clientes.services;

import br.com.leaf.clientes.dtos.AtualizaClienteRequestDTO;
import br.com.leaf.clientes.dtos.ClienteDTO;
import br.com.leaf.clientes.dtos.CriaClienteRequestDTO;
import br.com.leaf.clientes.exceptions.ClienteNegocioException;
import br.com.leaf.clientes.mappers.ClientesMapper;
import br.com.leaf.clientes.repositories.ClientesRepository;
import br.com.leaf.clientes.services.validation.ClientesValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
public class ClientesServices {

    private final ClientesMapper mapper;
    private final ClientesValidation validation;
    private final ClientesRepository repository;

    public UUID salvarCliente(CriaClienteRequestDTO request) {
        var cliente = this.mapper.criaClienteRequestDTOToClienteEntity(request);
        cliente.setSituacao(TRUE);
        this.validation.validarCliente(cliente);
        var clienteDB = this.repository.save(cliente);
        return clienteDB.getId();
    }

    public ClienteDTO atualizarCliente(String id, AtualizaClienteRequestDTO request) {
        var cliente = this.mapper.atualizaClienteRequestDTOToClienteEntity(request);
        var clientesOptional = this.repository.findById(UUID.fromString(id));
        var clienteOriginal = clientesOptional.orElseThrow(() -> new RuntimeException("Cliente não localizado"));
        cliente.setId(clienteOriginal.getId());
        cliente.setCpf(clienteOriginal.getCpf());
        cliente.setSituacao(clienteOriginal.getSituacao());
        var clienteDB = this.repository.save(cliente);
        return this.mapper.clienteTOClienteDTO(clienteDB);
    }


    public ClienteDTO obterClienteId(String id) {
        return this.mapper.clienteTOClienteDTO(this.repository.findById(UUID.fromString(id))
                                .orElseThrow(() -> new ClienteNegocioException("Cliente não localizado", 404)));
    }

    public void deletarCliente(String id) {
        var cliente = this.repository.findById(UUID.fromString(id))
                .orElseThrow(() -> new ClienteNegocioException("Cliente não localizado", 404));
        cliente.setSituacao(Boolean.FALSE);
        this.repository.save(cliente);
    }
}
