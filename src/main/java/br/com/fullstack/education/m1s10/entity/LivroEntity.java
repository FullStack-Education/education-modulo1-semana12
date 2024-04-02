package br.com.fullstack.education.m1s10.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "livro")
public class LivroEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime criadoEm;

    private LocalDateTime alteradoEm;

    @Column(nullable = false, length = 150)
    private String titulo;

    private String subtitulo;

    @Column(nullable = false)
    private String autor;

    @Column(nullable = false)
    private Integer nroPaginas;

    @Column(nullable = false, length = 50)
    private String isbn;

    private LocalDate dataPublicacao;

}
