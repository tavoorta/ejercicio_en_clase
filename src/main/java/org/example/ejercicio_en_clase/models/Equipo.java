package org.example.ejercicio_en_clase.models;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Equipo {
    private Long Id;
    private String Nombre;
    private String Descripcion;
    private LocalDate Fecha;
    private Estado Estado;
}
