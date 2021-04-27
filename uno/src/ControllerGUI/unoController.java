package ControllerGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import unoMain.Card;
import unoMain.Game;
import unoMain.Player;
import unoMain.Players;
import GUI.gameOverGUI;
import GUI.invalidMsgGUI;
import GUI.unoGUI;
import GUI.wildCardGUI;

public class unoController {

	/**
	 * constructor for main game page GUI's controlling file
	 * @param unoGUI
	 * @param game
	 */
	public unoController(unoGUI unoGUI, Game game) {
		if (game.isGameOver()) {
			new gameOverGUI("Game Over, Winner is: " + game.getWinner());
			unoGUI.getFrame().dispose();
			return;
		}
		int currentPlayerIndx = game.getPlayers().getCurrentPlayerIndx();
		JButton[] handCards = unoGUI.getCards();
		Players playersInfo = game.getPlayers();
		Player currentPlayer = playersInfo.getPlayer(currentPlayerIndx);
		if (!currentPlayer.getIsAI()) {
			for (int i = 0; i < handCards.length; i++) {
				final int inner_i = i;
				handCards[i].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Card played = game.playHandler(currentPlayerIndx, inner_i);
						afterPlayed(played,game,unoGUI);
					}
				});
			}
		}
		unoGUI.getSkipButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Card played = game.playHandler(currentPlayerIndx,-1);
				afterPlayed(played,game,unoGUI);
			}
		});
	}
	
	/**
	 * interact happened after card buttons are pressed
	 * @param played
	 * @param game
	 * @param unoGUI
	 */
	public void afterPlayed(Card played, Game game, unoGUI unoGUI) {
		if (played == null) {
			unoGUI.getFrame().dispose();
			new invalidMsgGUI("<html>no valid card played, receiving penalty, <br/>now please switch player, close to confirm<html/>",game,unoGUI);
		}else if (played.getCardColor() == Card.Color.WILD) {
			unoGUI.getFrame().dispose();
			wildCardGUI wild = new wildCardGUI(game);
			new wildCardController(wild,game,unoGUI);
		}else {
			unoGUI.getFrame().dispose();
			new invalidMsgGUI("Switching player, close to confirm",game,unoGUI);
		}
	}
	
}
