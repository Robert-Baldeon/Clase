package com.example.jpa.ejercicio_01.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actividad")
public class Actividad implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_actividad")
    private String nombreActividad;

    @ManyToOne()
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

    @ManyToMany(mappedBy = "actividades")
    private List<Socio> sociosInscritos = new ArrayList<>(); // Inicializada

    public Actividad() {}

    public Long getId() { return id; }
    public String getNombreActividad() { return nombreActividad; }
    public void setNombreActividad(String nombreActividad) { this.nombreActividad = nombreActividad; }
    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }
    public List<Socio> getSociosInscritos() { return sociosInscritos; }
    public void setSociosInscritos(List<Socio> sociosInscritos) { this.sociosInscritos = sociosInscritos; }
}
