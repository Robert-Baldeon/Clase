package com.example.prometeo.psp.unidad_01.ejemplos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestSqlInjectionPostgres {
    private static final String URL = "jdbc:postgresql://localhost:5432/taller_seguridad";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {
        // String inputUsuario = "' OR '1'='1"; Este no me ha funcionado
        String inputUsuario = "' OR '1'='1' -- ";
        String inputPassword = "cualquier_cosa";

        // SELECT * FROM usuarios WHERE username = '' OR '1'='1' --AND password = 'cualquier_cosa';
        String sql = "SELECT * FROM usuarios WHERE username = '" + inputUsuario + "' AND password = '" + inputPassword + "'";

        System.out.println("Consulta ejecutada: " + sql);

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Conexion establecida");

            boolean hayResultados = false;

            while (rs.next()) {
                hayResultados = true;
                System.out.println("Usuario: " + rs.getString("username"));
                System.out.println("Password: "+ rs.getString("password"));
                System.out.println("Email: " + rs.getString("email") + "\n");
            }

            if (hayResultados) {
                System.out.println("Ataque existoso");
            } else {
                System.out.println("Acceso denegado");
            }

        } catch (SQLException e) {
            System.err.println("Error de conexión");
            e.printStackTrace();
        } 
    }
}
