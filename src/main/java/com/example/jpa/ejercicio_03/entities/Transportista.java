package com.example.jpa.ejercicio_03.entities;

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
@Table(name = "transportista")
public class Transportista implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", length = 100)
    private String nombre;

    @Column(name = "empresa", length = 50)
    private String empresa;

    @ManyToMany(mappedBy = "transportistas")
    private List<Paquete> paquetesAsignados; // Relación N:M

    public Transportista() {
        paquetesAsignados = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEmpresa() { return empresa; }
    public void setEmpresa(String empresa) { this.empresa = empresa; }
    public List<Paquete> getPaquetesAsignados() { return paquetesAsignados; }
    public void setPaquetesAsignados(List<Paquete> paquetesAsignados) { this.paquetesAsignados = paquetesAsignados; }
}
