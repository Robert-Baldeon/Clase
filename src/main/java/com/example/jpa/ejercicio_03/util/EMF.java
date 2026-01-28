package com.example.jpa.ejercicio_03.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class EMF {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("UnidadLogistica");

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close () {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
