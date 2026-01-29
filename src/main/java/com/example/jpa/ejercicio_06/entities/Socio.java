package com.example.jpa.ejercicio_06.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
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
@Table(name = "socio")
public class Socio implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @OneToOne
    @JoinColumn(name = "id_carnet", unique = true)
    private Carnet carnet; // Relación 1:1 con Carnet

    @ManyToMany
    @JoinTable(name = "prestamos", joinColumns = @JoinColumn(name = "id_socio"), inverseJoinColumns = @JoinColumn(name = "id_libro"))
    private List<Libro> librosPrestados; // Relación N:M con Libro

    public Socio() { librosPrestados = new ArrayList<>(); }

	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	public String getTelefono() { return telefono; }
	public void setTelefono(String telefono) { this.telefono = telefono; }
	public Carnet getCarnet() { return carnet; }
	public void setCarnet(Carnet carnet) { this.carnet = carnet; }
	public List<Libro> getLibrosPrestados() { return librosPrestados; }
	public void setLibrosPrestados(List<Libro> librosPrestados) { this.librosPrestados = librosPrestados; }
}
