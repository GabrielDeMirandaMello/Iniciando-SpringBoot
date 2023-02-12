package aplication.api.controller;

import aplication.domain.entity.Cliente;
import aplication.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
//@CrossOrigin("http://localhost:3000")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clientes
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) { return clientes.save(cliente); }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id) {
        clientes.findById(id)
                .map( cliente -> {clientes.delete(cliente);
                return cliente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado"));
    }

    @DeleteMapping("/{id}/{nome}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteByName(@PathVariable Integer id, @PathVariable String nome) {
        clientes.findById(id)
                .map( cliente -> {clientes.deleteByNome(nome);
                    return cliente;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update ( @PathVariable Integer id,
                         @RequestBody Cliente cliente) {
        clientes.findById(id).map( clienteExistente -> {
            cliente.setId(clienteExistente.getId());
            clientes.save(cliente);
            return clienteExistente;
        } ).orElseThrow( () -> new  ResponseStatusException(HttpStatus.NOT_FOUND,
                "Cliente não encontrado"));
    }

    @GetMapping()
    public List<Cliente> findAll(Cliente filtro) {
        ExampleMatcher mather = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(filtro, mather);
        return clientes.findAll(example);
    }
}
