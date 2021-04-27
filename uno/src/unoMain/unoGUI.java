package unoMain;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class unoGUI {
	private String invalidMessage;
	
	public unoGUI(Game game) {
		invalidMessage = "Nothing abnormal";
		JFrame frame = new JFrame();
		JFrame message = new JFrame();
		JFrame wildCard = new JFrame();
		JFrame setting = new JFrame();
		
		JPanel wildPanel = new JPanel();
		JPanel mainPanel = new JPanel();
		JPanel gameStatePanel = new JPanel();
		JPanel handCardPanel = new JPanel();

		wildPanelSetup(wildPanel, game);
		handCardPanelSetUp(handCardPanel, game);
	    gameStatePanelSetUp(gameStatePanel,game);		
		mainPanelSetUp(mainPanel, gameStatePanel, handCardPanel);
		
		setPopUp(message, wildCard, setting, wildPanel,game);
		
	    frame.setSize(800, 600);
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("UNO GUI");
		frame.setVisible(true);
	}

	
	/**
	 * set GUI for poping up window
	 * @param message
	 * @param wildCard
	 * @param setting
	 * @param wildPanel
	 * @param game
	 */
	public void setPopUp(JFrame message, JFrame wildCard, JFrame setting, JPanel wildPanel,Game game) {
		JPanel settingPanel = new JPanel();
		JPanel messagePanel = new JPanel();
		
		messagePanel.add(new JLabel(invalidMessage));
		messagePanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 10, 10));
	    settingPanelSetUp(settingPanel, game, invalidMessage);

		setting.add(settingPanel);
		setPopUp(setting);
		setting.setTitle("Setting");
		
		wildCard.add(wildPanel);
		setPopUp(wildCard);
		wildCard.setTitle("Wild Card");
		
		message.add(messagePanel);
		setPopUp(message);
		message.setTitle("Message");
		


	}
	
	/**
	 * set up panel for wild Card select
	 * @param wildPanel
	 * @param game
	 */
	public void wildPanelSetup(JPanel wildPanel, Game game) {
		wildPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 10, 10));
		JButton[] choices = new JButton[14];
		for (int i = 0; i < 4; i++) {
			choices[i] = new JButton(Card.printColor(Card.Color.values()[i]));
			wildPanel.add(choices[i]);
		}
		for (int i = 4; i < 14; i++) {
			choices[i] = new JButton(Card.printNumber(Card.Number.values()[i]));
			wildPanel.add(choices[i]);
		}
	}
	

	/**
	 * setup pop up window frame
	 * @param wildCard
	 */
	public void setPopUp(JFrame wildCard) {
		wildCard.setSize(400,300);
		wildCard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		wildCard.setVisible(true);
	}
	
	/**
	 * set up the panel for playerhandcard display
	 * @param handCardPanel the panel to be setup
	 * @param game ongoing game
	 */
	public void handCardPanelSetUp(JPanel handCardPanel, Game game) {
		Players playersInfo = game.getPlayers();
		JButton[] handCards = new JButton[game.getPlayers().getPlayerHandCard("John").size()+1];
		
		handCardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		handCardPanel.setLayout(new GridLayout(0,5));
		if (playersInfo.getPlayerNumber() == 0) {
			handCards[game.getPlayers().getPlayerHandCard("John").size()] = new JButton("Skip");
			handCardPanel.add(handCards[game.getPlayers().getPlayerHandCard("John").size()]);
			return;
		}
		for (int i = 0; i < playersInfo.getPlayerHandCard("John").size(); i++) {
			handCards[i] = new JButton(playersInfo.getPlayerHandCard("John").get(i).getCard());
			handCardPanel.add(handCards[i]);
		}
		handCards[game.getPlayers().getPlayerHandCard("John").size()] = new JButton("Skip");
		handCardPanel.add(handCards[game.getPlayers().getPlayerHandCard("John").size()]);
		
	}

	/**
	 * main panel display set up
	 * @param mainPanel
	 * @param gameStatePanel
	 * @param handCardPanel
	 * @param settingPanel
	 */
	public void mainPanelSetUp(JPanel mainPanel, JPanel gameStatePanel, JPanel handCardPanel) {
		JPanel draw = new JPanel();
		draw.add(new JButton("Draw"));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
		mainPanel.setLayout(new GridLayout(3,1));		
		mainPanel.add(gameStatePanel);
		mainPanel.add(handCardPanel);
		mainPanel.add(draw);
	}

	/**
	 * set up settingPanel for main panel
	 * @param settingPanel
	 * @param game ongoing game
	 * @param invalidMessage invalid message if anything goes wrong
	 */
	public void settingPanelSetUp(JPanel settingPanel, Game game, String invalidMessage) {
		JPanel insertPanel = new JPanel();
		JPanel statePanel = new JPanel();
		
		
		JTextField insertPlayer = new JTextField();
		
		JButton addPlayer = new JButton("+");
		JButton removePlayer = new JButton("-");
		JButton startGame = new JButton("Start");
		
		JLabel playersNum = new JLabel("Players: " + game.getPlayers().getPlayerNumber());
		
		statePanel.setLayout(new GridLayout(0,1));
		statePanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 10));
		statePanel.add(playersNum);
		statePanel.add(new JLabel("Is Game Start?: " + !game.isGameOver()));
		
		insertPanel.setLayout(new GridLayout(0,1));
		insertPanel.add(insertPlayer);
		insertPanel.add(addPlayer);
		insertPanel.add(removePlayer);
		insertPanel.add(startGame);
		
		settingPanel.setLayout(new GridLayout(0,2));
		settingPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		settingPanel.add(insertPanel);
		settingPanel.add(statePanel);
		
	}

	/**
	 * set up gameState panel for main panel
	 * @param gameStatePanel
	 * @param game the ongoing game
	 */
	public void gameStatePanelSetUp(JPanel gameStatePanel, Game game) {
		Players playersInfo = game.getPlayers();
		Deck deckInfo = game.getDeck();
		
		JButton openSetting = new JButton("Open Setting");
		JLabel currentPlayer = new JLabel("CurrentPlayer: " + playersInfo.getCurrentPlayerID());
		JLabel deck = new JLabel("Deck: " + deckInfo.getCardSize());
		JLabel drawPile = new JLabel("DrawPile: " + game.getDrawPile());
		JLabel nextPlayer = new JLabel("NextPlayer: " + playersInfo.getNextPlayer());
		JLabel discardPile = new JLabel("DiscardPile: " + game.getDiscardSize());
		JLabel previousPlayed = new JLabel("Previous Card: " + game.getValidCard().getCard());
		
		gameStatePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gameStatePanel.setLayout(new GridLayout(0,2));
		gameStatePanel.add(discardPile);
		gameStatePanel.add(drawPile);
		gameStatePanel.add(nextPlayer);
		gameStatePanel.add(deck);
		gameStatePanel.add(currentPlayer);
		gameStatePanel.add(previousPlayed);
		gameStatePanel.add(openSetting);
	}
}
