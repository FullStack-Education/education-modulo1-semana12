package br.com.fullstack.education.m1s10.service;

import br.com.fullstack.education.m1s10.dto.UsuarioFiltro;
import br.com.fullstack.education.m1s10.entity.UsuarioEntity;
import br.com.fullstack.education.m1s10.exception.error.UsuarioByIdNotFoundException;
import br.com.fullstack.education.m1s10.exception.error.UsuarioByLoginNotFoundException;
import br.com.fullstack.education.m1s10.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsuarioEntity> buscarTodos(UsuarioFiltro filtro) {
        if (StringUtils.hasText(filtro.getNome()) && StringUtils.hasText(filtro.getLogin())) {
            return repository.findByNomeContainingIgnoreCaseAndLogin(filtro.getNome(), filtro.getLogin());
        } else if (StringUtils.hasText(filtro.getNome())) {
            return repository.findByNomeContainingIgnoreCase(filtro.getNome());
        } else if (StringUtils.hasText(filtro.getLogin())) {
            return repository.findByLogin(filtro.getLogin());
        }
        return repository.findAll();
    }

    @Override
    public UsuarioEntity buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new UsuarioByIdNotFoundException(id));
    }

    @Override
    public UsuarioEntity buscarPorLogin(String login) {
        Optional<UsuarioEntity> opt = repository.findTop1ByLogin(login);
        return opt.orElseThrow(() -> new UsuarioByLoginNotFoundException(login));
    }

    @Override
    public UsuarioEntity criar(UsuarioEntity usuario) throws Exception {
        usuario.setId(null);
        return repository.save(usuario);
    }

    @Override
    public UsuarioEntity alterar(Long id, UsuarioEntity usuario) {
        UsuarioEntity entity = buscarPorId(id);
        entity.setNome(usuario.getNome());
        entity.setLogin(usuario.getLogin());
        entity.setSenha(usuario.getSenha());

        return repository.save(entity);
    }

    @Override
    public void apagar(Long id) {
        UsuarioEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}
