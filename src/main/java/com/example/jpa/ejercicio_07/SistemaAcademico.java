package com.example.jpa.ejercicio_07;

import java.util.List;
import com.example.jpa.ejercicio_07.entities.Alumno;
import com.example.jpa.ejercicio_07.entities.Curso;
import com.example.jpa.ejercicio_07.entities.Direccion;
import com.example.jpa.ejercicio_07.entities.Modulo;
import com.example.jpa.ejercicio_07.util.EMF;
import jakarta.persistence.EntityManager;

public class SistemaAcademico {
    public static void main(String[] args) {
        // 1. CREACIÓN
        crearAlumnoConDireccion("Lara Croft", "lara@tomb.com", "Mansión Croft 1", "Londres");
        crearCursoConModulos("Programación Java");

        // 2. CONSULTA
        listarAlumnos();

        // 3. ACTUALIZACIÓN (Usamos 1L porque el ID es Long)
        actualizarNombreAlumno(1L, "Lara Croft Editado");

        // 4. INSCRIPCIÓN
        inscribirAlumnoEnCurso(1L, 1L);

        // Cerramos la factoría al final
        EMF.close();
    }

    public static void crearAlumnoConDireccion(String nombre, String email, String calle, String ciudad) {
        EntityManager em = EMF.getEntityManager();
        try {
            em.getTransaction().begin();

            // Usamos tu constructor: public Direccion(String calle, String ciudad)
            Direccion dir = new Direccion(calle, ciudad);

            // Usamos tu constructor: public Alumno(String nombre, String email)
            Alumno alu = new Alumno(nombre, email);
            alu.setDireccion(dir);

            // Al tener CascadeType.ALL en Alumno.java, persistir alu guarda la dirección automáticamente
            em.persist(alu);

            em.getTransaction().commit();
            System.out.println("Alumno y dirección guardados con éxito.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void crearCursoConModulos(String titulo) {
        EntityManager em = EMF.getEntityManager();
        try {
            em.getTransaction().begin();

            // Constructor: public Curso(String titulo)
            Curso curso = new Curso(titulo);

            // Constructor: public Modulo (String nombreModulo, int horas, Curso curso)
            Modulo m1 = new Modulo("Introducción", 20, curso);
            Modulo m2 = new Modulo("JDBC", 40, curso);

            // Importante: Añadirlos a la lista del curso para que la relación esté sincronizada en memoria
            curso.getModulos().add(m1);
            curso.getModulos().add(m2);

            // Al tener CascadeType.ALL en Curso.java, persistir curso guarda los módulos automáticamente
            em.persist(curso);

            em.getTransaction().commit();
            System.out.println("Curso y módulos guardados con éxito.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public static void listarAlumnos() {
        EntityManager em = EMF.getEntityManager();
        try {
            List<Alumno> alumnos = em.createQuery("SELECT a FROM Alumno a", Alumno.class).getResultList();

            System.out.println("\n--- LISTADO DE ALUMNOS ---");
            for (Alumno a : alumnos) {
                String ciudad = (a.getDireccion() != null) ? a.getDireccion().getCiudad() : "Sin ciudad";
                System.out.println("ID " + a.getId() + ": " + a.getNombre() + " | Ciudad: " + ciudad);
            }
        } finally {
            em.close();
        }
    }

    public static void actualizarNombreAlumno(Long id, String nuevoNombre) {
        EntityManager em = EMF.getEntityManager();
        try {
            em.getTransaction().begin();
            Alumno alu = em.find(Alumno.class, id);
            if (alu != null) {
                alu.setNombre(nuevoNombre);
                em.getTransaction().commit();
                System.out.println("Nombre del alumno ID " + id + " actualizado.");
            } else {
                System.out.println("Alumno no encontrado.");
            }
        } finally {
            em.close();
        }
    }

    public static void inscribirAlumnoEnCurso(Long alumnoId, Long cursoId) {
        EntityManager em = EMF.getEntityManager();
        try {
            em.getTransaction().begin();

            Alumno alu = em.find(Alumno.class, alumnoId);
            Curso cur = em.find(Curso.class, cursoId);

            if (alu != null && cur != null) {
                // Al ser ManyToMany, añadimos el curso a la lista del alumno
                alu.getCursos().add(cur);
                em.getTransaction().commit();
                System.out.println("Alumno " + alu.getNombre() + " inscrito en " + cur.getTitulo());
            }
        } finally {
            em.close();
        }
    }
}
