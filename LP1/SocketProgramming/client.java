package LP1.SocketProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class client {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 8888;

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to server on port " + port);

            String messageToBeSent = "Hello I am Client 1";

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream())); // Read from the
                                                                                                        // server
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true); // Write to the server

            writer.println(messageToBeSent); // Send the message to the server

            String serverResponse = reader.readLine(); // Read the server's response

            System.out.println("Server response " + serverResponse);

            socket.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}