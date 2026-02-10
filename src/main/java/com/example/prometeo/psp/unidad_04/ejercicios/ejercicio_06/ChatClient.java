// Objetivo: Crear un sistema de chat donde múltiples usuarios puedan conectarse simultáneamente
// Cuando un usuario envía un mensaje, el servidor debe reenviarlo a todos los demás clientes conectados

// Especificaciones del Cliente (ChatClient.java)
// Hasta ahora, tus clientes hacían: Enviar -> Esperar respuesta -> Leer. En un chat, esto no sirve porque alguien puede escribirte mientras tú estás pensando qué poner
// Multihilo en el Cliente: El cliente debe lanzar un hilo secundario que esté en un bucle infinito haciendo entrada.readLine()
// Hilo Principal: Se encargará únicamente de leer del teclado y enviar al servidor
// Resultado: El usuario podrá recibir mensajes en cualquier momento, independientemente de si está escribiendo o no

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// Este es especial porque usa dos hilos en la misma aplicación: uno para enviar y otro para recibir
public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PUERTO = 1234;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PUERTO)) {
            System.out.println("Conectado al chat. Escribe 'SALIR' para abandonar");

            // HILO DE LECTURA: Se encarga de estar siempre escuchando al servidor
            // Se usa un hilo aparte porque readLine() es bloqueante y no nos dejaría escribir
            Thread hiloLectura = new Thread(() -> {
                try (BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    String mensaje;

                    // Cuando llega un mensaje de otro lo imprimimos
                    while ((mensaje = entrada.readLine()) != null) {
                        System.out.println("\n[Mensaje]: " + mensaje);
                        System.out.print("> "); // Volvemos a poner el cursor para escribir
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            hiloLectura.start();

            // HILO PRINCIPAL (escritura): Se encarga de leer del teclado y enviar al servidor
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner teclado = new Scanner(System.in);
            String texto;

            while (true) {
                System.out.print("> ");
                texto = teclado.nextLine();
                salida.println(texto);

                if (texto.equalsIgnoreCase("SALIR")) {
                    teclado.close();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
