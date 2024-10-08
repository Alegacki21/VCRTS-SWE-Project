import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Titlebar extends JPanel {
	public Titlebar() {
		setLayout(new BorderLayout());
        setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("Discord");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setForeground(Color.white);
        titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
       
        add(titleLabel, BorderLayout.CENTER);
       
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
         

 
        JButton closeButton = new JButton("X");
        closeButton.setBackground(Color.RED);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> System.exit(0)); // Close the application
        buttonPanel.add(closeButton);
        
        add(buttonPanel, BorderLayout.EAST);
    }
}