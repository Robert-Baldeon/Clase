package com.example.jpa.ejercicio_07.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String email;

    // Relación 1:1 con Dirección
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_direccion") // Esta es la FK en la tabla alumno
    private Direccion direccion;

    // Relación N:M con Curso
    @ManyToMany
    @JoinTable(name = "inscripciones", joinColumns = @JoinColumn(name = "id_alumno"), inverseJoinColumns = @JoinColumn(name = "id_curso"))
    private List<Curso> cursos = new ArrayList<>();

    public Alumno() {}

    public Alumno(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	public Direccion getDireccion() { return direccion; }
	public void setDireccion(Direccion direccion) { this.direccion = direccion; }
	public List<Curso> getCursos() { return cursos; }
	public void setCursos(List<Curso> cursos) { this.cursos = cursos; }
}
