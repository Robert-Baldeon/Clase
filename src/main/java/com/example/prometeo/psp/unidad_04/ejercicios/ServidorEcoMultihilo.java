package com.example.prometeo.psp.unidad_04.ejercicios;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// Este programa es el punto de entrada. Su única misión es escuchar en el puerto y delegar el trabajo rápido para no hacer esperar al siguiente cliente
public class ServidorEcoMultihilo {
    public static void main(String[] args) {
        int puerto = 5000;

        // Abrimos el ServerSocket dentro de un try-with-resources para asegurar el cierre del puerto
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor MULTIHILO iniciado en el puerto: " + puerto);

            // Bucle infinito: el servidor nunca deja de escuchar nuevas peticiones
            while (true) {
                // El método accept() detiene la ejecución hasta que llega un cliente (bloqueante)
                Socket cliente = servidor.accept();
                System.out.println("Nuevo cliente conectado desde: " + cliente.getInetAddress());

                // --- CLAVE DEL MULTIHILO ---
                // No procesamos al cliente aquí mismo. Creamos un "manejador" y le pasamos el socket
                ClientHandler manejador = new ClientHandler(cliente);

                // Creamos un hilo de ejecución independiente para este cliente
                Thread hilo = new Thread(manejador);

                // Iniciamos el hilo (esto ejecuta el método run() de ClientHandler)
                // Al hacerlo así, el bucle while vuelve arriba inmediatamente para aceptar otro cliente
                hilo.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
