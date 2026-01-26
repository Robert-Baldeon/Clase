package com.example.jpa.ejercicio_01;

import com.example.jpa.ejercicio_01.entities.*;
import com.example.jpa.ejercicio_01.util.EMF;
import jakarta.persistence.EntityManager;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Obtener el EntityManager (Como abrir una conexión)
        try (EntityManager em = EMF.getEntityManager()) {
            
            em.getTransaction().begin();

            // --- PASO A: CREAR DATOS ---
            // Creamos una membresía y un socio (Relación 1:1)
            Membresia m1 = new Membresia();
            m1.setCodigoSocio("S-001");
            m1.setTipoPlan("Premium");

            Socio s1 = new Socio();
            s1.setNombre("Alex Arch");
            s1.setEmail("alex@arch.com");
            s1.setMembresia(m1); // Unimos los objetos

            // Creamos un entrenador y una actividad (Relación 1:N)
            Entrenador e1 = new Entrenador();
            e1.setNombre("Carlos Trainer");
            e1.setEspecialidad("Crossfit");

            Actividad a1 = new Actividad();
            a1.setNombreActividad("Sesión Mañana");
            a1.setEntrenador(e1); // Asignamos el entrenador a la actividad

            // Relación N:M (Inscribimos al socio en la actividad)
            s1.getActividades().add(a1);

            // --- PASO B: GUARDAR (Persist) ---
            // Gracias al CascadeType.ALL que pusimos en las entidades, 
            // al persistir el Socio y el Entrenador, se guardan sus relaciones.
            em.persist(e1); 
            em.persist(s1); 

            em.getTransaction().commit();
            System.out.println("Datos insertados correctamente.");

            // --- PASO C: CONSULTAR (JPQL) ---
            System.out.println("\n--- Lista de Socios y sus Planes ---");
            List<Socio> socios = em.createQuery("SELECT s FROM Socio s", Socio.class).getResultList();
            
            for (Socio s : socios) {
                System.out.println("Socio: " + s.getNombre() + 
                                   " | Plan: " + s.getMembresia().getTipoPlan());
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            EMF.close(); // Cerramos la fábrica al final de la app
        }
    }
}
