package com.example.jpa.ejercicio_03.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "seguro")
public class Seguro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "codigo_poliza", nullable = false, length = 50)
    private String codigoPoliza;

    @Column(name = "cobertura_maxima", precision = 10, scale = 2)
    private BigDecimal coberturaMaxima;

    public Seguro() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getCodigoPoliza() { return codigoPoliza; }
    public void setCodigoPoliza(String codigoPoliza) { this.codigoPoliza = codigoPoliza; }
    public BigDecimal getCoberturaMaxima() { return coberturaMaxima; }
    public void setCoberturaMaxima(BigDecimal coberturaMaxima) { this.coberturaMaxima = coberturaMaxima; }
}
