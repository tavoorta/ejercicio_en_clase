package org.example.ejercicio_en_clase.models;

import lombok.Data;

import java.util.Date;

@Data
public class Equipo {
    private Long Id;
    private String Nombre;
    private String Descripcion;
    private Date Fecha;
    private Estado Estado;
}
