package com.project.spring_boot.dto;

import com.project.spring_boot.model.Libro;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class LibroDTO {

    private Integer id;
    private String titulo, editorial, genero;
    private BigDecimal precio;
    private LocalDate fechaedicion;
    private AutorDTO autorDTO;

    public LibroDTO(Libro p_libro) {
        this.id = p_libro.getId();
        this.titulo = p_libro.getTitulo();
        this.editorial = p_libro.getEditorial();
        this.genero = p_libro.getGenero();
        this.precio = p_libro.getPrecio();
        this.fechaedicion = p_libro.getFechaedicion();
        autorDTO = new AutorDTO(p_libro.getAutor());
    }
}
