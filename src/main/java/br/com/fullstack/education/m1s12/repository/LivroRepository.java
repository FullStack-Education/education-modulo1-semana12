package br.com.fullstack.education.m1s12.repository;

import br.com.fullstack.education.m1s12.entity.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
}
