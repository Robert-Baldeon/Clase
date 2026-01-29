package com.example.jpa.ejercicio_06.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ejemplar")
public class Ejemplar implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "estado", length = 50)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_libro")
    private Libro libro; // Relación N:1 con Libro

    public Ejemplar() {}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getEstado() { return estado; }
	public void setEstado(String estado) { this.estado = estado; }
	public Libro getLibro() { return libro; }
	public void setLibro(Libro libro) { this.libro = libro; }
}
