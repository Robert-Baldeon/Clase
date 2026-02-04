// Objetivo: Escribir un servidor y cliente TCP “eco”.
// Servidor: ServerSocket (p. ej., 5000), bucle infinito (accept()), lee una línea, la imprime y la devuelve, cierra el socket del cliente.

// Toma el código del servidor de eco.
// Crea ClientHandler implements Runnable → mueve la lógica de E/S al run().
// En el bucle while(true) del servidor principal, tras accept(), crea un ClientHandler con el socket, luego un Thread y start().
// Conecta varios clientes a la vez: ahora el servidor debe atenderlos simultáneamente.

package com.example.prometeo.psp.unidad_04.ejercicios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEco {
    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto: " + puerto);

            while (true) {
                // Se queda pausado aquí hasta que un cliente se conecta
                try (Socket cliente = servidor.accept(); BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream())); PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true); ) {
                    while (true) {
                        // Leemos lo que envía el cliente
                        String mensaje = entrada.readLine();
                        if (mensaje != null) {
                            System.out.println("Cliente dice: " + mensaje);
                            // Lo devolvemos desde el servidor (eco)
                            salida.println("ECO: " + mensaje);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
