package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter salida = new PrintWriter(clientSocket.getOutputStream(), true);
            ) {

            String mensaje;

            while ((mensaje = entrada.readLine()) != null) {
                mensaje = mensaje.toUpperCase();
                System.out.println("Mensaje: " + mensaje);

                if ("SALIR".equals(mensaje)) {
                    break;
                }

                try {
                    String strings[] = mensaje.split(":");

                    int n1 = Integer.parseInt(strings[1]);
                    int n2 = Integer.parseInt(strings[2]);

                    salida.println(
                            switch (strings[0]) {
                                case "SUMA", "+" -> n1 + n2;
                                case "RESTA", "-" -> n1 - n2;
                                case "MULTIPLICACION", "MULTIPLICACIÓN", "*" -> n1 * n2;
                                case "DIVISION", "DIVISIÓN", "/" -> (n2 != 0)  ? (n1 / n2) : "Error: División por cero";
                                default -> "Argumento inválido";
                            });
                } catch (Exception e) {
                    salida.println("Operación no soportada");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
                System.out.println("Conexión cerrada con el cliente");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
