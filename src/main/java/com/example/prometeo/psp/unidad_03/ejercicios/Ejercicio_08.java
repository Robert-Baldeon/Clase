// Enunciado: El mostrador ahora es más grande y caben hasta 3 panes.
// En la clase Mostrador, cambia boolean hayPan por int panes = 0;.
// Panadero (producir): Solo debe esperar si el mostrador está lleno (panes == 3). Si hay sitio, añade uno (panes++).
// Cliente (consumir): Solo debe esperar si el mostrador está vacío (panes == 0). Si hay pan, coge uno (panes--).
// Imprime siempre cuántos panes quedan después de cada acción (ej: "Panadero puso pan. Stock: 2").

package com.example.prometeo.psp.unidad_03.ejercicios;

public class Ejercicio_08 {
    public static void main(String[] args) {
        Mostrador_02 mostrador = new Mostrador_02();

        Thread panadero = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                mostrador.producir();
            }
        });

        Thread cliente = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                mostrador.consumir();
            }
        });

        panadero.start();
        cliente.start();
    }
}

class Mostrador_02 {
    private int panes = 0;
    private static final int CAPACIDAD = 3;

    // Panadero
    public synchronized void producir() {
        while (panes == CAPACIDAD) { // Si el mostrador está lleno
            try {
                wait(); // Dormimos al panadero para que no produzca más panes
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Si llegamos hasta aquí, producimos panes
        panes++;
        System.out.println("Panadero puso pan. Stock: " + panes);

        // Avisamos a TODOS (clientes y otros panaderos) de que el stock ha cambiado
        notifyAll();
    }

    // Cliente
    public synchronized void consumir() {
        while (panes == 0) { // Si el mostrador está vacío
            try {
                wait(); // Dormimos al cliente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Si lllegamos aquí, significa que hay al menos un pan
        panes--;
        System.out.println("Cliente se llevo un pan. Stock: " + panes);
        notifyAll();
    }
}
