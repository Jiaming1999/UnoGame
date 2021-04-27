package GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class gameOverGUI {
	JFrame message = new JFrame();
	JPanel messagePanel = new JPanel();

	public gameOverGUI(String gameOver) {
		messagePanel.add(new JLabel(gameOver));
		messagePanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));
		message.setLocationRelativeTo(null);
		message.add(messagePanel);
		message.setTitle("Message");
		message.setSize(400,300);
		message.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		message.setVisible(true);
	}
}
