package unoMain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
	The deck consists of 108 cards.
*/
public class Deck {
	private ArrayList<Card> cards;
	
	/**
	 * Constructor
	 */
	public Deck() {
		cards = new ArrayList<Card>();
	}

	/**
	 * setup for the Deck
	 * Used as the beginning of the game
	 * Insert all 108 cards into deck
	 */
	public void setup() {
		Card.Color[] colors = Card.Color.values();
		for (int i = 0; i < 4; i++) {
			Card.Color color = colors[i];
			for (int j = 0; j < 13; j++) {
				if (j == 0) {
					cards.add(new Card(color,Card.Number.values()[j]));
				}else {
					cards.add(new Card(color,Card.Number.values()[j]));
					cards.add(new Card(color,Card.Number.values()[j]));
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			cards.add(new Card(Card.Color.WILD,Card.Number.values()[13])) ;
			cards.add(new Card(Card.Color.WILD,Card.Number.values()[14]));
		}
	}
	
	/**
	 * get deck size
	 * @return deck size
	 */
	public int getCardSize() {
		return cards.size();
	}
	
	/**
	 * check if any card left in deck
	 * @return true if none, false otherwise
	 */
	public boolean isEmpty() {
		return cards.isEmpty();
	}
	
	/**
	 * shuffle the deck by randomly swapping them
	 */
	public void shuffle() {
		int length = cards.size();
		Random random = new Random();
		for (int i = 0; i < cards.size(); i++) {
			int index = i + random.nextInt(length - i);
			Collections.swap(cards, i, index);
		}
	}
	
	/**
	 * draw one card from deck
	 * @return card in the top
	 * @throws IllegalStateException if there is no card in the deck
	 */
	public Card drawCard() throws IllegalStateException {
		Card top = cards.remove(cards.size()-1);
		return top;
	}
	
	/**
	 * draw n cards from deck
	 * @param n:number of cards needed 
	 * @return a array of drew card
	 */
	public Card[] drawCards(int n){
		Card[] topCards = new Card[n];
		for (int i = 0; i < n; i++) {
			topCards[i] = drawCard();
		}
		return topCards;
	}
	
	/**
	 * rebuild deck with discard pile
	 * @param pile discarded cards pool
	 */
	public void rebulidDeck(ArrayList<Card> pile) {
		this.cards = pile;
	}
	
	/**
	 * get how many cards inside the deck
	 * @return number of cards in deck
	 */
	public int getInDeck() {
		return this.cards.size();
	}


}
