import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class Register extends Database implements ActionListener {

    private static final JFrame window = new JFrame("Register Account");
    private static final JLabel email = new JLabel("Email:");
    private static final JLabel username = new JLabel("Username:");
    private static final JLabel password = new JLabel("Password:");
    private static final JTextField typeEmail = new JTextField(20);
    private static final JTextField typeUserName = new JTextField(20);
    private static final JPasswordField typePassword = new JPasswordField(20);
    private JLabel title = new JLabel("Create an Account");
    private JButton cont = new JButton("Continue");
    private final JLabel success = new JLabel("");
	public static HashMap<String, String> users = new HashMap<>();

  //  private HashMap<String, String> userCredentials;  
    public Register() {
    
        Container contentPane = window.getContentPane();
        contentPane.setLayout(new BorderLayout());

        ImageIcon icon = new ImageIcon("images/cloudconsole.png");
        window.setIconImage(icon.getImage());

        title.setFont(new Font("GG Sans", Font.BOLD, 20));
        title.setBounds(200, 0, 300, 100);
        window.add(title);

        email.setBounds(50, 100, 100, 25);
        window.add(email);

        username.setBounds(50, 200, 75, 25);
        window.add(username);

        typeEmail.setBounds(150, 100, 300, 25);
        window.add(typeEmail);

        typeUserName.setBounds(150, 200, 300, 25);
        window.add(typeUserName);

        password.setBounds(50, 300, 100, 25);
        window.add(password);

        typePassword.setBounds(150, 300, 300, 25);
        window.add(typePassword);

        cont.setBounds(50, 380, 450, 30);
        cont.setFocusable(false);
        cont.setBackground(new Color(50, 40, 40));
        cont.setForeground(Color.white);
        window.add(cont);
        cont.addActionListener(this);

        success.setForeground(Color.red);
        success.setBounds(250, 450, 300, 100);
        window.add(success);

        window.setResizable(false);
        window.getContentPane().setBackground(new Color(40, 90, 70));
        window.setSize(600, 600);
        window.setLocationRelativeTo(null);
        window.setLayout(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = typeUserName.getText();
        String pass = new String(typePassword.getPassword());
        String mail = typeEmail.getText();

        if (e.getSource() == cont) {
            if (mail.isEmpty() || user.isEmpty() || pass.isEmpty()) {
                success.setText("One or more fields are blank");
            } else {
                if (users.containsKey(user)) {
                    success.setText("Username already exists.");
                } else {
                    // Add the new user to the system
                    users.put(user, pass);
                    success.setForeground(Color.GREEN);
                    success.setText("Registration successful!");
					window.dispose();
					 new Login();
            
                }
            }
        }
    }
	public static void main(String[] args) { // TO test the Register
		Register r = new Register();
	}
}
