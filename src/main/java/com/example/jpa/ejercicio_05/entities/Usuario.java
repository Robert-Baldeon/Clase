package com.example.jpa.ejercicio_05.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre_completo", nullable = false, length = 100)
    private String nombreCompleto;

    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String username;

    // Colección de tipos básicos
    // @ElementCollection para Tipos Básicos (Strings)
    // Aquí JPA entiende que 'telefonos' no son entidades, sino simples datos vinculados.
    // Hibernate creará una tabla 'usuario_telefonos' con dos columnas: 
    // id_usuario (FK) y telefono (el valor del String).
    @ElementCollection
    @CollectionTable(name = "usuario_telefonos", joinColumns = @JoinColumn(name = "id_usuario"))
    @Column(name = "telefono")
    private List<String> telefonos;

    // Colección de objetos complejos (no entidades)
    // @ElementCollection para Objetos Empotrados (Embeddables)
    // A diferencia de los Strings, aquí JPA "aplana" los campos de DireccionEmpotrada
    // dentro de la tabla de colección 'usuario_direcciones'.
    // Creará columnas para 'calle', 'ciudad' y 'tipoDireccion' junto a la FK del usuario.
    @ElementCollection
    @CollectionTable(name = "usuario_direcciones", joinColumns = @JoinColumn(name = "id_usuario"))
    private List<DireccionEmpotrada> direcciones;

    public Usuario() { direcciones = new ArrayList<>();  }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public List<String> getTelefonos() { return telefonos; }
    public void setTelefonos(List<String> telefonos) { this.telefonos = telefonos; }
    public List<DireccionEmpotrada> getDirecciones() { return direcciones; }
    public void setDirecciones(List<DireccionEmpotrada> direcciones) { this.direcciones = direcciones; }
}
