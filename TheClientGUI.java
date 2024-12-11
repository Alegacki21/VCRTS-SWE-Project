// Importing necessary libraries for GUI and file operations
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;

public class TheClientGUI extends JFrame {
    private JPanel mainPanel;
    private Integer jobCounter = 0;

    
    
    // Hardcoded login credentials for different user types (for testing purposes)
    private static final String OWNER_USERNAME = "owner123";
    private static final String OWNER_PASSWORD = "password123";
    private static final String CLIENT_USERNAME = "client123";
    private static final String CLIENT_PASSWORD = "password123";
    private static final String CONTROLLER_USERNAME = "admin";
    private static final String CONTROLLER_PASSWORD = "admin123";
    
    private String loggedInOwner;
    private String loggedInClient;
    private String loggedInController;

    Authentication auth = new Authentication();
    private static final String DB_URL = System.getenv("url");
    private static final String DB_USERNAME = System.getenv("sqlusername");
    private static final String DB_PASSWORD =   System.getenv("password");
    // String url = "jdbc:mysql://localhost:3306/vcrts";
    // String sqlusername = "bryan";
    // String password = "password"; 
    
    // Constructor for the main application window
    public TheClientGUI() {
        // Setting up the main frame properties
        setTitle("Vehicular Cloud Real-Time System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        
        // Creating and configuring the main panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        
        // Adding title and description to the main panel
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; width: 800px;'>(Client Side) Vehicular Cloud Real-Time System</div></html>");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Description of VCRTS from the requirements specification
        String description = "<html><div style='text-align: center; width: 800px;'>The Vehicular Cloud Real-Time System (VCRTS) is vehicular cloud system<br>" +
                           "that leverages the computational resources of parked vehicles in parking<br>" +
                           "lots to execute computational jobs and create a static cloud computing<br>" +
                           "environment.</div></html>";
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Creating a panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        // Adding user type labels and buttons
        JLabel newUserLabel = new JLabel("New User?");
        JLabel existingUserLabel = new JLabel("Existing User?");
        
        JButton registerButton = createStyledButton("Register");
        JButton loginButton = createStyledButton("Login");
        
        // Setting up action for the register button
        registerButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createRegistrationTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        // Setting up action for the login button
        loginButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createAccountTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        // Adding components to the button panel
        buttonPanel.add(newUserLabel);
        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(existingUserLabel);
        buttonPanel.add(loginButton);
        
        // Adding all components to the main panel with spacing
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(descLabel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(buttonPanel);
        
        // Adding the main panel to the frame
        add(mainPanel);
        
    }
    
    // Method to create the account type selection panel
    private JPanel createAccountTypePanel() {
        JPanel accountTypePanel = new JPanel();
        accountTypePanel.setLayout(new BoxLayout(accountTypePanel, BoxLayout.Y_AXIS));
        accountTypePanel.setBackground(Color.WHITE);
        
        // Adding main title
        JLabel titleLabel = createTitleLabel();
        
        JLabel subtitleLabel = new JLabel("Choose Account Type to Login:");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Creating radio button panel
        JPanel radioPanel = new JPanel(new GridBagLayout());
        radioPanel.setBackground(Color.WHITE);

        JPanel radioButtonsContainer = new JPanel();
        radioButtonsContainer.setLayout(new BoxLayout(radioButtonsContainer, BoxLayout.Y_AXIS));
        radioButtonsContainer.setBackground(Color.WHITE);

        // Creating and styling radio buttons
        JRadioButton ownerButton = new JRadioButton("Owner");
        JRadioButton clientButton = new JRadioButton("Client");
        JRadioButton controllerButton = new JRadioButton("Cloud Controller");

        Font radioFont = new Font("Arial", Font.PLAIN, 18);
        ownerButton.setFont(radioFont);
        clientButton.setFont(radioFont);
        controllerButton.setFont(radioFont);

        // Grouping radio buttons
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(ownerButton);
        accountGroup.add(clientButton);
        accountGroup.add(controllerButton);

        radioButtonsContainer.add(ownerButton);
        radioButtonsContainer.add(Box.createVerticalStrut(5));
        radioButtonsContainer.add(clientButton);
        radioButtonsContainer.add(Box.createVerticalStrut(5));
        radioButtonsContainer.add(controllerButton);
        
        controllerButton.setVisible(false);

        radioPanel.add(radioButtonsContainer);

        // Creating button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backButton = createStyledButton("Back");
        JButton nextButton = createStyledButton("Next");
        
        // Setting up back button action
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            TheClientGUI newFrame = new TheClientGUI();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        
        // Adding components to the account type panel
        accountTypePanel.add(Box.createVerticalStrut(10));
        accountTypePanel.add(titleLabel);
        accountTypePanel.add(Box.createVerticalStrut(50));
        accountTypePanel.add(subtitleLabel);
        accountTypePanel.add(Box.createVerticalStrut(10));
        accountTypePanel.add(radioPanel);
        accountTypePanel.add(Box.createVerticalStrut(10));
        accountTypePanel.add(buttonPanel);
        accountTypePanel.add(Box.createVerticalStrut(200));
        
        // Setting up next button action
        nextButton.addActionListener(e -> {
            if (controllerButton.isSelected()) {
                mainPanel.removeAll();
                mainPanel.add(createCloudControllerLoginPanel());
                mainPanel.revalidate();
                mainPanel.repaint();
            } else if (ownerButton.isSelected()) {
                mainPanel.removeAll();
                mainPanel.add(createLoginPanel("Owner"));
                mainPanel.revalidate();
                mainPanel.repaint();
            } else if (clientButton.isSelected()) {
                mainPanel.removeAll();
                mainPanel.add(createLoginPanel("Client"));
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        
        return accountTypePanel;
    }
    
    // Method to create login panel for all user types
    private JPanel createLoginPanel(String userType) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Color.WHITE);
        
        // Adding title
        JLabel titleLabel = createTitleLabel();
        
        // Welcome message based on user type
        JLabel welcomeLabel = new JLabel("Welcome Back, " + userType + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Creating username and password fields
        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(300, 40));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        // Adding labels for fields
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Creating button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton loginButton = createStyledButton("Login");
        JButton backButton = createStyledButton("Back");
        
        // Setting up back button action
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createAccountTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        buttonPanel.add(backButton);
        buttonPanel.add(loginButton);
        
        // Setting up login button action
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            // Validating input
            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(loginPanel, 
                    "Username and password are required", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Checking credentials and redirecting to appropriate home panel
            if (userType.equals("Owner")) {
                if (auth.authenticateVehicleOwner(username, password) ||  (username.equals(OWNER_USERNAME) && password.equals(OWNER_PASSWORD))) { //if (username.equals(OWNER_USERNAME) && password.equals(OWNER_PASSWORD)) {
                    mainPanel.removeAll();
                    mainPanel.add(createOwnerHomePanel(username));
                    mainPanel.revalidate();
                    mainPanel.repaint();
                    loggedInOwner = username;
                } else {
                    JOptionPane.showMessageDialog(loginPanel, 
                        "Invalid credentials", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (userType.equals("Client")) {
                if (auth.authenticateClient(username, password) || username.equals(CLIENT_USERNAME) && password.equals(CLIENT_PASSWORD)) { //  (username.equals(CLIENT_USERNAME) && password.equals(CLIENT_PASSWORD)) {
                    mainPanel.removeAll();
                    mainPanel.add(createClientHomePanel(username));
                    mainPanel.revalidate();
                    mainPanel.repaint();
                    loggedInClient = username;
                } else {
                    JOptionPane.showMessageDialog(loginPanel, 
                        "Invalid credentials", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (userType.equals("Cloud Controller")) { 
                if (auth.authenticateCloudController(username, password) || username.equals(CONTROLLER_USERNAME) && password.equals(CONTROLLER_PASSWORD)) { //username.equals(CONTROLLER_USERNAME) && password.equals(CONTROLLER_PASSWORD)
                    mainPanel.removeAll();
                    mainPanel.add(createCloudControllerHomePanel(username));
                    mainPanel.revalidate();
                    mainPanel.repaint();
                    loggedInController = username;
                } else {
                    JOptionPane.showMessageDialog(loginPanel, 
                        "Invalid credentials", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Adding components to the login panel
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(titleLabel);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(welcomeLabel);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(usernameLabel);
        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(usernameField);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(passwordLabel);
        loginPanel.add(Box.createVerticalStrut(5));
        loginPanel.add(passwordField);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(buttonPanel);
        
        return loginPanel;
    }

    // Method to create Cloud Controller login panel
    private JPanel createCloudControllerLoginPanel() {
        return createLoginPanel("Cloud Controller");
    }

    // Method to create user login panel
    private JPanel createUserLoginPanel(String userType) {
        return createLoginPanel(userType);
    }
    
    // Method to create registration type selection panel
    private JPanel createRegistrationTypePanel() {
        JPanel registrationTypePanel = new JPanel();
        registrationTypePanel.setLayout(new BoxLayout(registrationTypePanel, BoxLayout.Y_AXIS));
        registrationTypePanel.setBackground(Color.WHITE);
        
        // Adding main title
        JLabel titleLabel = createTitleLabel();
        
        // Adding registration type title
        JLabel subtitleLabel = new JLabel("Choose Account Type to Register:");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Creating radio button panel
        JPanel radioPanel = new JPanel(new GridBagLayout());
        radioPanel.setBackground(Color.WHITE);

        JPanel radioButtonsContainer = new JPanel();
        radioButtonsContainer.setLayout(new BoxLayout(radioButtonsContainer, BoxLayout.Y_AXIS));
        radioButtonsContainer.setBackground(Color.WHITE);

        // Creating and styling radio buttons (only Owner and Client for registration)
        JRadioButton ownerButton = new JRadioButton("Owner");
        JRadioButton clientButton = new JRadioButton("Client");

        Font radioFont = new Font("Arial", Font.PLAIN, 18);
        ownerButton.setFont(radioFont);
        clientButton.setFont(radioFont);

        // Grouping radio buttons
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(ownerButton);
        accountGroup.add(clientButton);

        // Adding buttons to container
        radioButtonsContainer.add(ownerButton);
        radioButtonsContainer.add(Box.createVerticalStrut(5));
        radioButtonsContainer.add(clientButton);

        radioPanel.add(radioButtonsContainer);

        // Creating button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backButton = createStyledButton("Back");
        JButton nextButton = createStyledButton("Next");
        
        // Setting up back button action
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            TheClientGUI newFrame = new TheClientGUI();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        // Adding components to the registration type panel
        registrationTypePanel.add(Box.createVerticalStrut(20));
        registrationTypePanel.add(titleLabel);
        registrationTypePanel.add(Box.createVerticalStrut(30));
        registrationTypePanel.add(subtitleLabel);
        registrationTypePanel.add(Box.createVerticalStrut(20));
        registrationTypePanel.add(radioPanel);
        registrationTypePanel.add(Box.createVerticalStrut(20));
        registrationTypePanel.add(buttonPanel);
        registrationTypePanel.add(Box.createVerticalStrut(200));
        
        // Setting up next button action
        nextButton.addActionListener(e -> {
            if (ownerButton.isSelected()) {
                mainPanel.removeAll();
                mainPanel.add(createRegistrationPanel("Owner"));
                mainPanel.revalidate();
                mainPanel.repaint();
            } else if (clientButton.isSelected()) {
                mainPanel.removeAll();
                mainPanel.add(createRegistrationPanel("Client"));
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });
        
        return registrationTypePanel;
    }

    // Method to create styled buttons with custom background color
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }

    // Overloaded method for creating styled buttons with default color
    private JButton createStyledButton(String text) {
        return createStyledButton(text, new Color(51, 122, 183));
    }

    // Utility method for creating consistent title
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Vehicular Cloud Real-Time System");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }
    
    // Method to create registration panel for Owner and Client
    private JPanel createRegistrationPanel(String userType) {
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new BoxLayout(registrationPanel, BoxLayout.Y_AXIS));
        registrationPanel.setBackground(Color.WHITE);

        // Adding title
        JLabel titleLabel = new JLabel(userType + " Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Personal Information");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creating form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Creating form fields
        final String[] labels = {"Username:", "Email:", "Create Password:", "Confirm Password:", 
                          "Address:", "State/Territory:", "Country:", "Phone Number:"};
        JTextField[] fields = new JTextField[labels.length];
        
        // Adding labels and fields to the form
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            
            fields[i] = new JTextField(20);
            fields[i].setPreferredSize(new Dimension(300, 30));
            
            // Making password fields secure
            if (i == 2 || i == 3) {
                fields[i] = new JPasswordField(20);
            }

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            formPanel.add(fields[i], gbc);
        }

        // Creating button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = createStyledButton("Back");
        JButton actionButton = createStyledButton(userType.equals("Owner") ? "Next" : "Submit");

        // Setting up back button action
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createRegistrationTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(backButton);
        buttonPanel.add(actionButton);

        // Adding components to the registration panel
        registrationPanel.add(Box.createVerticalStrut(20));
        registrationPanel.add(titleLabel);
        registrationPanel.add(Box.createVerticalStrut(10));
        registrationPanel.add(subtitleLabel);
        registrationPanel.add(Box.createVerticalStrut(20));
        registrationPanel.add(formPanel);
        registrationPanel.add(Box.createVerticalStrut(20));
        registrationPanel.add(buttonPanel);
        registrationPanel.add(Box.createVerticalStrut(20));

        // Setting up action button functionality
        actionButton.addActionListener(e -> {
            // Validating all fields
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(registrationPanel,
                        "All fields are required. Please fill in " + labels[i],
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Handling different user types
            try {
                String username = fields[0].getText();
                String email = fields[1].getText();
                String password = new String(((JPasswordField) fields[2]).getPassword());
                String confirmPassword = new String(((JPasswordField) fields[3]).getPassword());
                String address = fields[4].getText();
                String state = fields[5].getText();
                String country = fields[6].getText();
                String phoneNumber = fields[7].getText();
        
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(registrationPanel,
                            "Passwords do not match!",
                            "Validation Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                boolean ownersucess = false;
                boolean clientsucess = false;
                if (userType.equals("Owner")) {
                    ownersucess = auth.registerVehicleOwner(username, email, password, address, state, country, phoneNumber);
                } else {
                    clientsucess = auth.registerJobSubmitter(username, email, password, address, state, country, phoneNumber);
                }
        
                if (ownersucess) {
                    JOptionPane.showMessageDialog(registrationPanel,
                            "Registration successful!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    mainPanel.removeAll();
                    mainPanel.add(createOwnerHomePanel(username)); // Redirect to home
                    mainPanel.revalidate();
                    mainPanel.repaint();
                    loggedInOwner = username;
                } else if (clientsucess) {
                    JOptionPane.showMessageDialog(registrationPanel,
                            "Registration successful!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    mainPanel.removeAll();
                    mainPanel.add(createClientHomePanel(username)); // Redirect to home
                    mainPanel.revalidate();
                    mainPanel.repaint();
                    loggedInClient = username;
                }else {
                    JOptionPane.showMessageDialog(registrationPanel,
                            "Registration failed. Please try again.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(registrationPanel,
                        "Invalid input format. Please check your entries.",
                        "Input Error",
                        JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(registrationPanel,
                        "An unexpected error occurred. Please try again.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            
        });
        return registrationPanel;
    }

    // Method to create vehicle registration panel for Owners
    private JPanel createVehicleRegistrationPanel() {
        JPanel vehiclePanel = new JPanel();
        vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS));
        vehiclePanel.setBackground(Color.WHITE);

        // Adding title
        JLabel titleLabel = new JLabel("Owner Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Vehicle Registration");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Creating form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Creating form fields
        final String[] labels = {
            "VIN:", 
            "Model:", 
            "Make:", 
            "Year:", 
            "Computational Power:", 
            "Storage Capacity:"
        };
        JTextField[] fields = new JTextField[labels.length];
        
        // Adding labels and fields to the form
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            
            fields[i] = new JTextField(20);
            fields[i].setPreferredSize(new Dimension(300, 30));

            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.WEST;
            formPanel.add(label, gbc);

            gbc.gridx = 1;
            gbc.gridy = i;
            formPanel.add(fields[i], gbc);
        }

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
                // If that somehow doesn't work again do ("VCRTS-SWE-Project/resources/vehicle_resources.txt");
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
                mainPanel.add(createOwnerHomePanel(loggedInOwner)); 
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
            "Vehicle Identification Number:",
            "Approximate Residency Time (hh:mm:ss):",
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
            TheClientGUI newFrame = new TheClientGUI();
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
                    String timestamp = java.time.LocalDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    // Ask the user for input
                    Thread owner = new Thread(() -> {
                        // Show "Please wait" dialog
                       // JDialog j = VehicleOwner.vehicleServerResponse(ownerPanel);
                    
                        // Connect to the server
                        try (Socket socket = new Socket("127.0.0.1", 5000);
                             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                    
                            // Send the user input to the server
                            String userInput2 = "Timestamp: " + timestamp + "\n" +
                                                "Owner ID: " + fields[0].getText() + "\n" +
                                                "Vehicle Identification Number: " + fields[1].getText() + "\n" +
                                                "Residency Time (hh:mm:ss): " + fields[2].getText() + "\n" +
                                                "Computational Power: " + fields[3].getText() + "\n" +
                                                "Notes: " + fields[4].getText() + "\nEND";
                            output.println(userInput2);
                            System.out.println("Message sent to the server: " + userInput2);
                    
                            // Wait for the server's response
                            String serverResponse = input.readLine();
                            System.out.println("Response from the server: " + serverResponse);
                    
                           
                    
                            if (serverResponse.equals("Accepted")) {
                                try {
                                
                            
                                    // Establish connection to the database // url, sqlusername, password
                                    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
                            
                                    // SQL query to insert data
                                    String sql = "INSERT INTO Vehicle (USERNAME, ownerID, VIN, residencyTime, compPower, notes) VALUES (?, ?, ?, ?, ?, ?)";
                            
                                    // Prepare statement
                                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                    preparedStatement.setString(1, loggedInOwner); // USERNAME
                                    preparedStatement.setString(2,  fields[0].getText()); // ownerID
                                    preparedStatement.setString(3, fields[1].getText()); // VIN
                                    preparedStatement.setTime(4, Time.valueOf(fields[2].getText())); // residencyTime
                                    preparedStatement.setInt(5, Integer.parseInt(fields[3].getText())); // compPower
                                    preparedStatement.setString(6, fields[4].getText()); // notes
                            
                                    // Execute the statement
                                    preparedStatement.executeUpdate();
                            
                                    // Close the connection
                                    preparedStatement.close();
                                    connection.close();
                            
                                    JOptionPane.showMessageDialog(ownerPanel,
                                        "Vehicle resource submitted and saved successfully!",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            
                                    // Clear fields after successful submission
                                    for (JTextField field : fields) {
                                        field.setText("");
                                    }
                            
                                } catch (SQLException ex) {
                                    JOptionPane.showMessageDialog(ownerPanel,
                                        "Error connecting to the database: " + ex.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                    ex.printStackTrace();
                                }
                            } else if (serverResponse.equals("Rejected")) {
                                JOptionPane.showMessageDialog(ownerPanel,
                                    "Vehicle resource was rejected and failed to submit.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                                    for (JTextField field : fields) {
                                        field.setText("");
                                    }
                            } else {
                                JOptionPane.showMessageDialog(ownerPanel,
                                    "Please fill in all fields.",
                                    "Validation Error",
                                    JOptionPane.ERROR_MESSAGE);
                            }
                    
                        } catch (IOException ex) {
                            // Dispose of the "Please wait" dialog in case of error
                           // SwingUtilities.invokeLater(j::dispose);
                            JOptionPane.showMessageDialog(ownerPanel,
                                "Error connecting to the server: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    });
                    owner.start(); }
                    
        finally {
        }
    }
    }
        );

        // Add this new action listener for viewJobsButton
        viewJobsButton.addActionListener(e -> { //TYPE FOR CTRL-F later
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
            "Priority Level:",
            "Approximate Job Duration (hh:mm:ss):",
            "Job Deadline (year-month-day):",
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
            TheClientGUI newFrame = new TheClientGUI();
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
                    String timestamp = java.time.LocalDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    // Ask the user for input
                    Thread submitter = new Thread(() -> {
                        // Show "Please wait" dialog
                        // JDialog j = JobSubmitter.jobServerResponse(clientPanel);
            
                        // Connect to the server
                        try (Socket socket = new Socket("127.0.0.1", 5000);
                             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            
                            // Send the user input to the server
                            String userInput = "Timestamp: " + timestamp + "\n" +
                            "Client ID: " + fields[0].getText() + "\n" +
                            "Priority Level " + fields[1].getText() + "\n" +
                            "Approximate Job Duration (in hh:mm:ss): " + fields[2].getText() + "\n" +
                            "Job Deadline (yyyy/mm/dd): " + fields[3].getText() + "\n" +
                            "Purpose/Reason: " + fields[4].getText() + "\nEND";
                            output.println(userInput);
                            System.out.println("Message sent to the server: " + userInput);
            
                            // Wait for the server's response
                            String serverResponse = input.readLine();
                            System.out.println("Response from the server: " + serverResponse);
            
                            // Dispose of the "Please wait" dialog
                            // SwingUtilities.invokeLater(j::dispose);
            
                            if (serverResponse.equals("Accepted")) {
                                try {
                                // Database connection parameters
                                        //url, sqlusername, password
                                    Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME,DB_PASSWORD);
                                    String sql = "INSERT INTO JOB (USERNAME, clientID, priorityLevel, jobDuration, jobDeadline, purpose, timestamp) VALUES (?,?,?,?,?,?, CURRENT_TIMESTAMP)";

                                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                                    // Set parameters
                                    preparedStatement.setString(1,loggedInClient); //logged in User
                                    preparedStatement.setString(2,fields[0].getText());  // clientID
                                    preparedStatement.setString(3, fields[1].getText()); // set Priority Level
                                    preparedStatement.setTime(4, Time.valueOf(fields[2].getText())); // job duration
                                    java.sql.Date jobDeadline = java.sql.Date.valueOf(fields[3].getText()); // Assuming input is YYYY-MM-DD 
                                    preparedStatement.setDate(5, jobDeadline); // jobDeadline 
                                    preparedStatement.setString(6, fields[4].getText());//purpose
                            
                                    // Execute the statement
                                    preparedStatement.executeUpdate();
                            
                                    // Close the statement
                                    preparedStatement.close();
                            
                                    JOptionPane.showMessageDialog(clientPanel,
                                        "Job submitted and saved successfully!",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                            
                                    // Clear fields after successful submission
                                    for (JTextField field : fields) {
                                        field.setText("");
                                    }
                                
                                } catch (SQLException ext) {
                                    JOptionPane.showMessageDialog(clientPanel,
                                        "Error connecting to the database: " + ext.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                    ext.printStackTrace();
                                }
                            } else if (serverResponse.equals("Rejected")) {
                                JOptionPane.showMessageDialog(clientPanel,
                                    "Job submission was rejected.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                                    for (JTextField field : fields) {
                                        field.setText("");
                                    }
                            }
                        } catch (IOException ex) {
                            // Dispose of the "Please wait" dialog in case of error
                            // SwingUtilities.invokeLater(j::dispose);
                            JOptionPane.showMessageDialog(clientPanel,
                                "Error connecting to the server: " + ex.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                        }
                    });
                    submitter.start();
                } finally {
                }
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
    }

    // Cloud controller home panel (post registration)
    private JPanel createCloudControllerHomePanel(String username) {
        JPanel controllerPanel = new JPanel();
        controllerPanel.setLayout(new BoxLayout(controllerPanel, BoxLayout.Y_AXIS));
        controllerPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Cloud Controller Dashboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create two columns panel
        JPanel columnsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        columnsPanel.setBackground(Color.WHITE);
        columnsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        columnsPanel.setMaximumSize(new Dimension(900, 500));

        // Jobs Panel
        JPanel jobsPanel = new JPanel();
        jobsPanel.setLayout(new BoxLayout(jobsPanel, BoxLayout.Y_AXIS));
        jobsPanel.setBackground(new Color(240, 240, 240));
        jobsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel jobsTitle = new JLabel("Jobs Queue");
        jobsTitle.setFont(new Font("Arial", Font.BOLD, 18));
        jobsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add Calculate Time button at the top
        JButton calculateAllButton = createStyledButton("Calculate Completion Times");
        calculateAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Jobs List Panel with scroll
        JPanel jobsListPanel = new JPanel();
        jobsListPanel.setLayout(new BoxLayout(jobsListPanel, BoxLayout.Y_AXIS));
        jobsListPanel.setBackground(Color.WHITE);

        // Add components to jobs panel
        jobsPanel.add(jobsTitle);
        jobsPanel.add(Box.createVerticalStrut(10));
        jobsPanel.add(calculateAllButton);
        jobsPanel.add(Box.createVerticalStrut(10));
        jobsPanel.add(new JScrollPane(jobsListPanel));

        // Resources Panel
        JPanel resourcesPanel = new JPanel();
        resourcesPanel.setLayout(new BoxLayout(resourcesPanel, BoxLayout.Y_AXIS));
        resourcesPanel.setBackground(new Color(240, 240, 240));
        resourcesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel resourcesTitle = new JLabel("Available Resources");
        resourcesTitle.setFont(new Font("Arial", Font.BOLD, 18));
        resourcesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Resources List Panel with scroll
        JPanel resourcesListPanel = new JPanel();
        resourcesListPanel.setLayout(new BoxLayout(resourcesListPanel, BoxLayout.Y_AXIS));
        resourcesListPanel.setBackground(Color.WHITE);

        try { // Move this to a back-end class // If that somehow doesn't work again do ("VCRTS-SWE-Project/resources/vehicle_resources.txt");
            File resourcesFile = new File("resources/vehicle_resources.txt");
            if (resourcesFile.exists()) {
                java.util.Scanner scanner = new java.util.Scanner(resourcesFile);
                //StringBuilder currentContent = new StringBuilder();
                
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    
                    if (line.startsWith("Timestamp:")) {
                        // Create new resource panel with adjusted height
                        JPanel resourceItemPanel = new JPanel(new BorderLayout());
                        resourceItemPanel.setPreferredSize(new Dimension(400, 120));
                        resourceItemPanel.setMaximumSize(new Dimension(400, 120));
                        resourceItemPanel.setBackground(new Color(230, 230, 230));
                        
                        // Create info panel with GridBagLayout for centering
                        JPanel infoPanel = new JPanel(new GridBagLayout());
                        infoPanel.setBackground(new Color(230, 230, 230));
                        
                        // Create text panel with vertical BoxLayout
                        JPanel textPanel = new JPanel();
                        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                        textPanel.setBackground(new Color(230, 230, 230));
                        
                        // Add the timestamp with center alignment
                        JLabel infoLabel = new JLabel(line);
                        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        textPanel.add(infoLabel);
                        
                        // Add textPanel to infoPanel for centering
                        infoPanel.add(textPanel);
                        
                        // Add panel to resource item
                        resourceItemPanel.add(infoPanel, BorderLayout.CENTER);
                        resourcesListPanel.add(resourceItemPanel);
                        resourcesListPanel.add(Box.createVerticalStrut(10));
                        
                        //currentContent = new StringBuilder();  // Reset for next item
                    } else if (!line.equals("------------------------")) {
                        // Add content to the current resource panel
                        JPanel currentPanel = (JPanel)((JPanel)resourcesListPanel.getComponent(
                            resourcesListPanel.getComponentCount() - 2)).getComponent(0);
                        JPanel textPanel = (JPanel)currentPanel.getComponent(0);
                        
                        JLabel contentLabel = new JLabel(line);
                        contentLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        contentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        textPanel.add(contentLabel);
                    }
                }
                scanner.close();
            }
        } catch (IOException ex) {
            System.err.println("Error reading resources: " + ex.getMessage());
        }

        JScrollPane resourcesScrollPane = new JScrollPane(resourcesListPanel);
        resourcesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resourcesScrollPane.setPreferredSize(new Dimension(400, 400));

        // Add components to resources panel
        resourcesPanel.add(resourcesTitle);
        resourcesPanel.add(Box.createVerticalStrut(10));
        resourcesPanel.add(new JScrollPane(resourcesListPanel));

        // Add both panels to the columns panel
        columnsPanel.add(jobsPanel);
        columnsPanel.add(resourcesPanel);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0)); // Add bottom padding

        JButton logoutButton = createStyledButton("Logout");
        JButton refreshButton = createStyledButton("Refresh");
        JButton viewPendingButton = createStyledButton("View Pending Jobs & Resources");
        viewPendingButton.setPreferredSize(new Dimension(250, 40));


        
        logoutButton.addActionListener(e -> {
            mainPanel.removeAll();
            TheClientGUI newFrame = new TheClientGUI();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        refreshButton.addActionListener(e -> {
            mainPanel.removeAll();
            if(loggedInClient !=null) {
            mainPanel.add(createCloudControllerHomePanel(loggedInClient));
            }
            if(loggedInOwner !=null) {
                mainPanel.add(createCloudControllerHomePanel(loggedInOwner));
                }
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        viewPendingButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createPendingPanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        // Adding view jobs & resources button functionality later (Client-Server)

        buttonPanel.add(logoutButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(viewPendingButton);

        // Add all components to main panel
        controllerPanel.add(Box.createVerticalStrut(20));
        controllerPanel.add(titleLabel);
        controllerPanel.add(Box.createVerticalStrut(20));
        controllerPanel.add(columnsPanel);
        controllerPanel.add(Box.createVerticalStrut(10)); // Reduced from 20 to 10
        controllerPanel.add(buttonPanel);
        controllerPanel.add(Box.createVerticalStrut(10)); // Reduced from 20 to 10

        // Add calculate button functionality
        calculateAllButton.addActionListener(e -> {
            System.out.println("Button clicked"); // Debug print
            CloudController controller = CloudController.getInstance(); // Use getInstance instead of new
            controller.calculateCompletionTime();
            // Refresh the panel to show updated times
            mainPanel.removeAll();
            mainPanel.add(createCloudControllerHomePanel(username));
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        return controllerPanel;
    }

    // Submitted jobs panel for Clients
    private JPanel createSubmittedJobsPanel() {
        JPanel submittedPanel = new JPanel();
        submittedPanel.setLayout(new BoxLayout(submittedPanel, BoxLayout.Y_AXIS));
        submittedPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Submitted Jobs");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Jobs list panel (now wrapped in a scroll pane)
        JPanel jobsListPanel = new JPanel();
        jobsListPanel.setLayout(new BoxLayout(jobsListPanel, BoxLayout.Y_AXIS));
        jobsListPanel.setBackground(Color.WHITE);
        jobsListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(jobsListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Set preferred size for scroll pane
        scrollPane.setPreferredSize(new Dimension(450, 400));
        scrollPane.setMaximumSize(new Dimension(450, 400));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) { 
            String sql = "SELECT timestamp, jobID, USERNAME, clientID, priorityLevel, jobDuration, jobDeadline, purpose, completionTime " + 
            "FROM Job WHERE USERNAME = ?";            
             PreparedStatement preparedStatement = connection.prepareStatement(sql); 
             preparedStatement.setString(1, loggedInClient); // Set the specific username parameter
            ResultSet resultSet = preparedStatement.executeQuery(); 
            while (resultSet.next()) { 
                // Fetch job details from the result set 
                String timestamp = resultSet.getString("timestamp"); 
                int jobID = resultSet.getInt("jobID"); 
                String username = resultSet.getString("USERNAME"); 
                int clientID = resultSet.getInt("clientID"); 
                String priorityLevel = resultSet.getString("priorityLevel"); 
                String jobDurationStr = resultSet.getString("jobDuration"); 
                Date jobDeadline = resultSet.getDate("jobDeadline"); 
                String purpose = resultSet.getString("purpose"); 
                int completionTime = resultSet.getInt("completionTime"); // Convert jobDuration to LocalTime 
                LocalTime jobDuration = LocalTime.parse(jobDurationStr, DateTimeFormatter.ofPattern("HH:mm:ss")); 
                // Create a panel for each job 
                JPanel jobPanel = new JPanel(); 
                jobPanel.setLayout(new BoxLayout(jobPanel, BoxLayout.Y_AXIS)); 
                jobPanel.setBackground(Color.WHITE); 
                jobPanel.setBorder(BorderFactory.createCompoundBorder( BorderFactory.createLineBorder(new Color(200, 200, 200)), BorderFactory.createEmptyBorder(5, 5, 5, 5))); 
                // Add timestamp label to the job panel 
                JLabel timestampLabel = new JLabel("Timestamp: " + timestamp); 
                timestampLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
                timestampLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                jobPanel.add(timestampLabel); // Add job ID label to the job panel 
                JLabel jobIDLabel = new JLabel("Job ID: " + jobID); 
                jobIDLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
                jobIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                jobPanel.add(jobIDLabel); // Add username label to the job panel 
                JLabel usernameLabel = new JLabel("From User: " + username); 
                usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
                usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                jobPanel.add(usernameLabel); // Add client ID label to the job panel 
                JLabel clientIDLabel = new JLabel("Client ID: " + clientID); 
                clientIDLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
                clientIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                jobPanel.add(clientIDLabel); // Add priority level label to the job panel 
                JLabel priorityLevelLabel = new JLabel("Priority Level: " + priorityLevel); 
                priorityLevelLabel.setFont(new Font("Arial", Font.BOLD, 12)); 
                if ("high".equalsIgnoreCase(priorityLevel)) { 
                    priorityLevelLabel.setForeground(Color.RED); 
                } 
                    else if ("medium".equalsIgnoreCase(priorityLevel) || "med".equalsIgnoreCase(priorityLevel)) { 
                        priorityLevelLabel.setForeground(new Color(150, 150, 0)); 
                    } else { 
                        priorityLevelLabel.setForeground(Color.GREEN); 
                    } 
                    priorityLevelLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                    jobPanel.add(priorityLevelLabel); // Add job duration label to the job panel 
                    JLabel jobDurationLabel = new JLabel("Job Duration: " + jobDuration.toString()); 
                    jobDurationLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
                    jobDurationLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                    jobPanel.add(jobDurationLabel); // Add job deadline label to the job panel 
                    JLabel jobDeadlineLabel = new JLabel("Job Deadline: " + jobDeadline.toString()); 
                    jobDeadlineLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
                    jobDeadlineLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                    jobPanel.add(jobDeadlineLabel); // Check if the job deadline has passed and add a label if it has 
                    Date currentDate = new Date(System.currentTimeMillis()); 
                    if (jobDeadline.before(currentDate)) { 
                        JLabel deadlinePassedLabel = new JLabel("Deadline has passed!"); 
                        deadlinePassedLabel.setFont(new Font("Arial", Font.BOLD, 12)); 
                        deadlinePassedLabel.setForeground(Color.RED); 
                        deadlinePassedLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                        jobPanel.add(deadlinePassedLabel); } // Add purpose label to the job panel 
                        JLabel purposeLabel = new JLabel("Purpose: " + purpose); 
                        purposeLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
                        purposeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                        jobPanel.add(purposeLabel); // Add completion time label to the job panel 
                        JLabel completionTimeLabel = new JLabel("Completion Time: " + completionTime + " minutes"); 
                        completionTimeLabel.setFont(new Font("Arial", Font.PLAIN, 12)); 
                        completionTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); 
                        jobPanel.add(completionTimeLabel); // Add the job panel to the jobs list panel 
                        jobsListPanel.add(jobPanel); 
                        jobsListPanel.add(Box.createVerticalStrut(10)); 
                    } 
                    resultSet.close(); 
                    preparedStatement.close(); 
                } catch (SQLException ex) { 
                    System.err.println("Error reading from database: " + ex.getMessage());
                     ex.printStackTrace(); 
                } 

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton logoutButton = createStyledButton("Logout");
        JButton backButton = createStyledButton("Back");

        // Add button functionality
        logoutButton.addActionListener(e -> {
            mainPanel.removeAll();
            TheClientGUI newFrame = new TheClientGUI();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createClientHomePanel("Client")); // Pass actual username
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(logoutButton);
        buttonPanel.add(backButton);

        // Add components to panel
        submittedPanel.add(Box.createVerticalStrut(20));
        submittedPanel.add(titleLabel);
        submittedPanel.add(Box.createVerticalStrut(20));
        submittedPanel.add(scrollPane);  // Add scrollPane instead of jobsListPanel
        submittedPanel.add(buttonPanel);
        submittedPanel.add(Box.createVerticalStrut(20));

        return submittedPanel;
    }

    // Submitted resources/vehicles panel for Owners
    private JPanel createSubmittedResourcesPanel() {
        JPanel submittedPanel = new JPanel();
        submittedPanel.setLayout(new BoxLayout(submittedPanel, BoxLayout.Y_AXIS));
        submittedPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Registered Vehicles");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Resources list panel
        JPanel resourcesListPanel = new JPanel();
        resourcesListPanel.setLayout(new BoxLayout(resourcesListPanel, BoxLayout.Y_AXIS));
        resourcesListPanel.setBackground(Color.WHITE);
        resourcesListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(resourcesListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        scrollPane.setPreferredSize(new Dimension(450, 400));
        scrollPane.setMaximumSize(new Dimension(450, 400));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        //OPPPP
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            String sql = "SELECT timestamp, USERNAME, ownerID, VIN, residencyTime, compPower, notes FROM Vehicle WHERE USERNAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, loggedInOwner); // Set the specific username parameter

            System.out.println("Executing query for owner: " + loggedInOwner);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                System.out.println("No vehicles found for username: " + loggedInOwner);
            } else {
                while (resultSet.next()) {
                    // Fetch vehicle details from the result set
                    String timestamp = resultSet.getString("timestamp");
                    String username = resultSet.getString("USERNAME");
                    String ownerID = resultSet.getString("ownerID");
                    String VIN = resultSet.getString("VIN");
                    String residencyTimeStr = resultSet.getString("residencyTime");
                    int compPower = resultSet.getInt("compPower");
                    String notes = resultSet.getString("notes");

                    // Debugging output
                    System.out.println("Vehicle found - VIN: " + VIN);

                    // Convert residencyTime to LocalTime
                    LocalTime residencyTime = LocalTime.parse(residencyTimeStr, DateTimeFormatter.ofPattern("HH:mm:ss"));

                    // Create a panel for each vehicle
                    JPanel vehiclePanel = new JPanel();
                    vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS));
                    vehiclePanel.setBackground(Color.WHITE);
                    vehiclePanel.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(200, 200, 200)),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)));

                    // Add timestamp label to the vehicle panel
                    JLabel timestampLabel = new JLabel("Timestamp: " + timestamp);
                    timestampLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    timestampLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    vehiclePanel.add(timestampLabel);

                    // Add username label to the vehicle panel
                    JLabel usernameLabel = new JLabel("From Vehicle Owner: " + username);
                    usernameLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    vehiclePanel.add(usernameLabel);

                    // Add owner ID label to the vehicle panel
                    JLabel ownerIDLabel = new JLabel("Owner ID: " + ownerID);
                    ownerIDLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    ownerIDLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    vehiclePanel.add(ownerIDLabel);

                    // Add VIN label to the vehicle panel
                    JLabel vinLabel = new JLabel("VIN: " + VIN);
                    vinLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    vinLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    vehiclePanel.add(vinLabel);

                    // Add residency time label to the vehicle panel
                    JLabel residencyTimeLabel = new JLabel("Residency Time: " + residencyTime.toString());
                    residencyTimeLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    residencyTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    vehiclePanel.add(residencyTimeLabel);

                    // Add comp power label to the vehicle panel
                    JLabel compPowerLabel = new JLabel("Computation Power: " + compPower + " units");
                    compPowerLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    compPowerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    vehiclePanel.add(compPowerLabel);

                    // Add notes label to the vehicle panel
                    JLabel notesLabel = new JLabel("Notes: " + notes);
                    notesLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                    notesLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                    vehiclePanel.add(notesLabel);
                    resourcesListPanel.add(vehiclePanel);
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException ex) {
            System.err.println("Error reading from database: " + ex.getMessage());
            ex.printStackTrace();
        }
       

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton logoutButton = createStyledButton("Logout");
        JButton backButton = createStyledButton("Back");

        // Button functionality
        logoutButton.addActionListener(e -> {
            mainPanel.removeAll();
            TheClientGUI newFrame = new TheClientGUI();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createOwnerHomePanel(loggedInOwner));
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(logoutButton);
        buttonPanel.add(backButton);

        // Add components to panel
        submittedPanel.add(Box.createVerticalStrut(20));
        submittedPanel.add(titleLabel);
        submittedPanel.add(Box.createVerticalStrut(20));
        submittedPanel.add(scrollPane);
        submittedPanel.add(buttonPanel);
        submittedPanel.add(Box.createVerticalStrut(20));

        return submittedPanel;
    }

    private JPanel createPendingPanel() {
        JPanel pendingPanel = new JPanel();
        pendingPanel.setLayout(new BoxLayout(pendingPanel, BoxLayout.Y_AXIS));
        pendingPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Pending Jobs & Resources");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create two columns panel
        JPanel columnsPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        columnsPanel.setBackground(Color.WHITE);
        columnsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Left column - Pending Jobs
        JPanel jobsPanel = new JPanel();
        jobsPanel.setLayout(new BoxLayout(jobsPanel, BoxLayout.Y_AXIS));
        jobsPanel.setBackground(Color.WHITE);
        jobsPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel jobsTitle = new JLabel("Pending Jobs");
        jobsTitle.setFont(new Font("Arial", Font.BOLD, 20));
        jobsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Jobs scroll pane
        JPanel jobsListPanel = new JPanel();
        jobsListPanel.setLayout(new BoxLayout(jobsListPanel, BoxLayout.Y_AXIS));
        jobsListPanel.setBackground(Color.WHITE);

        // Example pending job item
        JPanel jobItem = createPendingJobItem("Pending Job #1");
        jobsListPanel.add(jobItem);
        jobsListPanel.add(Box.createVerticalStrut(10));

        JScrollPane jobsScrollPane = new JScrollPane(jobsListPanel);
        jobsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jobsScrollPane.setBorder(null);

        // Right column - Pending Resources
        JPanel resourcesPanel = new JPanel();
        resourcesPanel.setLayout(new BoxLayout(resourcesPanel, BoxLayout.Y_AXIS));
        resourcesPanel.setBackground(Color.WHITE);
        resourcesPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        JLabel resourcesTitle = new JLabel("Pending Resources");
        resourcesTitle.setFont(new Font("Arial", Font.BOLD, 20));
        resourcesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Resources scroll pane
        JPanel resourcesListPanel = new JPanel();
        resourcesListPanel.setLayout(new BoxLayout(resourcesListPanel, BoxLayout.Y_AXIS));
        resourcesListPanel.setBackground(Color.WHITE);

        // Example pending resource item
        JPanel resourceItem = createPendingResourceItem("Pending Resource #1");
        resourcesListPanel.add(resourceItem);
        resourcesListPanel.add(Box.createVerticalStrut(10));

        JScrollPane resourcesScrollPane = new JScrollPane(resourcesListPanel);
        resourcesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resourcesScrollPane.setBorder(null);

        // Add components to panels
        jobsPanel.add(Box.createVerticalStrut(10));
        jobsPanel.add(jobsTitle);
        jobsPanel.add(Box.createVerticalStrut(10));
        jobsPanel.add(jobsScrollPane);
        jobsPanel.add(Box.createVerticalStrut(10));

        resourcesPanel.add(Box.createVerticalStrut(10));
        resourcesPanel.add(resourcesTitle);
        resourcesPanel.add(Box.createVerticalStrut(10));
        resourcesPanel.add(resourcesScrollPane);
        resourcesPanel.add(Box.createVerticalStrut(10));

        columnsPanel.add(jobsPanel);
        columnsPanel.add(resourcesPanel);

        // Back button
        JButton backButton = createStyledButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createCloudControllerHomePanel(CONTROLLER_USERNAME));
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        // Add all components to main panel
        pendingPanel.add(Box.createVerticalStrut(20));
        pendingPanel.add(titleLabel);
        pendingPanel.add(Box.createVerticalStrut(20));
        pendingPanel.add(columnsPanel);
        pendingPanel.add(Box.createVerticalStrut(20));
        pendingPanel.add(backButton);
        pendingPanel.add(Box.createVerticalStrut(20));

        return pendingPanel;
    }

    private JPanel createPendingJobItem(String jobTitle) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(new Color(230, 230, 230));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel titleLabel = new JLabel(jobTitle);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(230, 230, 230));

        JButton acceptButton = createStyledButton("Accept Job");
        JButton rejectButton = createStyledButton("Reject Job");

        acceptButton.addActionListener(e -> {
            // Add accept job logic here
        });

        rejectButton.addActionListener(e -> {
            // Add reject job logic here
        });

        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);

        itemPanel.add(titleLabel);
        itemPanel.add(Box.createVerticalStrut(10));
        itemPanel.add(buttonPanel);

        return itemPanel;
    }

    private JPanel createPendingResourceItem(String resourceTitle) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(new Color(230, 230, 230));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        JLabel titleLabel = new JLabel(resourceTitle);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(230, 230, 230));

        JButton acceptButton = createStyledButton("Accept Resource");
        JButton rejectButton = createStyledButton("Reject Resource");

        acceptButton.addActionListener(e -> {
            // Add accept resource logic here
        });

        rejectButton.addActionListener(e -> {
            // Add reject resource logic here
        });

        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);

        itemPanel.add(titleLabel);
        itemPanel.add(Box.createVerticalStrut(10));
        itemPanel.add(buttonPanel);

        return itemPanel;
    }

     // Main method to run the program
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TheClientGUI frame = new TheClientGUI();
            frame.setVisible(true);
        });
    }
}