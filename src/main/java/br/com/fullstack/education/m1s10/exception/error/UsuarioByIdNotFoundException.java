package br.com.fullstack.education.m1s10.exception.error;

public class UsuarioByIdNotFoundException extends NotFoundException {
    public UsuarioByIdNotFoundException(Long id) {
        super("Usuário não encontrado com id: " + id);
    }
}
