package com.example.jpa.ejercicio_07.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "modulo")
public class Modulo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreModulo;
    private int horas;

    // Relación N:1 con Curso
    @ManyToOne
    @JoinColumn(name = "id_curso") // Esta es la columna que creará en la tabla modulo
    private Curso curso;

    public Modulo() {}

    public Modulo (String nombreModulo, int horas, Curso curso) {
        this.nombreModulo = nombreModulo;
        this.horas = horas;
        this.curso = curso;
    }

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNombreModulo() { return nombreModulo; }
	public void setNombreModulo(String nombreModulo) { this.nombreModulo = nombreModulo; }
	public int getHoras() { return horas; }
	public void setHoras(int horas) { this.horas = horas; }
	public Curso getCurso() { return curso; }
	public void setCurso(Curso curso) { this.curso = curso; }
}
