package com.example.jpa.ejercicio_03.entities;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

// La anotación @Embeddable indica que esta clase no es una "Entidad" independiente,
// sino que sus atributos están destinados a ser parte (embebidos) de otra entidad.
// * ¿POR QUÉ LO PONEMOS?
// 1. Organización: Evitamos tener una clase 'Paquete' con muchísimos atributos sueltos.
// Agrupamos los datos que tienen relación entre sí (alto, ancho, profundo).
// 2. Reutilización: Si mañana tuviéramos una entidad 'Contenedor', podría usar
// esta misma clase 'Dimensiones' sin repetir código.
// 3. Diseño de BD: En la base de datos NO se crea una tabla "dimensiones". Los campos
// aparecerán directamente en la tabla "paquete". Esto es más eficiente que hacer
// un JOIN con otra tabla para datos tan simples.

@Embeddable
public class Dimensiones implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double alto;
    private Double ancho;
    private Double profundo;

    public Dimensiones() {}

    public Double getAlto() { return alto; }
    public void setAlto(Double alto) { this.alto = alto; }
    public Double getAncho() { return ancho; }
    public void setAncho(Double ancho) { this.ancho = ancho; }
    public Double getProfundo() { return profundo; }
    public void setProfundo(Double profundo) { this.profundo = profundo; }
}
