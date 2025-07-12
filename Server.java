import java.net.*;                                      // Import the classes needed to create network connections like ServerSocket (server-side) and Socket (client-side).
import java.io.*;                                       // Import classes to handle input and output operations (like reading from or writing to files or network connections).
import java.util.*;                                    // Import utility classes like Vector, which is a kind of list to store multiple items (in this case, client connections).

public class Server implements Runnable {                     // Define a new class called 'Server' that can run in a separate thread to handle each client.

    Socket socket;                                           // Declare a variable called 'socket' which will store the connection between this server and a client.

    public static Vector client = new Vector();                // Create a static list (Vector) to keep track of all the clients currently connected to the server.

    public Server(Socket socket) {                              // This is the constructor. It gets called when a new 'Server' object is created and initializes the connection (socket).
        try {
            this.socket = socket;                                // Store the incoming connection (socket) in the class's 'socket' variable.
        } catch (Exception e) {                                  // If something goes wrong while saving the socket, catch the error.
            System.out.println(e.getMessage());                      // Print the error message to the console (if there is any).
        }
    }

    public void run() {                                             // This is the 'run' method that gets executed when the server starts handling a client in a separate thread.
        try {
                                                                                                           // Set up a reader to read data coming from the client.
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                                                                                            // Set up a writer to send data back to the client.
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            client.add(writer);                                                              // Add this client’s writer to the 'client' list, so we can send messages to this client later.

            while(true) {                                                                    // Keep running forever to keep listening to the client’s messages and send data.
                String data = reader.readLine().trim();                           // Read a line of text from the client. Remove any extra spaces at the beginning or end.
                System.out.println("Received " + data);                               // Print the message received from the client to the server's console (for logging purposes).
                
                // Now, loop through every client in the list and send the message we received to all of them.
                for (int i = 0; i < client.size(); i++) {                                         // Go through each client in the 'client' list.
                    try {
                        BufferedWriter bw = (BufferedWriter) client.get(i);                         // Get the writer for the current client.
                        bw.write(data);                                                       // Send the message to this client.
                        bw.write("\r\n");                                                        // Add a new line after the message so that the clients know the message has ended.
                        bw.flush();                                                         // Make sure the message is actually sent to the client right away (flush the writer).
                    } catch (Exception e) {                                                 // If an error happens while sending the message to this client, catch it.
                        System.out.println(e.getMessage());                                // Print any error messages to the server's console.
                    }
                }
            }
        } catch (Exception e) {                                                             // If something goes wrong while running the 'run' method, catch the error.
            System.out.println(e.getMessage());                                             // Print the error message to the console.
        }
    }

    public static void main(String[] args) throws Exception {                              // The main method that runs when the program starts.
        ServerSocket s = new ServerSocket(2003);                                           // Create a server that listens for incoming client connections on port 2003.
        while(true) {                                                                      // Keep running the server to accept new clients forever.
            Socket socket = s.accept();                 // Wait for a new client to connect to the server and accept that connection (this line blocks the program until a client connects).
            Server server = new Server(socket);                // Create a new Server object to handle the new client’s connection.
            Thread thread = new Thread(server);                     // Create a new thread (separate part of the program) to run the 'run' method and handle the client in parallel.
            thread.start();                                    // Start the new thread, which means the 'run' method will execute and handle the client.
        }
    }
}
