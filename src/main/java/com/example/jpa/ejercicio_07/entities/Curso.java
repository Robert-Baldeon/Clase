package com.example.jpa.ejercicio_07.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "curso")
public class Curso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    // Relación 1:N con Modulo
    // mappedBy indica que el dueño de la relación es el campo "curso" en la clase Modulo
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Modulo> modulos = new ArrayList<>();

    // Relación N:M con Alumno (Inversa)
    @ManyToMany(mappedBy = "cursos")
    private List<Alumno> alumnos = new ArrayList<>();

    public Curso() {}

    public Curso(String titulo) {
        this.titulo = titulo;
    }

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getTitulo() { return titulo; }
	public void setTitulo(String titulo) { this.titulo = titulo; }
	public List<Modulo> getModulos() { return modulos; }
	public void setModulos(List<Modulo> modulos) { this.modulos = modulos; }
	public List<Alumno> getAlumnos() { return alumnos; }
	public void setAlumnos(List<Alumno> alumnos) { this.alumnos = alumnos; }
}
