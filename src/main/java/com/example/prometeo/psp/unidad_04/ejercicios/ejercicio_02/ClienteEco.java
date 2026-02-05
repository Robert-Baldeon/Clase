// Objetivo: Escribir un servidor y cliente TCP “eco”.
// Cliente: Socket a localhost:5000, lee del teclado, envía, y muestra la respuesta.

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEco {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try (
                Socket socket = new Socket(host, puerto);
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                Scanner teclado = new Scanner(System.in);
            ) {

            while (true) {
                System.out.print("Escribe un mensaje para el servidor: ");
                String mensaje = teclado.nextLine();

                // Enviar al servidor
                salida.println(mensaje);

                if ("salir".equalsIgnoreCase(mensaje)) {
                    System.out.println("Cerrando cliente...");
                    break;
                }

                // Leer respuesta
                String respuesta = entrada.readLine();

                if (respuesta == null) {
                    System.out.println("El servidor ha cortado la conexión");
                    break;
                }

                System.out.println("Respuesta del servidor: " + respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
