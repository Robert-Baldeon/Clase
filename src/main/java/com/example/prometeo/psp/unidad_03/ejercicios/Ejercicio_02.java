// El objetivo es simular una condición de carrera y solucionarla con synchronized.
// Crea una clase Contador con un int contador y un método incrementar() que hace contador++.
// Crea un Runnable que llame al método incrementar() 10,000 veces en un bucle.
// En main, crea una única instancia de Contador. Crea dos hilos, ambos trabajando con la misma instancia de Contador y el mismo Runnable.
// Inicia ambos hilos y espera a que terminen (usando el método join()). Imprime el valor final del contador. ¿Es 20,000? Ejecútalo varias veces. Verás que casi nunca lo es.
// Ahora, simplemente añade la palabra synchronized al método incrementar(). Vuelve a ejecutar el programa. ¿Cuál es el resultado ahora? (Tiempo estimado: 40 minutos).

package com.example.prometeo.psp.unidad_03.ejercicios;

class Contador {
    private int contador;

    public Contador() {
        this.contador = 0;
    }

    public Contador(int contador) {
        this.contador = contador;
    }

    public synchronized void incrementar() {
        contador++;
    }

    public int getContador() { return contador; }
    public void setContador(int contador) { this.contador = contador; }
}

public class Ejercicio_02 implements Runnable {
    private Contador contador;

    public Ejercicio_02(Contador contador) {
        this.contador = contador;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            contador.incrementar();
        }
    }

    public static void main(String[] args) {
        Contador contadorCompartido = new Contador();

        Ejercicio_02 tarea = new Ejercicio_02(contadorCompartido);

        Thread hiloA = new Thread(tarea, "Hilo-A");
        Thread hiloB = new Thread(tarea, "Hilo-B");

        hiloA.start();
        hiloB.start();

        try {
            hiloA.join();
            hiloB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Contador final: " + contadorCompartido.getContador());
    }
}
