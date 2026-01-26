package com.example.jpa.ejercicio_01.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Marca la clase como entidad JPA
@Table(name = "membresia") // Tabla "membresia" en la BD
public class Membresia implements Serializable {
    // Serializable permite que esta entidad pueda ser convertida a bytes.
    // Esto es útil si se guarda en caché, se envía por red o se usa en sesiones.

    // Identificador de versión de la clase.
    // Evita errores al deserializar si la clase cambia.
    // Si no se pone, Java genera uno automáticamente, pero da warning.
    private static final long serialVersionUID = 1L; // Control de serialización

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment en MySQL
    private Long id;

    @Column(name = "codigo_socio")
    private String codigoSocio;

    @Column(name = "tipo_plan")
    private String tipoPlan;

    public Membresia() {} // Constructor vacío requerido por JPA

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCodigoSocio() { return codigoSocio; }
    public void setCodigoSocio(String codigoSocio) { this.codigoSocio = codigoSocio; }

    public String getTipoPlan() { return tipoPlan; }
    public void setTipoPlan(String tipoPlan) { this.tipoPlan = tipoPlan; }
}
