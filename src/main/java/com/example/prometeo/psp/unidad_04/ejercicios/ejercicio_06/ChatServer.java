// Objetivo: Crear un sistema de chat donde múltiples usuarios puedan conectarse simultáneamente
// Cuando un usuario envía un mensaje, el servidor debe reenviarlo a todos los demás clientes conectados

// Especificaciones del Servidor (ChatServer.java)
// Gestión de Clientes: El servidor debe mantener una lista global y estática de todos los hilos (ChatHandler) o de sus flujos de salida (PrintWriter)
// Concurrencia Segura: La lista debe ser de un tipo que soporte acceso de varios hilos a la vez (por ejemplo, usando Collections.synchronizedList)
// Método Broadcast: Debe incluir un método llamado enviarATodos(String mensaje, ChatHandler remitente) que recorra la lista y entregue el mensaje a cada cliente (excepto, opcionalmente, al que lo envió)

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_06;

public class ChatServer {
    public static void main(String[] args) {
    }
}
