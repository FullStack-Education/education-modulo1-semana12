package br.com.fullstack.education.m1s12.controller;

import br.com.fullstack.education.m1s12.dto.UsuarioFiltro;
import br.com.fullstack.education.m1s12.entity.UsuarioEntity;
import br.com.fullstack.education.m1s12.service.UsuarioService;
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

    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> get(UsuarioFiltro filtro) {
        String queryParams = ParamUtil.objetoParaQueryParam(filtro);
        log.info("GET /usuarios{} -> Início", queryParams);

        List<UsuarioEntity> usuarios = service.buscarTodos(filtro);
        log.info("GET /usuarios{} -> Encontrados {} registros", queryParams, usuarios.size());

        log.info("GET /usuarios{} -> 200 OK", queryParams);
        log.debug("GET /usuarios{} -> Response Body:\n{}\n", queryParams, JsonUtil.objetoParaJson(usuarios));
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioEntity> getId(@PathVariable Long id) throws Exception {
        log.info("GET /usuarios/{} -> Início", id);

        UsuarioEntity usuario = service.buscarPorId(id);
        log.info("GET /usuarios/{} -> Encontrado", id);

        log.info("GET /usuarios/{} -> 200 OK", id);
        log.debug("GET /usuarios/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @GetMapping("login/{login}")
    public ResponseEntity<UsuarioEntity> getLogin(@PathVariable String login) throws Exception {
        log.info("GET /usuarios/login/{} -> Início", login);

        UsuarioEntity usuario = service.buscarPorLogin(login);
        log.info("GET /usuarios/login/{} -> Encontrado", login);

        log.info("GET /usuarios/login/{} -> 200 OK", login);
        log.debug("GET /usuarios/login/{} -> Response Body:\n{}\n", login, JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> post(@RequestBody UsuarioEntity usuarioRequisicao) throws Exception {
        log.info("POST /usuarios");

        UsuarioEntity usuario = service.criar(usuarioRequisicao);
        log.info("POST /usuarios -> Cadastrado");

        log.info("POST /usuarios -> 201 CREATED");
        log.debug("POST /usuarios -> Response Body:\n{}\n", JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioEntity> put(@PathVariable Long id, @RequestBody UsuarioEntity usuarioRequisicao) throws Exception {
        log.info("PUT /usuarios/{}", id);

        UsuarioEntity usuario = service.alterar(id, usuarioRequisicao);
        log.info("PUT /usuarios/{} -> Atualizado", id);

        log.info("PUT /usuarios/{} -> 200 OK", id);
        log.debug("PUT /usuarios/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(usuario));
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        log.info("DELETE /usuarios/{}", id);

        service.apagar(id);
        log.info("DELETE /usuarios/{} -> Excluído", id);

        log.info("DELETE /usuarios/{} -> 204 NO CONTENT", id);
        return ResponseEntity.noContent().build();
    }

}
