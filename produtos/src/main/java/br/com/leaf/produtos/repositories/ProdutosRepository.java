package br.com.leaf.produtos.repositories;

import br.com.leaf.produtos.models.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, UUID>, JpaSpecificationExecutor<Produtos> {
}
