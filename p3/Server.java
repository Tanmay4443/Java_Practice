import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // Create the HelloWorldImpl servant
            HelloWorldImpl helloWorldImpl = new HelloWorldImpl();

            // Connect the servant to the ORB
            orb.connect(helloWorldImpl);

            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Bind the remote object to the naming service
            String name = "HelloWorld";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, helloWorldImpl);

            System.out.println("Server ready and waiting...");

            // Wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
