package aplication.api.controller;

import aplication.domain.entity.Cliente;
import aplication.domain.entity.Pedido;
import aplication.domain.entity.Produto;
import aplication.domain.repository.Pedidos;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController{

    private Pedidos pedidos;

    private Produto produto;

    private Cliente cliente;

    public PedidoController(Pedidos pedidos) {
        this.pedidos = pedidos;
    }

    @GetMapping("{id}")
    public Pedido getPedidoById(@PathVariable Integer id) {
        return pedidos.findById(id)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    @PostMapping
    public Pedido savePedido (@RequestBody Pedido pedido) {
        return pedidos.save(pedido);
    }

    @DeleteMapping("{id}")
    public void deletePedido(@PathVariable Integer id) {
        pedidos.findById(id)
                .map( pedido -> {pedidos.delete(pedido);
                    return pedido;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    public void update (@PathVariable Integer id,
                        @RequestBody Pedido pedido) {
        pedidos.findById(id).map( pedidoExistente -> {
            cliente.setId(pedidoExistente.getId());
            pedidos.save(pedido);
            return pedidoExistente;
        } ).orElseThrow( () -> new  ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado"));
    }

    @GetMapping
    public Set<Pedido> listaPedido (@RequestBody Cliente cliente) {
        return pedidos.findByCliente(cliente);
    }
}
