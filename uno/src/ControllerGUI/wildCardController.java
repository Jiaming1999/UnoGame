package ControllerGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import GUI.invalidMsgGUI;
import GUI.unoGUI;
import GUI.wildCardGUI;
import unoMain.Card;
import unoMain.Game;

public class wildCardController {
	private wildCardGUI wildCard;
	
	/**
	 * controller for wildCard panel
	 * @param wild
	 * @param game
	 * @param main
	 */
	public wildCardController(wildCardGUI wild, Game game, unoGUI main) {
		this.wildCard = wild;
		JButton[] choices = wildCard.getChoices();
		JFrame frame = wildCard.getFrame();
		for (int i = 0; i < 4; i++) {
			final int innerInt = i;
			choices[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					game.changeColor(Card.Color.values()[innerInt]);
					game.changeNumber(Card.Number.ANY);
					game.getPlayers().nextPlayer();
					new invalidMsgGUI("Switching player, close to confirm",game,main);
					frame.dispose();
				}
			});
		}
		for (int i = 4; i < 14; i++) {
			final int innerInt = i - 4;
			choices[i].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					game.changeNumber(Card.Number.values()[innerInt]);
					game.changeColor(Card.Color.ANY);
					game.getPlayers().nextPlayer();
					new invalidMsgGUI("Switching player, close to confirm",game,main);
					frame.dispose();
				}
			});
		}
	}
	
};
