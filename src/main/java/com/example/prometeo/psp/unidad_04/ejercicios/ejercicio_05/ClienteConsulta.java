// Objetivo: Desarrollar un sistema Cliente/Servidor TCP donde el servidor actúe como un repositorio de información (diccionario) y los clientes puedan realizar consultas simultáneas

// Especificaciones del Cliente (ClienteConsulta.java)
// Interfaz de Usuario: Debe pedir al usuario por teclado qué término desea consultar
// Bucle de Consulta: El cliente no debe cerrarse tras una consulta; debe permitir al usuario seguir preguntando términos hasta que este escriba "SALIR"
// Gestión de Respuesta: Debe imprimir por pantalla la definición recibida del servidor de forma clara

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteConsulta {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(HOST, PUERTO);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                Scanner teclado = new Scanner(System.in);
                ) {
            while (true) {
                System.out.print("¿Qué término quieres consultar?: ");
                String termino = teclado.nextLine().toUpperCase().trim();

                if ("SALIR".equals(termino)) {
                    System.out.println("Cerrando la conexión. ¡Hasta pronto!");
                    break;
                }

                salida.println(termino);

                String definicion = entrada.readLine();

                if (definicion == null) {
                    System.out.println("El servidor ha cortado la conexión");
                    break;
                }

                System.out.println("Definición: " + definicion);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
