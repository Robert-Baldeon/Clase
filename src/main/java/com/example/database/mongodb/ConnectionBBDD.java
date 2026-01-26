package com.example.database.mongodb;

import com.mongodb.client.MongoClient;   // - MongoClient: representa la conexión con MongoDB
import com.mongodb.client.MongoClients;  // - MongoClients: clase de utilidad para crear un MongoClient
import com.mongodb.client.MongoDatabase; // - MongoDatabase: representa una base de datos concreta en MongoDB

public class ConnectionBBDD {
    private static final String URL_MONGO = "mongodb://localhost:27017";
    private static final String DB_NAME = "clase";

    // Singleton para el cliente 
    // Variable estática para guardar la instancia de MongoClient
    // Se usa static para que haya un solo cliente compartido en toda la aplicación
    private static MongoClient mongoClient = null;

    // Método privado que devuelve el MongoClient (singleton)
    private static MongoClient getClient() {
        if (mongoClient == null) { // Si no existe un cliente, se crea uno
            try {
                mongoClient = MongoClients.create(URL_MONGO); // Conecta al servidor MongoDB
                System.out.println("Conexión exitosa al servidor de MongoDB");
            } catch (Exception e) {
                System.err.println("Error al conectar a MongoDB: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return mongoClient; // Retorna la instancia del cliente (nueva o existente)
    }

    // Método público que devuelve la base de datos a la que nos queremos conectar
    public static MongoDatabase getConnectionMongo() {
        return getClient().getDatabase(DB_NAME);
        // Llama a getClient() para asegurarse de tener un MongoClient,
        // luego obtiene la base de datos específica usando su nombre
    }

    // Método para cerrar la conexión al terminal el programa
    public static void close() {
        if (mongoClient != null) { // Solo cierra si existe un cliente activo
            mongoClient.close(); // Cierra la conexión
            mongoClient = null; // Reinicia la variable para permitir reconexión si se desea

        }
    }
}
