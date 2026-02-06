package com.example.prometeo.psp.unidad_04.ejemplos.multihilo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

// Client class
class Client {
    // Driver code
    public static void main(String[] args) {
        // Establish a connection by providing host and port number
        try (Socket socket = new Socket("localhost", 1234)) {
            // Writing to server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Reading from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Object of scanner class
            Scanner sc = new Scanner(System.in);
            String line = null;

            while (!"exit".equalsIgnoreCase(line)) {
                // Reading from user
                line = sc.nextLine();

                // Sending the user input to server
                out.println(line);

                // Displaying server reply
                System.out.println("Server replied: " + in.readLine());
            }

            // Closing the scanner object
            sc.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
