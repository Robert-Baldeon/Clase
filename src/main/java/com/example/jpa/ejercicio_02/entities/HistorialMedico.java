package com.example.jpa.ejercicio_02.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "historial_medico")
public class HistorialMedico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_historial", nullable = false, length = 50)
    private String codigoHistorial;

    @Column(name = "fecha_creacion")
    private LocalDate fechaCreacion;

    public HistorialMedico() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCodigoHistorial() { return codigoHistorial; }
    public void setCodigoHistorial(String codigoHistorial) { this.codigoHistorial = codigoHistorial; }
    public LocalDate getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
