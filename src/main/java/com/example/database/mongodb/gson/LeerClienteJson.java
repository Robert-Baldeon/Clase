package com.example.database.mongodb.gson;

import java.io.InputStream;                 // Para obtener el archivo como flujo de bytes
import java.io.InputStreamReader;           // Para poder leer ese flujo como texto

import com.google.gson.Gson;                // Clase principal de Gson para convertir JSON ↔ Java
import com.google.gson.JsonArray;           // Para trabajar con arreglos JSON
import com.google.gson.JsonElement;         // Representa un elemento dentro del JSON
import com.google.gson.JsonObject;          // Representa un objeto JSON (con llaves {})
import com.google.gson.JsonSyntaxException; // // Para capturar errores si el JSON está mal escrito

public class LeerClienteJson {
    public static void main(String[] args) {
        try {
            // Cargamos el archivo Cliente.json desde la carpeta resources/json
            InputStream inputStream = LeerClienteJson.class.getResourceAsStream("/json/Cliente.json");

            // Si no se encuentra el archivo, mostramos un mensaje de error y salimos
            if (inputStream == null) {
                System.err.println("Error: No se pudo encontrar el archivo json");
                return;
            }

            // Creamos un objeto Gson para convertir JSON a objetos Java
            Gson gson = new Gson();

            // Leemos el JSON y lo convertimos a un objeto JsonObject
            JsonObject cliente = gson.fromJson(new InputStreamReader(inputStream), JsonObject.class);

            System.out.println("========================================");
            System.out.println("        DATOS DEL CLIENTE (JSON)        ");
            System.out.println("========================================");

            int id = cliente.get("id").getAsInt();
            String nombre = cliente.get("nombre").getAsString();
            String email = cliente.get("email").getAsString();
            double saldo = cliente.get("saldo").getAsDouble();
            boolean activo = cliente.get("activo").getAsBoolean();

            System.out.println("ID: " + id);
            System.out.println("Nombre: " + nombre);
            System.out.println("Email: " + email);
            System.out.println("Saldo: " + saldo + "€");
            System.out.println("Estado: " + (activo ? "Activo" : "Inactivo"));

            // Leemos y mostramos los teléfonos de contacto (JSON Array)
            System.out.println("\nTeléfonos de contacto:");
            JsonArray telefonos = cliente.getAsJsonArray("telefonos");
            for (JsonElement telefono : telefonos) {
                System.out.println("  - " + telefono.getAsString());
            }

            // Leemos la dirección (objeto JSON anidado)
            JsonObject direccion = cliente.getAsJsonObject("direccion");
            String ciudad = direccion.get("ciudad").getAsString();
            String cp = direccion.get("cp").getAsString();

            System.out.println("\nUbicación:");
            System.out.println("  Ciudad: " + ciudad);
            System.out.println("  Código Postal: " + cp);
            System.out.println("========================================");


        } catch (JsonSyntaxException e) {
            System.err.println("Error: El formato del JSON no es válido");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
