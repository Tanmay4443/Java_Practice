import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            // Create a server socket
            ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Listening on port 5000...");

            // Wait for a client to connect
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());

            // Get the input and output streams
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();

            // Read data from the client
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String requestData = new String(buffer, 0, bytesRead);
            System.out.println("Received data from client: " + requestData);

            // Process the request (e.g., perform some computation)
            String responseData = "Hello from the server!";

            // Send response back to the client
            outputStream.write(responseData.getBytes());
            System.out.println("Sent response to client: " + responseData);

            // Close the connections
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
