package br.com.fullstack.education.m1s12.facade;

import br.com.fullstack.education.m1s12.dto.UsuarioFiltro;
import br.com.fullstack.education.m1s12.entity.LivroEntity;
import br.com.fullstack.education.m1s12.entity.UsuarioEntity;
import br.com.fullstack.education.m1s12.service.LivroService;
import br.com.fullstack.education.m1s12.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioFacadeImpl implements UsuarioFacade {

    private final LivroService livroService;
    private final UsuarioService usuarioService;

    public UsuarioFacadeImpl(LivroService livroService, UsuarioService usuarioService) {
        this.livroService = livroService;
        this.usuarioService = usuarioService;
    }

    @Override
    public List<UsuarioEntity> buscarTodos(UsuarioFiltro filtro) {
        return usuarioService.buscarTodos(filtro);
    }

    @Override
    public UsuarioEntity buscarPorId(Long id) throws Exception {
        return usuarioService.buscarPorId(id);
    }

    @Override
    public UsuarioEntity buscarPorLogin(String login) throws Exception {
        return usuarioService.buscarPorLogin(login);
    }

    @Override
    public UsuarioEntity criar(UsuarioEntity usuario) throws Exception {
        return usuarioService.criar(usuario);
    }

    @Override
    public UsuarioEntity alterar(Long id, UsuarioEntity usuario) throws Exception {
        return usuarioService.alterar(id, usuario);
    }

    @Override
    public void apagar(Long id) throws Exception {
        usuarioService.apagar(id);
    }

    @Override
    public UsuarioEntity emprestar(Long id, Long livroId) throws Exception {
        UsuarioEntity usuario = buscarPorId(id);
        LivroEntity livro = livroService.buscarPorId(livroId);

        livroService.emprestar(livro);
        return usuarioService.emprestar(usuario);
    }
}
