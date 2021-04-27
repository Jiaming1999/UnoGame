package unoMain;

import java.util.ArrayList;
import java.util.Arrays;

import playerAI.playerAI;

public class Game {
	/**
	 * key attributes for Game class
	 * Including tracking the players' status
	 * tracking Deck status
	 */
	private Deck deck;
	private Players players;
	private ArrayList<Card> discardPile;
	private int drawPile;
	private ArrayList<ArrayList<Card>> playersHandCard;
	private String winner;
	private String[] playersID; 
	private Card.Number validNumber;
	private Card.Color validColor;
	
	/**
	 * Constructor
	 * @param players in game
	 * @param startPlayer first play the game
	 * @param isClockWised the order of the game
	 * 
	 * Usage of ArrayList for all players hand card inspired by Tutorial
	 * https://www.youtube.com/watch?v=_6ZdGGou6pM&t=72s
	 * By Code Clique
	 */
	public Game(int playerNum, int AINum ,boolean isClockWised) {
		this.discardPile = new ArrayList<Card>();
		this.playersHandCard = new ArrayList<ArrayList<Card>>();
		this.drawPile = 0;
		this.deck = new Deck();
		this.deck.setup();
		this.deck.shuffle();
		this.winner = " ";	
		playersID = new String[playerNum + AINum];
		setUpPlayers(playerNum, AINum, isClockWised);
	}

	/**
	 * set up players in the game
	 * @param playerNum number of real players
	 * @param AINum number of AI players
	 * @param isClockWised direction of the game
	 */
	public void setUpPlayers(int playerNum, int AINum, boolean isClockWised) {
		for (int i = 0; i < playersID.length; i++) {
			playersID[i] = "Player" + (i+1);
			this.playersHandCard.add(new ArrayList<Card>(Arrays.asList(this.deck.drawCards(7))));
		}
		this.players = new Players(playerNum, AINum, playersID, this.playersHandCard ,0);
		this.players.setDirection(isClockWised);
	}
	
	/**
	 * start the game, draw the first card, repeated if no valid start card gotten.
	 * update discard pile
	 * @param game: the game to be started
	 */
	public void start(Game game) {
		Card firstCard = deck.drawCard();
		validColor = firstCard.getCardColor();
		validNumber = firstCard.getCardNumber();
		discardPile.add(firstCard);
		if (firstCard.getCardColor() == Card.Color.WILD
				|| firstCard.getCardNumber() == Card.Number.ADD2
				|| firstCard.getCardNumber() == Card.Number.REVERSE
				|| firstCard.getCardNumber() == Card.Number.SKIP) {
			start(game);
		}
	}
	
	/**
	 * higher function control how each turn should be played
	 * @param pid
	 * @param i
	 * @return 0 for player draw card, 1 for play right card, and 2 for play wrong card
	 */
	public Card playHandler(int id, int i) {
		Card played = playCard(id,i);
		if (played == null) {
			if (drawPile != 0) {
				drawPenalty(id);
			}else {
				draw(id);
			}
			nextPlayerHandler();
			return null;
		}
		if (isCardValid(played)) {
			takeEffect(played);
			return played;
		}else {
			penaltyOnWrongCard(id);
			return null;
		}
	}
	
	/**
	 * play player pid's chosen card
	 * @param pid the player who played the card
	 * @param pick the index of which card to be played
	 * @return the card played
	 */
	public Card playCard(int id, int pick) {
		if (pick >= players.getPlayerHandCard(id).size() || pick == -1) {
			return null;
		}
		Card played = players.getPlayerHandCard(id).get(pick);
		players.getPlayerHandCard(id).remove(pick);
		discardPile.add(played);
		return played;
	}
	
	/**
	 * Turn for next player in order to play
	 */
	public void nextPlayerHandler() {
		players.nextPlayer();
		if (players.getCurrentPlayer().getIsAI()) {
			playerAI AI = new playerAI(players.getCurrentPlayer(),this);
			AI.turn();
		}
	}
	
	/**
	 * take effect handler for special card to work
	 * @param played
	 */
	private void takeEffect(Card played) {
		switch(played.getCardNumber()) {
			case ADD2:
				add2Player();
				changeColor(played.getCardColor());
				changeNumber(played.getCardNumber());
				nextPlayerHandler();
				break;
			case ADD4:
				add4Player();
				changeColor(Card.Color.ANY);
				changeNumber(played.getCardNumber());
				//number or color input need scan from gui input later
				break;
			case CHANGECOLOR:
				changeNumber(played.getCardNumber());
				break;
			case SKIP:
				changeColor(played.getCardColor());
				changeNumber(played.getCardNumber());
				skipPlayer();
				break;
			case REVERSE:
				changeColor(played.getCardColor());
				changeNumber(played.getCardNumber());
				reversePlayer();
				break;
			default:
				changeColor(played.getCardColor());
				changeNumber(played.getCardNumber());
				nextPlayerHandler();
			
		}		
		return;	
	}

	/**
	 * handle the wild card effect
	 * @param color
	 * 
	 */
	public void wildCardAIHandler(Card.Color color) {
		changeColor(color);
		changeNumber(Card.Number.ANY);
		nextPlayerHandler();
	}
	
	/**
	 * the penalty on playing the invalid card
	 * if played card is invalid trigger this function
	 * take played card back and draw another 1 card
	 * Play continues on next player in order
	 * @param pid
	 * @param card
	 */
	public void penaltyOnWrongCard(int id) {
		takeBackCard(id);
		draw(id);
		nextPlayerHandler();
	}
	
