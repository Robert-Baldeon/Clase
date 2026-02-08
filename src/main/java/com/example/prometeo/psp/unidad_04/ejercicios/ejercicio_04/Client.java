// Crea un servidor que acepte una cadena del cliente con el formato operacion:numero1:numero2 (por ejemplo, SUMA:15:30 o MULT:4:5)
// Cliente: Debe permitir al usuario introducir la operación y los números, y mostrar el resultado final

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String HOST = "localhost";
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (
                Socket cliente = new Socket(HOST, PUERTO);
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
                Scanner teclado = new Scanner(System.in);
            ) {

            while (true) {
                System.out.println("Introduce la cadena con el siguiente formato: (operación:numero1:numero1)");
                System.out.print("Introduce la cadena: ");
                String mensaje = teclado.nextLine();

                if ("salir".equalsIgnoreCase(mensaje)) {
                    break;
                }

                salida.println(mensaje);

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
