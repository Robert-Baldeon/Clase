// Objetivo: Desarrollar un sistema Cliente/Servidor TCP donde el servidor actúe como un repositorio de información (diccionario) y los clientes puedan realizar consultas simultáneas

// 1. Especificaciones del Servidor (ServidorDiccionario.java)
// Base de Datos Lógica: El servidor debe contener una estructura de datos estática (preferiblemente un HashMap<String, String>) que almacene términos técnicos y sus definiciones
// Ejemplo de datos: TCP, UDP, SOCKET, IP, DNS
// Gestión de Conexiones: Debe escuchar en el puerto 5000 y ser multihilo. Por cada cliente que se conecte, debe lanzar un hilo DiccionarioHandler
// Persistencia: La lista de palabras debe estar disponible para todos los clientes por igual

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServidorDiccionario {
    private static final int PUERTO = 5000;
    // ConcurrentHashMap para seguridad total entre hilos
    // Se usar en lugar de HashMap porque es "Thread-Safe"
    // Permite que múltiples hilos (clientes) lean datos simultáneamente sin riesgo de corrupción
    public static final Map<String, String> diccionario = new ConcurrentHashMap<>();

    // El bloque static solo se ejecuta una sola vez cuando la clase se carga en memoria
    static {
        inicializarDatos();
    }

    private static void inicializarDatos() {
        // Definiciones de Protocolos
        diccionario.put("TCP", "Transmission Control Protocol: Protocolo orientado a conexión que garantiza que los datos lleguen sin errores.");
        diccionario.put("UDP", "User Datagram Protocol: Protocolo no orientado a conexión, más rápido que TCP pero no garantiza la llegada.");
        diccionario.put("IP", "Internet Protocol: Protocolo encargado del direccionamiento y envío de paquetes en la red.");
        diccionario.put("HTTP", "Hypertext Transfer Protocol: Protocolo de transferencia de hipertexto usado para la navegación web.");

        // Conceptos de Red
        diccionario.put("SOCKET", "Punto final (IP + Puerto) de un enlace de comunicación bidireccional entre dos programas.");
        diccionario.put("DNS", "Domain Name System: Traduce nombres de dominio (como google.com) en direcciones IP.");
        diccionario.put("PUERTO", "Interfaz de software que permite a un ordenador diferenciar entre distintas aplicaciones de red.");
        diccionario.put("LOCALHOST", "Dirección IP especial (127.0.0.1) que apunta al propio ordenador en el que se ejecuta el código.");

        // Conceptos de Programación (PSP)
        diccionario.put("THREAD", "Hilo: Flujo de ejecución independiente dentro de un proceso.");
        diccionario.put("RUNNABLE", "Interfaz en Java que define el método run() para que una clase pueda ser ejecutada por un hilo.");
    }

    public static void main(String[] args) {
        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("========================================");
            System.out.println("  SERVIDOR DE DICCIONARIO MULTIHILO     ");
            System.out.println("  Escuchando en puerto: " + PUERTO);
            System.out.println("========================================");

            // El bucle se mantiene mientras el hilo principal no sea interrumpido
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    // El servidor se queda bloqueado en accept() hasta que llega un cliente
                    Socket cliente = servidor.accept();
                    System.out.println("[+] Nuevo cliente desde: " + cliente.getInetAddress().getHostName());

                    // MULTIHILO: No procesamos la consulta aquí
                    // Delegamos el socker a un DiccionarioHandler y lo lanzamos en un nuevo hilo
                    // Esto permite que el servidor vuelva al accept() de inmediato
                    new Thread(new DiccionarioHandler(cliente)).start();
                } catch (IOException e) {
                    // Si falla un cliente específico el servidor no debe morir
                    System.err.println("[-] Error al aceptar el cliente: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("[-] Error crítico: No se pudo abrir el puerto " + PUERTO);
            e.printStackTrace();
        }
    }
}
