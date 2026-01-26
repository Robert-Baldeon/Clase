package com.example.database.mongodb;

// Importamos Document: representa un documento BSON, el formato que MongoDB usa internamente.
import org.bson.Document; // Representa un documento BSON, JSON binario que MongoDB usa internamente

import com.mongodb.client.MongoCollection; // Representa una colección de MongoDB (como una "tabla")
import com.mongodb.client.MongoCursor; // Cursor para iterar documentos de la colección
import com.mongodb.client.model.Filters; // Para filtros (condiciones WHERE)
import com.mongodb.client.model.Updates; // Para operaciones de actualización (SET)

// Clase Alumno
// Gestiona operaciones CRUD (Crear, Leer, Actualizar, Borrar) en la colección "Alumnos" de MongoDB
public class Alumno {
    // Método  privado para obtener la colección "Alumnos"
    // Accede a la base de datos a través de una conexión y devuelve la colección "Alumnos"
    private MongoCollection<Document> getCollection() {
        // ConnectionBBDD.getConnectionMongo() devuelve la base de datos
        // .getCollection("Alumnos") devuelve la colección específica
        return ConnectionBBDD.getConnectionMongo().getCollection("alumnos");
    }

    // 1. LISTAR TODOS LOS ALUMNOS
    public void listarAlumnos() {
        // Usamos try-with-resources para que el cursor se cierre automáticamente al terminar
        try (MongoCursor<Document> mongoCursor = getCollection().find().iterator()) {

            // Si no hay documentos, mostramos un mensaje y salimos
            if (!mongoCursor.hasNext()) {
                System.out.println("No hay alumnos registrados en la base de datos.");
                return;
            }

            // Iteramos sobre todos los documentos
            while (mongoCursor.hasNext()) {
                Document alumno = mongoCursor.next();

                // Usamos getString y getInteger
                // Imprimimos los campos del documento: nombre, apellidos y edad
                System.out.println(alumno.getString("nombre") + " " + alumno.getString("apellido1") + " " + alumno.getString("apellido2") + " " + alumno.getInteger("edad"));
            }
        } catch (Exception e) {
            System.err.println("Error al listar los alumnos: " + e.getMessage());
        }
    }

    // 2. ENCONTRAR POR NOMBRE
    public boolean encontrarPorNombre(String nombre) {
        // Busca el primer documento que tenga el campo "nombre" igual al parámetro
        Document alumno = getCollection().find(Filters.eq("nombre", nombre)).first();

        if (alumno != null) {
            System.out.println("El alumno ya existe: " + nombre);
            return true;
        }

        System.out.println("No existe el alumno: " + nombre);
        return false;
    }

    // 3. INSERTAR NUEVO ALUMNO
    public void insertarAlumno(String nombre, String apellido1, String apellido2, int edad) {
        // Primero comprobamos si ya existe un alumno con ese nombre
        if (encontrarPorNombre(nombre)) {
            return; 
        }

        // Creamos un documento BSON con los datos del nuevo alumno
        Document nuevoAlumno = new Document("nombre", nombre).append("apellido1", apellido1).append("apellido2", apellido2).append("edad", edad);

        // Insertamos el documento en la colección "Alumnos"
        getCollection().insertOne(nuevoAlumno);

        System.out.println("Alumno insertado: " + nombre);

        // Listamos todos los alumnos
        listarAlumnos();
    }

    // 4. BORRAR ALUMNO POR NOMBRE
    public void eliminarPorNombre(String nombre) {
        // Eliminamos el primer documento que tenga el campo "nombre" igual al parámetro
        long alumnoEliminado = getCollection().deleteOne(Filters.eq("nombre", nombre)).getDeletedCount(); // Devuelve cuántos documentos fueron eliminados

        if (alumnoEliminado > 0) {
            System.out.println("Alumno eliminado: " + nombre);      
        } else {
            System.out.println("No existe el alumno: " + nombre);
        }
    }

    // 5. ACTUALIZAR UN ALUMNO
    public void actualizarAlumno(String nombreActual, String nuevoNombre, String nuevoApellido1, String nuevoApellido2, int nuevaEdad) {
        // Actualizamos el primer documento cuyo campo "nombre" coincida con nombreActual
        long alumnoActualizado = getCollection().updateOne(Filters.eq("nombre", nombreActual), Updates.combine(Updates.set("nombre", nuevoNombre), Updates.set("apellido1", nuevoApellido1), Updates.set("apellido2", nuevoApellido2), Updates.set("edad", nuevaEdad))).getModifiedCount();

        if (alumnoActualizado > 0) {
            System.out.println("Alumno actualizado: " + nombreActual + " → " + nuevoNombre);
        } else {
            System.out.println("No existe el alumno: " + nombreActual);
        }
    }
}
