package com.project.spring_boot.controlers;

import com.project.spring_boot.model.Autor;
import com.project.spring_boot.service.AutorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService serv_autor;

    @GetMapping
    public ResponseEntity<List<Autor>> listarTodos(){
        List<Autor> autores = serv_autor.ListaAutor();
        return new ResponseEntity<>(autores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscar(@PathVariable long id){
        Optional<Autor> autor = serv_autor.BuscaId(id);
        return autor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Autor> insertar(@RequestBody Autor in_autor){
        Autor insertado = serv_autor.guardar(in_autor);
        return new ResponseEntity<>(insertado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizaAutor(@PathVariable long id, @RequestBody Autor autor){
        Optional<Autor> autorExiste = serv_autor.BuscaId(id);
        if(autorExiste.isPresent()){
            Autor actualizado = autorExiste.get();
            actualizado.setNombre(autor.getNombre());
            actualizado.setApellido(autor.getApellido());
            actualizado.setTelefono(autor.getTelefono());
            return  new ResponseEntity<>(serv_autor.guardar(actualizado), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAutor(@PathVariable long id){
        try {
            serv_autor.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

}
