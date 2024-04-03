package br.com.fullstack.education.m1s12.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "usuario")
public class UsuarioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    private LocalDateTime criadoEm = LocalDateTime.now();

    @JsonIgnore
    private LocalDateTime alteradoEm = LocalDateTime.now();

    private String nome;

    private String login;

    private String senha;

}
