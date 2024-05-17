package br.com.leaf.clientes.repositories;

import br.com.leaf.clientes.models.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, UUID> {

    Optional<Clientes> findByCpf(String cpf);

}
