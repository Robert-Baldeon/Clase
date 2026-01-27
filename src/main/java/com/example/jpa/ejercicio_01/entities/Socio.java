package com.example.jpa.ejercicio_01.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "socio")
public class Socio implements Serializable { 
    /*
     * Implementar Serializable permite que la entidad pueda ser convertida a bytes
     * Esto es útil si se guarda en caché, se envía por red o se utiliza en sesiones
     * Muchos frameworks (como Hibernate o Spring) recomiendan que las entidades sean serializables
     */
    private static final long serialVersionUID = 1L; 
    // Identificador de versión de la clase
    // Ayuda a mantener compatibilidad al deserializar objetos si la clase cambia

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private String email;

    /*
     * ===== RELACIÓN 1:1 CON MEMBRESIA =====
     * Cada socio tiene exactamente una membresía.
     * La columna "id_membresia" está en la tabla "socio" (lado dueño de la relación)
     */
    @OneToOne
    @JoinColumn(name = "id_membresia")
    private Membresia membresia;

    /*
     * ===== RELACIÓN N:M CON ACTIVIDAD =====
     * Un socio puede estar inscrito en muchas actividades,
     * y una actividad puede tener muchos socios.
     *
     * @ManyToMany indica que es una relación de muchos a muchos.
     * @JoinTable define la tabla intermedia que resuelve la relación:
     *   - name = "inscripciones_actividades" → nombre de la tabla intermedia
     *   - joinColumns → columna que apunta a este lado (socio)
     *   - inverseJoinColumns → columna que apunta al otro lado (actividad)
     *
     * La lista está inicializada con ArrayList para evitar NullPointerException.
     * Warning de serialización aparece porque List<Actividad> está en una clase Serializable.
     * Opciones para el warning:
     *   - Ignorar, ya que no afecta JPA
     *   - Marcar @SuppressWarnings("serial")
     *   - Asegurarse que Actividad también implemente Serializable (ya hecho)
     */
    @ManyToMany
    @JoinTable(
    name = "inscripciones_actividades",
    joinColumns = @JoinColumn(name = "id_socio"),
    inverseJoinColumns = @JoinColumn(name = "id_actividad"))
    private List<Actividad> actividades = new ArrayList<>();

    // Constructor vacío obligatorio por JPA
    public Socio() {}

    // ===== GETTERS Y SETTERS =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Membresia getMembresia() { return membresia; }
    public void setMembresia(Membresia membresia) { this.membresia = membresia; }

    public List<Actividad> getActividades() { return actividades; }
    public void setActividades(List<Actividad> actividades) { this.actividades = actividades; }
}
