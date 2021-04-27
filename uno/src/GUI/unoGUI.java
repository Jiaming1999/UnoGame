package GUI;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ControllerGUI.unoController;
import unoMain.Deck;
import unoMain.Game;
import unoMain.Player;
import unoMain.Players;

/**
 * main panel where handling all game logic
 * @author 11043
 *
 */
public class unoGUI {
	private boolean isVisible = true;
	JFrame frame = new JFrame();
	JButton[] handCards;
	JButton skip;
	
	public unoGUI(Game game) {
		JPanel mainPanel = new JPanel();
		JPanel gameStatePanel = new JPanel();
		JPanel handCardPanel = new JPanel();
		handCardPanelSetUp(handCardPanel, game);
	    gameStatePanelSetUp(gameStatePanel,game);		
		mainPanelSetUp(mainPanel, gameStatePanel, handCardPanel);		
	    frame.setSize(800, 600);
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("UNO GUI");
		frame.setVisible(isVisible);
	}

	/**
	 * set up the panel for playerhandcard display
	 * @param handCardPanel the panel to be setup
	 * @param game ongoing game
	 */
	public void handCardPanelSetUp(JPanel handCardPanel, Game game) {
		int currentPlayerIndx = game.getPlayers().getCurrentPlayerIndx();
		Players playersInfo = game.getPlayers();
		Player currentPlayer = playersInfo.getPlayer(currentPlayerIndx);
		handCards = new JButton[game.getPlayers().getPlayerHandCard(currentPlayerIndx).size()];
		handCardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		handCardPanel.setLayout(new GridLayout(0,5));
		if (!currentPlayer.getIsAI()) {
			for (int i = 0; i < playersInfo.getPlayerHandCard(currentPlayerIndx).size(); i++) {
				handCards[i] = new JButton(playersInfo.getPlayerHandCard(currentPlayerIndx).get(i).getCard());
				handCardPanel.add(handCards[i]);
			}
		}
	}


	/**
	 * main panel display set up
	 * @param mainPanel
	 * @param gameStatePanel
	 * @param handCardPanel
	 * @param settingPanel
	 */
	public void mainPanelSetUp(JPanel mainPanel, JPanel gameStatePanel, JPanel handCardPanel) {
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		mainPanel.setLayout(new GridLayout(2,1));		
		mainPanel.add(gameStatePanel);
		mainPanel.add(handCardPanel);
	}

	/**
	 * set up gameState panel for main panel
	 * @param gameStatePanel
	 * @param game the ongoing game
	 */
	public void gameStatePanelSetUp(JPanel gameStatePanel, Game game) {
		Players playersInfo = game.getPlayers();
		Deck deckInfo = game.getDeck();	
		JLabel playerNumber = new JLabel("PlayerNumber: " + playersInfo.getPlayerNumber());
		JLabel currentPlayer = new JLabel("CurrentPlayer: " + playersInfo.getCurrentPlayerID());
		JLabel deck = new JLabel("Deck: " + deckInfo.getCardSize());
		JLabel drawPile = new JLabel("DrawPile: " + game.getDrawPile());
		JLabel nextPlayer = new JLabel("NextPlayer: " + playersInfo.getNextPlayer());
		JLabel discardPile = new JLabel("DiscardPile: " + game.getDiscardSize());
		JLabel previousPlayed = new JLabel("Previous Card: " + game.getValidCard().getCard());
		skip = new JButton("Skip");
		gameStatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gameStatePanel.setLayout(new GridLayout(0,2));
		gameStatePanel.add(discardPile);
		gameStatePanel.add(drawPile);
		gameStatePanel.add(nextPlayer);
		gameStatePanel.add(deck);
		gameStatePanel.add(currentPlayer);
		gameStatePanel.add(previousPlayed);
		gameStatePanel.add(playerNumber);
		gameStatePanel.add(skip);
	}
	
	/**
	 * refresh the game if gamestate updated
	 * @param game
	 */
	public void refresh(Game game) {
		unoGUI uno = new unoGUI(game);
		new unoController(uno,game);
	}
	
	/**
	 * getter for cards 
	 * @return
	 */
	public JButton[] getCards() {
		return this.handCards;
	}
	
	/**
	 * getter for frame
	 * @return
	 */
	public JFrame getFrame() {
		return this.frame;
	}
	
	/**
	 * getter for skip button
	 * @return
	 */
	public JButton getSkipButton() {
		return this.skip;
	}
}
