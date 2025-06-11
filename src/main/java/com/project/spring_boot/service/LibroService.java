package com.project.spring_boot.service;

import com.project.spring_boot.model.Libro;
import com.project.spring_boot.repositorio.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private LibroRepositorio repo_libro;

    public List<Libro> ListaLibro(){
        return repo_libro.findAll();
    }

    public Optional<Libro> buscarId(int id){
        return repo_libro.findById(id);
    }

    public Libro Actualizar(int id, Libro actualiza){
        Libro libroExiste = repo_libro.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no Existe" +id));
        return repo_libro.save(libroExiste);
    }

    public Libro guardar(Libro libro){
        return repo_libro.save(libro);
    }

    public void eliminar(int id){
        repo_libro.deleteById(id);
    }
}
