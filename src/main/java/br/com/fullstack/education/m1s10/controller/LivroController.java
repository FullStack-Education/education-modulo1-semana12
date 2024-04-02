package br.com.fullstack.education.m1s10.controller;

import br.com.fullstack.education.m1s10.entity.LivroEntity;
import br.com.fullstack.education.m1s10.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("livros")
public class LivroController {

    private final LivroService service;

    @GetMapping
    public ResponseEntity<List<LivroEntity>> get() {
        List<LivroEntity> livros = service.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(livros); // Retorna 200
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroEntity> getId(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id)); // Retorna 200
    }

    @PostMapping
    public ResponseEntity<LivroEntity> post(@RequestBody LivroEntity livro) throws Exception {
        LivroEntity livroCriado = service.criar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado); // Retorna 201
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroEntity> put(@PathVariable Long id, @RequestBody LivroEntity livro) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, livro)); // Retorna 200
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        service.apagar(id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

}
