import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server implements RemoteInterface {
    public Server() throws RemoteException {
        super();
    }

    @Override
    public String someMethod() throws RemoteException {
        // Implementation of the method
        return "Hello from the server!";
    }

    public static void main(String[] args) {
        try {
            Server server = new Server();

            // Export the server object and bind it to the RMI registry
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.createRegistry(9999); // Default RMI registry port
            registry.rebind("RemoteInterface", stub);

            System.out.println("Server is running and waiting for client requests...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
