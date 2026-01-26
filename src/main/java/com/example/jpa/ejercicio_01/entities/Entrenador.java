package com.example.jpa.ejercicio_01.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entrenador")
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String especialidad;

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL)
    private List<Actividad> actividadesDirigidas = new ArrayList<>(); // Inicializada

    public Entrenador() {}

    // Getters y Setters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public List<Actividad> getActividadesDirigidas() { return actividadesDirigidas; }
    public void setActividadesDirigidas(List<Actividad> actividadesDirigidas) { this.actividadesDirigidas = actividadesDirigidas; }
}
