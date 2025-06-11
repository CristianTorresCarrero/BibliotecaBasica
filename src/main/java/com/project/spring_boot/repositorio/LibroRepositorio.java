package com.project.spring_boot.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.spring_boot.model.Libro;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, Integer> {

}
