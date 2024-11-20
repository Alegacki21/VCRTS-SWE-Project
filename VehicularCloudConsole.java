/* Project: Project Milestone 2: GUI
* Class: vehicularCloudConsole.java
* Author: Albert Legacki, Allan Ilyasov, Tomas Santos Yciano, Bryan Fung, Mathew Martinez 
* Date: October 7, 2024
* This program is a GUI for the Vehicular Cloud Console. It allows the user to 
  input information about a client or owner.
*/

// Importing libraries
import java.awt.*;
<<<<<<< Updated upstream
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
=======
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
>>>>>>> Stashed changes

public class VehicularCloudConsole {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel homeScreenPanel;
    private JPanel loginScreenPanel;
    private JPanel clientPanel;
    private JPanel ownerPanel;
    private CardLayout cardLayout;
    private JTextField clientIdField;
    private JTextField jobDurationField;
    private JTextField jobDeadlineField;
    private JTextField subscriptionPlan;
    private JTextField ownerIdField;
    private JTextField vehicleField;
    private JTextField residencyField;
    private JTextField availabilityField;
    private JButton clientSubmitButton;
    private JButton clientLogoutButton;
    private JButton ownerSubmitButton;
    private JButton ownerLogoutButton;

    public VehicularCloudConsole() {
        //Frame Setup
        frame = new JFrame("Vehicular Cloud Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        //Icon Setup (Image doesn't show up when running program, could implement in future)
        //ImageIcon icon = new ImageIcon("images/cloudconsole.png");
        //frame.setIconImage(icon.getImage());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //Home Screen Setup
        homeScreenPanel = new JPanel();
        JLabel homeScreenLabel = new JLabel("Welcome to the VehiCloud - The Vehicular Cloud Real Time System");
        homeScreenPanel.add(homeScreenLabel);

        //Login Button
        JButton loginButton = new JButton("Login");

        //Action Listeners for Login
        loginButton.addActionListener(e -> {
            frame.setTitle("VehiCloud - Login");
            cardLayout.show(mainPanel, "Login");
        });

        // Login Screen Panel
        loginScreenPanel = new JPanel();
        loginScreenPanel.setLayout(new GridLayout(3, 2));
        loginScreenPanel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField();
        loginScreenPanel.add(usernameField);
        loginScreenPanel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField();
        loginScreenPanel.add(passwordField);
        JButton loginSubmitButton = new JButton("Login");
        loginSubmitButton.addActionListener(e -> {

            // Get the username and password from the text fields
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Check if the username and password are correct for owner and client (Hardcoded for simplicity)
            if (username.equals("usertype1") && password.equals("usertype1")) {
                // Show a message to the Owner, then switch to the Owner panel
                JOptionPane.showMessageDialog(frame, "Welcome Owner!");
                cardLayout.show(mainPanel, "Owner");
                ownerSubmitButton.setVisible(true);
                ownerLogoutButton.setVisible(true);
            } else if (username.equals("usertype2") && password.equals("usertype2")) {
                JOptionPane.showMessageDialog(frame, "Welcome Client!");
                cardLayout.show(mainPanel, "Client");
                clientSubmitButton.setVisible(true);
                clientLogoutButton.setVisible(true);
            } else {
                // Show invalid credentials message, prompt the user to try again
                JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
            }
            }
        );
        loginScreenPanel.add(new JLabel()); // Empty label for spacing
        loginScreenPanel.add(loginSubmitButton);

        // Add login screen panel to main panel
        mainPanel.add(loginScreenPanel, "Login");

        homeScreenPanel.add(loginButton);

        // Add the home screen panel to the main panel
        mainPanel.add(homeScreenPanel, "Home");

        // Client panel
        clientPanel = new JPanel(new GridLayout(0, 2));

        clientPanel.add(new JLabel("Client ID:"));
        clientIdField = new JTextField();
        clientPanel.add(clientIdField);

        clientPanel.add(new JLabel("Approximate Job Duration:"));
        jobDurationField = new JTextField();
        clientPanel.add(jobDurationField);

        clientPanel.add(new JLabel("Job Deadline:"));
        jobDeadlineField = new JTextField();
        clientPanel.add(jobDeadlineField);

        clientPanel.add(new JLabel("Subscription Plan:"));
        subscriptionPlan = new JTextField();
        clientPanel.add(subscriptionPlan);

        clientPanel.setBackground(new Color(128,128,128));

        // Create submit and logout buttons for client
        clientSubmitButton = new JButton("Submit");
        clientLogoutButton = new JButton("Logout");

        // Add submit and logout buttons to client panel
        JPanel clientButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        clientButtonPanel.add(clientSubmitButton);
        clientButtonPanel.add(clientLogoutButton);
        clientPanel.add(clientButtonPanel);

        mainPanel.add(clientPanel, "Client");

        // Owner panel
        ownerPanel = new JPanel(new GridLayout(0, 2));
        ownerPanel.add(new JLabel("Owner ID:"));
        ownerIdField = new JTextField();
        ownerPanel.add(ownerIdField);
        ownerPanel.add(new JLabel("Vehicle Information:"));
        vehicleField = new JTextField();
        ownerPanel.add(vehicleField);
        ownerPanel.add(new JLabel("Approximate Residency Time:"));
        residencyField = new JTextField();
        ownerPanel.add(residencyField);
        ownerPanel.add(new JLabel("Available Computational Power:"));
        availabilityField = new JTextField();
        ownerPanel.add(availabilityField);

        ownerPanel.setBackground(new Color(128,128,128));

        // Create submit and logout buttons for owner
        ownerSubmitButton = new JButton("Submit");
        ownerLogoutButton = new JButton("Logout");

        // Add submit and logout buttons to owner panel
        JPanel ownerButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        ownerButtonPanel.add(ownerSubmitButton);
        ownerButtonPanel.add(ownerLogoutButton);
        ownerPanel.add(ownerButtonPanel);

        mainPanel.add(ownerPanel, "Owner");

        // Add radio panel and main panel to frame
        frame.setLayout(new BorderLayout());
        frame.add(mainPanel, BorderLayout.CENTER);
        
        // Set the home screen panel as the default panel
        cardLayout.show(mainPanel, "Home");

        // Add the main panel to the frame
        frame.add(mainPanel);

        // Set the frame visible
        frame.setVisible(true);

        loginButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Login");
            frame.revalidate();
        });

