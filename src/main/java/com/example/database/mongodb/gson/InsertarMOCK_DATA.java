package com.example.database.mongodb.gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.example.database.mongodb.ConnectionBBDD;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class InsertarMOCK_DATA {
    public static void main(String[] args) {
        // Obtener el archivo JSON desde la carpeta resources
        InputStream inputStream = InsertarMOCK_DATA.class.getResourceAsStream("/json/MOCK_DATA.json");

        // Verificar que el archivo JSON exista
        if (inputStream == null) {
            System.err.println("Error: No se pudo encontrar el archivo json");
            return;
        }

        // Crear un objeto Gson para manejar la conversión de JSON a objetos Java
        Gson gson = new Gson();

        // Leer el JSON y convertirlo en un arreglo de elementos JSON
        JsonArray listaJson = gson.fromJson(new InputStreamReader(inputStream), JsonArray.class);

        // Conectar a la base de datos MongoDB usando la clase ConnectionBBDD
        MongoDatabase mongoDatabase = ConnectionBBDD.getConnectionMongo();

        // Obtener la colección "personas" de la base de datos
        MongoCollection<Document> collecion = mongoDatabase.getCollection("personas");

        // Formato de fecha que se espera en el JSON (dd/MM/yyyy)
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Lista para almacenar los documentos que se van a insertar en MongoDB
        List<Document> personas = new ArrayList<>();

        // Iterar por cada elemento del JSON
        for (JsonElement element : listaJson) {
            JsonObject obj = element.getAsJsonObject();

            try {
                // Convertir la fecha de texto a un objeto Date
                String fechaTexto = obj.get("fecha_alta").getAsString();
                Date fechaConvertida = simpleDateFormat.parse(fechaTexto);

                // Crear un documento de MongoDB a partir de los campos del JSON
                Document doc = new Document()
                    .append("id", obj.get("id").getAsInt()) // ID de la persona
                    .append("nombre", obj.get("nombre").getAsString()) // Nombre
                    .append("email", obj.get("email").getAsString()) // Correo electrónico
                    .append("edad", obj.get("edad").getAsInt()) // Edad
                    .append("activo", obj.get("activo").getAsBoolean()) // Estado activo
                    .append("saldo", obj.get("saldo").getAsDouble()) // Saldo económico
                    .append("fecha_alta", fechaConvertida) // Fecha de alta (convertida a Date)
                    .append("telefono", obj.get("telefono").getAsString()) // Teléfono
                    .append("pais", obj.get("pais").getAsString()); // País

                // Agregar el documento a la lista de personas
                personas.add(doc);
            } catch (ParseException e) {
                // Manejar error si la fecha no se puede convertir
                System.err.println("Error procesando registro");
            }
        }

        // Verificar si hay documentos para insertar
        if (!personas.isEmpty()) {
            // Insertar todos los documentos en la colección "personas"
            collecion.insertMany(personas);
            System.out.println("Se insertaron " + personas.size() + " personas correctamente");
        } else {
            System.out.println("No hay personas para insertar");
        }

        // Cerrar la conexión a la base de datos
        ConnectionBBDD.close();
        System.exit(0); // Para matar los hilos residuales
    }
}
