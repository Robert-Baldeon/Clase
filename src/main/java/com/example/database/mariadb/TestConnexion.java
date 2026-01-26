package com.example.database.mariadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexion {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost/0_tienda";
        String user = "mariadb";
        String password = "1234";

        try {
            Connection cnx = DriverManager.getConnection(url, user, password);
            System.out.println("Estado de la conexión: ");
            System.out.print(cnx.isClosed() ? "cerrada" : "abierta");
        } catch (SQLException e) {
            System.out.println("Un error se ha producido durante la conexión a la base de datos");
            e.printStackTrace();
        }
    }
}
