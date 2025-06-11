package com.project.spring_boot.service;

import com.project.spring_boot.model.Autor;
import com.project.spring_boot.repositorio.AutorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {
    @Autowired
    private AutorRepositorio repo_autor;

    public List<Autor> ListaAutor(){
        return repo_autor.findAll();
    }

    public Optional<Autor> BuscaId(long id){
        return repo_autor.findById(id);
    }

    public Autor actualizar(long id, Autor actualiza){
        Autor autorExiste = repo_autor.findById(id)
                .orElseThrow(() -> new RuntimeException("Autor no Existe" +id));
        return repo_autor.save(autorExiste);
    }

    public Autor guardar(Autor autor){
        return (Autor) repo_autor.save(autor);
    }

    public void eliminar(long id){
        repo_autor.deleteById(id);
    }


}
