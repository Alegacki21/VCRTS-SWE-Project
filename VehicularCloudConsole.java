// Importing Libraries
import javax.swing.*;
import java.awt.*;

public class VehicularCloudConsole extends JFrame {
    private JPanel mainPanel;
    
    //Constants for login credentials
    private static final String OWNER_USERNAME = "owner123";
    private static final String OWNER_PASSWORD = "password123";
    private static final String CLIENT_USERNAME = "client123";
    private static final String CLIENT_PASSWORD = "password123";
    private static final String CONTROLLER_USERNAME = "admin";
    private static final String CONTROLLER_PASSWORD = "admin123";
    
    // Main frame
    public VehicularCloudConsole() {
        // Set up the main frame
        setTitle("Vehicular Cloud Real-Time System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        
        // Main panel with vertical BoxLayout
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);
        
        // Title
        JLabel titleLabel = new JLabel("<html><div style='text-align: center; width: 800px;'>Vehicular Cloud Real-Time System</div></html>");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Description of VCRTS (from Requirements Specification)
        String description = "<html><div style='text-align: center; width: 800px;'>The Vehicular Cloud Real-Time System (VCRTS) is vehicular cloud system<br>" +
                           "that leverages the computational resources of parked vehicles in parking<br>" +
                           "lots to execute computational jobs and create a static cloud computing<br>" +
                           "environment.</div></html>";
        JLabel descLabel = new JLabel(description);
        descLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);
        
        // User type labels and buttons
        JLabel newUserLabel = new JLabel("New User?");
        JLabel existingUserLabel = new JLabel("Existing User?");
        
        JButton registerButton = createStyledButton("Register");
        JButton loginButton = createStyledButton("Login");
        
        // Register button action listener
        registerButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createRegistrationTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        // Login button action listener
        loginButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createAccountTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        // Components to button panel
        buttonPanel.add(newUserLabel);
        buttonPanel.add(registerButton);
        buttonPanel.add(Box.createHorizontalStrut(20));
        buttonPanel.add(existingUserLabel);
        buttonPanel.add(loginButton);
        
