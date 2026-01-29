package com.example.jpa.ejercicio_05;

import java.util.ArrayList;
import java.util.List;

import com.example.jpa.ejercicio_05.entities.DireccionEmpotrada;
import com.example.jpa.ejercicio_05.entities.Usuario;
import com.example.jpa.ejercicio_05.util.EMF;

import jakarta.persistence.EntityManager;

public class Main {
    public static void main(String[] args) {
        EntityManager entityManager = EMF.getEntityManager();
        try {
            entityManager.getTransaction().begin();

            Usuario usuario = new Usuario();
            usuario.setNombreCompleto("Alex Smith");
            usuario.setUsername("asmith");

            // Añadir tipos básicos
            List<String> telefonos = new ArrayList<>();
            telefonos.add("600111222");
            telefonos.add("912334455");
            usuario.setTelefonos(telefonos);

            // Añadir objetos empotrados
            DireccionEmpotrada direccionEmpotrada = new DireccionEmpotrada();
            direccionEmpotrada.setCalle("Avenida Siempre Viva 123");
            direccionEmpotrada.setCiudad("Springfield");
            direccionEmpotrada.setTipoDireccion("CASA");

            List<DireccionEmpotrada> direcciones = new ArrayList<>();
            direcciones.add(direccionEmpotrada);
            usuario.setDirecciones(direcciones);

            // Al persistir el usuario, se guardan automáticamente sus colecciones
            entityManager.persist(usuario);

            entityManager.getTransaction().commit();
            System.out.println("Usuario con colecciones guardado con éxito.");
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) entityManager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            entityManager.close();
            EMF.close();
        }
    }
}