        clientSubmitButton.addActionListener(e -> saveInformation());
        ownerSubmitButton.addActionListener(e -> saveInformation());

        clientLogoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Home");
            frame.revalidate();
        });
        ownerLogoutButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Home");
            frame.revalidate();
        });

        // Initially hide all submit and logout buttons
        clientSubmitButton.setVisible(false);
        clientLogoutButton.setVisible(false);
        ownerSubmitButton.setVisible(false);
        ownerLogoutButton.setVisible(false);
    }

    private void saveInformation() {
        String userType = mainPanel.getComponent(1) == clientPanel ? "Client" : "Owner";
        String id = userType.equals("Client") ? clientIdField.getText() : ownerIdField.getText();
        String vehicleInfo = vehicleField.getText();
        String residencyTime = residencyField.getText();
        String availability = availabilityField.getText();

        String jobDuration = jobDurationField.getText();
        String jobDeadline = jobDeadlineField.getText();
        String subscription = subscriptionPlan.getText();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicular_cloud_data.txt", true))) {
            writer.write("Timestamp: " + timestamp);
            writer.newLine();
            writer.write("User Type: " + userType);
            writer.newLine();
            writer.write("ID: " + id);
            writer.newLine();
            if (userType.equals("Owner")) {
                writer.write("Vehicle Information: " + vehicleInfo);
                writer.newLine();
                writer.write("Residency Time: " + residencyTime);
                writer.newLine();
                writer.write("Computational Power: " + availability);
                writer.newLine();
            } else {
                writer.write("Job Duration: " + jobDuration);
                writer.newLine();
                writer.write("Job Deadline: " + jobDeadline);
                writer.newLine();
                writer.write("Subscription Plan: " + subscription);
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

<<<<<<< Updated upstream
        JOptionPane.showMessageDialog(frame, "Information saved successfully!");
        clearFields();
    }

    // Clears the fields for the next input by user
    private void clearFields() {
        clientIdField.setText("");
        jobDurationField.setText("");
        jobDeadlineField.setText("");
        subscriptionPlan.setText("");
        ownerIdField.setText("");
        vehicleField.setText("");
        residencyField.setText("");
        availabilityField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VehicularCloudConsole::new);
=======
        // Creating button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = createStyledButton("Back");
        JButton submitButton = createStyledButton("Submit");

        // Setting up back button action
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createRegistrationPanel("Owner"));
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        // Adding components to the vehicle panel
        vehiclePanel.add(Box.createVerticalStrut(20));
        vehiclePanel.add(titleLabel);
        vehiclePanel.add(Box.createVerticalStrut(10));
        vehiclePanel.add(subtitleLabel);
        vehiclePanel.add(Box.createVerticalStrut(20));
        vehiclePanel.add(formPanel);
        vehiclePanel.add(Box.createVerticalStrut(20));
        vehiclePanel.add(buttonPanel);
        vehiclePanel.add(Box.createVerticalStrut(20));

        // Setting up submit button action
        submitButton.addActionListener(e -> {
            try {
                // Creating Vehicle object using builder pattern
                Vehicle vehicle = new Vehicle.VehicleBuilder(
                    fields[0].getText(),  // VIN
                    fields[1].getText(),  // Model 
                    fields[2].getText()   // Make
                )
                .year(Integer.parseInt(fields[3].getText()))
                .computationalPower(Double.parseDouble(fields[4].getText()))
                .storageCapacity(Double.parseDouble(fields[5].getText()))
                .build();

                // Creating VehicleOwner and registering vehicle
                VehicleOwner owner = new VehicleOwner(
                    "OWNER_" + System.currentTimeMillis(),
                    "", "", "Owner", "", 0.0, "",
                    new ArrayList<>(), ""
                );

                // Registering the vehicle (this will write to the file)

                owner.registerVehicle(vehicle);

                File resourcesFile = new File("resources/vehicle_resources.txt");
                if (resourcesFile.exists()) {
                    resourcesFile.delete();
                }

                owner.registerVehicle(vehicle);

                // Showing success message
                JOptionPane.showMessageDialog(vehiclePanel,
                    "Vehicle registered successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                    
                // Redirecting to owner home
                mainPanel.removeAll();
                mainPanel.add(createOwnerHomePanel("Owner")); 
                mainPanel.revalidate();
                mainPanel.repaint();
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vehiclePanel,
                    "Please enter valid numbers for Year, Computational Power, and Storage Capacity",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        return vehiclePanel;
    }

    // Method to create Owner home panel (post login & registration)
    private JPanel createOwnerHomePanel(String ownerName) {
        JPanel ownerPanel = new JPanel();
        ownerPanel.setLayout(new BoxLayout(ownerPanel, BoxLayout.Y_AXIS));
        ownerPanel.setBackground(Color.WHITE);

        // Adding welcome header
        JLabel welcomeLabel = new JLabel("Welcome Home, " + ownerName + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding submission form title
        JLabel submissionTitle = new JLabel("Vehicle Resource Submission");
        submissionTitle.setFont(new Font("Arial", Font.BOLD, 20));
        submissionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creating form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Creating form fields
        String[] labels = {
            "Owner ID:",
            "Vehicle Information:",
            "Approximate Residency Time:",
            "Available Computational Power:",
            "Notes:"
        };

        JTextField[] fields = new JTextField[labels.length];

        // Adding labels and fields to the form
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            
            fields[i] = new JTextField(20);
            fields[i].setPreferredSize(new Dimension(300, 30));
            
            // Make Notes field taller
            if (i == labels.length - 1) {
                fields[i] = new JTextField(20);
                fields[i].setPreferredSize(new Dimension(300, 60));
            }

            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            formPanel.add(fields[i], gbc);
        }

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton logoutButton = createStyledButton("Logout");
        JButton submitButton = createStyledButton("Submit");
        JButton viewJobsButton = createStyledButton("View Vehicles");

        // Style buttons with gray background
        Color buttonColor = new Color(192, 192, 192); // Light gray
        logoutButton.setBackground(buttonColor);
        submitButton.setBackground(buttonColor);
        viewJobsButton.setBackground(buttonColor);

        // Add button functionality
        logoutButton.addActionListener(e -> {
            mainPanel.removeAll();
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        submitButton.addActionListener(e -> {
            // Validate fields
            boolean allFilled = true;
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getText().trim().isEmpty()) {
                    allFilled = false;
                    break;
                }
            }

            if (allFilled) {
                try {
                    // Add timestamp
                    String timestamp = java.time.LocalDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    
                    Scanner scanner = new Scanner(System.in);

        // Ask the user for input
        Thread owner = new Thread(() -> {
        System.out.print(" Enter your message to the server: ");
        

        // Connect to the server
        try (Socket socket = new Socket("127.0.0.1", 5000);
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Send the user input to the server

            String userInput2 ="Timestamp: " + timestamp + "\n" + "Owner ID: " + fields[0].getText() + "\n" + "Vehicle Info: " + fields[1].getText() + "\n" +
            "Residency Time: " + fields[2].getText() + "\n" + "Computational Power: " + fields[3].getText() + "\n" + "Notes: " + fields[4].getText() + "\n"
            + "\nEND";
            output.println(userInput2);
            System.out.println("Message sent to the server: " + userInput2);

            // Wait for the server's response
            String serverResponse = input.readLine();
            System.out.println("Response from the server: " + serverResponse);

        } catch (IOException ex) {
            System.err.println("Error: Unable to connect to the server.");
            ex.printStackTrace();
        }
    }); 
    owner.start();

    VehicleOwner.vehicleServerResponse(ownerPanel);
    
                    // Create resources directory if it doesn't exist
                    File directory = new File("resources");
                    if (!directory.exists()) {
                        directory.mkdir();
                    }
                    
                    FileWriter writer = new FileWriter("resources/vehicle_resources.txt", true);
                    writer.write("Timestamp: " + timestamp + "\n");
                    writer.write("Owner ID: " + fields[0].getText() + "\n");
                    writer.write("Vehicle Info: " + fields[1].getText() + "\n");
                    writer.write("Residency Time: " + fields[2].getText() + "\n");
                    writer.write("Computational Power: " + fields[3].getText() + "\n");
                    writer.write("Notes: " + fields[4].getText() + "\n");
                    writer.write("------------------------\n");
                    writer.close();

                    JOptionPane.showMessageDialog(ownerPanel,
                        "Vehicle resource submitted and saved successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    // Clear fields after successful submission
                    for (JTextField field : fields) {
                        field.setText("");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(ownerPanel,
                        "Error saving resource information: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(ownerPanel,
                    "Please fill in all fields",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add this new action listener for viewJobsButton
        viewJobsButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createSubmittedResourcesPanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(logoutButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(viewJobsButton);

        // Add components to main panel
        ownerPanel.add(Box.createVerticalStrut(20));
        ownerPanel.add(welcomeLabel);
        ownerPanel.add(Box.createVerticalStrut(20));
        ownerPanel.add(submissionTitle);
        ownerPanel.add(Box.createVerticalStrut(30));
        ownerPanel.add(formPanel);
        ownerPanel.add(Box.createVerticalStrut(30));
        ownerPanel.add(buttonPanel);
        ownerPanel.add(Box.createVerticalStrut(20));

        return ownerPanel;
    }

    // Client home panel (post login & registration)
    private JPanel createClientHomePanel(String clientName) {
        JPanel clientPanel = new JPanel();
        clientPanel.setLayout(new BoxLayout(clientPanel, BoxLayout.Y_AXIS));
        clientPanel.setBackground(Color.WHITE);

        // Welcome header
        JLabel welcomeLabel = new JLabel("Welcome Home, " + clientName + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Job submission title
        JLabel submissionTitle = new JLabel("Job Submission");
        submissionTitle.setFont(new Font("Arial", Font.BOLD, 20));
        submissionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        // Form fields
        String[] labels = {
            "Client ID:",
            "Subscription Plan:",
            "Approximate Job Duration (Minutes):",
            "Job Deadline:",
            "Purpose/Reason:"
        };

        JTextField[] fields = new JTextField[labels.length];

        // Add labels and fields
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            
            fields[i] = new JTextField(20);
            fields[i].setPreferredSize(new Dimension(300, 30));
            
            // Make Purpose/Reason field taller
            if (i == labels.length - 1) {
                fields[i] = new JTextField(20);
                fields[i].setPreferredSize(new Dimension(300, 60));
            }

            gbc.gridx = 0;
            gbc.gridy = i;
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            formPanel.add(fields[i], gbc);
        }

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton logoutButton = createStyledButton("Logout");
        JButton submitButton = createStyledButton("Submit");
        JButton viewJobsButton = createStyledButton("View Jobs");

        // Style buttons
        Color buttonColor = new Color(192, 192, 192);
        logoutButton.setBackground(buttonColor);
        submitButton.setBackground(buttonColor);
        viewJobsButton.setBackground(buttonColor);

        // Add button functionality
        logoutButton.addActionListener(e -> {
            mainPanel.removeAll();
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        submitButton.addActionListener(e -> {
            boolean allFilled = true;
            for (JTextField field : fields) {
                if (field.getText().trim().isEmpty()) {
                    allFilled = false;
                    break;
                }
            }

            if (allFilled) {
                try {
                    Scanner scanner = new Scanner(System.in);
                    Thread submitter = new Thread(() -> {
                        System.out.print(" Enter your message to the server: ");
                        String userInput = scanner.nextLine();
                
                        // Connect to the server
                        try (Socket socket = new Socket("127.0.0.1", 5000);
                             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                
                            // Send the user input to the server
                            output.println(userInput);
                            System.out.println("Message sent to the server: " + userInput);
                
                            // Wait for the server's response
                            String serverResponse = input.readLine();
                            System.out.println("Response from the server: " + serverResponse);
                
                        } catch (IOException ex) {
                            System.err.println("Error: Unable to connect to the server.");
                            ex.printStackTrace();
                        }
                    }); 
                    submitter.start();

                    
                    // Job ID counter, if null it is initialized to 0
                    if (jobCounter == null) {
                        jobCounter = 0;
                    }
                    
                    // Create a new Job object
                    String jobId = String.format("%03d", jobCounter++);
                    int duration = Integer.parseInt(fields[2].getText()); // Approximate Job Duration field
                    int arrivalTime = (int)(System.currentTimeMillis() / 1000); // Current time in seconds
                    Job newJob = new Job(jobId, duration, arrivalTime);
                    
                    // JobSubmitter instance
                    JobSubmitter jobSubmitter = new JobSubmitter(
                        fields[0].getText(), // Client ID
                        "", // email
                        "", // username
                        "", // name
                        "", // password
                        0.0, // balance
                        fields[1].getText(), // subscription plan as payment method
                        new ArrayList<>(), // empty job list
                        fields[1].getText(), // subscription plan
                        "" // payment account
                    );

                    // Submit the job
                    jobSubmitter.submitJob(newJob);

                    // Show success message
                    JOptionPane.showMessageDialog(clientPanel,
                        "Job submitted and saved successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);

                    // Clear fields after successful submission
                    for (JTextField field : fields) {
                        field.setText("");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(clientPanel,
                        "Error submitting job: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(clientPanel,
                    "Please fill in all fields",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add button functionality
        viewJobsButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createSubmittedJobsPanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(logoutButton);
        buttonPanel.add(submitButton);
        buttonPanel.add(viewJobsButton);

        // Add components to main panel
        clientPanel.add(Box.createVerticalStrut(20));
        clientPanel.add(welcomeLabel);
        clientPanel.add(Box.createVerticalStrut(20));
        clientPanel.add(submissionTitle);
        clientPanel.add(Box.createVerticalStrut(30));
        clientPanel.add(formPanel);
        clientPanel.add(Box.createVerticalStrut(30));
        clientPanel.add(buttonPanel);
        clientPanel.add(Box.createVerticalStrut(20));

        return clientPanel;
>>>>>>> Stashed changes
    }
}