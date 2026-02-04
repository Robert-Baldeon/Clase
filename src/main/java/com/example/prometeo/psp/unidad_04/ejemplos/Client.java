package com.example.prometeo.psp.unidad_04.ejemplos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // Creamos un socket para conectarse al servidor que se ejecuta en localhost en el puerto 9090
        Socket socket = new Socket("localhost", 9090);

        // Configuramos el flujo de salida para enviar datos al servidor
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

        // Configuramos el flujo de entrada para recibir datos desde el servidor
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        // Enviamos un mensaje al servidor
        printWriter.println("Hello from client!");

        // Recivimos desde el servidor
        String response = bufferedReader.readLine();
        System.out.println("Server says: " + response);

        socket.close();
    }
}
