// Nivel 3: El Desafío del Puente (ReentrantLock)
// Ahora que dominas synchronized, vamos a por el siguiente nivel. Vamos a usar un cerrojo manual (ReentrantLock). Es más flexible porque tú decides cuándo abrirlo y cerrarlo explícitamente.
// Reto: El Puente Estrecho. Imagina un puente por el que solo puede cruzar un coche a la vez.
// Crea una clase Puente con un objeto private final ReentrantLock cerrojo = new ReentrantLock();.
// Crea un método cruzar(String coche).
// Usa cerrojo.lock() para tomar el control del puente.
// Importante: Usa un bloque try { ... } finally { cerrojo.unlock(); }. El finally asegura que, pase lo que pase, el puente quede libre para el siguiente.

package com.example.prometeo.psp.unidad_03.ejercicios;

import java.util.concurrent.locks.ReentrantLock;

class Puente {
    // El cerrojo que controla el acceso al puente
    private final static ReentrantLock cerrojo = new ReentrantLock();

    public void cruzar(String coche) {
        System.out.println("--- " + coche + " llega al puente y espera");

        cerrojo.lock();

        try {
            // SECCIÓN CRÍTICA: Solo un hilo puede estar aquí dentro
            System.out.println(">>> [PUENTE] " + coche + " está CRUZANDO...");


            // Simulamos que tarda 2 segundos en cruzar
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("<<< [PUENTE] " + coche + " ha salido");
            cerrojo.unlock();
        }
    }
}

public class Ejercicio_05 {
    public static void main(String[] args) {
        Puente puente = new Puente();

        Thread coche1 = new Thread(() -> puente.cruzar("Coche rojo"));
        Thread coche2 = new Thread(() -> puente.cruzar("Coche azul"));
        Thread coche3 = new Thread(() -> puente.cruzar("Coche verde"));

        coche1.start();
        coche2.start();
        coche3.start();
    }
}
