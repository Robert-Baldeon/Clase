package com.example.jpa.ejercicio_01.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entrenador")
public class Entrenador implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "especialidad")
    private String especialidad;

    @OneToMany(mappedBy = "entrenador")
    private List<Actividad> actividadesDirigidas = new ArrayList<>();

    public Entrenador() {}

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public List<Actividad> getActividadesDirigidas() { return actividadesDirigidas; }
    public void setActividadesDirigidas(List<Actividad> actividadesDirigidas) { this.actividadesDirigidas = actividadesDirigidas; }
}
