package com.example.jpa.ejercicio_02.entities;

import java.io.Serializable;

public class Mascota implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer  id;
    private String nombre;
    private String especia;
    private HistorialMedico historialMedico;
}
