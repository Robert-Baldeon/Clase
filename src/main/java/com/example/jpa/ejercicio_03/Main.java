package com.example.jpa.ejercicio_03;

import java.math.BigDecimal;

import com.example.jpa.ejercicio_03.entities.Dimensiones;
import com.example.jpa.ejercicio_03.entities.Incidencia;
import com.example.jpa.ejercicio_03.entities.Paquete;
import com.example.jpa.ejercicio_03.entities.Seguro;
import com.example.jpa.ejercicio_03.entities.Transportista;
import com.example.jpa.ejercicio_03.util.EMF;

import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EMF.getEntityManager();

        try {
            entityManager.getTransaction().begin();

            // 1. Crear seguro (Relación 1:1 con paquete)
            Seguro seguro = new Seguro();
            seguro.setCodigoPoliza("POL-777");
            seguro.setCoberturaMaxima(new BigDecimal("1500.50"));

            // 2. Crear paquete con Dimensiones (Embebido)
            Paquete paquete = new Paquete();
            paquete.setDescripcion("Ordenador Portátil");
            paquete.setPeso(2.5);
            paquete.setSeguro(seguro);

            // 3. Crear y configurar el objeto embebido
            Dimensiones dimensiones = new Dimensiones();
            dimensiones.setAlto(10.0);
            dimensiones.setAncho(40.0);
            dimensiones.setProfundo(30.0);
            paquete.setDimensiones(dimensiones);

            // 4. Crear un Transportista (Relación N:M)
            Transportista transportista = new Transportista();
            transportista.setNombre("Paco Express");
            transportista.setEmpresa("Logística Global");

            // 5. Crear Incidencia
            Incidencia incidencia = new Incidencia();
            incidencia.setComentario("Caja ligeramente golpeada");
            incidencia.setPaquete(paquete);

            paquete.getIncidencias().add(incidencia);
            paquete.getTransportistas().add(transportista);
            transportista.getPaquetesAsignados().add(paquete);

            entityManager.persist(seguro);
            entityManager.persist(transportista);
            entityManager.persist(paquete);
            entityManager.persist(incidencia);

            entityManager.getTransaction().commit();

            System.out.println("Mapeo con Logística (con Embedded) completado");
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
