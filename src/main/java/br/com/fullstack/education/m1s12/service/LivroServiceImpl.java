package br.com.fullstack.education.m1s12.service;

import br.com.fullstack.education.m1s12.entity.LivroEntity;
import br.com.fullstack.education.m1s12.exception.error.LivroByIdNotFoundException;
import br.com.fullstack.education.m1s12.repository.LivroRepository;
import br.com.fullstack.education.m1s12.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository repository;

    public LivroServiceImpl(LivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LivroEntity> buscarTodos() {
        log.info("Buscando todos os livros");

        List<LivroEntity> livros = repository.findAll();

        log.info("Buscando todos os livros -> {} Encontrados", livros.size());
        log.debug("Buscando todos os livros -> Registros encontrados:\n{}\n", JsonUtil.objetoParaJson(livros));
        return livros;
    }

    @Override
    public LivroEntity buscarPorId(Long id) {
        log.info("Buscando livro por id ({})", id);

        Optional<LivroEntity> livro = repository.findById(id);

        if (livro.isEmpty()) {
            log.error("Buscando livro por id ({}) -> NÃO Encontrado", id);
            throw new LivroByIdNotFoundException(id);
        }

        log.info("Buscando livro por id ({}) -> Encontrado", id);
        log.debug("Buscando livro por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objetoParaJson(livro.get()));
        return livro.get();
    }

    @Override
    public LivroEntity criar(LivroEntity livroNovo) throws Exception {
        livroNovo.setId(null);

        log.info("Criando livro -> Salvar: \n{}\n", JsonUtil.objetoParaJson(livroNovo));
        LivroEntity livro = repository.save(livroNovo);

        log.info("Criando livro -> Salvo com sucesso");
        log.debug("Criando livro -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(livro));
        return livro;
    }

    @Override
    public LivroEntity alterar(Long id, LivroEntity livro) {
        buscarPorId(id);
        livro.setId(id);

        log.info("Alterando livro com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(livro));
        LivroEntity entity = repository.save(livro);

        log.info("Alterando livro -> Salvo com sucesso");
        log.debug("Alterando livro -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(entity));
        return entity;
    }

    @Override
    public void apagar(Long id) {
        LivroEntity entity = buscarPorId(id);
        log.info("Excluindo livro com id ({}) -> Excluindo", id);
        repository.delete(entity);
        log.info("Excluindo livro com id ({}) -> Excluído com sucesso", id);
    }
}
