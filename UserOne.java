import javax.swing.*;                         // Import the Swing library, which is used to create graphical user interface (GUI) components 							like buttons, text fields, etc.
import javax.swing.border.*;                  // Import the library for handling borders of components like panels, buttons, etc.
import java.awt.*;                            // Import the Abstract Window Toolkit (AWT) for handling graphics, layouts, colors, etc.
import java.awt.event.*;                      // Import the library to handle events, like button clicks, mouse movements, etc.
import java.util.*;                           // Import utility classes like Calendar (to get the current time), etc.
import java.text.*;                           // Import the library for formatting text, especially dates and times.
import java.net.*;                            // Import the library for networking (handling network connections with sockets).
import java.io.*;                             // Import the library to handle input/output operations like reading from and writing to files.

public class UserOne implements ActionListener, Runnable {                                  // Define a class called 'UserTwo' that can handle user actions and run in a separate thread.
    
    JTextField text;                                    // Declare a text field where the user will type their messages.
    JPanel a1;                                          // Declare a panel to hold the messages.
    static Box vertical = Box.createVerticalBox();      // Create a vertical box layout that holds the message panels one below the other.
    static JFrame f = new JFrame();                     // Declare a JFrame (the main window of the application).
    static DataOutputStream dout;                      // Declare a data output stream to send data to the server (not used directly here, but could be used for sending messages).
    
    BufferedReader reader;           // Declare a buffered reader to read messages from the server.
    BufferedWriter writer;           // Declare a buffered writer to send messages to the server.
    String name = "U-2";             // Set the user's name as "U-2" (could be any identifier for the user).
    
    UserOne() {                       // Constructor for the UserTwo class that sets up the GUI and establishes a connection with the server.
        
        f.setLayout(null);            // Set the layout of the JFrame to null (manual positioning of components).
        
        JPanel p1 = new JPanel();                     // Create a new panel p1 to hold the top bar of the window.
        p1.setBackground(new Color(7, 94, 84));        // Set the background color of the panel (dark teal).
        p1.setBounds(0, 0, 450, 70);                     // Set the position and size of the panel (top of the window).
        p1.setLayout(null);                            // Set the layout of the panel to null (manual positioning of components).
        f.add(p1);                                     // Add the panel to the JFrame.
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/arrow back.png"));                             // Load an image (back arrow) from the system resources.
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);                                   // Scale the image to the desired size.
        ImageIcon i3 = new ImageIcon(i2);                                                                          // Create an ImageIcon from the scaled image.
        JLabel back = new JLabel(i3);                                                                               // Create a JLabel to hold the image.
        back.setBounds(5, 20, 25, 25);                                                                             // Set the position and size of the back arrow label.
        p1.add(back);                                                                                              // Add the back arrow label to the top bar panel.
        
