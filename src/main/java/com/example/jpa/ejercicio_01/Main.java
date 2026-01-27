package com.example.jpa.ejercicio_01;

import com.example.jpa.ejercicio_01.entities.*;
import com.example.jpa.ejercicio_01.util.EMF;

import jakarta.persistence.EntityManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Abrimos una conexión a la base de datos usando EntityManager
        // try-with-resources asegura que el EntityManager se cierre automáticamente
        try (EntityManager em = EMF.getEntityManager()) {

            // Iniciamos la transacción
            em.getTransaction().begin();

            // =====================================================
            // PASO A: CREACIÓN DE DATOS
            // =====================================================

            // ---------- Relación 1:1 (Socio - Membresía) ----------

            // Creamos una membresía
            Membresia membresia = new Membresia();
            membresia.setCodigoSocio("S-001");     // Código identificador del socio
            membresia.setTipoPlan("Premium");      // Tipo de plan

            // Creamos un socio
            Socio socio = new Socio();
            socio.setNombre("Alex Arch");           // Nombre del socio
            socio.setEmail("alex@arch.com");        // Email del socio
            socio.setMembresia(membresia);          // Asociamos la membresía al socio

            // ---------- Relación 1:N (Entrenador - Actividad) ----------

            // Creamos un entrenador
            Entrenador entrenador = new Entrenador();
            entrenador.setNombre("Carlos Trainer"); // Nombre del entrenador
            entrenador.setEspecialidad("Crossfit"); // Especialidad

            // Creamos una actividad
            Actividad actividad = new Actividad();
            actividad.setNombreActividad("Sesión Mañana"); // Nombre de la actividad
            actividad.setEntrenador(entrenador);           // Asignamos el entrenador

            // Mantenemos coherencia en memoria (lado inverso de la relación)
            entrenador.getActividadesDirigidas().add(actividad);

            // ---------- Relación N:M (Socio - Actividad) ----------

            // Inscribimos al socio en la actividad
            socio.getActividades().add(actividad);
            actividad.getSociosInscritos().add(socio);

            // =====================================================
            // PASO B: PERSISTENCIA DE DATOS
            // =====================================================

            // Persistimos primero las entidades independientes
            em.persist(membresia);    // Guardamos la membresía
            em.persist(entrenador);   // Guardamos el entrenador
            em.persist(actividad);    // Guardamos la actividad

            // Finalmente persistimos el socio
            em.persist(socio);

            // Confirmamos la transacción
            em.getTransaction().commit();
            System.out.println("Datos insertados correctamente.");

            // =====================================================
            // PASO C: CONSULTA DE DATOS
            // =====================================================

            System.out.println("\n--- Lista de Socios y sus Planes ---");

            // Consulta JPQL para obtener todos los socios
            List<Socio> socios = em
                    .createQuery("SELECT s FROM Socio s", Socio.class)
                    .getResultList();

            // Recorremos los socios y mostramos su nombre y plan
            for (Socio s : socios) {
                System.out.println(
                        "Socio: " + s.getNombre() +
                        " | Plan: " + s.getMembresia().getTipoPlan()
                );
            }

        } catch (Exception e) {
            // Capturamos cualquier error de persistencia o transacción
            e.printStackTrace();
        } finally {
            // Cerramos la fábrica de EntityManager al finalizar la aplicación
            EMF.close();
        }
    }
}
