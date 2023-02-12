package aplication.api.controller;

import aplication.domain.entity.ItemPedido;
import aplication.domain.entity.Pedido;
import aplication.domain.repository.ItensPedido;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@RestController
@RequestMapping("/api/itempedidos")
public class ItemPedidoController {

    private ItensPedido itensPedido;

    private Pedido pedido;

    public ItemPedidoController(ItensPedido itensPedido) {
        this.itensPedido = itensPedido;
    }


    @GetMapping("{id}")
    public ItemPedido buscarItemPedido(@PathVariable Integer id){
        return itensPedido.findById(id).orElseThrow( () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Itens do pedido não encontrado"));
    }
    @GetMapping
    public Set<ItemPedido> listaItemPedido(@RequestBody Pedido pedido){
        return itensPedido.findByPedido(pedido);
    }
    @PostMapping
    public ItemPedido saveItemPedido(@RequestBody ItemPedido itemPedido) {
        return itensPedido.save(itemPedido);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteItemPedido(@PathVariable Integer id) {
        itensPedido.deleteById(id);
    }


}
