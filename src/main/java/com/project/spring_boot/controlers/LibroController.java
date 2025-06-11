package com.project.spring_boot.controlers;

import com.project.spring_boot.model.Libro;
import com.project.spring_boot.service.LibroService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/libros")

public class LibroController {

    @Autowired
    private LibroService serv_libro;

    @GetMapping
    public ResponseEntity<List<Libro>> listarTodos(){
        List<Libro> libros = serv_libro.ListaLibro();
        return new ResponseEntity<>(libros, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> buscar(@PathVariable int id){
        Optional<Libro> libro = serv_libro.buscarId(id);
        return libro.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Libro> insertar(@RequestBody Libro in_libro){
        Libro insertado = serv_libro.guardar(in_libro);
        return new ResponseEntity<>(insertado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizaLibro(@PathVariable int id, @RequestBody Libro libro){
        Optional<Libro> libroExiste = serv_libro.buscarId(id);
        if (libroExiste.isPresent()){
            Libro actualizado = libroExiste.get();
            actualizado.setTitulo(libro.getTitulo());
            actualizado.setEditorial(libro.getEditorial());
            actualizado.setGenero(libro.getGenero());
            actualizado.setFechaedicion(libro.getFechaedicion());
            actualizado.setPrecio(libro.getPrecio());
            return new ResponseEntity<>(serv_libro.guardar(actualizado), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLibro(@PathVariable int id){
        try {
            serv_libro.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
