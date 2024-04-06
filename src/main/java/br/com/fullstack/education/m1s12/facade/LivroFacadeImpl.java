package br.com.fullstack.education.m1s12.facade;

import br.com.fullstack.education.m1s12.entity.LivroEntity;
import br.com.fullstack.education.m1s12.entity.UsuarioEntity;
import br.com.fullstack.education.m1s12.service.LivroService;
import br.com.fullstack.education.m1s12.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroFacadeImpl implements LivroFacade {

    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public LivroFacadeImpl(LivroService livroService, UsuarioService usuarioService) {
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<LivroEntity> buscarTodos() {
        return livroService.buscarTodos();
    }

    @Override
    public LivroEntity buscarPorId(Long id) throws Exception {
        return livroService.buscarPorId(id);
    }

    @Override
    public LivroEntity criar(LivroEntity livro) throws Exception {
        return livroService.criar(livro);
    }

    @Override
    public LivroEntity alterar(Long id, LivroEntity livro) throws Exception {
        return livroService.alterar(id, livro);
    }

    @Override
    public void apagar(Long id) throws Exception {
        livroService.apagar(id);
    }

    @Override
    public LivroEntity emprestar(Long id, Long usuarioId) throws Exception {
        LivroEntity livro = buscarPorId(id);
        UsuarioEntity usuario = usuarioService.buscarPorId(usuarioId);

        usuarioService.emprestar(usuario);
        return livroService.emprestar(livro);
    }
}
