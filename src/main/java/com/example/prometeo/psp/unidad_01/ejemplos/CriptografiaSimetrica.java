// Genera una clave AES con KeyGenerator.getInstance("AES").generateKey().
// Crea un Cipher con Cipher.getInstance("AES").
// Inicialízalo en ENCRYPT_MODE para cifrar un mensaje.
// Luego, inicialízalo en DECRYPT_MODE para recuperar el mensaje original.

package com.example.prometeo.unidad_01.ejemplos;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class CriptografiaSimetrica {
    public static void metodoGemini() {
        try {
            String mensajeOriginal = "Este es un mensaje secreto";

            // 1. Generar la clave AES
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128); // Tamaño de la clave
            SecretKey claveSecreta = keyGen.generateKey();

            // 2. Crear el objeto Cipher para CIFRAR
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, claveSecreta);

            byte[] mensajeCifradoBytes = cipher.doFinal(mensajeOriginal.getBytes());
            // Lo pasamos a Base64 para que sea legible
            String mensajeCifrado = Base64.getEncoder().encodeToString(mensajeCifradoBytes);

            System.out.println("Mensaje original: " + mensajeOriginal);
            System.out.println("Mensaje cifrado (Base64): " + mensajeCifrado);

            // 3. Configurar el Cipher para DESCIFRAR
            cipher.init(Cipher.DECRYPT_MODE, claveSecreta);
            byte[] mensajeDescifradoBytes = cipher.doFinal(Base64.getDecoder().decode(mensajeCifrado));
            String mensajeDescifrado = new String(mensajeDescifradoBytes);

            System.out.println("Mensaje descifrado: " + mensajeDescifrado);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Genera y devuelve una clave secreta criptográfica
    private static Key getKeyFromKeyGenerator(String cipher, int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(cipher);
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println("AES Key: " + Base64.getEncoder().encodeToString(getKeyFromKeyGenerator("AES", 256).getEncoded()));
    }
}
