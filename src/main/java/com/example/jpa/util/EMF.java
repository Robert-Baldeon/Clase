package com.example.jpa.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Clase de utilidad para gestionar el EntityManagerFactory (EMF)
public class EMF {
    // Se crea una única instancia de la fábrica para toda la aplicación (Singleton)
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadMariaDB");

    // Retorna un nuevo EntityManager para realizar operaciones en la base de datos
    // Es como pedirle una "conexión" individual a la fábrica
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Cierra la fábrica de conexiones
    // Debe llamarse al finalizar la ejecución del programa
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
