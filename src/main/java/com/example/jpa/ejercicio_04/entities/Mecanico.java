package com.example.jpa.ejercicio_04.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "mecanico")
public class Mecanico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "especialidad", length = 50)
    private String especialidad;

    @ManyToMany(mappedBy = "mecanicos")
    private List<Reparacion> reparacionesAsignadas; // Relación N:M con

    public Mecanico() {
        reparacionesAsignadas = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public List<Reparacion> getReparacionesAsignadas() { return reparacionesAsignadas; }
    public void setReparacionesAsignadas(List<Reparacion> reparacionesAsignadas) { this.reparacionesAsignadas = reparacionesAsignadas; }
}
