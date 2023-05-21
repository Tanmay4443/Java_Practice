import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;

public class Client {
    public static void main(String[] args) {
        try {
            String serverAddress = "127.0.0.1";
            int serverPort = 9999; // Default RMI registry port

            // Connect to the RMI registry on the server
            Registry registry = LocateRegistry.getRegistry(serverAddress, serverPort);

            // Lookup the remote object by the binding name
            String bindingName = "RemoteInterface";
            RemoteInterface remoteObject = (RemoteInterface) registry.lookup(bindingName);

            // Use the remote object methods
            String result = remoteObject.someMethod();
            System.out.println("Result from server: " + result);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
