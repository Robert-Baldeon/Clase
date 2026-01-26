package com.example.jpa.ejercicio_01.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "socio")
public class Socio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    @OneToOne()
    @JoinColumn(name = "id_membresia")
    private Membresia membresia;

    @ManyToMany()
    @JoinTable(
        name = "inscripciones_actividades",
        joinColumns = @JoinColumn(name = "id_socio"),
        inverseJoinColumns = @JoinColumn(name = "id_actividad")
    )
    private List<Actividad> actividades = new ArrayList<>(); // CLAVE: Inicializada para evitar NullPointer

    public Socio() {}

    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Membresia getMembresia() { return membresia; }
    public void setMembresia(Membresia membresia) { this.membresia = membresia; }
    public List<Actividad> getActividades() { return actividades; }
    public void setActividades(List<Actividad> actividades) { this.actividades = actividades; }
}
