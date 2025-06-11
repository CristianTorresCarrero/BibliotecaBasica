package com.project.spring_boot.model;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo, editorial, genero;
    private BigDecimal precio;
    private LocalDate fechaedicion;

    @ManyToOne
    private Autor autor;
}
