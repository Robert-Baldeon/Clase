package com.example.jpa.ejercicio_02.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "veterinario")
public class Veterinario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "num_colegiado")
    private String numColegiado;

    @OneToMany(mappedBy = "veterinario")
    private List<Consulta> consultasRealizadas;

    public Veterinario() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getNumColegiado() { return numColegiado; }
    public void setNumColegiado(String numColegiado) { this.numColegiado = numColegiado; }

    public List<Consulta> getConsultasRealizadas() { return consultasRealizadas; }
    public void setConsultasRealizadas(List<Consulta> consultasRealizadas) { this.consultasRealizadas = consultasRealizadas; }
}
