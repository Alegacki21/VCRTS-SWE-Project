/* Project: Project Milestone 2: GUI
* Class: VehicularCloudConsole.java
* Author: Albert Legacki, Allan Ilyasov, Thomas Javier Santos Yciano, Bryan Fung, Matthew Martinez 
* Date: October 7, 2024
* This program is a GUI for the Vehicular Cloud Console. It allows the user to 
  input information about a client or owner.
*/

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
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import javax.swing.*;

public class VehicularCloudConsole {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel clientPanel;
    private JPanel ownerPanel;
    private CardLayout cardLayout;
    private JRadioButton clientButton;
    private JRadioButton ownerButton;
    private JLabel clientIdLabel;
    private JLabel jobDurationLabel;
    private JTextField jobDurationField;
    private JLabel jobDeadlineLabel;
    private JTextField jobDeadlineField;
    private JLabel ownerIdLabel;
    private JLabel vehicleLabel;
    private JTextField vehicleField;
    private JLabel residencyLabel;
    private JTextField residencyField;
    private JLabel availabilityLabel;
    private JTextField availabilityField;
    private JButton submitButton;
    private ButtonGroup userTypeGroup;

    private int clientIdCounter = 0;
    private int ownerIdCounter = 0;

    private String currentClientId = null;
    private String currentOwnerId = null;

    // Sets to keep track of used IDs
    private Set<String> clientIds = new HashSet<>();
    private Set<String> ownerIds = new HashSet<>();

    public VehicularCloudConsole() {
        frame = new JFrame("Vehicular Cloud Console");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("images/cloudconsole.png");
        frame.setIconImage(icon.getImage());

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Client panel
        clientPanel = new JPanel(new GridLayout(0, 2));
        clientPanel.setBorder(BorderFactory.createTitledBorder("Client Information"));

        clientPanel.add(new JLabel("Client ID:"));
        clientIdLabel = new JLabel("0000");
        clientPanel.add(clientIdLabel);

        clientPanel.add(new JLabel("Approximate Job Duration:"));
        jobDurationField = new JTextField();
        clientPanel.add(jobDurationField);

        clientPanel.add(new JLabel("Job Deadline:"));
        jobDeadlineField = new JTextField();
        clientPanel.add(jobDeadlineField);

        mainPanel.add(clientPanel, "Client");

        // Owner panel
        ownerPanel = new JPanel(new GridLayout(0, 2));
        ownerPanel.setBorder(BorderFactory.createTitledBorder("Owner Information"));

        ownerPanel.add(new JLabel("Owner ID:"));
        ownerIdLabel = new JLabel("0000");
        ownerPanel.add(ownerIdLabel);

        ownerPanel.add(new JLabel("Vehicle Information:"));
        vehicleField = new JTextField();
        ownerPanel.add(vehicleField);

        ownerPanel.add(new JLabel("Approximate Residency Time:"));
        residencyField = new JTextField();
        ownerPanel.add(residencyField);

        ownerPanel.add(new JLabel("Available Computational Power:"));
        availabilityField = new JTextField();
        ownerPanel.add(availabilityField);

        mainPanel.add(ownerPanel, "Owner");

        // Create radio buttons for user type selection
        clientButton = new JRadioButton("Client");
        ownerButton = new JRadioButton("Owner");
        userTypeGroup = new ButtonGroup();
        userTypeGroup.add(clientButton);
        userTypeGroup.add(ownerButton);

        JPanel radioPanel = new JPanel();
        radioPanel.add(new JLabel("Select User Type:"));
        radioPanel.add(clientButton);
        radioPanel.add(ownerButton);

        // Add radio panel and main panel to frame
        frame.setLayout(new BorderLayout());
        frame.add(radioPanel, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        // Add submit button
        submitButton = new JButton("Submit");
        frame.add(submitButton, BorderLayout.SOUTH);

        // Add action listeners
        clientButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Client");
            if (currentClientId == null) {
                currentClientId = generateNextId("Client");
            }
            clientIdLabel.setText(currentClientId);
            clearFieldsExceptId("Client");
        });

        ownerButton.addActionListener(e -> {
            cardLayout.show(mainPanel, "Owner");
            if (currentOwnerId == null) {
                currentOwnerId = generateNextId("Owner");
            }
            ownerIdLabel.setText(currentOwnerId);
            clearFieldsExceptId("Owner");
        });

        submitButton.addActionListener(e -> saveInformation());

        // Default to client panel
        clientButton.setSelected(true);
        clientButton.doClick();

        frame.setVisible(true);
    }

    private String generateNextId(String userType) {
        String nextId = "0000";
        Set<String> usedIds = userType.equals("Client") ? clientIds : ownerIds;

        for (int i = 1; i <= 500; i++) {
            String id = String.format("%04d", i);
            if (!usedIds.contains(id)) {
                usedIds.add(id);
                nextId = id;
                break;
            }
        }
        return nextId;
    }

    private void saveInformation() {
        String userType = clientButton.isSelected() ? "Client" : "Owner";
        String id = userType.equals("Client") ? currentClientId : currentOwnerId;

        String vehicleInfo = vehicleField.getText().trim();
        String residencyTime = residencyField.getText().trim();
        String availability = availabilityField.getText().trim();

        String jobDuration = jobDurationField.getText().trim();
        String jobDeadline = jobDeadlineField.getText().trim();

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Basic validation
        if (userType.equals("Owner")) {
            if (vehicleInfo.isEmpty() || residencyTime.isEmpty() || availability.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                return;
            }
        } else {
            if (jobDuration.isEmpty() || jobDeadline.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("vehicular_cloud_data.txt", true))) {
            writer.write("Timestamp: " + timestamp);
            writer.newLine();
            writer.write("User Type: " + userType);
            writer.newLine();
            if (userType.equals("Owner")) {
                writer.write("OwnerID: " + id);
                writer.newLine();
                writer.write("Vehicle Information: " + vehicleInfo);
                writer.newLine();
                writer.write("Residency Time: " + residencyTime);
                writer.newLine();
                writer.write("Computational Power: " + availability);
                writer.newLine();
            } else {
                writer.write("ClientID: " + id);
                writer.newLine();
                writer.write("Job Duration: " + jobDuration);
                writer.newLine();
                writer.write("Job Deadline: " + jobDeadline);
                writer.newLine();
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        JOptionPane.showMessageDialog(frame, "Information saved successfully!\nYour " + userType + " ID is: " + id);
        clearFields();
/* */
        // Reset current IDs to allow new entries 
        if (userType.equals("Client")) {
            currentClientId = null;
            clientIdLabel.setText("0000");
        } else {
            currentOwnerId = null;
            ownerIdLabel.setText("0000");
        }
    }

    private void clearFields() {
        if (clientButton.isSelected()) {
            jobDurationField.setText("");
            jobDeadlineField.setText("");
        } else {
            vehicleField.setText("");
            residencyField.setText("");
            availabilityField.setText("");
        }
    }

    private void clearFieldsExceptId(String userType) {
        if (userType.equals("Client")) {
            jobDurationField.setText("");
            jobDeadlineField.setText("");
        } else {
            vehicleField.setText("");
            residencyField.setText("");
            availabilityField.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(VehicularCloudConsole::new);
    }
}