package aplication.domain.repository;

import aplication.domain.entity.Cliente;
import aplication.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    Set<Pedido> findByCliente (Cliente cliente);
}
