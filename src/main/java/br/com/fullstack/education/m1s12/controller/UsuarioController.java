package br.com.fullstack.education.m1s12.controller;

import br.com.fullstack.education.m1s12.dto.UsuarioFiltro;
import br.com.fullstack.education.m1s12.entity.LivroEntity;
import br.com.fullstack.education.m1s12.entity.UsuarioEntity;
import br.com.fullstack.education.m1s12.facade.UsuarioFacade;
import br.com.fullstack.education.m1s12.util.JsonUtil;
import br.com.fullstack.education.m1s12.util.ParamUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioFacade facade;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> get(UsuarioFiltro filtro) {
        String queryParams = ParamUtil.objetoParaQueryParam(filtro);
        log.info("GET /usuarios{} -> Início", queryParams);

        List<UsuarioEntity> usuarios = facade.buscarTodos(filtro);
        log.info("GET /usuarios{} -> Encontrados {} registros", queryParams, usuarios.size());

        log.info("GET /usuarios{} -> 200 OK", queryParams);
        log.debug("GET /usuarios{} -> Response Body:\n{}\n", queryParams, JsonUtil.objetoParaJson(usuarios));
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioEntity> getId(@PathVariable Long id) throws Exception {
        log.info("GET /usuarios/{} -> Início", id);

        UsuarioEntity usuario = facade.buscarPorId(id);
        log.info("GET /usuarios/{} -> Encontrado", id);

        log.info("GET /usuarios/{} -> 200 OK", id);
        log.debug("GET /usuarios/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @GetMapping("login/{login}")
    public ResponseEntity<UsuarioEntity> getLogin(@PathVariable String login) throws Exception {
        log.info("GET /usuarios/login/{} -> Início", login);

        UsuarioEntity usuario = facade.buscarPorLogin(login);
        log.info("GET /usuarios/login/{} -> Encontrado", login);

        log.info("GET /usuarios/login/{} -> 200 OK", login);
        log.debug("GET /usuarios/login/{} -> Response Body:\n{}\n", login, JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> post(@RequestBody UsuarioEntity usuarioRequisicao) throws Exception {
        log.info("POST /usuarios");

        UsuarioEntity usuario = facade.criar(usuarioRequisicao);
        log.info("POST /usuarios -> Cadastrado");

        log.info("POST /usuarios -> 201 CREATED");
        log.debug("POST /usuarios -> Response Body:\n{}\n", JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioEntity> put(@PathVariable Long id, @RequestBody UsuarioEntity usuarioRequisicao) throws Exception {
        log.info("PUT /usuarios/{}", id);

        UsuarioEntity usuario = facade.alterar(id, usuarioRequisicao);
        log.info("PUT /usuarios/{} -> Atualizado", id);

        log.info("PUT /usuarios/{} -> 200 OK", id);
        log.debug("PUT /usuarios/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PatchMapping("{id}/livro-id/{livroId}")
    public ResponseEntity<UsuarioEntity> patch(
            @PathVariable Long id, @PathVariable Long livroId
    ) throws Exception {
        log.info("PATCH /usuarios/{}/livro-id/{}", id, livroId);

        UsuarioEntity usuario = facade.emprestar(id, livroId);
        log.info("PATCH /usuarios/{}/livro-id/{} -> Atualizado", id, livroId);

        log.info("PATCH /usuarios/{}/livro-id/{} -> 200 OK", id, livroId);
        log.debug("PATCH /usuarios/{}/livro-id/{} -> Response Body:\n{}\n", id, livroId, JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.OK).body(usuario); // Retorna 200
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        log.info("DELETE /usuarios/{}", id);

        facade.apagar(id);
        log.info("DELETE /usuarios/{} -> Excluído", id);

        log.info("DELETE /usuarios/{} -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build();
    }

}