	/**
	 * when there is no card in deck, update deck with discarded card.
	 */
	public void reuseDiscard() {
		deck.rebulidDeck(discardPile);
		deck.shuffle();
		discardPile = new ArrayList<Card>();
	}
	
	/**
	 * draw card from deck for player pid
	 * @param pid the player who draw card
	 * @return the card pid gets
	 */
	public Card draw(int id) {
		if (deck.isEmpty() == true) {
			reuseDiscard();
		}
		Card drawCard = deck.drawCard();
		players.getPlayerHandCard(id).add(drawCard);
		return drawCard;
	}
	
	/**
	 * draw several cards for player pid
	 * Used for draw2 and wildDraw
	 * @param pid player who need to draw cards
	 */
	public void drawPenalty(int id) {
		for (int i = 0 ; i < drawPile; i++) {
			if (deck.isEmpty() == true) {
				reuseDiscard();
			}
			players.getPlayerHandCard(id).add(deck.drawCard());
		}
		drawPile = 0;
		changeNumber(Card.Number.ANY);
	}

	/**
	 * reverse the current game direction
	 */
	public void reversePlayer() {
		players.setDirection(!players.getDirection());
		nextPlayerHandler();
	}
	
	/**
	 * skip next player
	 */
	public void skipPlayer() {
		players.nextPlayer();
		nextPlayerHandler();
	}
	
	/**
	 * add 2 to drawPile
	 */
	public void add2Player() {
		drawPile += 2;
	}
	
	/**
	 * wild draw, add 4 to drawPile
	 */
	public void add4Player() {
		drawPile += 4;
	}
	/**
	 * set valid color to given color
	 * @param color the color needed to be set
	 */
	public void changeColor(Card.Color color) {
		validColor = color;
	}
	
	/**
	 * set valid number to given number
	 * @param number the number needed to be set
	 */
	public void changeNumber(Card.Number number) {
		validNumber = number;
	}
	
	/**
	 * for test purpose
	 */
	public void setColor(Card.Color color) {
		validColor = color;
	}
	
	/**
	 * for number on black rule wild Card
	 * @param number
	 */
	public void setNumber(Card.Number number) {
		if (number == Card.Number.ADD2 
				|| number == Card.Number.ADD4 
				|| number == Card.Number.SKIP 
				|| number == Card.Number.SKIP 
				|| number == Card.Number.REVERSE)
		{
			throw new IllegalStateException("invalid choice");
		}
		validNumber = number;
	}
	
	/**
	 * check if anyone plays all cards out and end the game
	 * @return true if game can end; false otherwise
	 */
	public boolean isGameOver() {
		for (int i = 0; i < players.getPlayerNumber(); i++) {
			if (playersHandCard.get(i).isEmpty() == true) {
				this.winner = this.playersID[i];
				return true;
			}
		}
		return false;
	}
	
	/**
	 * return the winner
	 * @return
	 */
	public String getWinner() {
		return this.winner;
	}
	
	/**
	 * check if played card is valid for play
	 * @param card: the card needed to play
	 * @return true if valid; false otherwise
	 */
	public boolean isCardValid(Card card) {
		if (validNumber == Card.Number.ANY && validColor == Card.Color.ANY) {
			return true;
		}else if (validNumber == Card.Number.ANY) {
			return card.getCardColor() == validColor || card.getCardColor() == Card.Color.WILD;
		}else if (validColor == Card.Color.ANY) {
			return card.getCardNumber() == validNumber;
		}
		switch (validNumber) {
			case ADD2:
				return card.getCardColor() == validColor 
				|| card.getCardNumber() == validNumber 
				|| card.getCardNumber() == Card.Number.ADD4;
			case ADD4:
				return card.getCardNumber() == Card.Number.ADD4;
			case CHANGECOLOR:
				return card.getCardColor() == validColor 
				|| card.getCardColor() == Card.Color.WILD;
			default:
				return card.getCardColor() == validColor 
				|| card.getCardNumber() == validNumber 
				|| card.getCardColor() == Card.Color.WILD;
		}
	}

	/**
	 * take played card back if invalid card played
	 * @param pid
	 * @param card
	 */
	public void takeBackCard(int id) {
		Card card = discardPile.remove(discardPile.size()-1);
		players.getPlayerHandCard(id).add(card);
	}


	/**
	 * get the exact same card fit the valid condition
	 * @return the card formed
	 */
	public Card getValidCard() {
		return new Card(validColor,validNumber);
	}
	
	/**
	 * get the deck
	 * @return deck
	 */
	public Deck getDeck() {
		return this.deck;
	}
	
	/**
	 * get size of discard pile
	 * @return number of discarded cards
	 */
	public int getDiscardSize() {
		return this.discardPile.size();
	}
	
	/**
	 * get current valid card color
	 * @return valid color
	 */
	public Card.Color getValidColor(){
		return validColor;
	}
	
	/**
	 * get current valid card number
	 * @return valid number
	 */
	public Card.Number getValidNumber(){
		return validNumber;
	}
	
	/**
	 * getPlayers
	 * @return
	 */
	public Players getPlayers() {
		return this.players;
	}
	
	/**
	 * get draw penalty card pool
	 * @return
	 */
	public int getDrawPile() {
		return this.drawPile;
	}
	
}
