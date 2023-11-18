package LP1.SocketProgramming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {
    public static void main(String args[]) {
        int port = 8888;
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Server started and running on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println(clientSocket.getInetAddress());

                ClientThread clientThread = new ClientThread(clientSocket); // Create a new thread for each client
                clientThread.start(); // Start the thread

            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

class ClientThread extends Thread {
    private Socket clientSocket;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            // Read from the client
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // Write to the client
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine = reader.readLine(); // Read the client's message

            System.out.println("Recieved from client " + inputLine);

            writer.println("Server: " + inputLine); // Send the message back to the client

            System.out.println("Client disconnected");

            clientSocket.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
