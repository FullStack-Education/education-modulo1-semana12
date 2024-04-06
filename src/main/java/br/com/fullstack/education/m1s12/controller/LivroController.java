package br.com.fullstack.education.m1s12.controller;

import br.com.fullstack.education.m1s12.entity.LivroEntity;
import br.com.fullstack.education.m1s12.facade.LivroFacade;
import br.com.fullstack.education.m1s12.util.JsonUtil;
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

    private final LivroFacade facade;

    @GetMapping
    public ResponseEntity<List<LivroEntity>> get() {
        log.info("GET /livros -> Início");

        List<LivroEntity> livros = facade.buscarTodos();
        log.info("GET /livros -> Encontrados {} registros", livros.size());

        log.info("GET /livros -> 200 OK");
        log.debug("GET /livros -> Response Body:\n{}\n", JsonUtil.objetoParaJson(livros));
        return ResponseEntity.status(HttpStatus.OK).body(livros); // Retorna 200
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroEntity> getId(@PathVariable Long id) throws Exception {
        log.info("GET /livros/{} -> Início", id);

        LivroEntity livro = facade.buscarPorId(id);
        log.info("GET /livros/{} -> Encontrado", id);

        log.info("GET /livros/{} -> 200 OK", id);
        log.debug("GET /livros/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(livro));
        return ResponseEntity.status(HttpStatus.OK).body(livro); // Retorna 200
    }

    @PostMapping
    public ResponseEntity<LivroEntity> post(@RequestBody LivroEntity livroRequisicao) throws Exception {
        log.info("POST /livros");

        LivroEntity livro = facade.criar(livroRequisicao);
        log.info("POST /livros -> Cadastrado");

        log.info("POST /livros -> 201 CREATED");
        log.debug("POST /livros -> Response Body:\n{}\n", JsonUtil.objetoParaJson(livro));
        return ResponseEntity.status(HttpStatus.CREATED).body(livro); // Retorna 201
    }

    @PutMapping("{id}")
    public ResponseEntity<LivroEntity> put(@PathVariable Long id, @RequestBody LivroEntity livroRequisicao) throws Exception {
        log.info("PUT /livros/{}", id);

        LivroEntity livro = facade.alterar(id, livroRequisicao);
        log.info("PUT /livros/{} -> Atualizado", id);

        log.info("PUT /livros/{} -> 200 OK", id);
        log.debug("PUT /livros/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(livro));
        return ResponseEntity.status(HttpStatus.OK).body(livro); // Retorna 200
    }

    @PatchMapping("{id}/usuario-id/{usuarioId}")
    public ResponseEntity<LivroEntity> patch(
            @PathVariable Long id, @PathVariable Long usuarioId
    ) throws Exception {
        log.info("PATCH /livros/{}/usuario-id/{}", id, usuarioId);

        LivroEntity livro = facade.emprestar(id, usuarioId);
        log.info("PATCH /livros/{}/usuario-id/{} -> Atualizado", id, usuarioId);

        log.info("PATCH /livros/{}/usuario-id/{} -> 200 OK", id, usuarioId);
        log.debug("PATCH /livros/{}/usuario-id/{} -> Response Body:\n{}\n", id, usuarioId, JsonUtil.objetoParaJson(livro));
        return ResponseEntity.status(HttpStatus.OK).body(livro); // Retorna 200
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        log.info("DELETE /livros/{}", id);

        facade.apagar(id);
        log.info("DELETE /livros/{} -> Excluído", id);

        log.info("DELETE /livros/{} -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build(); // Retorna 204
    }

}
