// Importing necessary libraries for GUI components and file operations
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class VehicularCloudConsole extends JFrame {
    private JPanel mainPanel;
    private Integer jobCounter = 0;
    
    // Hardcoded login credentials for testing (for testing purposes ONLY)
    private static final String OWNER_USERNAME = "owner123";
    private static final String OWNER_PASSWORD = "password123";
    private static final String CLIENT_USERNAME = "client123";
    private static final String CLIENT_PASSWORD = "password123";
    private static final String CONTROLLER_USERNAME = "admin";
    private static final String CONTROLLER_PASSWORD = "admin123";
    
    // UI components for displaying resources and jobs
    private JPanel resourcesListPanel;
    private JScrollPane resourcesScrollPane;
    private JScrollPane jobsScrollPane;
    
    // Setting up the main application window
    public VehicularCloudConsole() {
        // Configuring main frame properties
        setTitle("Vehicular Cloud Real-Time System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        
        // Set up the main panel with a vertical box layout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        
        // Add title and description to the main panel
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; width: 800px;'>Vehicular Cloud Real-Time System</div></html>");
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
        
        // Create a panel for user action buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        // Add user type labels and buttons
        JLabel newUserLabel = new JLabel("New User?");
        JLabel existingUserLabel = new JLabel("Existing User?");
        
        JButton registerButton = createStyledButton("Register");
        JButton loginButton = createStyledButton("Login");
        
        // Set up action for the register button
        registerButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createRegistrationTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        // Set up action for the login button
        loginButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createAccountTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        // Add components to the button panel
        buttonPanel.add(newUserLabel);
        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(existingUserLabel);
        buttonPanel.add(loginButton);
        
        // Add all components to the main panel with spacing
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(descLabel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(buttonPanel);
        
        // Add the main panel to the frame
        add(mainPanel);
        
        // Initialize resource and job display components
        resourcesListPanel = new JPanel();
        resourcesListPanel.setLayout(new BoxLayout(resourcesListPanel, BoxLayout.Y_AXIS));
        resourcesListPanel.setBackground(Color.WHITE);
        
        resourcesScrollPane = new JScrollPane(resourcesListPanel);
        resourcesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resourcesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        
        // Set up jobs list panel and scroll pane
        JPanel jobsListPanel = new JPanel();
        jobsListPanel.setLayout(new BoxLayout(jobsListPanel, BoxLayout.Y_AXIS));
        jobsScrollPane = new JScrollPane(jobsListPanel);
    }
    
    // Create the account type selection panel for login
    private JPanel createAccountTypePanel() {
        JPanel accountTypePanel = new JPanel();
        accountTypePanel.setLayout(new BoxLayout(accountTypePanel, BoxLayout.Y_AXIS));
        accountTypePanel.setBackground(Color.WHITE);
        
        // Add main title
        JLabel titleLabel = createTitleLabel();
        
        JLabel subtitleLabel = new JLabel("Choose Account Type to Login:");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Set up radio buttons for account type selection
        JPanel radioPanel = new JPanel(new GridBagLayout());
        radioPanel.setBackground(Color.WHITE);

        JPanel radioButtonsContainer = new JPanel();
        radioButtonsContainer.setLayout(new BoxLayout(radioButtonsContainer, BoxLayout.Y_AXIS));
        radioButtonsContainer.setBackground(Color.WHITE);

        // Create and style radio buttons
        JRadioButton ownerButton = new JRadioButton("Owner");
        JRadioButton clientButton = new JRadioButton("Client");
        JRadioButton controllerButton = new JRadioButton("Cloud Controller");

        Font radioFont = new Font("Arial", Font.PLAIN, 18);
        ownerButton.setFont(radioFont);
        clientButton.setFont(radioFont);
        controllerButton.setFont(radioFont);

        // Group radio buttons
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(ownerButton);
        accountGroup.add(clientButton);
        accountGroup.add(controllerButton);

        radioButtonsContainer.add(ownerButton);
        radioButtonsContainer.add(Box.createVerticalStrut(5));
        radioButtonsContainer.add(clientButton);
        radioButtonsContainer.add(Box.createVerticalStrut(5));
        radioButtonsContainer.add(controllerButton);

        radioPanel.add(radioButtonsContainer);

        // Create button panel for navigation
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backButton = createStyledButton("Back");
        JButton nextButton = createStyledButton("Next");
        
        // Set up back button action to return to main screen
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        
        // Add components to the account type panel with spacing in between
        accountTypePanel.add(Box.createVerticalStrut(10));
        accountTypePanel.add(titleLabel);
        accountTypePanel.add(Box.createVerticalStrut(50));
        accountTypePanel.add(subtitleLabel);
        accountTypePanel.add(Box.createVerticalStrut(10));
        accountTypePanel.add(radioPanel);
        accountTypePanel.add(Box.createVerticalStrut(10));
        accountTypePanel.add(buttonPanel);
        accountTypePanel.add(Box.createVerticalStrut(200));
        
        // Set up next button action to navigate to appropriate login panel
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
    
    // Create login panel for all user types
    private JPanel createLoginPanel(String userType) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Color.WHITE);
        
        // Add title
        JLabel titleLabel = createTitleLabel();
        
        // Welcome message based on user type (For testing purposes ONLY)
        JLabel welcomeLabel = new JLabel("Welcome Back, " + userType + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create username and password input fields
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
        
        // Add labels for input fields
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create button panel for navigation
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton loginButton = createStyledButton("Login");
        JButton backButton = createStyledButton("Back");
        
        // Set up back button action to return to account type selection
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createAccountTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        buttonPanel.add(backButton);
        buttonPanel.add(loginButton);
        
        // Set up login button action with credential validation
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            // Validate input
            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(loginPanel, 
                    "Username and password are required", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check credentials and redirect to appropriate home panel
            if (userType.equals("Owner")) {
                if (username.equals(OWNER_USERNAME) && password.equals(OWNER_PASSWORD)) {
                    mainPanel.removeAll();
                    mainPanel.add(createOwnerHomePanel(username));
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(loginPanel, 
                        "Invalid credentials", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (userType.equals("Client")) {
                if (username.equals(CLIENT_USERNAME) && password.equals(CLIENT_PASSWORD)) {
                    mainPanel.removeAll();
                    mainPanel.add(createClientHomePanel(username));
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(loginPanel, 
                        "Invalid credentials", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            } else if (userType.equals("Cloud Controller")) {
                if (username.equals(CONTROLLER_USERNAME) && password.equals(CONTROLLER_PASSWORD)) {
                    mainPanel.removeAll();
                    mainPanel.add(createCloudControllerHomePanel(CONTROLLER_USERNAME));
                    mainPanel.revalidate();
                    mainPanel.repaint();
                } else {
                    JOptionPane.showMessageDialog(loginPanel, 
                        "Invalid credentials", 
                        "Login Error", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Add components to the login panel
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

    // Create Cloud Controller specific login panel
    private JPanel createCloudControllerLoginPanel() {
        return createLoginPanel("Cloud Controller");
    }
    
    // Create registration type selection panel
    private JPanel createRegistrationTypePanel() {
        JPanel registrationTypePanel = new JPanel();
        registrationTypePanel.setLayout(new BoxLayout(registrationTypePanel, BoxLayout.Y_AXIS));
        registrationTypePanel.setBackground(Color.WHITE);
        
        // Add main title
        JLabel titleLabel = createTitleLabel();
        
        // Add registration type selection prompt
        JLabel subtitleLabel = new JLabel("Choose Account Type to Register:");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create radio button panel for account type selection
        JPanel radioPanel = new JPanel(new GridBagLayout());
        radioPanel.setBackground(Color.WHITE);

        JPanel radioButtonsContainer = new JPanel();
        radioButtonsContainer.setLayout(new BoxLayout(radioButtonsContainer, BoxLayout.Y_AXIS));
        radioButtonsContainer.setBackground(Color.WHITE);

        // Create and style radio buttons (only Owner and Client for registration)
        JRadioButton ownerButton = new JRadioButton("Owner");
        JRadioButton clientButton = new JRadioButton("Client");

        Font radioFont = new Font("Arial", Font.PLAIN, 18);
        ownerButton.setFont(radioFont);
        clientButton.setFont(radioFont);

        // Group radio buttons
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(ownerButton);
        accountGroup.add(clientButton);

        // Add buttons to container
        radioButtonsContainer.add(ownerButton);
        radioButtonsContainer.add(Box.createVerticalStrut(5));
        radioButtonsContainer.add(clientButton);

        radioPanel.add(radioButtonsContainer);

        // Create button panel for navigation
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backButton = createStyledButton("Back");
        JButton nextButton = createStyledButton("Next");
        
        // Set up back button action to return to main screen
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        // Add components to the registration type panel
        registrationTypePanel.add(Box.createVerticalStrut(20));
        registrationTypePanel.add(titleLabel);
        registrationTypePanel.add(Box.createVerticalStrut(30));
        registrationTypePanel.add(subtitleLabel);
        registrationTypePanel.add(Box.createVerticalStrut(20));
        registrationTypePanel.add(radioPanel);
        registrationTypePanel.add(Box.createVerticalStrut(20));
        registrationTypePanel.add(buttonPanel);
        registrationTypePanel.add(Box.createVerticalStrut(200));
        
        // Set up next button action to navigate to appropriate registration panel
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

    // Create styled buttons with custom background color
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

    // Utility method for creating consistent title label
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Vehicular Cloud Real-Time System");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }
    
    // Create registration panel for Owner and Client
    private JPanel createRegistrationPanel(String userType) {
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new BoxLayout(registrationPanel, BoxLayout.Y_AXIS));
        registrationPanel.setBackground(Color.WHITE);

        // Add title
        JLabel titleLabel = new JLabel(userType + " Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Personal Information");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create form panel for user details
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create form fields
        final String[] labels = {"Name:", "Email:", "Create Password:", "Confirm Password:", 
                          "Address:", "State/Territory:", "Country:", "Phone Number:"};
        JTextField[] fields = new JTextField[labels.length];
        
        // Add labels and fields to the form
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            
            fields[i] = new JTextField(20);
            fields[i].setPreferredSize(new Dimension(300, 30));
            
            // Make password fields secure (for testing purposes ONLY)
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

        // Create button panel for navigation
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = createStyledButton("Back");
        JButton actionButton = createStyledButton(userType.equals("Owner") ? "Next" : "Submit");

        // Set up back button action to return to registration type selection
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createRegistrationTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(backButton);
        buttonPanel.add(actionButton);

        // Add components to the registration panel
        registrationPanel.add(Box.createVerticalStrut(20));
        registrationPanel.add(titleLabel);
        registrationPanel.add(Box.createVerticalStrut(10));
        registrationPanel.add(subtitleLabel);
        registrationPanel.add(Box.createVerticalStrut(20));
        registrationPanel.add(formPanel);
        registrationPanel.add(Box.createVerticalStrut(20));
        registrationPanel.add(buttonPanel);
        registrationPanel.add(Box.createVerticalStrut(20));

        // Set up action button functionality
        actionButton.addActionListener(e -> {
            // Validate all fields
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(registrationPanel,
                        "All fields are required. Please fill in " + labels[i],
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // Handle different user types
            if (userType.equals("Owner")) {
                mainPanel.removeAll();
                mainPanel.add(createVehicleRegistrationPanel());
                mainPanel.revalidate();
                mainPanel.repaint();
            } else {
                // Show success message for client registration
                JOptionPane.showMessageDialog(registrationPanel,
                    "Registration successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                    
                // Redirect to client home page
                mainPanel.removeAll();
                if (userType.equals("Client")) {
                    mainPanel.add(createClientHomePanel(fields[0].getText())); // Using Name field as username
                } 
                mainPanel.revalidate();
                mainPanel.repaint();
            }
        });

        return registrationPanel;
    }

    // Create vehicle registration panel for Owners
    private JPanel createVehicleRegistrationPanel() {
        JPanel vehiclePanel = new JPanel();
        vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS));
        vehiclePanel.setBackground(Color.WHITE);

        // Add title
        JLabel titleLabel = new JLabel("Owner Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Vehicle Registration");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create form panel for vehicle details
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create form fields
        final String[] labels = {
            "VIN:", 
            "Model:", 
            "Make:", 
            "Year:", 
            "Computational Power:", 
            "Storage Capacity:"
        };
        JTextField[] fields = new JTextField[labels.length];
        
        // Add labels and fields to the form
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

        // Create button panel for navigation
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = createStyledButton("Back");
        JButton submitButton = createStyledButton("Submit");

        // Set up back button action to return to personal information registration
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createRegistrationPanel("Owner"));
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        // Add components to the vehicle panel
        vehiclePanel.add(Box.createVerticalStrut(20));
        vehiclePanel.add(titleLabel);
        vehiclePanel.add(Box.createVerticalStrut(10));
        vehiclePanel.add(subtitleLabel);
        vehiclePanel.add(Box.createVerticalStrut(20));
        vehiclePanel.add(formPanel);
        vehiclePanel.add(Box.createVerticalStrut(20));
        vehiclePanel.add(buttonPanel);
        vehiclePanel.add(Box.createVerticalStrut(20));

        // Set up submit button action
        submitButton.addActionListener(e -> {
            try {
                // Create Vehicle object using builder pattern
                Vehicle vehicle = new Vehicle.VehicleBuilder(
                    fields[0].getText(),  // VIN
                    fields[1].getText(),  // Model 
                    fields[2].getText()   // Make
                )
                .year(Integer.parseInt(fields[3].getText()))
                .computationalPower(Double.parseDouble(fields[4].getText()))
                .storageCapacity(Double.parseDouble(fields[5].getText()))
                .build();

                // Create VehicleOwner and register vehicle
                VehicleOwner owner = new VehicleOwner(
                    "OWNER_" + System.currentTimeMillis(),
                    "", "", "Owner", "", 0.0, "",
                    new ArrayList<>(), ""
                );

                // Register the vehicle (this will write to the file)
                owner.registerVehicle(vehicle);

                File resourcesFile = new File("pendingResources/pending_vehicles.txt");
                if (resourcesFile.exists()) {
                    resourcesFile.delete();
                }
                
                owner.registerVehicle(vehicle);

                // Show success message
                JOptionPane.showMessageDialog(vehiclePanel,
                    "Vehicle registered successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                    
                // Redirect to owner home
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

    // Create Owner home panel (post login & registration)
    private JPanel createOwnerHomePanel(String ownerName) {
        JPanel ownerPanel = new JPanel();
        ownerPanel.setLayout(new BoxLayout(ownerPanel, BoxLayout.Y_AXIS));
        ownerPanel.setBackground(Color.WHITE);

        // Add welcome header
        JLabel welcomeLabel = new JLabel("Welcome Home, " + ownerName + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add submission form title
        JLabel submissionTitle = new JLabel("Vehicle Resource Submission");
        submissionTitle.setFont(new Font("Arial", Font.BOLD, 20));
        submissionTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create form panel for vehicle resource submission
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
                    // Create resources directory if it doesn't exist
                    File directory = new File("pendingResources");
                    if (!directory.exists()) {
                        directory.mkdir();
                    }

                    // Add timestamp
                    String timestamp = java.time.LocalDateTime.now()
                        .format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                    
                    FileWriter writer = new FileWriter("pendingResources/pending_vehicles.txt", true);
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

        // Add components to main panel with spacing in between
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

        // Add components to main panel with spacing in between
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

        boolean hasJobs = false;
        try {
            File jobsFile = new File("jobs/submitted_jobs.txt");
            if (jobsFile.exists() && jobsFile.length() > 0) {
                java.util.Scanner scanner = new java.util.Scanner(jobsFile);
                JPanel currentJobItem = null;
                
                while (scanner.hasNextLine()) {
                    hasJobs = true;
                    String line = scanner.nextLine();
                    
                    if (line.startsWith("JobID:") || line.startsWith("Job ID:")) {
                        // Create new job panel
                        currentJobItem = new JPanel();
                        currentJobItem.setLayout(new BoxLayout(currentJobItem, BoxLayout.Y_AXIS));
                        currentJobItem.setBackground(Color.WHITE);
                        currentJobItem.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(200, 200, 200)),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)
                        ));
                        
                        // Add info panel
                        JPanel infoPanel = new JPanel();
                        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                        infoPanel.setBackground(Color.WHITE);
                        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        
                        JLabel infoLabel = new JLabel(line);
                        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        infoPanel.add(infoLabel);
                        currentJobItem.add(infoPanel);
                        
                        // Add assign button
                        JButton assignButton = createStyledButton("Assign Job");
                        assignButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                        currentJobItem.add(assignButton);
                        
                        jobsListPanel.add(currentJobItem);
                        jobsListPanel.add(Box.createVerticalStrut(10));
                    } else if (currentJobItem != null && !line.equals("------------------------")) {
                        // Add additional job information
                        JPanel infoPanel = (JPanel)currentJobItem.getComponent(0);
                        JLabel infoLabel = new JLabel(line);
                        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        infoPanel.add(infoLabel);
                    }
                }
                scanner.close();
            }
            
            if (!hasJobs) {
                JPanel emptyMessagePanel = new JPanel(new GridBagLayout());
                emptyMessagePanel.setBackground(Color.WHITE);
                
                JLabel noJobsLabel = new JLabel("No jobs in queue");
                noJobsLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                noJobsLabel.setForeground(Color.GRAY);
                
                emptyMessagePanel.add(noJobsLabel);
                jobsListPanel.add(Box.createVerticalGlue()); // Add space at top
                jobsListPanel.add(emptyMessagePanel);
                jobsListPanel.add(Box.createVerticalGlue()); // Add space at bottom
            }
        } catch (IOException ex) {
            System.err.println("Error reading jobs: " + ex.getMessage());
        }

        // Add components to jobs panel
        jobsPanel.add(Box.createVerticalStrut(10));
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

        boolean hasResources = false;
        try {
            File resourcesFile = new File("resources/approved_vehicles.txt");
            if (resourcesFile.exists() && resourcesFile.length() > 0) {
                java.util.Scanner scanner = new java.util.Scanner(resourcesFile);
                JPanel currentResourceItem = null;
                
                while (scanner.hasNextLine()) {
                    hasResources = true;
                    String line = scanner.nextLine();
                    
                    if (line.startsWith("VIN:")) {
                        currentResourceItem = new JPanel();
                        currentResourceItem.setLayout(new BoxLayout(currentResourceItem, BoxLayout.Y_AXIS));
                        currentResourceItem.setBackground(Color.WHITE);
                        currentResourceItem.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createLineBorder(new Color(200, 200, 200)),
                            BorderFactory.createEmptyBorder(5, 5, 5, 5)
                        ));
                        
                        JPanel infoPanel = new JPanel();
                        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                        infoPanel.setBackground(Color.WHITE);
                        infoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        currentResourceItem.add(infoPanel);
                        
                        resourcesListPanel.add(currentResourceItem);
                        resourcesListPanel.add(Box.createVerticalStrut(10));
                    }
                    
                    if (currentResourceItem != null && !line.equals("------------------------")) {
                        JPanel infoPanel = (JPanel)currentResourceItem.getComponent(0);
                        JLabel infoLabel = new JLabel(line);
                        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        infoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
                        infoPanel.add(infoLabel);
                    }
                }
                scanner.close();
            }
            
            if (!hasResources) {
                JPanel emptyMessagePanel = new JPanel(new GridBagLayout());
                emptyMessagePanel.setBackground(Color.WHITE);
                
                JLabel noResourcesLabel = new JLabel("No available resources");
                noResourcesLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                noResourcesLabel.setForeground(Color.GRAY);
                
                emptyMessagePanel.add(noResourcesLabel);
                resourcesListPanel.add(Box.createVerticalGlue()); // Add space at top
                resourcesListPanel.add(emptyMessagePanel);
                resourcesListPanel.add(Box.createVerticalGlue()); // Add space at bottom
            }
        } catch (IOException ex) {
            System.err.println("Error reading resources: " + ex.getMessage());
        }

        // Create scroll pane for resources
        JScrollPane resourcesScrollPane = new JScrollPane(resourcesListPanel);
        resourcesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resourcesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        // Add components to resources panel
        resourcesPanel.add(Box.createVerticalStrut(10));
        resourcesPanel.add(resourcesTitle);
        resourcesPanel.add(Box.createVerticalStrut(10));
        resourcesPanel.add(resourcesScrollPane);
        resourcesPanel.add(Box.createVerticalStrut(10));

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
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        refreshButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createCloudControllerHomePanel(username));
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

        try {
            File jobsFile = new File("pendingJobs/pending_jobs.txt");
            if (jobsFile.exists()) {
                java.util.Scanner scanner = new java.util.Scanner(jobsFile);
                
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    
                    if (line.startsWith("Timestamp:")) {
                        // Create new job panel with adjusted height
                        JPanel jobItemPanel = new JPanel(new BorderLayout());
                        jobItemPanel.setPreferredSize(new Dimension(400, 120));
                        jobItemPanel.setMaximumSize(new Dimension(400, 120));
                        jobItemPanel.setBackground(new Color(230, 230, 230));
                        
                        // Create info panel with vertical layout
                        JPanel infoPanel = new JPanel();
                        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                        infoPanel.setBackground(new Color(230, 230, 230));
                        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                        
                        // Add the client ID
                        JLabel infoLabel = new JLabel(line);
                        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        infoPanel.add(infoLabel);
                        
                        // Create button panel with GridBagLayout
                        JPanel buttonPanel = new JPanel(new GridBagLayout());
                        buttonPanel.setBackground(new Color(230, 230, 230));
                        
                        // Add View Status button
                        JButton viewStatusButton = createStyledButton("View Status");
                        viewStatusButton.setPreferredSize(new Dimension(120, 30));
                        
                        // Add the action listener
                        final String jobDetails = line;
                        viewStatusButton.addActionListener(e -> {
                            String status = "Pending";
                            JOptionPane.showMessageDialog(
                                jobItemPanel,
                                "Job Status: " + status + "\n\n" +
                                "Waiting for Cloud Controller assignment.\n" +
                                jobDetails,
                                "Job Status",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        });
                        
                        buttonPanel.add(viewStatusButton);
                        
                        // Add panels to job item
                        jobItemPanel.add(infoPanel, BorderLayout.CENTER);
                        jobItemPanel.add(buttonPanel, BorderLayout.EAST);
                        jobsListPanel.add(jobItemPanel);
                        jobsListPanel.add(Box.createVerticalStrut(10));
                        
                    } else if (!line.equals("------------------------")) {
                        // Add content to the current job panel
                        JPanel currentPanel = (JPanel)((JPanel)jobsListPanel.getComponent(
                            jobsListPanel.getComponentCount() - 2)).getComponent(0);
                        JLabel contentLabel = new JLabel(line);
                        contentLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        currentPanel.add(contentLabel);
                    }
                }
                scanner.close();
            }
        } catch (IOException ex) {
            System.err.println("Error reading jobs: " + ex.getMessage());
        }

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton logoutButton = createStyledButton("Logout");
        JButton backButton = createStyledButton("Back");

        // Add button functionality
        logoutButton.addActionListener(e -> {
            mainPanel.removeAll();
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
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
    
        try {
            File pendingFile = new File("pendingResources/pending_vehicles.txt");
            if (pendingFile.exists()) {
                java.util.Scanner scanner = new java.util.Scanner(pendingFile);
                
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    
                    if (line.startsWith("Timestamp:")) {
                        // Create new vehicle panel with fixed dimensions
                        JPanel vehicleItemPanel = new JPanel(new BorderLayout());
                        vehicleItemPanel.setPreferredSize(new Dimension(400, 120));
                        vehicleItemPanel.setMaximumSize(new Dimension(400, 120));
                        vehicleItemPanel.setBackground(new Color(230, 230, 230));
                        
                        // Create info panel with vertical layout
                        JPanel infoPanel = new JPanel();
                        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
                        infoPanel.setBackground(new Color(230, 230, 230));
                        infoPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                        
                        // Add the timestamp
                        JLabel infoLabel = new JLabel(line);
                        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        infoPanel.add(infoLabel);
                        
                        // Create button panel with GridBagLayout
                        JPanel buttonPanel = new JPanel(new GridBagLayout());
                        buttonPanel.setBackground(new Color(230, 230, 230));
                        
                        // Add View Status button
                        JButton viewStatusButton = createStyledButton("View Status");
                        viewStatusButton.setPreferredSize(new Dimension(120, 30));
                        
                        // Add the action listener
                        final String vehicleDetails = line;
                        viewStatusButton.addActionListener(e -> {
                            String status = "Active";
                            JOptionPane.showMessageDialog(
                                vehicleItemPanel,
                                "Vehicle Status: Pending\n\n" +
                                "Waiting for Cloud Controller assignment.\n" +
                                vehicleDetails,
                                "Vehicle Status",
                                JOptionPane.INFORMATION_MESSAGE
                            );
                        });
                        
                        buttonPanel.add(viewStatusButton);
                        
                        // Add panels to vehicle item
                        vehicleItemPanel.add(infoPanel, BorderLayout.CENTER);
                        vehicleItemPanel.add(buttonPanel, BorderLayout.EAST);
                        resourcesListPanel.add(vehicleItemPanel);
                        resourcesListPanel.add(Box.createVerticalStrut(10));
                        
                    } else if (!line.equals("------------------------")) {
                        // Add content to the current vehicle panel
                        JPanel currentPanel = (JPanel)((JPanel)resourcesListPanel.getComponent(
                            resourcesListPanel.getComponentCount() - 2)).getComponent(0);
                        JLabel contentLabel = new JLabel(line);
                        contentLabel.setFont(new Font("Arial", Font.PLAIN, 12));
                        currentPanel.add(contentLabel);
                    }
                }
                scanner.close();
            }
        } catch (IOException ex) {
            System.err.println("Error reading resources: " + ex.getMessage());
        }
    
        // Create scroll pane
        JScrollPane scrollPane = new JScrollPane(resourcesListPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        
        // Set preferred size for scroll pane
        scrollPane.setPreferredSize(new Dimension(450, 400));
        scrollPane.setMaximumSize(new Dimension(450, 400));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
    
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        
        JButton backButton = createStyledButton("Back");
        JButton logoutButton = createStyledButton("Logout");
    
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createOwnerHomePanel("Owner"));
            mainPanel.revalidate();
            mainPanel.repaint();
        });
    
        logoutButton.addActionListener(e -> {
            mainPanel.removeAll();
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
    
        buttonPanel.add(backButton);
        buttonPanel.add(logoutButton);
    
        // Add components to panel
        submittedPanel.add(Box.createVerticalStrut(20));
        submittedPanel.add(titleLabel);
        submittedPanel.add(Box.createVerticalStrut(20));
        submittedPanel.add(scrollPane);
        submittedPanel.add(buttonPanel);
        submittedPanel.add(Box.createVerticalStrut(20));
    
        return submittedPanel;
    }

    // Pending jobs & resources panel for Cloud Controller for accepting/rejecting jobs/resources
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

        // Update Jobs List Panel
        try {
            File jobsFile = new File("pendingJobs/pending_jobs.txt");
            System.out.println("Debug: Checking for jobs file at " + jobsFile.getAbsolutePath());
            
            if (jobsFile.exists()) {
                System.out.println("Debug: Jobs file exists, reading contents...");
                java.util.Scanner scanner = new java.util.Scanner(jobsFile);
                StringBuilder currentJob = new StringBuilder();
                int jobCount = 0;
                
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("Timestamp:")) {
                        if (currentJob.length() > 0) {
                            JPanel jobItem = createPendingJobItem(currentJob.toString());
                            jobsListPanel.add(jobItem);
                            jobsListPanel.add(Box.createVerticalStrut(10));
                            jobCount++;
                        }
                        currentJob = new StringBuilder(line + "\n");
                    } else if (!line.equals("------------------------")) {
                        currentJob.append(line).append("\n");
                    }
                }
                
                // Add last job if exists
                if (currentJob.length() > 0) {
                    JPanel jobItem = createPendingJobItem(currentJob.toString());
                    jobsListPanel.add(jobItem);
                    jobCount++;
                }
                scanner.close();
                System.out.println("Debug: Found " + jobCount + " pending jobs");
            } else {
                System.out.println("Debug: Jobs file does not exist");
            }
            
            // Add "No pending jobs" message if no jobs were found
            if (jobsListPanel.getComponentCount() == 0) {
                System.out.println("Debug: No jobs found, displaying empty message");
                JPanel emptyMessagePanel = new JPanel();
                emptyMessagePanel.setLayout(new GridBagLayout());
                emptyMessagePanel.setBackground(Color.WHITE);
                emptyMessagePanel.setPreferredSize(new Dimension(400, 300));
                
                JLabel noJobsLabel = new JLabel("No pending jobs");
                noJobsLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                noJobsLabel.setForeground(Color.GRAY);
                
                emptyMessagePanel.add(noJobsLabel);
                jobsListPanel.add(emptyMessagePanel);
            }
        } catch (IOException ex) {
            System.err.println("Error reading jobs: " + ex.getMessage());
            ex.printStackTrace();
        }

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

        // Update Resources List Panel
        try {
            File resourcesFile = new File("pendingResources/pending_vehicles.txt");
            System.out.println("Debug: Checking for resources file at " + resourcesFile.getAbsolutePath());
            
            if (resourcesFile.exists()) {
                System.out.println("Debug: Resources file exists, reading contents...");
                java.util.Scanner scanner = new java.util.Scanner(resourcesFile);
                StringBuilder currentResource = new StringBuilder();
                int resourceCount = 0;
                
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("Timestamp:")) {
                        if (currentResource.length() > 0) {
                            JPanel resourceItem = createPendingResourceItem(currentResource.toString());
                            resourcesListPanel.add(resourceItem);
                            resourcesListPanel.add(Box.createVerticalStrut(10));
                            resourceCount++;
                        }
                        currentResource = new StringBuilder(line + "\n");
                    } else if (!line.equals("------------------------")) {
                        currentResource.append(line).append("\n");
                    }
                }
                
                // Add last resource if exists
                if (currentResource.length() > 0) {
                    JPanel resourceItem = createPendingResourceItem(currentResource.toString());
                    resourcesListPanel.add(resourceItem);
                    resourceCount++;
                }
                scanner.close();
                System.out.println("Debug: Found " + resourceCount + " pending resources");
            } else {
                System.out.println("Debug: Resources file does not exist");
            }
            
            // Add "No pending resources" message if no resources were found
            if (resourcesListPanel.getComponentCount() == 0) {
                System.out.println("Debug: No resources found, displaying empty message");
                JPanel emptyMessagePanel = new JPanel();
                emptyMessagePanel.setLayout(new GridBagLayout());
                emptyMessagePanel.setBackground(Color.WHITE);
                emptyMessagePanel.setPreferredSize(new Dimension(400, 300));
                
                JLabel noResourcesLabel = new JLabel("No pending resources");
                noResourcesLabel.setFont(new Font("Arial", Font.ITALIC, 14));
                noResourcesLabel.setForeground(Color.GRAY);
                
                emptyMessagePanel.add(noResourcesLabel);
                resourcesListPanel.add(emptyMessagePanel);
            }
        } catch (IOException ex) {
            System.err.println("Error reading resources: " + ex.getMessage());
            ex.printStackTrace();
        }

        // Create scroll panes for jobs and resources
        JScrollPane jobsScrollPane = new JScrollPane(jobsListPanel);
        jobsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jobsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jobsScrollPane.setBorder(null);
        jobsScrollPane.getVerticalScrollBar().setUnitIncrement(16);

        JScrollPane resourcesScrollPane = new JScrollPane(resourcesListPanel);
        resourcesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        resourcesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        resourcesScrollPane.setBorder(null);
        resourcesScrollPane.getVerticalScrollBar().setUnitIncrement(16);

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

    // Create pending job item panel for Cloud Controller
    private JPanel createPendingJobItem(String jobDetails) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(new Color(230, 230, 230));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 175));

        // Add job details
        String[] lines = jobDetails.split("\n");
        for (String line : lines) {
            JLabel detailLabel = new JLabel(line);
            detailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            detailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemPanel.add(detailLabel);
        }

        // Add buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(230, 230, 230));
        
        JButton acceptButton = createStyledButton("Accept Job");
        JButton rejectButton = createStyledButton("Reject Job");
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);
        
        itemPanel.add(Box.createVerticalStrut(10));
        itemPanel.add(buttonPanel);

        return itemPanel;
    }

    // Create pending resource item panel for Cloud Controller
    private JPanel createPendingResourceItem(String resourceDetails) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBackground(new Color(230, 230, 230));
        itemPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        itemPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 175));

        // Add resource details
        String[] lines = resourceDetails.split("\n");
        for (String line : lines) {
            JLabel detailLabel = new JLabel(line);
            detailLabel.setFont(new Font("Arial", Font.PLAIN, 12));
            detailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemPanel.add(detailLabel);
        }

        // Add buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(230, 230, 230));
        
        JButton acceptButton = createStyledButton("Accept Resource");
        JButton rejectButton = createStyledButton("Reject Resource");
        buttonPanel.add(acceptButton);
        buttonPanel.add(rejectButton);

        
        itemPanel.add(Box.createVerticalStrut(10));
        itemPanel.add(buttonPanel);

        return itemPanel;
    }

     // Main method to run the program
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VehicularCloudConsole frame = new VehicularCloudConsole();
            frame.setVisible(true);
        });
    }
}
