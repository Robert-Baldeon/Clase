package com.example.prometeo.psp.unidad_04.ejemplos.monohilo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        // Creamos un server socket en el puerto 9090
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Server is running and waiting for client connection...");

        // Aceptar conexiones de cliente entrante
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected!");

        // Configuramos flujos de entrada y salida para la comunicación con el cliente
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);

        // Leemos el mensaje del cliente
        String message = bufferedReader.readLine();
        printWriter.println("Client says: " + message);

        // Enviamos respuesta al cliente
        printWriter.println("Message received by the server.");

        // Close the client socket
        clientSocket.close();

        // Close the server socket
        serverSocket.close();
    }
}
