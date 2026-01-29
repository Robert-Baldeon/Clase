package com.example.jpa.ejercicio_06;

import java.time.LocalDate;

import com.example.jpa.ejercicio_06.entities.Carnet;
import com.example.jpa.ejercicio_06.entities.Ejemplar;
import com.example.jpa.ejercicio_06.entities.Libro;
import com.example.jpa.ejercicio_06.entities.Socio;
import com.example.jpa.ejercicio_06.util.EMF;

import jakarta.persistence.EntityManager;

public class SistemaBibliotecaDos {
    public static void main(String[] args) {
        // 1. POBLAR (Crear socios, carnets, libros y préstamos)
        poblarDatosIniciales();

        // 2. BUSCAR
        buscarSocio(1);
        buscarLibro(1);

        // 3. ELIMINAR
        // Intentaremos eliminar un libro (Ojo con las FK de 'ejemplar' y 'prestamos')
        eliminarLibro(1);

        EMF.close();
    }

    public static void poblarDatosIniciales() {
        EntityManager em = EMF.getEntityManager();
        try {
            em.getTransaction().begin();

            // --- Crear Carnet y Socio ---
            Carnet c1 = new Carnet();
            c1.setCodigoBarras("CARNET-001");
            c1.setFechaEmision(LocalDate.now());
            em.persist(c1);

            Socio s1 = new Socio();
            s1.setNombre("Ana García");
            s1.setTelefono("600111222");
            s1.setCarnet(c1);
            em.persist(s1);

            // --- Crear Libros ---
            Libro l1 = new Libro();
            l1.setTitulo("El Quijote");
            l1.setIsbn("978-111");
            em.persist(l1);

            Libro l2 = new Libro();
            l2.setTitulo("Java para Expertos");
            l2.setIsbn("978-222");
            em.persist(l2);

            // --- Crear Ejemplares para el Libro 1 ---
            Ejemplar ej1 = new Ejemplar();
            ej1.setEstado("Bueno");
            ej1.setLibro(l1);
            em.persist(ej1);

            // --- Crear un Préstamo (Relación ManyToMany) ---
            s1.getLibrosPrestados().add(l1);
            l1.getSocios().add(s1);

            em.getTransaction().commit();
            System.out.println("Datos iniciales cargados correctamente.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void buscarSocio(int id) {
        EntityManager em = EMF.getEntityManager();

        try {
            Socio socio = em.find(Socio.class, id);

            if (socio != null) {
                System.out.println("Socio encontrado: " + socio.getNombre());
                System.out.println("Libros en préstamo: " + socio.getLibrosPrestados().size());
            } else {
                System.out.println("Socio con ID " + id + " no encontrado.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void buscarLibro(int id) {
        EntityManager em = EMF.getEntityManager();

        try {
            Libro libro = em.find(Libro.class, id);

            if (libro != null) {
                System.out.println("Libro encontrado: " + libro.getTitulo());
            } else {
                System.out.println("Libro con ID " + id + " no encontrado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void eliminarLibro(int id) {
        EntityManager em = EMF.getEntityManager();

        try {
            em.getTransaction().begin();
            Libro libro = em.find(Libro.class, id);

            if (libro != null) {
                // 1. IMPORTANTE: Romper relación ManyToMany antes de borrar
                // Si no quitamos el libro de la lista de los socios, la tabla 'prestamos' dará error.
                for (Socio s : libro.getSocios()) {
                    s.getLibrosPrestados().remove(libro);
                }

                // 2. IMPORTANTE: Borrar ejemplares asociados (FK)
                for (Ejemplar e : libro.getEjemplares()) {
                    em.remove(e);
                }

                em.remove(libro);
                em.getTransaction().commit();
                System.out.println("Libro e historial eliminados correctamente.");
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            System.err.println("Error al eliminar: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
