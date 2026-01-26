package com.example.database.mongodb.gson;

import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class LeerClienteJson {
    public static void main(String[] args) {
        try {
            Gson gson = new Gson();

            InputStream inputStream = LeerClienteJson.class.getResourceAsStream("/json/Cliente.json");

            if (inputStream == null) {
                System.err.println("Error: No se pudo encontrar el archivo");
                return;
            }

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

            System.out.println("\nTeléfonos de contacto:");
            JsonArray telefonos = cliente.getAsJsonArray("telefonos");
            for (JsonElement telefono : telefonos) {
                System.out.println("  - " + telefono.getAsString());
            }

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
