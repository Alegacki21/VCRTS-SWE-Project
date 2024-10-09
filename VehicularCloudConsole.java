/* Project: Project Milestone 2: GUI
* Class: VehicularCloudConsole.java
* Author: Albert Legacki, Allan Ilyasov, Thomas Javier Santos Yciano, Bryan Fung, Matthew Martinez 
* Date: October 7, 2024
* This program is a GUI for the Vehicular Cloud Console. It allows the user to 
  input information about a client or owner.
*/

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

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

        //Icon Setup
        ImageIcon icon = new ImageIcon("images/cloudconsole.png");
        frame.setIconImage(icon.getImage());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        //Home Screen Setup
        homeScreenPanel = new JPanel();
        JLabel homeScreenLabel = new JLabel("Welcome to the VehiCloud - The Vehicular Cloud Real Time System");
        homeScreenPanel.add(homeScreenLabel);

        //Login & Register Buttons
        JButton loginButton = new JButton("Login");

        //Action Listeners for Login & Registration
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

            // Check if the username and password are correct for owner and client
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

        JOptionPane.showMessageDialog(frame, "Information saved successfully!");
        clearFields();
    }

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
    }
}