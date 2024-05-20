package br.com.leaf.pedidos.repositories;

import br.com.leaf.pedidos.models.Pedidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, UUID> {
}