        back.addMouseListener(new MouseAdapter() {                                                                // Add a mouse listener to detect clicks on the back arrow.
            public void mouseClicked(MouseEvent ae) {                                                             // If the back arrow is clicked, exit the program.
                System.exit(0);                                 // Close the application.
            }
        });
        
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("images/dp..jpg"));                             // Load an image for the user’s profile picture.
        Image i5 = i4.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);                          // Scale the profile picture.
        ImageIcon i6 = new ImageIcon(i5);                                              // Create an ImageIcon from the scaled profile picture.
        JLabel profile = new JLabel(i6);                                              // Create a JLabel to hold the profile picture.
        profile.setBounds(40, 5, 60, 60);                                             // Set the position and size of the profile picture.
        p1.add(profile);                                                                // Add the profile picture to the top bar panel.
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("images/video.png"));           // Load an image for the video button.
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);            // Scale the video button image.
        ImageIcon i9 = new ImageIcon(i8);                                                   // Create an ImageIcon from the scaled video button image.
        JLabel video = new JLabel(i9);                                                      // Create a JLabel to hold the video button.
        video.setBounds(300, 20, 30, 30);                                                   // Set the position and size of the video button.
        p1.add(video);                                                                      // Add the video button to the top bar panel.
        
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("images/phone.png"));             // Load an image for the phone button.
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);             // Scale the phone button image.
        ImageIcon i12 = new ImageIcon(i11);                                                    // Create an ImageIcon from the scaled phone button image.
        JLabel phone = new JLabel(i12);                                                        // Create a JLabel to hold the phone button.
        phone.setBounds(360, 20, 35, 30);                                                      // Set the position and size of the phone button.
        p1.add(phone);                                                                         // Add the phone button to the top bar panel.
        
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("images/menu.png"));                     // Load an image for the menu button.
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);                    // Scale the menu button image.
        ImageIcon i15 = new ImageIcon(i14);                                                           // Create an ImageIcon from the scaled menu button image.
        JLabel morevert = new JLabel(i15);                                                            // Create a JLabel to hold the menu button.
        morevert.setBounds(420, 20, 10, 25);                                                          // Set the position and size of the menu button.
        p1.add(morevert);                                                                             // Add the menu button to the top bar panel.
        
        JLabel name = new JLabel("Group Chat");                                            // Create a JLabel for the title of the chat.
        name.setBounds(110, 15, 100, 18);                                               // Set the position and size of the title label.
        name.setForeground(Color.WHITE);                                                 // Set the text color to white.
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));                             // Set the font style and size of the title.
        p1.add(name);                                                                  // Add the title label to the top bar panel.
        
        JLabel status = new JLabel("U-1, U-2, U-3,....");                                 // Create a JLabel for showing the status of the chat (users in the chat).
        status.setBounds(110, 35, 160, 18);                                               // Set the position and size of the status label.
        status.setForeground(Color.WHITE);                                                // Set the text color to white.
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));                             // Set the font style and size of the status label.
        p1.add(status);                                                                   // Add the status label to the top bar panel.
        
        a1 = new JPanel();                                                    // Create a new panel to hold the chat messages.
        a1.setBounds(5, 75, 440, 570);                                        // Set the position and size of the chat panel.
        a1.setBackground(new Color(2, 40, 50));                               // Set the background color of the chat panel to a dark color.
        f.add(a1);                                                            // Add the chat panel to the JFrame.
        
        JScrollPane scrollPane = new JScrollPane(a1);                             // Create a scrollable pane for the chat panel (so that messages can scroll).
        scrollPane.setBounds(15, 90, 420, 550);                                   // Set the position and size of the scrollable pane.
        f.add(scrollPane);                                                        // Add the scrollable pane to the JFrame.
        
        text = new JTextField();                                           // Create a text field where the user will type their message.
        text.setBounds(5, 655, 310, 40);                                   // Set the position and size of the text field.
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));               // Set the font style and size of the text field.
        f.add(text);                                                       // Add the text field to the JFrame.
        
        JButton send = new JButton("Send");                             // Create a button labeled "Send" to send the message.
        send.setBounds(320, 655, 123, 40);                             // Set the position and size of the send button.
        send.setBackground(new Color(7, 94, 84));                      // Set the background color of the send button.
        send.setForeground(Color.WHITE);                               // Set the text color of the send button to white.
        send.addActionListener(this);                                  // Add an action listener to the send button so that the message is sent when clicked.
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));           // Set the font style and size of the send button.
        f.add(send);                                                   // Add the send button to the JFrame.
        
        f.setSize(450, 700);                                     // Set the size of the JFrame (width: 450px, height: 700px).
        f.setLocation(200, 50);                                  // Set the position of the JFrame on the screen (x: 490px, y: 50px).
        f.setUndecorated(true);                                  // Remove the default window decorations (like title bar).
        f.getContentPane().setBackground(Color.WHITE);           // Set the background color of the JFrame to white.
        
        f.setVisible(true);                                      // Make the JFrame visible on the screen.
        
        try {
            Socket socket = new Socket("localhost", 2003);                                                   // Create a socket and connect to the server at "localhost" on port 2003.
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));                   // Create a BufferedWriter to send data to the server.
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));                      // Create a BufferedReader to read data from the server.
        } catch (Exception e) {                                                                               // Catch any exceptions that occur during the socket connection process.
            System.out.println(e.getMessage());                                                               // Print the error message to the console if there's an exception.
        }
    }
    
    public void actionPerformed(ActionEvent ae) {                                                         // Method to handle the event when the "Send" button is clicked.
        try {
            String out = "<html><p>" + name + "</p><p>" + text.getText() + "</p></html>";                 // Format the message to display the sender's name and message.

            JPanel p2 = formatLabel(out);                                                                 // Create a formatted label for the message.

            a1.setLayout(new BorderLayout());                                                              // Set the layout of the chat panel to BorderLayout.

            JPanel right = new JPanel(new BorderLayout());                                                 // Create a panel to hold the message on the right side.
            right.add(p2, BorderLayout.LINE_END);                                                          // Add the formatted label to the right side of the panel.
            right.setBackground(Color.WHITE);                                                             // Set the background color of the panel to white.
            vertical.add(right);                                                                          // Add the panel to the vertical layout.
            vertical.add(Box.createVerticalStrut(15));                                                    // Add a gap between messages.

            a1.add(vertical, BorderLayout.PAGE_START);                                                    // Add the vertical layout of messages to the top of the chat panel.

            try {
                writer.write(out);                                                                   // Write the message to the server.
                writer.write("\r\n");                                                                // Add a newline after the message.
                writer.flush();                                                                      // Send the message to the server.
            } catch (Exception e) {                                                                 // Catch any exceptions during writing to the server.
                System.out.println(e.getMessage());                                                 // Print the error message to the console.
            }

            // File Handling (Saving chat history)
            FileWriter f1 = new FileWriter("C:\\Users\\DELL\\Desktop\\SY Disha 54 24-25\\Sem 4\\Projects\\Java\\Chat-History.txt", true);       // Open the chat history file in append mode.

            f1.write("U-2: " + out + "\n");                          // Write the message to the file with the sender's identifier.
            f1.close();                                             // Close the file after writing.

            text.setText("");                                       // Clear the text field after sending the message.

            f.repaint();                                           // Repaint the JFrame to update the UI with the new message.
            f.invalidate();                                        // Invalidate the JFrame to force it to revalidate and refresh its layout.
            f.validate();                                         // Validate the layout to ensure the components are correctly arranged.
        } catch (Exception e) {                                  // Catch any exceptions that occur during the action.
            System.out.println(e.getMessage());                  // Print the error message to the console.
        }
    }
    
    public static JPanel formatLabel(String out) {                              // Method to format the message into a panel with the appropriate style.
        JPanel panel = new JPanel();                                            // Create a new panel to hold the message.
        panel.setBackground(Color.WHITE);                                      // Set the background color of the panel to white.
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));               // Use a vertical layout for the panel to stack components.

        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");                               // Create a label with the message content.
        output.setFont(new Font("Tahoma", Font.PLAIN, 16));                                                                // Set the font style and size of the label.
        output.setBackground(new Color(37, 211, 102));                                                                    // Set the background color of the label to a light green.
        output.setOpaque(true);                                                                                           // Make the label's background color visible.
        output.setBorder(new EmptyBorder(0, 15, 0, 50));                                                                 // Set padding around the label content.

        panel.add(output);                                                     // Add the message label to the panel.

        Calendar cal = Calendar.getInstance();                                 // Get the current time.
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");                  // Define the format for the time (hours and minutes).
        
        JLabel time = new JLabel();                                            // Create a label to show the message time.
        time.setText(sdf.format(cal.getTime()));                               // Set the text of the label to the current time.

        panel.add(time);                                                       // Add the time label to the panel.

        return panel;                                                          // Return the panel containing the formatted message.
    }
    
    public void run() {                                             // Method to continuously read messages from the server in a separate thread.
        try {
            String msg = "";                                       // Declare a variable to hold the incoming message.
            while(true) {                                         // Run the loop indefinitely to read new messages.
                msg = reader.readLine();                         // Read the next line from the server.
                if (msg.contains(name)) {                        // If the message is from this user, skip it (do not display it).
                    continue;
                }
                
                JPanel panel = formatLabel(msg);                            // Format the received message into a panel.
                
                JPanel left = new JPanel(new BorderLayout());               // Create a panel for the left side (received messages).
                left.setBackground(Color.WHITE);                            // Set the background color of the panel to white.
                left.add(panel, BorderLayout.LINE_START);                    // Add the formatted message to the left side of the panel.
                vertical.add(left);                                          // Add the left panel to the vertical layout.
                
                a1.add(vertical, BorderLayout.PAGE_START);                  // Add the vertical layout to the top of the chat panel.
                
                f.repaint();                                           // Repaint the JFrame to update the UI with the new message.
                f.invalidate();                                        // Invalidate the JFrame to force it to revalidate and refresh its layout.
                f.validate();                                         // Validate the layout to ensure the components are correctly arranged.
            }
        } catch (Exception e) {                                        // Catch any exceptions that occur during reading messages.
            System.out.println(e.getMessage());                        // Print the error message to the console.
        }
    }
    
    public static void main(String[] args) {                       // The main method that starts the program.
        UserOne one = new UserOne();                               // Create an instance of the UserTwo class.
        Thread t1 = new Thread(one);                               // Create a new thread to run the chat reading loop.
        t1.start();                                                // Start the thread to listen for incoming messages from the server.
    }
}
