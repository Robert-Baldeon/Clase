// Nivel 1: Fácil - El Método de Pago (Bloqueo de objeto)
// Objetivo: Aplicar synchronized en un contexto de recurso compartido simple.
// Enunciado: Crea una clase CuentaBancaria con un saldo inicial de 1000€. Crea un método retirarDinero(int cantidad).
// Tarea: Crea dos hilos ("Cajero 1" y "Cajero 2") que intenten retirar 600€ cada uno al mismo tiempo.
// Desafío: Sin sincronización, ambos hilos verán que hay saldo suficiente y dejarán la cuenta en números negativos (-200€). Usa synchronized para que el segundo hilo vea que ya no queda saldo suficiente y lance un mensaje de error.

package com.example.prometeo.psp.unidad_03.ejercicios;

class CuentaBancaria {
    private int saldo;

    public CuentaBancaria() {
        saldo = 1000;
    }

    public synchronized void retirarDinero(int cantidad) {
        String nombreHilo = Thread.currentThread().getName();

        System.out.println(nombreHilo + " intenta retirar " + cantidad + "€. Saldo actual: " + saldo + "€");

        if (saldo >= cantidad) {
            saldo -= cantidad;
            System.out.println(nombreHilo + " ha retirado el dinero con éxito. Nuevo saldo: " + saldo + "€");
        } else {
            System.err.println("Error: " + nombreHilo + " no puede retirar. Saldo insuficiente( " + saldo + "€)");
        }
    }

    public int getSaldo() { return saldo; }
    public void setSaldo(int saldo) { this.saldo = saldo; }
}


class Hilo implements Runnable {
    private CuentaBancaria cuentaBancaria;

    public Hilo(CuentaBancaria cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public void run() {
        cuentaBancaria.retirarDinero(600);
    }
}

public class Ejercicio_03 {
    public static void main(String[] args) {
        CuentaBancaria cuentaBancaria = new CuentaBancaria();

        Hilo hilo = new Hilo(cuentaBancaria);

        Thread hiloA = new Thread(hilo, "Cajero-1");
        Thread hiloB = new Thread(hilo, "Cajero-2");

        hiloA.start();
        hiloB.start();

        try {
            hiloA.join();
            hiloB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Cuenta bancaria: " + cuentaBancaria.getSaldo());
    }
}
