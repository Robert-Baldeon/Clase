// El Sistema de Impresión (Examen de Semáforos/Sincronización)

// Este es un ejercicio clásico de examen que simula la gestión de recursos limitados
// Enunciado: Una oficina tiene una Impresora compartida por varios empleados
// La impresora tiene una limitación: solo puede imprimir un documento a la vez
// Además, la impresora tiene un contador de "tóner" (puedes usar un número entero que empiece en 3)

// Requerimientos:
// Clase Impresora:
// Atributo toner = 3

// Método synchronized imprimir(String nombreEmpleado):
// Si el toner es 0, el empleado debe esperar (wait()) hasta que la impresora sea recargada
// Si hay tóner, resta 1 unidad, imprime "[Empleado] está imprimiendo..." y tarda 2 segundos (Thread.sleep(2000))

// Método synchronized recargar():
// Pone el toner de nuevo a 3 e imprime "--- IMPRESORA RECARGADA ---"
// Avisa a los hilos que están esperando (notifyAll())

// Clase Empleado (Runnable):
// Cada empleado intenta imprimir un documento

// Clase Tecnico (Runnable):
// El técnico simplemente espera 10 segundos (para dar tiempo a que se agote el tóner) y luego llama al método recargar()

// Clase Main:
// Lanza 6 hilos de Empleados a la vez y 1 hilo de Técnico
// Objetivo: Debes demostrar que los primeros 3 empleados imprimen uno tras otro, los siguientes 3 se quedan bloqueados esperando, y solo cuando el técnico recarga la impresora 10 segundos después, los últimos 3 terminan su trabajo de forma ordenada

package com.example.prometeo.psp.unidad_03.ejercicios;

public class Ejercicio_11 {
    public static void main(String[] args) {
        Impresora_01 impresora = new Impresora_01();
        Thread hiloEmpleado1 = new Thread(new Empleado_01("Robert", impresora));
        Thread hiloEmpleado2 = new Thread(new Empleado_01("Gian", impresora));
        Thread hiloEmpleado3 = new Thread(new Empleado_01("Alan", impresora));
        Thread hiloEmpleado4 = new Thread(new Empleado_01("Juan", impresora));
        Thread hiloEmpleado5 = new Thread(new Empleado_01("Bryan", impresora));
        Thread hiloEmpleado6 = new Thread(new Empleado_01("Adrián", impresora));

        Thread hiloTecnico = new Thread(new Tecnico_01(impresora));

        hiloEmpleado1.start();
        hiloEmpleado2.start();
        hiloEmpleado3.start();
        hiloEmpleado4.start();
        hiloEmpleado5.start();
        hiloEmpleado6.start();
        hiloTecnico.start();
    }
}

class Impresora_01 {
    private int toner = 3;

    public synchronized void imprimir(String nombreEmpleado) {
        while (toner == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        toner--;
        System.out.println("[Empleado] está imprimiendo...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void recargar() {
        toner = 3;

        System.out.println("--- IMPRESORA RECARGADA ---");
        notifyAll();
    }

    public int getToner() { return toner; }
    public void setToner(int toner) { this.toner = toner; }
}

class Empleado_01 implements Runnable {
    private String nombreEmpleado;
    private Impresora_01 impresora;

    public Empleado_01(String nombreEmpleado, Impresora_01 impresora) {
        this.nombreEmpleado = nombreEmpleado;
        this.impresora = impresora;
    }

    @Override
    public void run() {
        impresora.imprimir(nombreEmpleado);
    }

    public Impresora_01 getImpresora() { return impresora; }
    public void setImpresora(Impresora_01 impresora) { this.impresora = impresora; }
    public String getNombreEmpleado() { return nombreEmpleado; }
    public void setNombreEmpleado(String nombreEmpleado) { this.nombreEmpleado = nombreEmpleado; }

}

class Tecnico_01 implements Runnable {
    private Impresora_01 impresora;

    public Tecnico_01(Impresora_01 impresora) {
        this.impresora = impresora;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        impresora.recargar();
    }

    public Impresora_01 getImpresora() { return impresora; }
    public void setImpresora(Impresora_01 impresora) { this.impresora = impresora; }
}
