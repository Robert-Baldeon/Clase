package com.example.jpa.ejercicio_03.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "paquete")
public class Paquete implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion", length = 100)
    private String descripcion;

    @Column(name = "peso")
    private Double peso;

    @Embedded
    private Dimensiones dimensiones; // Atributo embebido

    @OneToOne
    @JoinColumn(name = "id_seguro", unique = true)
    private Seguro seguro; // Relación 1:1

    @OneToMany(mappedBy = "paquete")
    private List<Incidencia> incidencias;// Relación 1:N

    @ManyToMany
    @JoinTable(
    name = "asignacion_envios",
    joinColumns = @JoinColumn(name = "id_paquete"),
    inverseJoinColumns = @JoinColumn(name = "id_transportista")
    )
    private List<Transportista> transportistas;

    public Paquete () {
        this.incidencias = new ArrayList<>();
        this.transportistas = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }
    public Dimensiones getDimensiones() { return dimensiones; }
    public void setDimensiones(Dimensiones dimensiones) { this.dimensiones = dimensiones; }
    public Seguro getSeguro() { return seguro; }
    public void setSeguro(Seguro seguro) { this.seguro = seguro; }
    public List<Incidencia> getIncidencias() { return incidencias; }
    public void setIncidencias(List<Incidencia> incidencias) { this.incidencias = incidencias; }
    public List<Transportista> getTransportistas() { return transportistas; }
    public void setTransportistas(List<Transportista> transportistas) { this.transportistas = transportistas; }
}
