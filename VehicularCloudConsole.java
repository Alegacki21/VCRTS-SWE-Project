/* Project: Project Milestone 2: GUI
* Class: VehicularCloudConsole.java
* Author: Albert Legacki, Allan Ilyasov, Thomas Javier Santos Yciano, Bryan Fung, Matthew Martinez 
* Date: October 7, 2024
* This program is a GUI for the Vehicular Cloud Console. It allows the user to 
  input information about a client or owner.
*/

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

public class VehicularCloudConsole {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel clientPanel;
    private JPanel ownerPanel;
    private CardLayout cardLayout;
    private JRadioButton clientButton;
    private JRadioButton ownerButton;
    private JTextField clientIdField;
    private JTextField jobDurationField;
    private JTextField jobDeadlineField;
    private JTextField subscriptionPlan;
    private JTextField ownerIdField;
    private JTextField vehicleField;
    private JTextField residencyField;
    private JTextField availabilityField;
    private JButton submitButton;
    private ButtonGroup userTypeGroup;
   

    public VehicularCloudConsole() {
        frame = new JFrame("Vehicular Cloud Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("images/cloudconsole.png");
        frame.setIconImage(icon.getImage());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

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

        clientPanel.add(new JLabel("Subscription Plan:")); // added
        subscriptionPlan = new JTextField();
        clientPanel.add(subscriptionPlan);

        clientPanel.setBackground(new Color(128,128,128)); // added

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

        ownerPanel.setBackground(new Color(128,128,128)); // added

        mainPanel.add(ownerPanel, "Owner");


        // Create radio buttons for user type selection
        clientButton = new JRadioButton("Client");
        ownerButton = new JRadioButton("Owner");
        userTypeGroup = new ButtonGroup();
        userTypeGroup.add(clientButton);
        userTypeGroup.add(ownerButton);

         clientButton.setBackground(Color.gray);
         ownerButton.setBackground(Color.gray);

        JPanel radioPanel = new JPanel();
        radioPanel.add(clientButton);
        radioPanel.add(ownerButton);

        // Add radio panel and main panel to frame
        frame.setLayout(new BorderLayout());
        frame.add(radioPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Add submit button
        submitButton = new JButton("Submit");
        frame.add(submitButton, BorderLayout.SOUTH);

        frame.setVisible(true);

        clientButton.addActionListener(e -> cardLayout.show(mainPanel, "Client"));
        ownerButton.addActionListener(e -> cardLayout.show(mainPanel, "Owner"));

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveInformation();
            }
        });

        // Default to client panel
        clientButton.setSelected(true);
        cardLayout.show(mainPanel, "Client");
    }

    private void saveInformation() {
        String userType = clientButton.isSelected() ? "Client" : "Owner";
        String id = clientButton.isSelected() ? clientIdField.getText() : ownerIdField.getText();
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