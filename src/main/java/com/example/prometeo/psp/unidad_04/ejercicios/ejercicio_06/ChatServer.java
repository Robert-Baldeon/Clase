// Objetivo: Crear un sistema de chat donde múltiples usuarios puedan conectarse simultáneamente
// Cuando un usuario envía un mensaje, el servidor debe reenviarlo a todos los demás clientes conectados

// Especificaciones del Servidor (ChatServer.java)
// Gestión de Clientes: El servidor debe mantener una lista global y estática de todos los hilos (ChatHandler) o de sus flujos de salida (PrintWriter)
// Concurrencia Segura: La lista debe ser de un tipo que soporte acceso de varios hilos a la vez (por ejemplo, usando Collections.synchronizedList)
// Método Broadcast: Debe incluir un método llamado enviarATodos(String mensaje, ChatHandler remitente) que recorra la lista y entregue el mensaje a cada cliente (excepto, opcionalmente, al que lo envió)

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_06;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Su única función es aceptar gente y tener un método para que los hilos se comuniquen entre sí
public class ChatServer {
    private static final int PUERTO = 1234;

    // LISTA SINCRONIZADA
    // Como muchos hilos (clientes) entrarán y saldrán a la vez
    // Collections.synchronizedList evita que la lista se corrompa
    private static List<ChatHandler> clientes = Collections.synchronizedList(new ArrayList<>());

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("========================================");
            System.out.println("  SERVIDOR DE CHAT BROADCAST            ");
            System.out.println("  Escuchando en puerto: " + PUERTO);
            System.out.println("========================================");

            while (true) {
                // El hilo principal se bloquea aquí esperando nuevos clientes 
                Socket cliente = servidor.accept();
                System.out.println("[+] Nuevo cliente desde: " + cliente.getInetAddress().getHostName());

                // Por cada cliente, creamos un objeto manejador y lo lanzamos en su propio hilo
                new Thread(new ChatHandler(cliente)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método estático para que los hilos individuales puedan registrarse al nacer/morir
    public static void agregarCliente(ChatHandler cliente) {
        clientes.add(cliente);
    }

    public static void eliminarCliente(ChatHandler cliente) {
        clientes.remove(cliente);
    }

    // BROADCAST: Este método recorre la lista de todos los hilos activos
    public static void enviarATodos(String mensaje, ChatHandler remitente) {
        synchronized (clientes) { // Bloqueamos la lista mientras la recorremos por seguridad
            for (ChatHandler cliente : clientes) {
                // Enviamos el mensaje a todos MENOS al que lo escribió originalmente
                if (cliente != remitente) {
                    cliente.enviarMensaje(mensaje);
                }
            }
        }
    }
}
