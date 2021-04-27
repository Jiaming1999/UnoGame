package GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the panel showing reminding information
 * @author 11043
 *
 */
public class reminderGUI {
	JFrame message = new JFrame();
	JPanel messagePanel = new JPanel();

	public reminderGUI(String gameOver) {
		messagePanel.add(new JLabel(gameOver));
		messagePanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));
		message.add(messagePanel);
		message.setLocationRelativeTo(null);
		message.setTitle("Message");
		message.setSize(400,300);
		message.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		message.setVisible(true);
	}
}
