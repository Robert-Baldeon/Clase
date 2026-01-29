package com.example.jpa.ejercicio_07;

import com.example.jpa.ejercicio_07.entities.Alumno;
import com.example.jpa.ejercicio_07.entities.Curso;
import com.example.jpa.ejercicio_07.entities.Direccion;
import com.example.jpa.ejercicio_07.entities.Modulo;
import com.example.jpa.ejercicio_07.util.EMF;

import jakarta.persistence.EntityManager;

public class CargaDatos {
    public static void main(String[] args) {
        EntityManager em = EMF.getEntityManager();
        em.getTransaction().begin();

        // 1. Crear Dirección y Alumno
        Direccion d1 = new Direccion("Calle Mayor 1", "Madrid");
        Alumno a1 = new Alumno("Juan Perez", "juan@email.com");
        a1.setDireccion(d1);

        // 2. Crear Curso y Módulos
        Curso c1 = new Curso("Programación Java");
        Modulo m1 = new Modulo("Fundamentos", 40, c1);
        Modulo m2 = new Modulo("JPA y Hibernate", 60, c1);

        c1.getModulos().add(m1);
        c1.getModulos().add(m2);

        // 3. Relación ManyToMany (Inscripción)
        a1.getCursos().add(c1);

        em.persist(a1); // CascadeType.ALL debería guardar la dirección
        em.persist(c1); // Debería guardar los módulos

        em.getTransaction().commit();
        em.close();
        EMF.close();
    }
}
