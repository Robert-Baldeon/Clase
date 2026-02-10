// Reto : El Corredor de Maratón (Básico)

// Objetivo: Entender la creación de hilos y el comportamiento asíncrono
// Crea una clase Corredor que implemente Runnable
// Cada corredor debe tener un nombre y una velocidad (un número aleatorio entre 1 y 5)
// En el método run(), el corredor debe completar 10 etapas
// En cada etapa, imprime: "Corredor [Nombre] completa etapa [X]" y duerme el hilo (Thread.sleep) un tiempo proporcional a su velocidad
// En tu Main, lanza 3 hilos de corredores al mismo tiempo

package com.example.prometeo.psp.unidad_03.ejercicios;

import java.util.concurrent.ThreadLocalRandom;

public class Ejercicio_09 {
    public static void main(String[] args) {
        Thread corredor1 = new Thread(new Corredor("Robert"));
        Thread corredor2 = new Thread(new Corredor("Bryan"));
        Thread corredor3 = new Thread(new Corredor("Joel"));

        corredor1.start();
        corredor2.start();
        corredor3.start();
    }
}

class Corredor implements Runnable {
    private String nombre;
    private int velocidad;

    public Corredor(String nombre) {
        this.nombre = nombre;
        velocidad = ThreadLocalRandom.current().nextInt(1, 6);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            try {
                System.out.println("Corredor [" + nombre + "] completa etapa " + (i + 1));
                Thread.sleep(velocidad * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getVelocidad() { return velocidad; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
}
