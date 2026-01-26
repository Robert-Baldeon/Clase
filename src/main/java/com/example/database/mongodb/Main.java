package com.example.database.mongodb;

import com.mongodb.client.MongoDatabase;

public class Main {
    public static void main(String[] args) {
        try {
            MongoDatabase mongoDatabase = ConnectionBBDD.getConnectionMongo();
            System.out.println("Conexión establecida con MongoDB");
            System.out.println("Base de datos: " + mongoDatabase.getName());
        } catch (Exception e) {
            System.err.println("Error al conectar: " + e.getMessage());
            return; // Si no hay conexión, paramos el programa
        }

        Alumno gestorAlumnos = new Alumno();

        System.out.println("\n--- INICIANDO PRUEBAS DEL CRUD ---");

        // A. LISTAR
        gestorAlumnos.listarAlumnos();

        // B. INSERTAR ALUMNO
        gestorAlumnos.insertarAlumno("Ana", "Vázquez", "Guerrero", 25);

        // C. ACTUALIZAR ALUMNO
        gestorAlumnos.actualizarAlumno("Mónica", "Mónica", "Blásquez", "Güera", 20);

        // D. BORRAR ALUMNO
        gestorAlumnos.eliminarPorNombre("Ana");

        // E. LISTAR FINAL (Para ver cómo quedó la base de datos)
        System.out.println("\n--- ESTADO FINAL DE LA COLECCIÓN ---");
        gestorAlumnos.listarAlumnos();

        // 4. CERRAR CONEXIÓN
        // Es vital cerrar el cliente para liberar memoria en tu Arch Linux
        ConnectionBBDD.close();
        System.out.println("\nConexión cerrada. Programa finalizado.");
    }
}
