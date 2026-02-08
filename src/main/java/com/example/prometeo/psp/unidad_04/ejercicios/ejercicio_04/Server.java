// Crea un servidor que acepte una cadena del cliente con el formato operacion:numero1:numero2 (por ejemplo, SUMA:15:30 o MULT:4:5)
// El Servidor: Debe separar los valores, realizar el cálculo y devolver solo el resultado numérico

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_04;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor escuchando en el puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Ciente conectado: " + cliente.getInetAddress().getHostName());

                ClientHandler manejadorDeClientes = new ClientHandler(cliente);

                new Thread(manejadorDeClientes).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
