package LP1.SocketProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

class EchoClient {
    public static void main(String[] args) {
        String serverAddress = "localhost";
        int port = 8888;

        try {
            Socket socket = new Socket(serverAddress, port);
            System.out.println("Connected to server on port " + port);

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter message (type 'bye' to exit): ");
                String message = scanner.nextLine();

                writer.println(message);

                if (message.equals("bye")) {
                    break;
                }

                String response = reader.readLine();
                System.out.println("Server response: " + response);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
