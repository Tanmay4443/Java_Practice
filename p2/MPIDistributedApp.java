import mpi.MPI;

public class MPIDistributedApp {
    public static void main(String[] args) {
        // Initialize MPI execution environment
        MPI.Init(args);

        // Get the id of the process and the total number of processes
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        // Define the message to be sent
        int message = 0;
        if (rank == 0) {
            message = 42;  // Process 0 will send the message
        }

        // Send the message from process 0 to process 1
        if (rank == 0) {
            MPI.COMM_WORLD.Send(new int[]{message}, 0, 1, MPI.INT, 1, 0);
            System.out.println("Process " + rank + " sent message: " + message);
        }

        // Receive the message from process 0
        if (rank == 1) {
            int[] receivedMessage = new int[1];
            MPI.COMM_WORLD.Recv(receivedMessage, 0, 1, MPI.INT, 0, 0);
            System.out.println("Process " + rank + " received message: " + receivedMessage[0]);
        }

        // Terminate MPI execution environment
        MPI.Finalize();
    }
}
