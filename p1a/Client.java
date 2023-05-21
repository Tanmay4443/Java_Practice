import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            // Connect to the server
            Socket socket = new Socket("localhost", 5000);
            System.out.println("Connected to server: " + socket.getInetAddress().getHostAddress());

            // Get the input and output streams
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            // Send data to the server
            String requestData = "Hello from the client!";
            outputStream.write(requestData.getBytes());
            System.out.println("Sent data to server: " + requestData);

            // Read response from the server
            byte[] buffer = new byte[1024];
            int bytesRead = inputStream.read(buffer);
            String responseData = new String(buffer, 0, bytesRead);
            System.out.println("Received response from server: " + responseData);

            // Close the connection
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
