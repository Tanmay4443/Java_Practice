import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class Client {
    public static void main(String[] args) {
        try {
            // Create and initialize the ORB
            ORB orb = ORB.init(args, null);

            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolve the remote object reference
            String name = "HelloWorld";
            HelloWorldApp.HelloWorld helloWorld = HelloWorldApp.HelloWorldHelper.narrow(ncRef.resolve_str(name));

            // Invoke the remote method
            String result = helloWorld.sayHello();
            System.out.println("Response: " + result);
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
