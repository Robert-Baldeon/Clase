package com.example.prometeo.unidad_01.ejemplos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class GenerarHash {
    public static void metodoGemini() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Introduce un texto para hashear: ");
        String entrada = sc.nextLine();

        try {
            // 1. Instanciar el algoritmo SHA-256
            // Inicializa el motor criptográfico con el estándar SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 2. Aplicar el hash al texto
            // Procesa el array de bytes del String y genera el resumen (digest)
            // Devuelve un array de 32 bytes (256 bits)
            byte[] hashBytes = digest.digest(entrada.getBytes());

            // 3. Convertir los bytes a formato hexadecimal
            StringBuilder hexString = new StringBuilder();

            // Iteración sobre los 32 bytes del hash resultante
            for (byte b : hashBytes) {
                // Aplicación de máscara binaria 0xff (11111111)
                // Convierte el byte con signo de Java (-128 a 127) a un entero
                // Sin signo (0 a 255) para su correcta representación en base 16
                int valorSinSigno = 0xff & b;

                // Conversión del valor entero a String en base 16
                String hex = Integer.toHexString(valorSinSigno);

                // Control de padding: Si el valor es < 16, toHexString devuelve 1 solo carácter
                // Se concatena un '0' a la izquierda para mantener el formato de 2 caracteres por byte
                if (hex.length() == 1) {
                    hexString.append('0') ;
                }
                hexString.append(hex);
            }

            System.out.println("\n--- RESULTADOS ---");
            System.out.println("Texto original: " + entrada);
            System.out.println("Hash SHA-256:   " + hexString.toString());
            System.out.println("Longitud:       " + hexString.length() + " caracteres.");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }

    // El SHA (Secure Hash Algorithm) es una de las funciones hash criptográficas más populares. Se puede utilizar un hash para firma un fichero de texto o de datos.

    // Java proporciona la clase MessageDigest para hash SHA-256
    // MessageDigest no es seguro para subprocesos. En consecuencia, deberíamos utilizar una nueva instancia para cada hilo
    public static void metodo_01() {
        try {
            String originalString = "Hola soy Robert";

            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] encodeHash = messageDigest.digest(originalString.getBytes());

            // Sin embargo, aquí tenemos que usar un conversor de bytes a hexadecimal personalizado para obtener el valor hash en hexadecimal
            System.out.println(bytesToHex(encodeHash));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public static void main(String[] args) {
        metodo_01();
    }

}
