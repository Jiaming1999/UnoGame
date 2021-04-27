package playerAI;

import java.util.ArrayList;
import java.util.Random;

import unoMain.Card;
import unoMain.Game;
import unoMain.Player;

public class playerAI {
	private Card.Color maxColor;
	private ArrayList<Card> handCard;
	private int redApp = 0;
	private int blueApp = 0;
	private int yellowApp = 0;
	private int greenApp = 0;
	private ArrayList<Card> validCard;
	private ArrayList<Integer> validIdx;
	private Game game;
	private Player player;

	public playerAI(Player player,Game game) {
		this.player = player;
		this.game = game;
		this.validCard = new ArrayList<Card>();
		this.validIdx = new ArrayList<Integer>();
		this.handCard = player.getPlayerCard();
		setValidCard(game);
	}

	/**
	 * build a valid card deck for AI to play
	 * @param game ongoing game
	 */
	public void setValidCard(Game game) {
		int i = 0;
		for(Card card:handCard) {
			if (game.isCardValid(card)) {
				validCard.add(card);
				validIdx.add(i);
			}
			switch(card.getCardColor()) {
			case BLUE:
				blueApp++;
				break;
			case RED:
				redApp++;
				break;
			case YELLOW:
				yellowApp++;
				break;
			case GREEN:
				greenApp++;
				break;
			default:
				break;
		}
			i++;
		}
		getMostAppearance();
	}

	/**
	 * get the most appeared card color 
	 */
	public void getMostAppearance() {
		int maxApp = Math.max(Math.max(greenApp, blueApp), Math.max(redApp, yellowApp));
		if (maxApp == blueApp) {
			maxColor = Card.Color.BLUE;
		}else if (maxApp == redApp) {
			maxColor = Card.Color.RED;
		}else if (maxApp == yellowApp) {
			maxColor = Card.Color.YELLOW;
		}else {
			maxColor = Card.Color.GREEN;
		}
	}
	
	/**
	 * the Strategy AI to begin 
	 * @return
	 */
	public Card turn() {
		if (this.validCard.isEmpty()) {
			game.playHandler(player.getPlayerIndx(), -1);
			return null;
		}
		return advancedAI();
	}
	
	/**
	 * advanced AI play as following order:
	 * if several valid card:
	 * First play wild add4 if valid
	 * then wild change color
	 * Wild played change to maxcolor
	 * valid add2,skip,reverse
	 * If no then play a valid number card with color player has the most of 
	 * None of above applied then play a random valid color card.
	 */
	public Card advancedAI() {
		Card wildCard = new Card(Card.Color.WILD, Card.Number.CHANGECOLOR);
		Card wildDrawCard = new Card(Card.Color.WILD, Card.Number.ADD4);
		Card validAdd2Card = new Card(game.getValidColor(), Card.Number.ADD2);
		Card validSkipCard = new Card(game.getValidColor(), Card.Number.SKIP);
		Card validReverseCard = new Card(game.getValidColor(), Card.Number.REVERSE);
		Card maxColorValidNumCard = new Card(maxColor, game.getValidNumber());
		if (ifContain(validCard,wildDrawCard)) {
			game.playHandler(player.getPlayerIndx(), this.validIdx.get(indexOf(validCard,wildDrawCard)));
			game.wildCardAIHandler(maxColor);
			return wildDrawCard;
		}else if (ifContain(validCard,wildCard)) {
			game.playHandler(player.getPlayerIndx(), this.validIdx.get(indexOf(validCard,wildCard)));
			game.wildCardAIHandler(maxColor);
			return wildCard;
		}else if (ifContain(validCard,validAdd2Card)) {
			game.playHandler(player.getPlayerIndx(), this.validIdx.get(indexOf(validCard,validAdd2Card)));
			return validAdd2Card;
		}else if (ifContain(validCard,validSkipCard)) {
			game.playHandler(player.getPlayerIndx(), this.validIdx.get(indexOf(validCard,validSkipCard)));
			return validSkipCard;
		}else if (ifContain(validCard,validReverseCard)) {
			game.playHandler(player.getPlayerIndx(), this.validIdx.get(indexOf(validCard,validReverseCard)));
			return validReverseCard;
		}else if (ifContain(validCard,maxColorValidNumCard)) {
			game.playHandler(player.getPlayerIndx(), this.validIdx.get(indexOf(validCard,maxColorValidNumCard)));
			return maxColorValidNumCard;
		}else {
			return basicAI();
		}
	}
	
	/**
	 * basic AI that play a random valid card
	 */
	public Card basicAI() {
		int length = validCard.size();
		Random rand = new Random();
		int pick = rand.nextInt(length);
		game.playHandler(player.getPlayerIndx(), validIdx.get(pick));
		return validCard.get(pick);
	}
	
	/**
	 * to detect whether card existed in validcard
	 * list.contains fail to work
	 * @param validCard
	 * @param card
	 * @return
	 */
	public boolean ifContain(ArrayList<Card> validCard, Card card) {
		for (Card singleCard:validCard) {
			if (singleCard.getCardColor() == card.getCardColor() && singleCard.getCardNumber() == card.getCardNumber()) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * indexOf replacement, arrayList not working
	 * @param validCard
	 * @param card
	 * @return
	 */
	public int indexOf(ArrayList<Card> validCard, Card card) {
		int i = 0;
		for (Card singleCard:validCard) {
			if (singleCard.getCardColor() == card.getCardColor() && singleCard.getCardNumber() == card.getCardNumber()) {
				return i;
			}
		}
		return -1;
	}
}
