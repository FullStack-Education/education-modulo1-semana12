package br.com.fullstack.education.m1s10.exception.error;

public class UsuarioByLoginNotFoundException extends NotFoundException {
    public UsuarioByLoginNotFoundException(String login) {
        super("Usuário não encontrado com login: " + login);
    }
}
