// UNIDAD: Gestión de Hilos y Sincronización

// Ejercicio: El Almacén Logístico (Patrón Productor-Consumidor)
// Contexto: Una empresa de logística dispone de un Almacén con una capacidad muy limitada: solo puede almacenar un producto a la vez. Para gestionar este flujo, la empresa utiliza dos tipos de trabajadores: el Productor (que fabrica el producto) y el Consumidor (que lo retira)

// Requerimientos Técnicos:
// Clase Almacen:
// Debe contener una variable booleana (o un objeto) que indique si el almacén está "lleno" o "vacío"
// Debe implementar un método sincronizado llamado almacenar(int idProducto). Si el almacén ya está lleno, el hilo debe esperar hasta que quede libre
// Una vez almacenado, debe avisar a los demás hilos
// Debe implementar un método sincronizado llamado retirar()
// Si el almacén está vacío, el hilo debe esperar hasta que haya algo que retirar. Una vez retirado, debe avisar a los demás hilos

// Clase Productor (Runnable):
// Su tarea es intentar almacenar 5 productos en total
// Entre cada producción, el hilo debe simular una espera de 1 segundo
// Clase Consumidor (Runnable):
// Su tarea es intentar retirar 5 productos en total
// Entre cada retirada, el hilo debe simular una espera de 2 segundos

// Clase Main:
// Debe crear una única instancia del Almacen
// Debe lanzar un hilo Productor y un hilo Consumidor compartiendo dicho almacén
// El programa debe finalizar correctamente cuando ambos hilos hayan terminado sus 5 ciclos
// Objetivo del examen: El alumno debe demostrar el uso correcto de los mecanismos de monitorización de Java (synchronized, wait() y notify() o notifyAll()) para evitar condiciones de carrera y asegurar que el Productor nunca coloque un producto si el anterior no ha sido retirado, y que el Consumidor nunca intente retirar si el almacén está vacío

package com.example.prometeo.psp.unidad_03.ejercicios;

public class Ejercicio_10 {
    public static void main(String[] args) {
        Almacen almacen = new Almacen();

        Thread hiloProductor = new Thread(new Productor(almacen));
        Thread hiloConsumidor = new Thread(new Consumidor(almacen));

        hiloProductor.start();
        hiloConsumidor.start();

        try {
            hiloProductor.join();
            hiloConsumidor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("El programa ha terminado correctamente");
    }
}

class Almacen {
    private boolean almacenLleno = false; // Estado inicial: vacío

    public synchronized void almacenar(int idProducto) {
        // Usar un while para re-comprobar la condición tras un wait()
        while (almacenLleno) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Realizamos la acción y cambiar de estado
        almacenLleno = true;
        System.out.println("-> [PRODUCTOR]: Producto " + idProducto + " almacenado con éxito");

        // Notificamos a los hilos que esperan
        notifyAll();
    }

    public synchronized void retirar() {
        // Esperar mientras esté vacío
        while (!almacenLleno) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Cambiamos de estado
        almacenLleno = false;
        System.out.println("<- [CONSUMIDOR]: Producto retirado");

        notifyAll();
    }
}

class Productor implements Runnable {
    private Almacen almacen;

    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            almacen.almacenar(i + 1);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumidor implements Runnable {
    Almacen almacen;

    public Consumidor(Almacen almacen) {
        this.almacen = almacen;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            almacen.retirar();

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
