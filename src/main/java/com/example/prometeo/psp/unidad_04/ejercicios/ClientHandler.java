// Toma el código del servidor de eco.
// Crea ClientHandler implements Runnable → mueve la lógica de E/S al run().
// En el bucle while(true) del servidor principal, tras accept(), crea un ClientHandler con el socket, luego un Thread y start().
// Conecta varios clientes a la vez: ahora el servidor debe atenderlos simultáneamente.

package com.example.prometeo.psp.unidad_04.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Esta clase contiene la lógica de entrada/salida (E/S) que he movido desde el servidor original
// Cada cliente tendrá su propia instancia de esta clase corriendo en un hilo distinto
// Implementamos Runnable para que pueda ejecutarse en un hilo aparte
public class ClientHandler implements Runnable {
    private Socket socket; // Socket específico para un cliente concreto

    // El constructor recibe el socke que el servidor principal aceptó
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        // Creamos los flujos de lectura y escritura
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            ) {

            String mensaje;
            // Bucle de lectura: mientras el cliente envíe datos..
            // Bucle de comunicación: se mantiene mientras el cliente no cierre la conexión
            while ((mensaje = entrada.readLine()) != null) {
                System.out.println("Recibido de " + socket.getInetAddress() + ": " + mensaje);

                // Enviamos el ECO de vuelta al cliente
                salida.println("ECO multihilo: " + mensaje);

                if ("salir".equalsIgnoreCase(mensaje)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                System.out.println("Conexión cerrada con el cliente");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
