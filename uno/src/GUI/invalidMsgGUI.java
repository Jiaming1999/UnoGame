package GUI;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ControllerGUI.unoController;
import unoMain.Game;

public class invalidMsgGUI {
	JFrame message = new JFrame();
	JPanel messagePanel = new JPanel();

	public invalidMsgGUI(String invalidMessage, Game game, unoGUI main) {
		messagePanel.add(new JLabel(invalidMessage));
		messagePanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));
		message.add(messagePanel);
		message.setTitle("Message");
		message.setSize(400,300);
		message.setLocationRelativeTo(null);
		message.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				unoGUI uno = new unoGUI(game);
				new unoController(uno,game);
		    }
		});
		message.setVisible(true);
	}
	
}
