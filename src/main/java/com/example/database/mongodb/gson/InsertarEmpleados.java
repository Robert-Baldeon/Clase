package com.example.database.mongodb.gson; 

// Importaciones de Java estándar
import java.io.InputStream;       // Para leer archivos desde el classpath como un flujo de bytes
import java.io.InputStreamReader; // Para convertir el InputStream en un lector de caracteres
import java.util.ArrayList;       // Para crear listas dinámicas de elementos
import java.util.List;            // Interfaz de listas, permite almacenar varios documentos


// Importaciones de MongoDB
import org.bson.Document;                   // Representa un documento BSON de MongoDB
import com.mongodb.client.MongoCollection;  // Permite interactuar con colecciones de MongoDB
import com.mongodb.client.MongoDatabase;    // Representa la base de datos de MongoDB


// Importaciones de Gson para trabajar con JSON
import com.google.gson.Gson;          // Biblioteca principal para convertir JSON a objetos Java
import com.google.gson.JsonArray;     // Representa un arreglo JSON
import com.google.gson.JsonElement;   // Representa cualquier elemento JSON (objeto, array, primitivo)
import com.google.gson.JsonObject;    // Representa un objeto JSON


// Importación de tu propia clase para conectar con la base de datos
import com.example.database.mongodb.ConnectionBBDD; 
// Esta clase contiene métodos para conectarse y cerrar la conexión con MongoDB


/**
 * Clase para insertar empleados en la base de datos MongoDB
 * usando un archivo JSON ubicado en src/main/resources/json/Empleados.json
 */
public class InsertarEmpleados {
    public static void main(String[] args) {

        // --------------------------------------
        // 1. Leer el archivo JSON desde recursos
        // --------------------------------------
        InputStream inputStream = InsertarEmpleados.class.getResourceAsStream("/json/Empleados.json");

        if (inputStream == null) {
            // Si el archivo no se encuentra, mostrar error y salir
            System.err.println("Error: No se pudo encontrar el archivo json");
            return;
        }

        // ---------------------------------------------
        // 2. Convertir JSON en objetos Java usando Gson
        // ---------------------------------------------
        Gson gson = new Gson();
        JsonArray listaJson = gson.fromJson(new InputStreamReader(inputStream), JsonArray.class);
        // "listaJson" ahora contiene todos los empleados del archivo JSON como elementos de JsonArray

        // ----------------------------------------
        // 3. Conectarse a la base de datos MongoDB
        // ----------------------------------------
        MongoDatabase mongoDatabase = ConnectionBBDD.getConnectionMongo();
        MongoCollection<Document> coleccion = mongoDatabase.getCollection("empleados");
        // "coleccion" es la colección donde vamos a insertar los documentos

        // ---------------------------------------------------------------
        // 4. Convertir cada JSON a documento BSON y agregarlos a la lista
        // ---------------------------------------------------------------
        List<Document> empleados = new ArrayList<>();
        for (JsonElement element : listaJson) {
            JsonObject obj = element.getAsJsonObject();

            // Crear documento MongoDB a partir del JSON
            Document doc = new Document()
                .append("id_empleado", obj.get("id_empleado").getAsInt())
                .append("nombre", obj.get("nombre").getAsString())
                .append("puesto", obj.get("puesto").getAsString())
                .append("salario_hora", obj.get("salario_hora").getAsDouble())
                .append("activo", obj.get("activo").getAsBoolean());

            empleados.add(doc);
        }

        // ----------------------------------------------------------------
        // 5. Insertar documentos en la colección si la lista no está vacía
        // ----------------------------------------------------------------
        if (!empleados.isEmpty()) {
            coleccion.insertMany(empleados);
            System.out.println("Se insertaron " + empleados.size() + " empleados correctamente");
        } else {
            System.out.println("No hay empleados para insertar");
        }

        // ----------------------------------------
        // 6. Cerrar la conexión a la base de datos
        // ----------------------------------------
        ConnectionBBDD.close();
    }
}  
