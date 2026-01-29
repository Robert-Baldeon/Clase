package com.example.jpa.ejercicio_07.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "direccion")
public class Direccion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calle;
    private String ciudad;

    // Relación inversa (opcional, pero recomendada para 1:1 bidireccional)
    @OneToOne(mappedBy = "direccion")
    private Alumno alumno;

    public Direccion() {}

    public Direccion(String calle, String ciudad) {
        this.calle = calle;
        this.ciudad = ciudad;
    }

	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getCalle() { return calle; }
	public void setCalle(String calle) { this.calle = calle; }
	public String getCiudad() { return ciudad; }
	public void setCiudad(String ciudad) { this.ciudad = ciudad; }
	public Alumno getAlumno() { return alumno; }
	public void setAlumno(Alumno alumno) { this.alumno = alumno; }
}
