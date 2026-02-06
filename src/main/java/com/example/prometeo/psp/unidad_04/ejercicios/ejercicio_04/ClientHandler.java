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

            while ((mensaje = entrada.readLine().toUpperCase()) != null) {
                System.out.println("Mensaje: " + mensaje);

                if ("SALIR".equals(mensaje)) {
                    break;
                }

                String strings[] = mensaje.split(":");

                salida.println(
                        switch (strings[0]) {
                            case "SUMA", "+" -> Integer.parseInt(strings[1]) + Integer.parseInt(strings[2]);
                            case "RESTA", "-" -> Integer.parseInt(strings[1]) - Integer.parseInt(strings[2]);
                            case "MULTIPLICACION", "MULTIPLICACIÓN", "*" -> Integer.parseInt(strings[1]) * Integer.parseInt(strings[2]);
                            case "DIVISION", "DIVISIÓN", "/" -> (Integer.parseInt(strings[2]) < 0) ? Integer.parseInt(strings[1]) - Integer.parseInt(strings[2]) : "El segundo número es negativo";
                                                                                                                                                                   default -> "Argumento inválido";
                        });
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
