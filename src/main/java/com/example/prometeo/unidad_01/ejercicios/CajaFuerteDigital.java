package com.example.prometeo.unidad_01.ejercicios;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CajaFuerteDigital {
    // Constantes
    private static final String ALGORITMO = "AES";
    private static final String ALGORITMO_HASH = "SHA-256";
    private static final int KEY_SIZE = 256;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String masterPassword = "pinguino";
            String hashMaterPassword = toHexString_01(generarHash(masterPassword));

            System.out.print("Introduce la contraseña de la caja fuerte: ");
            String inputPassword = scanner.nextLine();

            String hashInputPassword = toHexString_02(generarHash(inputPassword));

            if (hashMaterPassword.equals(hashInputPassword)) {
                System.out.println("\nAcceso concedido");

                SecretKey llaveMaestra = generarLlaveAES(ALGORITMO, KEY_SIZE);

                String mensajeSecreto = "Coordenadas del tesoro: 40ºN 3ºW";

                String mensajeCifrado = cifrar(ALGORITMO, mensajeSecreto, llaveMaestra);
                System.out.println("Mensaje cifrado (Base64): " + mensajeCifrado);

                String mensajeDescifrado = descifrar(ALGORITMO, mensajeCifrado, llaveMaestra);
                System.out.println("Mensaje descifrado: " + mensajeDescifrado);
            } else {
                System.out.println("\nAcceso denegado");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método que calcula el hash SHA-256 de un String
    private static byte[]  generarHash(String string) throws NoSuchAlgorithmException {
        // Crea un objeto MessageDigest para SHA-256
        MessageDigest messageDigest = MessageDigest.getInstance(ALGORITMO_HASH);

        // Convierte el String a bytes usando la codificación por defecto (UTF-8)
        // Y calcula el hash SHA-256 devolviendo un array de 32 bytes
        // Es decir string.getBytes().length == 32
        return messageDigest.digest(string.getBytes());
    }

    // Método que convierte un array de bytes en un String hexadecimal
    private static String toHexString_01(byte[] hash) {
        // Convierte el array de bytes a un BigInteger positivo
        // Cada byte es interpretado como un "dígito base 256"
        BigInteger number = new BigInteger(1, hash); // 1 significa que el número que almacenará la variable number será positivo

        // Convierte el BigInteger a hexadecimal y lo guarda en un StringBuilder
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Rellena con 0 a la izquireda si la longitud es menor a 64, para asegurar que los 32 bytes se representen con exactamente 64 dígitos hexadecimales
        while (hexString.length() < 64) {
            // Insertamos en la posición 0 un 0
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    private static String toHexString_02(byte[] hash) {
        // Crea un StringBuilder con capacidad inicial de hash.length * 2
        StringBuilder hexString = new StringBuilder(hash.length * 2);

        // Recorre todos los bytes del array
        for (int i = 0; i < hash.length; i++) {
            // Convierte el byte a un int sin signo (0-255)
            // 0xff & hash[i] evita que los bytes negativos se interpreten como negativos en java
            // Ejemplo: -26 & 0xff = 230, con esto evitamos que aparezca como -26
            // Esto actua como una máscara
            /* En java, los bytes son con signo (-128 a 127)
             * 1 byte = 8 bits
             * -26 en binario es (8 bits) -> 11100110
             * Hacer 0xff & b
             * 0xff en binario -> 11111111 (255 en decimal)
             * Hacer AND (&) con b
             *    11100110 (-26)
             *  & 11111111 (0xff)
             *  -----------
             *    11100110 -> Con esto java lo interpreta como 230 en decima
             *  
             *  Es quivalente a -26 + 256 = 230
             */

            // Convierte cualquier byte (signed) a su valor unsigned (0-255)
            // = si byte >= 0 → igual
            // = si byte < 0  → byte + 256
            String hex = Integer.toHexString(0xff & hash[i]);

            // Si la longitud es 1, añade un '0' al principio
            // Esto asegura que cada byte se represente con 2 caracteres hexadecimales
            if (hex.length() == 1) {
                hexString.append('0');
            }

            // Añade los dígitos hexadecimales correspondientes a este byte
            hexString.append(hex);
        }

        return hexString.toString();
    }

    private static SecretKey generarLlaveAES(String algoritmo, int size) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algoritmo);
        keyGenerator.init(size);
        return keyGenerator.generateKey();
    }

    private static String cifrar(String algoritmo, String mensaje, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(algoritmo);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] cifradoBytes = cipher.doFinal(mensaje.getBytes());
        return Base64.getEncoder().encodeToString(cifradoBytes);
    }

    private static String descifrar(String algoritmo, String mensajeCifrado, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(algoritmo);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodificadoBase64 = Base64.getDecoder().decode(mensajeCifrado);
        byte[] descifradoBytes = cipher.doFinal(decodificadoBase64);
        return new String(descifradoBytes);
    }
}
