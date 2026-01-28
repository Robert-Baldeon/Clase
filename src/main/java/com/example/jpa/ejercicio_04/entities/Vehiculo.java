package com.example.jpa.ejercicio_04.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "matricula", nullable = false, length = 15)
    private String matricula;

    @Column(name = "modelo", length = 50)
    private String modelo;

    @OneToOne
    @JoinColumn(name = "id_ficha", unique = true)
    private FichaTecnica ficha; // Relación 1:1 con FichaTecnica

    @OneToMany(mappedBy = "vehiculo")
    private List<Reparacion> reparaciones; // Relación 1:N con Reparacion

    public Vehiculo() {
        reparaciones = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public FichaTecnica getFicha() { return ficha; }
    public void setFicha(FichaTecnica ficha) { this.ficha = ficha; }
    public List<Reparacion> getReparaciones() { return reparaciones; }
    public void setReparaciones(List<Reparacion> reparaciones) { this.reparaciones = reparaciones; }
}
