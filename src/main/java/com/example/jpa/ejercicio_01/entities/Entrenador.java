package com.example.jpa.ejercicio_01.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity // Marca la clase como entidad JPA
@Table(name = "entrenador") // Mapea la tabla "entrenador" en la BD
public class Entrenador implements Serializable {
    
    /*
     * Serializable permite que esta entidad pueda ser convertida a bytes.
     * Esto es útil para:
     *  - Guardarla en caché
     *  - Enviarla por red
     *  - Usarla en sesiones de servidor
     * Muchos frameworks recomiendan que las entidades sean serializables.
     */
    private static final long serialVersionUID = 1L; 
    // Identificador de versión de la clase
    // Evita errores al deserializar si la clase cambia
    // Si no se define, Java genera uno automáticamente pero da warning

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment MySQL
    private Long id;

    @Column(name = "nombre") // Nombre del entrenador
    private String nombre;

    @Column(name = "especialidad") // Especialidad del entrenador
    private String especialidad;

    /*
     * ===== RELACIÓN 1:N CON ACTIVIDAD =====
     * Un entrenador puede dirigir muchas actividades.
     * 
     * @OneToMany(mappedBy = "entrenador") indica que:
     *  - Relación 1:N desde Entrenador hacia Actividad
     *  - "mappedBy = 'entrenador'" → Actividad tiene la FK (lado dueño)
     *  - Este lado no tiene la FK, solo refleja la relación
     *
     * La lista se inicializa con ArrayList para evitar NullPointerException.
     * Warning de serialización aparece porque List<Actividad> está dentro de una clase Serializable.
     * Opciones:
     *  - Ignorar warning
     *  - Asegurarse que Actividad también sea Serializable (ya hecho)
     *  - Usar @SuppressWarnings("serial") para silenciar el warning
     */
    @SuppressWarnings("serial")
    @OneToMany(mappedBy = "entrenador")
    private List<Actividad> actividadesDirigidas = new ArrayList<>();

    // Constructor vacío obligatorio por JPA
    public Entrenador() {}

    // ===== GETTERS Y SETTERS =====
    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public List<Actividad> getActividadesDirigidas() { return actividadesDirigidas; }
    public void setActividadesDirigidas(List<Actividad> actividadesDirigidas) { this.actividadesDirigidas = actividadesDirigidas; }
}

