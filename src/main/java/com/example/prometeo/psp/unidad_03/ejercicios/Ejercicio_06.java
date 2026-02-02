// Ejercicio 5: El "TryLock" con Tiempo (Evitando esperas infinitas)
// A diferencia de synchronized, ReentrantLock nos permite intentar coger el cerrojo pero rendirnos si pasa demasiado tiempo. Esto es vital para que una aplicación no se quede "congelada".
// Escenario: El Servidor de Base de Datos
// Crea una clase BaseDeDatos con un ReentrantLock.
// Crea un método realizarConsulta(String usuario).
// Usa cerrojo.tryLock(2, TimeUnit.SECONDS).
// Si lo consigue: Imprime "Usuario [X] consultando...", duerme el hilo 3 segundos (para forzar que el siguiente falle) y luego libera el cerrojo.
// Si NO lo consigue en 2 segundos: Imprime "Error: Tiempo de espera agotado para el usuario [X]. Inténtelo más tarde".
// Lanza 3 hilos a la vez.

package com.example.prometeo.psp.unidad_03.ejercicios;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Ejercicio_06 {
    public static void main(String[] args) {
        BaseDeDatos baseDeDatos = new BaseDeDatos();

        Thread usuario1 = new Thread(() -> baseDeDatos.realizarConsulta("usuario 1"));
        Thread usuario2 = new Thread(() -> baseDeDatos.realizarConsulta("usuario 2"));
        Thread usuario3 = new Thread(() -> baseDeDatos.realizarConsulta("usaurio 3"));

        usuario1.start();
        usuario2.start();
        usuario3.start();
    }
}

class BaseDeDatos {
    private final static ReentrantLock rentReentrantLock = new ReentrantLock();

    public void realizarConsulta(String usuario) {
        boolean obtenido = false;

        try {
            // Intenta obtener el cerrojo durante 2 segundos
            // El hilo intentará entrar
            // Si el cerrojo está libre, 'obtenido' será true de inmediato
            // Si está ocupado el hilo esperará bloqueado un máximo de 2 segundos
            obtenido = rentReentrantLock.tryLock(2, TimeUnit.SECONDS);

            if (obtenido) {
                // Si entramos aquí, es porque tenemos la "llave" del cerrojo
                System.out.println(">>> Usuario " + usuario + " CONSULTANDO (3 segundos)...");
                // Dormimos 3 segundos
                // Como el tiempo de espera de los demás es de 2 segundos, el siguiente hilo que lo intente SEGURO que agotará su tiempo y dará false
                Thread.sleep(3000); // Forzamos a que el siguiente falle
            } else {
                // Si pasan los 2 segundos y NO hemos conseguido la llave o el cerrojo el método tryLock devuelve false e imprimos esto
                System.err.println("Error: Tiempo de espera agotado para el usuario " + usuario + ". Inténtelo más tarde");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (obtenido) {
                System.out.println("<<< Usuario " + usuario + " ha terminado");
                rentReentrantLock.unlock();
            }
        }
    }
}
