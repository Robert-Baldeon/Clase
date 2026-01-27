package com.example.jpa.ejercicio_01;

import com.example.jpa.ejercicio_01.entities.*;
import com.example.jpa.ejercicio_01.util.EMF;
import jakarta.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Abrir conexión a la BD usando EntityManager
        try (EntityManager em = EMF.getEntityManager()) {
            // Comenzar la transacción
            em.getTransaction().begin();

            // ----------------------
            // PASO A: CREAR DATOS
            // ----------------------

            // 1:1 - Socio con Membresia
            Membresia membresia = new Membresia();
            membresia.setCodigoSocio("S-001"); // Código del socio
            membresia.setTipoPlan("Premium");  // Tipo de plan

            Socio socio = new Socio();
            socio.setNombre("Alex Arch");
            socio.setEmail("alex@arch.com");
            socio.setMembresia(membresia); // Asociamos la membresía al socio

            // 1:N - Entrenador con Actividad
            Entrenador entrenador = new Entrenador();
            entrenador.setNombre("Carlos Trainer");
            entrenador.setEspecialidad("Crossfit");

            Actividad actividad = new Actividad();
            actividad.setNombreActividad("Sesión Mañana");
            actividad.setEntrenador(entrenador); // Asignamos el entrenador a la actividad

            // Mantener coherencia en memoria
            entrenador.getActividadesDirigidas().add(actividad); // Entrenador conoce su actividad

            // N:M - Inscribir Socio en Actividad
            socio.getActividades().add(actividad);         // Socio conoce la actividad
            actividad.getSociosInscritos().add(socio);     // Actividad conoce al socio

            // ----------------------
            // PASO B: GUARDAR DATOS
            // ----------------------

            // Persistimos todas las entidades.
            // Empezamos con Entrenador y Actividad para respetar FK
            em.persist(entrenador);  // Guardamos entrenador
            em.persist(actividad);  // Guardamos actividad
            em.persist(socio);  // Guardamos socio junto con su membresía (cascade)

            // Confirmamos la transacción
            em.getTransaction().commit();
            System.out.println("Datos insertados correctamente.");

            // ----------------------
            // PASO C: CONSULTAR DATOS
            // ----------------------

            System.out.println("\n--- Lista de Socios y sus Planes ---");
            // JPQL: obtener todos los socios
            List<Socio> socios = em.createQuery("SELECT s FROM Socio s", Socio.class)
                                   .getResultList();

            // Mostramos nombre del socio y tipo de plan
            for (Socio s : socios) {
                System.out.println("Socio: " + s.getNombre() +
                                   " | Plan: " + s.getMembresia().getTipoPlan());
            }

        } catch (Exception e) {
            // Captura errores de persistencia o transacción
            e.printStackTrace();
        } finally {
            // Cerramos la fábrica de EntityManager al final de la aplicación
            EMF.close();
        }
    }
}
