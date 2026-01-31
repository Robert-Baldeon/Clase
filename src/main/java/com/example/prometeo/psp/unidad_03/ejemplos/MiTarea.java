package com.example.prometeo.psp.unidad_03.ejemplos;

public class MiTarea implements Runnable {
    @Override
    public void run() {
        System.out.println("Ejecutando en hilo: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MiTarea(), "Hilo-Tarea");
        thread.start();
    }
}
