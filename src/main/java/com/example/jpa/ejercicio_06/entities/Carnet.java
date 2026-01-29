package com.example.jpa.ejercicio_06.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "carnet")
public class Carnet implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_barras", nullable = false, length = 50)
    private String codigoBarras;

    @Column(name = "fecha_emision")
    private LocalDate fechaEmision;

    public Carnet() {}

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getCodigoBarras() { return codigoBarras; }
	public void setCodigoBarras(String codigoBarras) { this.codigoBarras = codigoBarras; }
	public LocalDate getFechaEmision() { return fechaEmision; }
	public void setFechaEmision(LocalDate fechaEmision) { this.fechaEmision = fechaEmision; }
}
