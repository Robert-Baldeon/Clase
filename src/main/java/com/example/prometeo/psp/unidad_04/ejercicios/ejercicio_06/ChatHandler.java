// Objetivo: Crear un sistema de chat donde múltiples usuarios puedan conectarse simultáneamente
// Cuando un usuario envía un mensaje, el servidor debe reenviarlo a todos los demás clientes conectados

// Especificaciones del Manejador (ChatHandler.java)
// Registro: Al iniciar el método run(), el manejador debe añadirse a sí mismo a la lista global del servidor
// Escucha Activa: Debe esperar mensajes del cliente. Al recibir uno, llamará al método del servidor para repartirlo a los demás
// Salida Limpia: En el bloque finally, debe eliminarse obligatoriamente de la lista global para evitar errores al intentar enviar mensajes a un socket que ya se ha cerrado

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

// Este es el código que ejecuta cada hilo por separado
// Es el "puente" entre cliente y servidor
public class ChatHandler implements Runnable {
    private final Socket socket;
    private PrintWriter salida; // Guardamos el flujo de salida como variable de instancia

    public ChatHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ) {
            // Inicializamos el escritor con autoflush (true) para que el mensaje se envíe al instante
            this.salida = new PrintWriter(socket.getOutputStream(), true);

            // 1. REGISTRO: Nos añadimos a la lista del servidor nada más conectar
            ChatServer.agregarCliente(this);
            ChatServer.enviarATodos("Un nuevo usuario se ha unido al chat", this);

            String mensaje;
            // 2. BUCLE DE ESCUCHA: El hilo se queda aquí esperando mensajes de SU cliente
            while ((mensaje = entrada.readLine()) != null) {
                if (mensaje.equalsIgnoreCase("SALIR")) {
                    break;
                }

                // 3. DIFUSIÓN: Cuando recibe algo, le pide al servidor que lo reparta a los demás
                System.out.println("Retrasmitiendo: " + mensaje);
                ChatServer.enviarATodos(mensaje, this);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 4. LIMPIEZA: Al salir (por error o por 'SALIR', nos borramos de la lista)
            // Si no hiciéramos esto, el servidor intentaría escribir en un socket cerrado
            ChatServer.eliminarCliente(this);
            ChatServer.enviarATodos("Un usuario ha abandonado el chat", this);
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Este método lo llama el servidor desde fuera para mandarnos mensajes de otros clientes
    public void enviarMensaje(String mensaje) {
        if (salida != null) {
            salida.println(mensaje);
        }
    }
}
