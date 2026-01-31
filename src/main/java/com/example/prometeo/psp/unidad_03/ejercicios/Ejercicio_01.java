package com.example.prometeo.unidad_03.ejercicios;

// El objetivo es crear y ejecutar dos hilos simples para ver su comportamiento no determinista.
// Crea una clase que implemente Runnable. En su método run(), haz un bucle que imprima 10 veces el nombre del hilo actual y el número de iteración (ej: "Hilo-A: 1", "Hilo-A: 2"...).
// En tu método main, crea dos instancias de Thread, pasando a cada una la misma instancia de tu Runnable y dándoles nombres diferentes ("Hilo-A" y "Hilo-B").
// Llama al método start() en ambos hilos.
// Ejecuta el programa varias veces. ¿Las salidas de los dos hilos aparecen siempre en el mismo orden o se van intercalando de forma diferente en cada ejecución? (Tiempo estimado: 30 minutos).

public class Ejercicio_01 implements Runnable {
    @Override
    public void run() {
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        Thread procesoA = new Thread(new Ejercicio_01(), "Hilo-A");
        Thread procesoB = new Thread(new Ejercicio_01(), "Hilo-B");

        procesoA.start();
        procesoB.start();
    }
}
