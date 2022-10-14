package com.rafael.gvendas.gestaovendas.repository;

import com.rafael.gvendas.gestaovendas.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente findByNome(String nome);
}
