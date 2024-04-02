package br.com.fullstack.education.m1s12.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UsuarioFiltro implements Serializable {

    private String nome;
    private String login;

}
