// Crea un programa en Java que realice las siguientes tareas y las muestre por consola:
// Mi propia máquina: Obtén la dirección IP y el nombre de tu ordenador actual (LocalHost)
// Servidor remoto por nombre: Obtén la dirección IP del servidor de Google (www.google.com)
// Servidor remoto por IP: A partir de una dirección IP conocida (por ejemplo, la de los DNS de Google: 8.8.8.8), obtén cuál es su nombre de host asociado
// Múltiples direcciones: Google tiene muchas IPs. Usa el método adecuado para obtener todas las direcciones IP asociadas a www.google.com e imprímelas en un bucle

package com.example.prometeo.psp.unidad_04.ejercicios;

import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class Ejercicio_01 {
    public static void main(String[] args) {
        try {
            // --- 1. MI PROPIA MÁQUINA ---
            // getLocalHost() obtiene la IP configurada por defecto en el sistema
            InetAddress localhost = InetAddress.getLocalHost();

            System.out.println("=== MI EQUIPO ==");
            System.out.println("Nombre del host: " + localhost.getHostName()); // Muestra el nombre del PC
            System.out.println("Dirección IP: " + localhost.getHostAddress()); // Muestra la IP (ej: 192.168.1.15)
            System.out.println();

            // --- 2. SERVIDOR REMOTO POR NOMBRE ---
            // getByName() traduce un nombre de dominio (URL) a una dirección IP usando DNS
            InetAddress google = InetAddress.getByName("www.google.com");
            System.out.println("=== SERVIDOR REMOTO (Google) ===");
            System.out.println("Nombre: " + google.getHostName());
            System.out.println("IP: " + google.getHostAddress());
            System.out.println();

            // --- 3. SERVIDOR REMOTO POR IP (RESOLUCIÓN INVERSA) ---
            // Si pasamos una IP a getByName, el método getHostName() intentará buscar su nombre real
            InetAddress ipDNS = InetAddress.getByName("8.8.8.8");
            System.out.println("=== RESOLUCIÓN INVERSA ===");
            System.out.println("La IP 8.8.8.8 pertencece a: " + ipDNS.getHostName());
            System.out.println();

            // --- 4. MÚLTIPLES DIRECCIONES ---
            // getAllByName() devuelve un array porque sitios grandes tienen varias IPs para no saturarse
            InetAddress[] todasLasIps = InetAddress.getAllByName("www.google.com");
            System.out.println("=== TODAS LA IPS DE GOOGLE ===");
            for (int i = 0; i < todasLasIps.length; i++) {
                System.out.println("IP # " + (i + 1) + ": " + todasLasIps[i].getHostAddress());
            }
        } catch (UnknownHostException e) {
            System.err.println("Error: No se pudo encontrar el host solicitado");
            e.printStackTrace();
        }
    }

    // Este método utiza la biblioteca Java Net para realizar una conexión UDP
    // Aquí, para simplificar, utilizamos el DNS principal de Google como nuestro host de destino y proporcionamos la dirección IP 8.8.8.8
    // La biblioteca Java Net sólo comprueba la validez del formato de dirección en este momento, por lo que la dirección en sí puede ser inalcanzable
    // Además, estamos utilizando un puerto aleatorio 12345 para crear una conexión UDP con el método socket.connect()
    // En el fondo, configura todas las variables necesarias para enviar y recibir datos, incluida la dirección local de la máquina, sin enviar ninguna solicitud al host de destino
    // Si bien esta solución funciona muy bien en máquinas Linux y Windows, es problemática en macOS y no devuelve la dirección IP esperada.
    public static String getMyIp_01() throws Exception {
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 1234);
            return datagramSocket.getLocalAddress().getHostAddress().toString();
        }
    }

    // Alternativamente, podemos utilizar una conexión de socket a través de una conexión a Internet confiable para buscar la dirección IP:
    // Aquí, nuevamente por simplicidad, usamos google.com con una conexión en el puerto 80 para obtener la dirección del host
    // Podríamos utilizar cualquier otra URL para crear una conexión de socket siempre que sea accesible.
    public static String getMyIp_02() throws Exception {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("google.com", 80));
            return socket.getLocalAddress().getHostAddress().toString();
        }
    }

    // Además, podemos obtener nuestra dirección local usando el método getLocalHost():
    // En este código, invocamos el método getLocalHost() en Inet4Address para recuperar la dirección IPv4 de nuestra máquina local y luego usamos el método getHostAddress() para recuperar su dirección IP textual
    // En particular, getLocalHost() podría resolverse en una dirección de bucle invertido y devolver 127.0.0.1 en el caso de que el DNS del sistema esté mal configurado.
    public static String getMyIp_03() throws Exception {
        return Inet4Address.getLocalHost().getHostAddress().toString();
    }

    // En caso de que una máquina tenga varias direcciones IP, podemos usar la clase NetworkInterface para enumerar todas las interfaces de red disponibles y sus direcciones asociadas:
    // Aquí, recuperamos todas las interfaces de red disponibles en nuestra máquina e iteramos sobre las direcciones IP asociadas con cada una
    // Luego, devolvemos la lista de direcciones
    // Además, filtramos las interfaces inactivas y de bucle invertido antes de examinar sus direcciones
    public static List<String> getMyIps() {
        List<String> ipAddress = new ArrayList<>();
        Enumeration<NetworkInterface> networkInterfaceEnumeration = null;

        try {
            networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

        for (; networkInterfaceEnumeration.hasMoreElements(); ) {
            NetworkInterface networkInterface = networkInterfaceEnumeration.nextElement();

            try {
                if (!networkInterface.isUp() || networkInterface.isLoopback()) {
                    continue;
                }
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }

            Enumeration<InetAddress> address = networkInterface.getInetAddresses();
            for (; address.hasMoreElements(); ) {
                InetAddress addr = address.nextElement();
                ipAddress.add(addr.getHostAddress());
            }
        }

        return ipAddress;
    }
}
