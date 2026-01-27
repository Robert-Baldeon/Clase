package com.example.jpa.ejercicio_01.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity // Marca la clase como entidad JPA
@Table(name = "actividad") // Mapea la tabla "actividad"
public class Actividad implements Serializable {
    /*
     * Serializable permite que esta entidad pueda ser convertida a bytes.
     * Útil para:
     *  - Guardarla en caché
     *  - Enviarla por red
     *  - Usarla en sesiones de servidor
     */
    private static final long serialVersionUID = 1L; 
    // Identificador de versión de la clase
    // Evita errores al deserializar si la clase cambia

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment MySQL
    private Long id;

    @Column(name = "nombre_actividad") // Nombre de la actividad
    private String nombreActividad;

    /*
     * ===== RELACIÓN N:1 CON ENTRENADOR =====
     * Cada actividad tiene un único entrenador que la dirige.
     * @ManyToOne indica la relación N:1 (muchas actividades → un entrenador)
     * @JoinColumn(name = "id_entrenador") indica la columna FK en la tabla "actividad"
     *
     * Lado dueño: Actividad (porque tiene la FK)
     */
    @ManyToOne
    @JoinColumn(name = "id_entrenador")
    private Entrenador entrenador;

    /*
     * ===== RELACIÓN N:M CON SOCIO =====
     * Cada actividad puede tener muchos socios inscritos,
     * y cada socio puede estar inscrito en muchas actividades.
     *
     * Lado inverso: mapeado con "mappedBy = 'actividades'" en Socio
     * Lista inicializada con ArrayList para evitar NullPointerException
     * Warning de serialización aparece por tener la lista dentro de una clase Serializable.
     */
    @SuppressWarnings("serial")
    @ManyToMany(mappedBy = "actividades")
    private List<Socio> sociosInscritos = new ArrayList<>();

    // Constructor vacío obligatorio por JPA
    public Actividad() {}

    // ===== GETTERS Y SETTERS =====
    public Long getId() { return id; }

    public String getNombreActividad() { return nombreActividad; }
    public void setNombreActividad(String nombreActividad) { this.nombreActividad = nombreActividad; }

    public Entrenador getEntrenador() { return entrenador; }
    public void setEntrenador(Entrenador entrenador) { this.entrenador = entrenador; }

    public List<Socio> getSociosInscritos() { return sociosInscritos; }
    public void setSociosInscritos(List<Socio> sociosInscritos) { this.sociosInscritos = sociosInscritos; }
}

