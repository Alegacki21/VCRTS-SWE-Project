

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends Database implements ActionListener{

	
	private static final JFrame frame = new JFrame("VehicularCloudConsole");
	
	JPanel mainPanel = new JPanel(new GridBagLayout());
	

	private JLabel title = new JLabel("Welcome Back!");
	private  final JLabel username = new JLabel("Username:");
	private  final JLabel password = new JLabel("Password:");
	private  final JTextField typeUserName = new JTextField(30);
	private  final JPasswordField typePassword = new JPasswordField(30);
	private  final JButton login = new JButton("Login");
	private  final JLabel success = new JLabel("");
	private  final JButton register = new JButton("Register");
	
	public Login() { 
	
        Container contentPane = frame.getContentPane();
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 
		 ImageIcon icon = new ImageIcon("images/cloudconsole.png");
		 frame.setIconImage(icon.getImage());

		  
		GridBagConstraints gbc = new GridBagConstraints();
		mainPanel.setBackground(Color.DARK_GRAY);
        gbc.insets = new Insets(30, 0, 15, 30); // Padding between components
	
        
        gbc.gridx = 0;
        gbc.gridy = 0;

        
        gbc.gridx = 1;
        gbc.gridy = 0;
        Font l = new Font("GG Sans", Font.PLAIN, 14);
	      title.setFont(l);
     
        title.setForeground(Color.white);
        mainPanel.add(title, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        username.setForeground(Color.white);
        mainPanel.add(username, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(typeUserName, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        password.setForeground(Color.white);
        mainPanel.add(password, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(typePassword, gbc);
		frame.add(mainPanel);	
		
		gbc.gridx= 1;
	    gbc.gridy= 3;
	    success.setForeground(Color.RED);
	    mainPanel.add(success,gbc);
		
		gbc.gridx = 1;
        gbc.gridy = 4;
        login.setFocusable(false);    
        login.addActionListener(this);
        login.setBackground(new Color(51,153,255));
        login.setForeground(Color.white);
        login.setPreferredSize(new Dimension(350,25));
        mainPanel.add(login, gbc);
     
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        register.setFocusable(false);    
        register.addActionListener(this);
        register.setBackground(new Color(51,153,255));
        register.setForeground(Color.white);
        register.setPreferredSize(new Dimension(100,18));
        mainPanel.add(register, gbc);
        
      
		frame.setResizable(true);
		frame.setSize(550,500);

		frame.setLocationRelativeTo(null);

		frame.getContentPane().setBackground(Color.DARK_GRAY);

		 frame.add(mainPanel);
			
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	
		if(e.getSource() == register) {
			new Register();
			return;
		}
		String user = typeUserName.getText();
		String pass = typePassword.getText();
		if(e.getSource() == login) {
			if (Register.users.containsKey(user) && 
			Register.users.get(user).equals(pass)) {
			success.setText("Login successful!");
			success.setForeground(Color.GREEN);
			frame.dispose();
			new VehicularCloudConsole();
		} 
				else {
					success.setText("Username or password is incorrect!");
				}
			} 
			

		}
		public static void main(String[] args) { // // TO test the Login
			Login l = new Login();
		}
	}
	



