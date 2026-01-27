package com.example.jpa.ejercicio_01.entities;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity // Marca la clase como entidad JPA
@Table(name = "membresia") // Mapea la tabla "membresia" en la BD
public class Membresia implements Serializable {
    /*
     * Serializable permite que esta entidad pueda ser convertida a bytes.
     * Esto es útil para:
     *  - Guardarla en caché
     *  - Enviarla por red
     *  - Usarla en sesiones de servidor
     * Muchos frameworks (como Hibernate o Spring) recomiendan que las entidades sean serializables.
     */
    private static final long serialVersionUID = 1L; 
    // Identificador de versión de la clase
    // Ayuda a mantener compatibilidad al deserializar objetos si la clase cambia
    // Si no se define, Java genera uno automáticamente pero da warning

    @Id // Clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment en MySQL
    private Integer id;

    @Column(name = "codigo_socio") // Código del socio asociado a la membresía
    private String codigoSocio;

    @Column(name = "tipo_plan") // Tipo de plan: Mensual, Anual, Premium
    private String tipoPlan;

    // Constructor vacío requerido por JPA
    public Membresia() {}

    // ===== GETTERS Y SETTERS =====
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCodigoSocio() { return codigoSocio; }
    public void setCodigoSocio(String codigoSocio) { this.codigoSocio = codigoSocio; }

    public String getTipoPlan() { return tipoPlan; }
    public void setTipoPlan(String tipoPlan) { this.tipoPlan = tipoPlan; }
}
