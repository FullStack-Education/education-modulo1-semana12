package br.com.fullstack.education.m1s12.controller;

import br.com.fullstack.education.m1s12.entity.LivroEntity;
import br.com.fullstack.education.m1s12.service.LivroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("livros")
public class LivroController {

    private final LivroService service;

    @GetMapping
    public ResponseEntity<List<LivroEntity>> get() {
        log.trace("TRACE -> GET /livros"); // 4
        log.debug("DEBUG -> GET /livros"); // 3
        log.info ("INFO  -> GET /livros"); // 2
        log.warn ("WARN  -> GET /livros"); // 1
        log.error("ERROR -> GET /livros"); // 0

        List<LivroEntity> livros = service.buscarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(livros); // Retorna 200
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroEntity> getId(@PathVariable Long id) throws Exception {
        log.info("GET /livros/{}", id);

        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id)); // Retorna 200
    }

    @PostMapping
    public ResponseEntity<LivroEntity> post(@RequestBody LivroEntity livro) throws Exception {
        log.info("POST /livros");

        LivroEntity livroCriado = service.criar(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado); // Retorna 201
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroEntity> put(@PathVariable Long id, @RequestBody LivroEntity livro) throws Exception {
        log.info("PUT /livros/{}", id);
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, livro)); // Retorna 200
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        log.info("DELETE /livros/{}", id);
        service.apagar(id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

}