        // Components to main panel with spacing
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(30));
        mainPanel.add(descLabel);
        mainPanel.add(Box.createVerticalStrut(50));
        mainPanel.add(buttonPanel);
        
        // Main panel to frame
        add(mainPanel);
        
    }
    
    // Account type panel
    private JPanel createAccountTypePanel() {
        JPanel accountTypePanel = new JPanel();
        accountTypePanel.setLayout(new BoxLayout(accountTypePanel, BoxLayout.Y_AXIS));
        accountTypePanel.setBackground(Color.WHITE);
        
        // Main title
        JLabel titleLabel = createTitleLabel();
        
        JLabel subtitleLabel = new JLabel("Choose Account Type to Login:");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Radio panel
        JPanel radioPanel = new JPanel(new GridBagLayout());
        radioPanel.setBackground(Color.WHITE);

        JPanel radioButtonsContainer = new JPanel();
        radioButtonsContainer.setLayout(new BoxLayout(radioButtonsContainer, BoxLayout.Y_AXIS));
        radioButtonsContainer.setBackground(Color.WHITE);

        // Creating Radio buttons
        JRadioButton ownerButton = new JRadioButton("Owner");
        JRadioButton clientButton = new JRadioButton("Client");
        JRadioButton controllerButton = new JRadioButton("Cloud Controller");

        // Styling radio buttons
        Font radioFont = new Font("Arial", Font.PLAIN, 18);
        ownerButton.setFont(radioFont);
        clientButton.setFont(radioFont);
        controllerButton.setFont(radioFont);

        // Adding buttons to button group
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backButton = createStyledButton("Back");
        JButton nextButton = createStyledButton("Next");
        
        // Back button action listener
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);
        
        accountTypePanel.add(Box.createVerticalStrut(10));
        accountTypePanel.add(titleLabel);
        accountTypePanel.add(Box.createVerticalStrut(50));
        accountTypePanel.add(subtitleLabel);
        accountTypePanel.add(Box.createVerticalStrut(10));
        accountTypePanel.add(radioPanel);
        accountTypePanel.add(Box.createVerticalStrut(10));
        accountTypePanel.add(buttonPanel);
        accountTypePanel.add(Box.createVerticalStrut(200));
        
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
    
    // Login for all user types
    private JPanel createLoginPanel(String userType) {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBackground(Color.WHITE);
        
        // Using consistent title
        JLabel titleLabel = createTitleLabel();
        
        // Welcome message based on user type
        JLabel welcomeLabel = new JLabel("Welcome Back, " + userType + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Username field
        JTextField usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(300, 40));
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        // Password field
        JPasswordField passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 40));
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(Color.LIGHT_GRAY),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        
        // Field labels
        JLabel usernameLabel = new JLabel("Username");
        JLabel passwordLabel = new JLabel("Password");
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Button panel for login and back buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton loginButton = createStyledButton("Login");
        JButton backButton = createStyledButton("Back");
        
        // Back button functionality
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createAccountTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        buttonPanel.add(backButton);
        buttonPanel.add(loginButton);
        
        // Add login button functionality
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            
            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(loginPanel, 
                    "Username and password are required", 
                    "Validation Error", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
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
                    mainPanel.add(createCloudControllerHomePanel());
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

    // Cloud Controller login
    private JPanel createCloudControllerLoginPanel() {
        return createLoginPanel("Cloud Controller");
    }

    // User login
    private JPanel createUserLoginPanel(String userType) {
        return createLoginPanel(userType);
    }
    
    // Registration type panel
    private JPanel createRegistrationTypePanel() {
        JPanel registrationTypePanel = new JPanel();
        registrationTypePanel.setLayout(new BoxLayout(registrationTypePanel, BoxLayout.Y_AXIS));
        registrationTypePanel.setBackground(Color.WHITE);
        
        // Add main title
        JLabel titleLabel = createTitleLabel();
        
        // Add registration type title
        JLabel subtitleLabel = new JLabel("Choose Account Type to Register:");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Create radio panel with minimal spacing
        JPanel radioPanel = new JPanel(new GridBagLayout());
        radioPanel.setBackground(Color.WHITE);

        JPanel radioButtonsContainer = new JPanel();
        radioButtonsContainer.setLayout(new BoxLayout(radioButtonsContainer, BoxLayout.Y_AXIS));
        radioButtonsContainer.setBackground(Color.WHITE);

        // Create and style radio buttons (only Owner and Client for registration)
        JRadioButton ownerButton = new JRadioButton("Owner");
        JRadioButton clientButton = new JRadioButton("Client");

        // Style radio buttons
        Font radioFont = new Font("Arial", Font.PLAIN, 18);
        ownerButton.setFont(radioFont);
        clientButton.setFont(radioFont);

        // Add buttons to button group
        ButtonGroup accountGroup = new ButtonGroup();
        accountGroup.add(ownerButton);
        accountGroup.add(clientButton);

        // Add buttons to container with minimal spacing
        radioButtonsContainer.add(ownerButton);
        radioButtonsContainer.add(Box.createVerticalStrut(5));
        radioButtonsContainer.add(clientButton);

        radioPanel.add(radioButtonsContainer);

        // Create button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton backButton = createStyledButton("Back");
        JButton nextButton = createStyledButton("Next");
        
        // Add back button functionality
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });
        
        buttonPanel.add(backButton);
        buttonPanel.add(nextButton);

        registrationTypePanel.add(Box.createVerticalStrut(20));
        registrationTypePanel.add(titleLabel);
        registrationTypePanel.add(Box.createVerticalStrut(30));
        registrationTypePanel.add(subtitleLabel);
        registrationTypePanel.add(Box.createVerticalStrut(20));
        registrationTypePanel.add(radioPanel);
        registrationTypePanel.add(Box.createVerticalStrut(20));
        registrationTypePanel.add(buttonPanel);
        registrationTypePanel.add(Box.createVerticalStrut(200));
        
        // Add next button functionality
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

    // Every button is universally styled the same way
    private JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setBackground(bgColor);
        button.setForeground(Color.BLACK);
        button.setFocusPainted(false);
        return button;
    }

    // Add overloaded method for default color
    private JButton createStyledButton(String text) {
        return createStyledButton(text, new Color(51, 122, 183));
    }

    // Add utility method for creating consistent title
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Vehicular Cloud Real-Time System");
        titleLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return titleLabel;
    }
    
    // Main method to run the program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VehicularCloudConsole frame = new VehicularCloudConsole();
            frame.setVisible(true);
        });
    }

    // Registration panel for Owner and Client registration
    private JPanel createRegistrationPanel(String userType) {
        JPanel registrationPanel = new JPanel();
        registrationPanel.setLayout(new BoxLayout(registrationPanel, BoxLayout.Y_AXIS));
        registrationPanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel(userType + " Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Personal Information");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create and use form fields
        final String[] labels = {"Name:", "Email:", "Create Password:", "Confirm Password:", 
                          "Address:", "State/Territory:", "Country:", "Phone Number:"};
        JTextField[] fields = new JTextField[labels.length];
        
        // Add labels and fields
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setFont(new Font("Arial", Font.PLAIN, 14));
            
            fields[i] = new JTextField(20);
            fields[i].setPreferredSize(new Dimension(300, 30));
            
            // Make password fields secure
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

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = createStyledButton("Back");
        JButton actionButton = createStyledButton(userType.equals("Owner") ? "Next" : "Submit");

        // Back button functionality
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createRegistrationTypePanel());
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(backButton);
        buttonPanel.add(actionButton);

        // Add all components to the panel
        registrationPanel.add(Box.createVerticalStrut(20));
        registrationPanel.add(titleLabel);
        registrationPanel.add(Box.createVerticalStrut(10));
        registrationPanel.add(subtitleLabel);
        registrationPanel.add(Box.createVerticalStrut(20));
        registrationPanel.add(formPanel);
        registrationPanel.add(Box.createVerticalStrut(20));
        registrationPanel.add(buttonPanel);
        registrationPanel.add(Box.createVerticalStrut(20));

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
            
            // If validation passes
            if (userType.equals("Owner")) {
                mainPanel.removeAll();
                mainPanel.add(createVehicleRegistrationPanel());
                mainPanel.revalidate();
                mainPanel.repaint();
            } else {
                // Show success message
                JOptionPane.showMessageDialog(registrationPanel,
                    "Registration successful!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                    
                // Redirect to appropriate home page
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

    // Vehicle registration panel for Owners
    private JPanel createVehicleRegistrationPanel() {
        JPanel vehiclePanel = new JPanel();
        vehiclePanel.setLayout(new BoxLayout(vehiclePanel, BoxLayout.Y_AXIS));
        vehiclePanel.setBackground(Color.WHITE);

        // Title
        JLabel titleLabel = new JLabel("Owner Registration");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitleLabel = new JLabel("Vehicle Registration");
        subtitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Create and use form fields
        final String[] labels = {
            "VIN:", 
            "Model:", 
            "Make:", 
            "Year:", 
            "Computational Power:", 
            "Storage Capacity:"
        };
        JTextField[] fields = new JTextField[labels.length];
        
        // Add labels and fields
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

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);

        JButton backButton = createStyledButton("Back");
        JButton submitButton = createStyledButton("Submit");

        // Back button functionality
        backButton.addActionListener(e -> {
            mainPanel.removeAll();
            mainPanel.add(createRegistrationPanel("Owner"));
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(backButton);
        buttonPanel.add(submitButton);

        // Add all components to the panel
        vehiclePanel.add(Box.createVerticalStrut(20));
        vehiclePanel.add(titleLabel);
        vehiclePanel.add(Box.createVerticalStrut(10));
        vehiclePanel.add(subtitleLabel);
        vehiclePanel.add(Box.createVerticalStrut(20));
        vehiclePanel.add(formPanel);
        vehiclePanel.add(Box.createVerticalStrut(20));
        vehiclePanel.add(buttonPanel);
        vehiclePanel.add(Box.createVerticalStrut(20));

        submitButton.addActionListener(e -> {
            // Validating all fields
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(vehiclePanel,
                        "All fields are required. Please fill in " + labels[i],
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            
            // If validation passes
            JOptionPane.showMessageDialog(vehiclePanel,
                "Registration successful!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);
                
            // Redirecting to owner home page
            mainPanel.removeAll();
            mainPanel.add(createOwnerHomePanel("Owner")); // You might want to store and pass the actual username
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        return vehiclePanel;
    }

    // Owner home panel (post login & registration)
    private JPanel createOwnerHomePanel(String ownerName) {
        JPanel ownerPanel = new JPanel();
        ownerPanel.setLayout(new BoxLayout(ownerPanel, BoxLayout.Y_AXIS));
        ownerPanel.setBackground(Color.WHITE);

        // Welcome header
        JLabel welcomeLabel = new JLabel("Welcome Home, " + ownerName + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Submission form title
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

        // Form fields
        String[] labels = {
            "Owner ID:",
            "Vehicle Information:",
            "Approximate Residency Time:",
            "Available Computational Power:",
            "Notes:"
        };

        JTextField[] fields = new JTextField[labels.length];

        // Labels and fields
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
                JOptionPane.showMessageDialog(ownerPanel,
                    "Vehicle resource submitted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                // Clear fields after successful submission
                for (JTextField field : fields) {
                    field.setText("");
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
            "Approximate Job Duration:",
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
            // Validate fields
            boolean allFilled = true;
            for (int i = 0; i < fields.length; i++) {
                if (fields[i].getText().trim().isEmpty()) {
                    allFilled = false;
                    break;
                }
            }

            if (allFilled) {
                JOptionPane.showMessageDialog(clientPanel,
                    "Job submitted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                // Clear fields after successful submission
                for (JTextField field : fields) {
                    field.setText("");
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
    }

    // Cloud controller home panel (post registration)
    private JPanel createCloudControllerHomePanel() {
        JPanel controllerPanel = new JPanel();
        controllerPanel.setLayout(new BoxLayout(controllerPanel, BoxLayout.Y_AXIS));
        controllerPanel.setBackground(Color.LIGHT_GRAY);

        // Dashboard Title
        JLabel dashboardTitle = new JLabel("Admin Dashboard");
        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 28));
        dashboardTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Main content panel (split view)
        JPanel contentPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        contentPanel.setBackground(Color.LIGHT_GRAY);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Resources Panel
        JPanel resourcesPanel = new JPanel();
        resourcesPanel.setLayout(new BoxLayout(resourcesPanel, BoxLayout.Y_AXIS));
        resourcesPanel.setBackground(Color.WHITE);
        resourcesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel resourcesTitle = new JLabel("Available Resources");
        resourcesTitle.setFont(new Font("Arial", Font.BOLD, 20));
        resourcesTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Jobs Panel
        JPanel jobsPanel = new JPanel();
        jobsPanel.setLayout(new BoxLayout(jobsPanel, BoxLayout.Y_AXIS));
        jobsPanel.setBackground(Color.WHITE);
        jobsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel jobsTitle = new JLabel("Submitted Jobs");
        jobsTitle.setFont(new Font("Arial", Font.BOLD, 20));
        jobsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Resource and Job listings (placeholder for now)
        JPanel[] resourceItems = new JPanel[5];
        JPanel[] jobItems = new JPanel[5];

        for (int i = 0; i < 5; i++) {
            // Resource items
            resourceItems[i] = new JPanel();
            resourceItems[i].setPreferredSize(new Dimension(300, 50));
            resourceItems[i].setBackground(new Color(230, 230, 230));
            JLabel resourceLabel = new JLabel("Resource #" + (i + 1));
            resourceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            resourceItems[i].add(resourceLabel);

            // Job items
            jobItems[i] = new JPanel();
            jobItems[i].setPreferredSize(new Dimension(300, 50));
            jobItems[i].setBackground(new Color(230, 230, 230));
            JLabel jobLabel = new JLabel("Job #" + (i + 1));
            jobLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            jobItems[i].add(jobLabel);
        }

        // Navigation buttons panels
        JPanel resourceButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        resourceButtonPanel.setBackground(Color.WHITE);
        resourceButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JButton resourcePrevButton = createStyledButton("Previous");
        JButton resourceNextButton = createStyledButton("Next Page");
        resourceButtonPanel.add(resourcePrevButton);
        resourceButtonPanel.add(resourceNextButton);

        JPanel jobButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        jobButtonPanel.setBackground(Color.WHITE);
        jobButtonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        JButton computeButton = createStyledButton("Compute", new Color(40, 167, 69));
        jobButtonPanel.add(computeButton);
        JButton jobNextButton = createStyledButton("Next Page");
        jobButtonPanel.add(jobNextButton);

        // Adding components to resource panel
        resourcesPanel.add(resourcesTitle);
        resourcesPanel.add(Box.createVerticalStrut(20));
        for (JPanel resourceItem : resourceItems) {
            resourcesPanel.add(resourceItem);
            resourcesPanel.add(Box.createVerticalStrut(10));
        }
        resourcesPanel.add(resourceButtonPanel);

        // Adding components to jobs panel
        jobsPanel.add(jobsTitle);
        jobsPanel.add(Box.createVerticalStrut(20));
        for (JPanel jobItem : jobItems) {
            jobsPanel.add(jobItem);
            jobsPanel.add(Box.createVerticalStrut(10));
        }
        jobsPanel.add(jobButtonPanel);

        // Adding panels to content panel
        contentPanel.add(resourcesPanel);
        contentPanel.add(jobsPanel);

        // Logout button
        JButton logoutButton = createStyledButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.addActionListener(e -> {
            mainPanel.removeAll();
            VehicularCloudConsole newFrame = new VehicularCloudConsole();
            mainPanel.add(newFrame.mainPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        controllerPanel.add(Box.createVerticalStrut(20));
        controllerPanel.add(dashboardTitle);
        controllerPanel.add(Box.createVerticalStrut(20));
        controllerPanel.add(contentPanel);
        controllerPanel.add(Box.createVerticalStrut(20));
        controllerPanel.add(logoutButton);
        controllerPanel.add(Box.createVerticalStrut(20));

        // Add button functionality
        computeButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(controllerPanel,
                "No jobs available to compute at this time.",
                "No Jobs",
                JOptionPane.INFORMATION_MESSAGE);
        });

        resourceNextButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(controllerPanel,
                "No more resources to display.",
                "End of List",
                JOptionPane.INFORMATION_MESSAGE);
        });

        jobNextButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(controllerPanel,
                "No more jobs to display.",
                "End of List",
                JOptionPane.INFORMATION_MESSAGE);
        });

        resourcePrevButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(controllerPanel,
                "This is the first page.",
                "First Page",
                JOptionPane.INFORMATION_MESSAGE);
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

        // Jobs list panel
        JPanel jobsListPanel = new JPanel();
        jobsListPanel.setLayout(new BoxLayout(jobsListPanel, BoxLayout.Y_AXIS));
        jobsListPanel.setBackground(Color.WHITE);
        jobsListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add sample jobs (replace with actual submitted jobs later)
        for (int i = 1; i <= 3; i++) {
            JPanel jobItem = new JPanel();
            jobItem.setPreferredSize(new Dimension(400, 50));
            jobItem.setMaximumSize(new Dimension(400, 50));
            jobItem.setBackground(new Color(230, 230, 230));
            JLabel jobLabel = new JLabel("Job #" + i);
            jobLabel.setFont(new Font("Arial", Font.PLAIN, 16));
            jobItem.add(jobLabel);
            jobsListPanel.add(jobItem);
            jobsListPanel.add(Box.createVerticalStrut(10));
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
        submittedPanel.add(jobsListPanel);
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
        JLabel titleLabel = new JLabel("Resources Submitted");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Resources list panel
        JPanel resourcesListPanel = new JPanel();
        resourcesListPanel.setLayout(new BoxLayout(resourcesListPanel, BoxLayout.Y_AXIS));
        resourcesListPanel.setBackground(Color.WHITE);
        resourcesListPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add sample resource (replace with actual submitted resources later)
        JPanel resourceItem = new JPanel();
        resourceItem.setPreferredSize(new Dimension(400, 50));
        resourceItem.setMaximumSize(new Dimension(400, 50));
        resourceItem.setBackground(new Color(230, 230, 230));
        JLabel resourceLabel = new JLabel("Resource #1");
        resourceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resourceItem.add(resourceLabel);
        resourcesListPanel.add(resourceItem);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        
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
            mainPanel.add(createOwnerHomePanel("Owner")); // Pass actual username
            mainPanel.revalidate();
            mainPanel.repaint();
        });

        buttonPanel.add(logoutButton);
        buttonPanel.add(backButton);

        // Add components to panel
        submittedPanel.add(Box.createVerticalStrut(20));
        submittedPanel.add(titleLabel);
        submittedPanel.add(Box.createVerticalStrut(20));
        submittedPanel.add(resourcesListPanel);
        submittedPanel.add(buttonPanel);
        submittedPanel.add(Box.createVerticalStrut(20));

        return submittedPanel;
    }
}
