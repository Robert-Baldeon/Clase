// Nivel 2: Medio - El Bloque de Código (Optimización)
// Objetivo: Aprender que no siempre hay que sincronizar el método entero (lo que se llama granularidad).
// Enunciado: Tienes una clase Impresora con un método imprimirDocumento(String nombre).
// Tarea: El método debe:
// Imprimir "Preparando papel..." (esto no necesita sincronización).
// Imprimir el documento 5 veces (esto sí debe ser atómico para que no se mezclen las líneas de dos documentos distintos).
// Desafío: En lugar de marcar el método como synchronized, usa un bloque sincronizado (synchronized(this) { ... }) solo para la parte del bucle de impresión. Prueba a lanzar dos hilos y observa cómo la preparación se puede solapar, pero la impresión no.

package com.example.prometeo.psp.unidad_03.ejercicios;

class Impresora {
    public void imprimirDocumento(String nombre) {
        System.out.println(Thread.currentThread().getName() +  " preparando papel...");

        synchronized(this) {
            for (int i = 0; i < 5; i++) {
                System.out.println("Imprimiendo " + nombre);
            }
        }
    }
}

public class Ejercicio_04 {
    public static void main(String[] args) {
        Impresora impresora = new Impresora();

        Runnable tarea1 = () -> impresora.imprimirDocumento("Documento 1");
        Runnable tarea2 = () -> impresora.imprimirDocumento("Documento 2");

        Thread hiloA = new Thread(tarea1, "HILO-A");
        Thread hiloB = new Thread(tarea2, "HILO-B");

        hiloA.start();
        hiloB.start();
    }
}
