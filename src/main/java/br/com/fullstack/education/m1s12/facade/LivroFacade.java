package br.com.fullstack.education.m1s12.facade;

import br.com.fullstack.education.m1s12.entity.LivroEntity;

import java.util.List;

public interface LivroFacade {

    List<LivroEntity> buscarTodos();

    LivroEntity buscarPorId(Long id) throws Exception;

    LivroEntity criar(LivroEntity livro) throws Exception;

    LivroEntity alterar(Long id, LivroEntity livro) throws Exception;

    void apagar(Long id) throws Exception;

    LivroEntity emprestar(Long id, Long usuarioId) throws Exception;
}
