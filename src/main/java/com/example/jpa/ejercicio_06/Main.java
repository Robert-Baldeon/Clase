package com.example.jpa.ejercicio_06;

import java.time.LocalDate;

import com.example.jpa.ejercicio_06.entities.Carnet;
import com.example.jpa.ejercicio_06.entities.Ejemplar;
import com.example.jpa.ejercicio_06.entities.Libro;
import com.example.jpa.ejercicio_06.entities.Socio;
import com.example.jpa.ejercicio_06.util.EMF;

import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EMF.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // 1. Crear Carnet y Socio
            Carnet carnet = new Carnet();
            carnet.setCodigoBarras("BC-12345");
            carnet.setFechaEmision(LocalDate.now());

            entityManager.persist(carnet);

            Socio socio = new Socio();
            socio.setNombre("Carlos Gomez");
            socio.setTelefono("622765081");
            socio.setCarnet(carnet);

            entityManager.persist(socio);

            // 2. Crear Libro y Ejemplar
            Libro libro = new Libro();
            libro.setIsbn("978-84");
            libro.setTitulo("Don Quijote");

            entityManager.persist(libro);

            Ejemplar ejemplar = new Ejemplar();
            ejemplar.setEstado("Nuevo");
            ejemplar.setLibro(libro);
            entityManager.persist(ejemplar);

            socio.getLibrosPrestados().add(libro);
            libro.getEjemplares().add(ejemplar);
            libro.getSocios().add(socio);

            entityManager.getTransaction().commit();
            System.out.println("Mapeo completado y datos persistidos con éxito.");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            EMF.close();
        }
    }
}
