package br.com.fullstack.education.m1s10.exception.error;

public class LivroByIdNotFoundException extends NotFoundException {
    public LivroByIdNotFoundException(Long id) {
        super("Livro não encontrado com id: " + id);
    }
}
