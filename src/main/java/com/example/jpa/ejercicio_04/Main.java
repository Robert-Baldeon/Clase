package com.example.jpa.ejercicio_04;

import java.time.LocalDate;

import com.example.jpa.ejercicio_04.entities.FichaTecnica;
import com.example.jpa.ejercicio_04.entities.Mecanico;
import com.example.jpa.ejercicio_04.entities.Reparacion;
import com.example.jpa.ejercicio_04.entities.Vehiculo;
import com.example.jpa.ejercicio_04.util.EMF;

import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EMF.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // 1. Crear Ficha y Vehículo (Relación 1:1)
            FichaTecnica fichaTecnica = new FichaTecnica();
            fichaTecnica.setNumeroBastidor("XYZ123456789");
            fichaTecnica.setUltimaItv(LocalDate.now());

            entityManager.persist(fichaTecnica);

            Vehiculo vehiculo = new Vehiculo();
            vehiculo.setMatricula("1234-BBB");
            vehiculo.setModelo("Seat Ibiza");
            vehiculo.setFicha(fichaTecnica);

            entityManager.persist(vehiculo);

            // 2. Crear Mecanico
            Mecanico mecanico = new Mecanico();
            mecanico.setNombre("Pepe Taller");
            mecanico.setEspecialidad("Mantenimiento");

            entityManager.persist(mecanico);

            // 3. Crear Reparación y asociar (Relación N:1 y N:M)
            Reparacion reparacion = new Reparacion();
            reparacion.setDescripcion("Cambio de aceite y filtros");
            reparacion.setFecha(LocalDate.now());
            reparacion.setVehiculo(vehiculo);

            // Añadir mecánico a la reparación (N:M)
            reparacion.getMecanicos().add(mecanico);

            mecanico.getReparacionesAsignadas().add(reparacion);
            vehiculo.getReparaciones().add(reparacion);

            entityManager.persist(reparacion);

            entityManager.getTransaction().commit();
            System.out.println("Mapeo de Taller completado con éxito.");
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
