package br.com.fullstack.education.m1s12.exception.error;

public class UsuarioByIdNotFoundException extends NotFoundException {
    public UsuarioByIdNotFoundException(Long id) {
        super("Usuário não encontrado com id: " + id);
    }
}
