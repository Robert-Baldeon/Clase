package com.example.jpa.ejercicio_04.entities;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ficha_tecnica")
public class FichaTecnica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "numero_bastidor", nullable = false, length = 50)
    private String numeroBastidor;

    @Column(name = "ultima_itv")
    private LocalDate ultimaItv;

    public FichaTecnica() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNumeroBastidor() { return numeroBastidor; }
    public void setNumeroBastidor(String numeroBastidor) { this.numeroBastidor = numeroBastidor; }
    public LocalDate getUltimaItv() { return ultimaItv; }
    public void setUltimaItv(LocalDate ultimaItv) { this.ultimaItv = ultimaItv; }
}
