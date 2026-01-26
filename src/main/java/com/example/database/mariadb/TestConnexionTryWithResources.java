package com.example.database.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;

public class TestConnexionTryWithResources {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/0_tienda";
        String user = "mariadb";
        String password = "1234";

        try (Connection cnx = DriverManager.getConnection(url, user, password)) {
            // 1. Estado básico
            System.out.println("Estado de la conexión: ");
            System.out.print(cnx.isClosed() ? "cerrada\n" : "abierta\n");

            // 2. Validación real (Ping al servidor)
            testEsValido(cnx);

            // 3. Prueba de Solo Lectura
            testReadingSolo(cnx);

            // 4. Comprobar si hay avisos del servidor al conectar
            visualizacionWarnings(cnx);

            // 5. Cambiar de base de datos
            cambiarDeBaseDeDatos(cnx);
        } catch (SQLException e) {
            System.out.println("Un error se ha producido durante la conexión a la base de datos");
            e.printStackTrace();
        }
    }

    public static void testEsValido(Connection cnx) {
        boolean esValido;
        try {
            esValido = cnx.isValid(0);
            if (esValido) {
                System.out.println("La conexión es válida");
            } else {
                System.out.println("La conexión no es válida");
            }
        } catch (Exception e) {
            System.out.println("El método isValid no se soporta por el controlador");
            e.printStackTrace();
        }
    }

    public static void testReadingSolo(Connection cnx) {
        boolean estadoOriginal;
        try {
            // Guardamos el estado original
            estadoOriginal = cnx.isReadOnly();

            // Intentamos cambiarlo al opuesto
            cnx.setReadOnly(!estadoOriginal);

            // Si el cambio se ha aplicado, es que se soporta
            if (cnx.isReadOnly() != estadoOriginal) {
                System.out.println("El modo solo lectura se soporta por este controlador");
            } else {
                System.out.println("El modo solo lectura no se soporta por este controlador");
            }

            cnx.setReadOnly(estadoOriginal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void visualizacionWarnings(Connection cnx) {
        SQLWarning advertencia;

        try {
            advertencia = cnx.getWarnings();

            if (advertencia == null) {
                System.out.println("No hay advertencia");    
            } else {
                while (advertencia != null) {
                    System.out.println(advertencia.getMessage());
                    System.out.println(advertencia.getSQLState());
                    System.out.println(advertencia.getErrorCode());
                    System.out.println(advertencia.getNextWarning());
                }
                cnx.clearWarnings();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void cambiarDeBaseDeDatos(Connection cnx) {
        try {
            System.out.println("Base de datos actual: " + cnx.getCatalog());
            System.out.println("Cambio de base de datos");
            cnx.setCatalog("mi_negocio");
            System.out.println("Base de datos actual: " + cnx.getCatalog());
        } catch (SQLException e) {
            System.err.println("Problema en la manipulación de las bases de datos en el servidor");
            e.printStackTrace();
        }
    }

    public static void infoBase() {

    }
}
