package br.com.fullstack.education.m1s12.service;

import br.com.fullstack.education.m1s12.dto.UsuarioFiltro;
import br.com.fullstack.education.m1s12.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioService {

    List<UsuarioEntity> buscarTodos(UsuarioFiltro filtro);

    UsuarioEntity buscarPorId(Long id) throws Exception;

    UsuarioEntity buscarPorLogin(String login) throws Exception;

    UsuarioEntity criar(UsuarioEntity usuario) throws Exception;

    UsuarioEntity alterar(Long id, UsuarioEntity usuario) throws Exception;

    void apagar(Long id) throws Exception;
}
