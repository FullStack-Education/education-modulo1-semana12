package br.com.fullstack.education.m1s12.service;

import br.com.fullstack.education.m1s12.entity.LivroEntity;

import java.util.List;

public interface LivroService {

    List<LivroEntity> buscarTodos();

    LivroEntity buscarPorId(Long id) throws Exception;

    LivroEntity criar(LivroEntity livro) throws Exception;

    LivroEntity alterar(Long id, LivroEntity livro) throws Exception;

    void apagar(Long id) throws Exception;

    LivroEntity emprestar(LivroEntity livro);
}
