package br.com.fullstack.education.m1s12.entity;

import br.com.fullstack.education.m1s12.serializer.LocalDateDeserializer;
import br.com.fullstack.education.m1s12.serializer.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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

    @JsonIgnore
    private LocalDateTime criadoEm;

    @JsonIgnore
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

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dataPublicacao;

    private Integer vezesEmprestado;

}
