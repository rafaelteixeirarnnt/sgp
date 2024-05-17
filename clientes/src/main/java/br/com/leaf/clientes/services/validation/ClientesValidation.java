package br.com.leaf.clientes.services.validation;

import br.com.leaf.clientes.models.Clientes;
import br.com.leaf.clientes.repositories.ClientesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientesValidation {

    private final ClientesRepository repository;

    public void validarCliente(Clientes cliente) {
        var clientesOptional = this.repository.findByCpf(cliente.getCpf());
        if (clientesOptional.isPresent()) {
            throw new RuntimeException("Cpf já cadastrado");
        }
    }

}
