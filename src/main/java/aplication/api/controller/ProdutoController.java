package aplication.api.controller;

import aplication.domain.entity.Produto;
import aplication.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos produtos;

    public ProdutoController(Produtos produtos) {
        this.produtos = produtos;
    }

    @GetMapping("{id}")
    public Produto getProdutoById(@PathVariable Integer id){
        return produtos
                .findById(id)
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @PostMapping
    public Produto cadastrar(@RequestBody Produto produto){
        return produtos.save(produto);
    }

    @DeleteMapping("{id}")
    public void deletar(@PathVariable Integer id){
        produtos.findById(id)
                .map( produto -> {produtos.delete(produto);
                    return produto;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id, @RequestBody Produto produto){
        produtos.findById(id).map( produtoExistente -> {
            produto.setId(produtoExistente.getId());
            produtos.save(produto);
            return produtoExistente;
        }).orElseThrow( () -> new ResponseStatusException(HttpStatus.FORBIDDEN,
                "Não foi possivel atualizar"));
    }

    @GetMapping()
    public List<Produto> findAll(Produto filtro) {
        ExampleMatcher mather = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING
                );
        Example example = Example.of(filtro, mather);
        return produtos.findAll(example);
    }
}
