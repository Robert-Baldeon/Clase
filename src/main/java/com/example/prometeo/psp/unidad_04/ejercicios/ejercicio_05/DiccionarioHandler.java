// Objetivo: Desarrollar un sistema Cliente/Servidor TCP donde el servidor actúe como un repositorio de información (diccionario) y los clientes puedan realizar consultas simultáneas

// Especificaciones del Manejador (DiccionarioHandler.java)
// Protocolo de Comunicación:
// Recibe una palabra del cliente (ej: "TCP")
// Limpia la cadena (elimina espacios y la pasa a mayúsculas)
// Busca la palabra en el HashMap del servidor
// Respuesta: Si la palabra existe, devuelve su definición. Si no existe, debe devolver un mensaje tipo: "Error: El término no se encuentra en el diccionario"
// Si el cliente envía "SALIR", el hilo debe cerrar la conexión de forma ordenada.

package com.example.prometeo.psp.unidad_04.ejercicios.ejercicio_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Esta clase se encarga de gestionar la conversación con un cliente específico.
 * Al implementar Runnable, permite que el servidor principal lance cada consulta en un hilo nuevo.
 */
public class DiccionarioHandler implements Runnable {
    private final Socket socket; // Socket único para este cliente

    // El constructor recibe el socket aceptado por el servidor
    public DiccionarioHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        /**
         * Try-with-resources: Garantiza que los flujos de entrada y salida se cierren
         * automáticamente al terminar el bloque, incluso si ocurre una excepción.
         */
        try (
                // entrada: Lee lo que el cliente escribe
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                // salida: Envía texto al cliente. El parámetro 'true' activa el autoflush.
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                ) {
            String mensaje;

            /**
             * Bucle de comunicación: Se mantiene activo mientras el cliente envíe líneas de texto.
             * readLine() devuelve null si el cliente cierra la conexión de forma abrupta.
             */
            while ((mensaje = entrada.readLine()) != null) {
                // Normalizamos la entrada: quitamos espacios y pasamos a mayúsculas
                mensaje = mensaje.toUpperCase().trim();

                // Condición de salida pactada en el protocolo
                if ("SALIR".equals(mensaje)) {
                    salida.println("Desconectando del servidor de diccionario...");
                    break;
                }

                /**
                 * CLAVE: Acceso al recurso compartido.
                 * Consultamos el mapa estático definido en la clase ServidorDiccionario.
                 * getOrDefault() es más eficiente: si la clave no existe, devuelve el texto de error.
                 */
                String respuesta = ServidorDiccionario.diccionario.getOrDefault(mensaje, "Error: El término '" + mensaje + "' no se encuentra en el diccionario");

                // Enviamos la definición (o el error) de vuelta al cliente
                salida.println(respuesta);
            }
        } catch (IOException e) {
            // Este catch captura errores de lectura/escritura durante la sesión
            System.err.println("Error en la sesión con el cliente: " + e.getMessage());
        } finally {
            /**
             * El bloque finally se ejecuta SIEMPRE.
             * Es nuestra red de seguridad para no dejar sockets "zombis" abiertos.
             */
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
                System.out.println("Conexión cerrada con el cliente.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
