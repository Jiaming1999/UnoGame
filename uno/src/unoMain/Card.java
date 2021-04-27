package unoMain;

/**
 * This class is a single card type class in Uno game
 * The card should be formed by one color and one number
 * @author 11043
 * Usage of enum to store type inspired by Tutorial
 * https://www.youtube.com/watch?v=M14D7q8UoCE&t=2s
 * By Code Clique
 */

public class Card {
	public enum Color {
		RED,GREEN,YELLOW,BLUE,WILD,ANY;
	}
	
	public enum Number {
		ZERO,ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,REVERSE,SKIP,ADD2,ADD4,CHANGECOLOR,ANY;
	}
	
	/**
	 * Card Attribute
	 * color: color of the card
	 * number: value of the card
	 */	
	private Color color;
	private Number number;
	
	/**
	 * Constructor for a single Uno Card
	 */
	public Card(Color color, Number number) {
		this.color = color;
		this.number = number;
	}
	
	/**
	 * to get card color
	 * @return card number
	 */	
	public Color getCardColor() {
		return this.color;
	}
	
	/**
	 * to get card number
	 * @return card number
	 */
	public Number getCardNumber() {
		return this.number;
	}
	
	/**
	 * to print card out as string
	 * @return "cardcolor:cardnumber"
	 */
	public String getCard() {
		return this.color + ":" + this.number;
	}
	
	/**
	 * printer for gui display information
	 * @param color
	 * @return
	 */
	public static String printColor(Color color) {
		return " " + color;
	}
	
	public static String printNumber(Number number) {
		return " " + number;
	}
}
