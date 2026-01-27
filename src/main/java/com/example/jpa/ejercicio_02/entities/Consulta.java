package com.example.jpa.ejercicio_02.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "motivo", length = 255)
    private String motivo;

    @ManyToOne
    @JoinColumn(name = "id_veterinario")
    private Veterinario veterinario;

    @ManyToMany(mappedBy = "consultas")
    private List<Mascota> pacientes;

    public Consulta() {
        this.pacientes = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public Veterinario getVeterinario() { return veterinario; }
    public void setVeterinario(Veterinario veterinario) { this.veterinario = veterinario; }
    public List<Mascota> getPacientes() { return pacientes; }
    public void setPacientes(List<Mascota> pacientes) { this.pacientes = pacientes; }
}
