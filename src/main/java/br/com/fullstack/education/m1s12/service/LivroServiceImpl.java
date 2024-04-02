package br.com.fullstack.education.m1s12.service;

import br.com.fullstack.education.m1s12.entity.LivroEntity;
import br.com.fullstack.education.m1s12.exception.error.LivroByIdNotFoundException;
import br.com.fullstack.education.m1s12.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroServiceImpl implements LivroService {

    private final LivroRepository repository;

    public LivroServiceImpl(LivroRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<LivroEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public LivroEntity buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new LivroByIdNotFoundException(id));
    }

    @Override
    public LivroEntity criar(LivroEntity livro) throws Exception {
        livro.setId(null);
        return repository.save(livro);
    }

    @Override
    public LivroEntity alterar(Long id, LivroEntity livro) {
        buscarPorId(id);
        livro.setId(id);
        return repository.save(livro);
    }

    @Override
    public void apagar(Long id) {
        LivroEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}
