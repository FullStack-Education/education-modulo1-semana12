package br.com.fullstack.education.m1s12.service;

import br.com.fullstack.education.m1s12.dto.UsuarioFiltro;
import br.com.fullstack.education.m1s12.entity.UsuarioEntity;
import br.com.fullstack.education.m1s12.exception.error.UsuarioByIdNotFoundException;
import br.com.fullstack.education.m1s12.exception.error.UsuarioByLoginNotFoundException;
import br.com.fullstack.education.m1s12.repository.UsuarioRepository;
import br.com.fullstack.education.m1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceImpl(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<UsuarioEntity> buscarTodos(UsuarioFiltro filtro) {
        List<UsuarioEntity> usuarios;
        if (StringUtils.hasText(filtro.getNome()) && StringUtils.hasText(filtro.getLogin())) {
            log.info("Buscando todos os usuários -> com nome ({}) e login ({})", filtro.getNome(), filtro.getLogin());
            usuarios = repository.findByNomeContainingIgnoreCaseAndLogin(filtro.getNome(), filtro.getLogin());
        } else if (StringUtils.hasText(filtro.getNome())) {
            log.info("Buscando todos os usuários -> com nome ({})", filtro.getNome());
            usuarios = repository.findByNomeContainingIgnoreCase(filtro.getNome());
        } else if (StringUtils.hasText(filtro.getLogin())) {
            log.info("Buscando todos os usuários -> com login ({})", filtro.getLogin());
            usuarios = repository.findByLogin(filtro.getLogin());
        } else {
            log.info("Buscando todos os usuários");
            usuarios = repository.findAll();
        }

        log.info("Buscando todos os usuários -> {} Encontrados", usuarios.size());
        log.debug("Buscando todos os usuários -> Registros encontrados:\n{}\n", JsonUtil.objetoParaJson(usuarios));
        return usuarios;
    }

    @Override
    public UsuarioEntity buscarPorId(Long id) {
        log.info("Buscando usuário por id ({})", id);
        Optional<UsuarioEntity> usuario = repository.findById(id);

        if (usuario.isEmpty()) {
            log.error("Buscando usuário por id ({}) -> NÃO Encontrado", id);
            throw new UsuarioByIdNotFoundException(id);
        }

        log.info("Buscando usuário por id ({}) -> Encontrado", id);
        log.debug("Buscando usuário por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objetoParaJson(usuario.get()));
        return usuario.get();
    }

    @Override
    public UsuarioEntity buscarPorLogin(String login) {
        log.info("Buscando usuário por login ({})", login);
        Optional<UsuarioEntity> opt = repository.findTop1ByLogin(login);

        if (opt.isEmpty()) {
            log.error("Buscando usuário por login ({}) -> NÃO Encontrado", login);
            throw new UsuarioByLoginNotFoundException(login);
        }

        log.info("Buscando usuário por login ({}) -> Encontrado", login);
        log.debug("Buscando usuário por login ({}) -> Registro encontrado:\n{}\n", login, JsonUtil.objetoParaJson(opt.get()));
        return opt.get();
    }

    @Override
    public UsuarioEntity criar(UsuarioEntity usuarioNovo) throws Exception {
        usuarioNovo.setId(null);

        log.info("Criando usuário -> Salvar: \n{}\n", JsonUtil.objetoParaJson(usuarioNovo));
        UsuarioEntity usuario = repository.save(usuarioNovo);

        log.info("Criando usuário -> Salvo com sucesso");
        log.debug("Criando usuário -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(usuario));
        return usuario;
    }

    @Override
    public UsuarioEntity alterar(Long id, UsuarioEntity usuario) {
        UsuarioEntity entity = buscarPorId(id);
        entity.setNome(usuario.getNome());
        entity.setLogin(usuario.getLogin());
        entity.setSenha(usuario.getSenha());

        log.info("Alterando usuário com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(entity));
        entity = repository.save(entity);

        log.info("Alterando usuário -> Salvo com sucesso");
        log.debug("Alterando usuário -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(entity));
        return entity;
    }

    @Override
    public void apagar(Long id) {
        UsuarioEntity entity = buscarPorId(id);
        log.info("Excluindo usuário com id ({}) -> Excluindo", id);

        repository.delete(entity);
        log.info("Excluindo usuário com id ({}) -> Excluído com sucesso", id);
    }
}
