package com.example.prometeo.psp.unidad_03.ejemplos;

public class MiHilo extends Thread {
    @Override
    public void run() {
        System.out.println("Ejecutando en hilo: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        new MiHilo().start();
    }
}
