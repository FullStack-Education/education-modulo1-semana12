package br.com.fullstack.education.m1s12.controller;

import br.com.fullstack.education.m1s12.dto.UsuarioFiltro;
import br.com.fullstack.education.m1s12.entity.UsuarioEntity;
import br.com.fullstack.education.m1s12.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("usuarios")
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> get(UsuarioFiltro filtro) {
        List<UsuarioEntity> usuarios = service.buscarTodos(filtro);
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("{id}")
    public ResponseEntity<UsuarioEntity> getId(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @GetMapping("login/{login}")
    public ResponseEntity<UsuarioEntity> getLogin(@PathVariable String login) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorLogin(login));
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> post(@RequestBody UsuarioEntity usuario) throws Exception {
        UsuarioEntity usuarioCriado = service.criar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCriado);
    }

    @PutMapping("{id}")
    public ResponseEntity<UsuarioEntity> put(@PathVariable Long id, @RequestBody UsuarioEntity usuario) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(service.alterar(id, usuario));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws Exception {
        service.apagar(id);
        return ResponseEntity.noContent().build();
    }

}
