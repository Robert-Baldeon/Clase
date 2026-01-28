package com.example.jpa.ejercicio_02;

import java.time.LocalDate;

import com.example.jpa.ejercicio_02.entities.Consulta;
import com.example.jpa.ejercicio_02.entities.HistorialMedico;
import com.example.jpa.ejercicio_02.entities.Mascota;
import com.example.jpa.ejercicio_02.entities.Veterinario;
import com.example.jpa.ejercicio_02.util.EMF;

import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EMF.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            HistorialMedico historialMedico = new HistorialMedico();
            historialMedico.setCodigoHistorial("VET-101");
            historialMedico.setFechaCreacion(LocalDate.now());

            Mascota mascota = new Mascota();
            mascota.setNombre("Juanma");
            mascota.setEspecie("Perro");
            mascota.setHistorialMedico(historialMedico);

            Veterinario veterinario = new Veterinario();
            veterinario.setNombre("Bryan");
            veterinario.setNumColegiado("123456789");

            Consulta consulta = new Consulta();
            consulta.setMotivo("Revisión general");
            consulta.setVeterinario(veterinario);

            consulta.getPacientes().add(mascota);
            mascota.getConsultas().add(consulta);

            entityManager.persist(historialMedico);
            entityManager.persist(mascota);
            entityManager.persist(veterinario);
            entityManager.persist(consulta);

            entityManager.getTransaction().commit();
            System.out.println("Datos insertados correctamente");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }

            e.printStackTrace();
        } finally {
            entityManager.close();
            EMF.close();
        }
    }
}
