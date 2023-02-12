package aplication.domain.repository;

import aplication.domain.entity.Cliente;
import aplication.domain.entity.ItemPedido;
import aplication.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
    Set<ItemPedido> findByPedido (Pedido pedido);
}
