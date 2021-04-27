package GUI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import unoMain.Card;
import unoMain.Game;

public class wildCardGUI {
	JFrame wildCard = new JFrame();
	JPanel wildPanel = new JPanel();
	JButton[] choices = new JButton[14];
	Color[] colors = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE.brighter()};

	/**
	 * constructor of wildCard selection panel
	 * @param game current ongoing game
	 */
	public wildCardGUI(Game game) {
		for (int i = 0; i < 4; i++) {
			choices[i] = new JButton(Card.printColor(Card.Color.values()[i]));
			choices[i].setBackground(colors[i]);
			wildPanel.add(choices[i]);	
		}
		for (int i = 4; i < 14; i++) {
			choices[i] = new JButton(Card.printNumber(Card.Number.values()[i-4]));
			wildPanel.add(choices[i]);
		}
		
		wildPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
	    wildPanel.setBackground(Color.black);
		wildCard.setSize(400,300);
		wildCard.setDefaultCloseOperation(0);
		wildCard.setUndecorated(true);
		wildCard.setLocationRelativeTo(null);
		wildCard.setVisible(true);
		wildCard.add(wildPanel);
		wildCard.setTitle("Wild Card");
	}

	/**
	 * private variables getter
	 * @return
	 */
	public JButton[] getChoices() {
		return this.choices;
	}
	
	public JPanel getPanel() {
		return this.wildPanel;
	}
	
	public JFrame getFrame() {
		return this.wildCard;
	}
};
