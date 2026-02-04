// Ejercicio 7: El Almacén de Pan (Productor-Consumidor)
// Imagina una panadería pequeña donde solo hay sitio para un pan en el mostrador. Si el panadero hace un pan y ya hay uno, tiene que esperar. Si el cliente quiere comprar y no hay nada, tiene que esperar.
// Objetivo: Crear un sistema donde un hilo "Panadero" y un hilo "Cliente" se coordinen usando wait() y notify().
// Pasos a seguir:
// Clase Mostrador:
// Crea una variable boolean hayPan = false;.
// Método synchronized producir():
// Si hayPan es true, el hilo debe llamar a wait().
// Si es false, pone el pan (hayPan = true), imprime "Panadero: Pan recién hecho puesto en el mostrador" y llama a notify().
// Método synchronized consumir():
// Si hayPan es false, el hilo debe llamar a wait().
// Si es true, se lleva el pan (hayPan = false), imprime "Cliente: Me he llevado el pan del mostrador" y llama a notify().
// Hilos:
// Crea un hilo Panadero que intente producir 5 veces (usa un bucle for).
// Crea un hilo Cliente que intente consumir 5 veces (usa un bucle for).

package com.example.prometeo.psp.unidad_03.ejercicios;

public class Ejercicio_07 {
    public static void main(String[] args) {
        Mostrador_01 mostrador = new Mostrador_01();

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

class Mostrador_01 {
    private boolean hayPan = false;

    // Panadero entra aquí
    public synchronized void producir() {
        while (hayPan) { // Si hay pan, esperamos
            try {
                wait(); // Nos dormimos y soltamos la llave del mostrador
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Si llegamos aquí, es que hayPan == false
        hayPan = true;
        System.out.println("Panadero: Pan recién hecho puesto en el mostrador");
        notify(); // Despertamos al cliente
    }

    // Cliente entra aquí
    public synchronized void consumir() {
        while (!hayPan) { // Si no hay pan, esperamos
            try {
                wait(); // Nos dormimos y soltamos la llave
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Si llegamos aquí, es que hayPan == true
        hayPan = false;
        System.out.println("Cliente: Me he llevado el pan del mostrador");
        notify(); // Despertamos al panadero
    }
}
