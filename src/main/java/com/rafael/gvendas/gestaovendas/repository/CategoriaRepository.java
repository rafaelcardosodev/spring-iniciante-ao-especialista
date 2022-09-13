package com.rafael.gvendas.gestaovendas.repository;

import com.rafael.gvendas.gestaovendas.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByNome(String nome);
}
