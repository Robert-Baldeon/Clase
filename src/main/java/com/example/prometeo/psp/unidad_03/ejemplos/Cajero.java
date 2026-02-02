package com.example.prometeo.psp.unidad_03.ejemplos;

import java.util.concurrent.locks.ReentrantLock;

public class Cajero {
    // 1. Creamos el cerrojo
    private final ReentrantLock cerrojo = new ReentrantLock();

    public void sacarDinero(String cliente, int cantidad) {
        System.out.println(cliente + " llega al cajero");

        // 2. Intentamos bloquear
        cerrojo.lock();

        try {
            // 3. SECCIÓN CRÍTICA (solo entra uno)
            System.out.println(cliente + " está operando...");
            Thread.sleep(2000); // Simulamos el proceso
            System.out.println(cliente + " ha sacado " + cantidad + "€");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Siempre, siempre el unlock() dentro de un bloque finally. Si el código dentro de try da un error y no llegamos al unlock, el recurso quedatá bloqueado para siempre (Deadlock)
            System.out.println(cliente + " libera el cajero ");
            cerrojo.unlock();
        }
    }
}
