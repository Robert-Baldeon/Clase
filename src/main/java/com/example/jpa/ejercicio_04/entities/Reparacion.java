package com.example.jpa.ejercicio_04.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reparacion")
public class Reparacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo")
    private Vehiculo vehiculo; // Relación N:1 con Vehiculo

    @ManyToMany
    @JoinTable(
    name = "asignacion_reparaciones",
    joinColumns = @JoinColumn(name = "id_reparacion"),
    inverseJoinColumns = @JoinColumn(name = "id_mecanico")
    )
    private List<Mecanico> mecanicos; // Relación N:M con Mecanico

    public Reparacion() { mecanicos = new ArrayList<>(); }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
    public List<Mecanico> getMecanicos() { return mecanicos; }
    public void setMecanicos(List<Mecanico> mecanicos) { this.mecanicos = mecanicos; }
}
