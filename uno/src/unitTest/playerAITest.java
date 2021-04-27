package unitTest;

import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import playerAI.playerAI;
import unoMain.Card;
import unoMain.Game;
import unoMain.Player;

public class playerAITest {
	Player player;
	playerAI playerAI;
	Game newGame;
	ArrayList<Card> handCard;
	Card redFiveCard = new Card(Card.Color.RED,Card.Number.FIVE);
	Card greenTwoCard = new Card(Card.Color.GREEN,Card.Number.TWO);
	Card redOneCard = new Card(Card.Color.RED,Card.Number.ONE);
	Card wildCard = new Card(Card.Color.WILD,Card.Number.CHANGECOLOR);
	Card add4Card = new Card(Card.Color.WILD,Card.Number.ADD4);
	Card greenAdd2Card = new Card(Card.Color.GREEN,Card.Number.ADD2);
	Card redAdd2Card = new Card(Card.Color.RED,Card.Number.ADD2);
	Card redSkipCard = new Card(Card.Color.RED,Card.Number.SKIP);
	Card blueReverseCard = new Card(Card.Color.BLUE,Card.Number.REVERSE);
	Card blueOneCard = new Card(Card.Color.BLUE,Card.Number.ONE);
	
	@Before public void setupTest() {
		handCard = new ArrayList<Card>();
		handCard.add(add4Card);
		handCard.add(wildCard);
		handCard.add(redFiveCard);
		handCard.add(redOneCard);
		handCard.add(redAdd2Card);
		handCard.add(redSkipCard);
		handCard.add(greenAdd2Card);
		handCard.add(redOneCard);
		handCard.add(redOneCard);
		handCard.add(redOneCard);
		handCard.add(redOneCard);
		handCard.add(redOneCard);
		handCard.add(blueOneCard);
		handCard.add(blueReverseCard);

		newGame = new Game(1,1,true);
		newGame.start(newGame);
		player = new Player("John",handCard,0,true);
		playerAI = new playerAI(player, newGame);
	}
	
	@Test public void wildDrawTest() {
		newGame.changeColor(Card.Color.GREEN);
		newGame.changeNumber(Card.Number.ONE);
		Card played = playerAI.turn();
		assertEquals(Card.Color.WILD, played.getCardColor());
		assertEquals(Card.Number.ADD4, played.getCardNumber());
 	}
	
	@Test public void wildCardTest() {
		newGame.changeColor(Card.Color.GREEN);
		newGame.changeNumber(Card.Number.ONE);
		handCard.remove(add4Card);
		player = new Player("John",handCard,0,true);
		playerAI = new playerAI(player, newGame);
		Card played = playerAI.turn();
		assertEquals(Card.Color.WILD, played.getCardColor());
		assertEquals(Card.Number.CHANGECOLOR, played.getCardNumber());
	}
	
	@Test public void add2CardTest() {
		newGame.changeColor(Card.Color.RED);
		newGame.changeNumber(Card.Number.ONE);
		handCard.remove(add4Card);
		handCard.remove(wildCard);
		player = new Player("John",handCard,0,true);
		playerAI = new playerAI(player, newGame);
		Card played = playerAI.turn();
		assertEquals(Card.Color.RED, played.getCardColor());
		assertEquals(Card.Number.ADD2, played.getCardNumber());
	}
	
	@Test public void skipCardTest() {
		newGame.changeColor(Card.Color.RED);
		newGame.changeNumber(Card.Number.ONE);
		handCard.remove(add4Card);
		handCard.remove(wildCard);
		handCard.remove(redAdd2Card);
		player = new Player("John",handCard,0,true);
		playerAI = new playerAI(player, newGame);
		Card played = playerAI.turn();
		assertEquals(Card.Color.RED, played.getCardColor());
		assertEquals(Card.Number.SKIP, played.getCardNumber());
	}
	
	@Test public void reverseCardTest() {
		newGame.changeColor(Card.Color.BLUE);
		newGame.changeNumber(Card.Number.ONE);
		handCard.remove(add4Card);
		handCard.remove(wildCard);
		player = new Player("John",handCard,0,true);
		playerAI = new playerAI(player, newGame);
		Card played = playerAI.turn();
		assertEquals(Card.Color.BLUE, played.getCardColor());
		assertEquals(Card.Number.REVERSE, played.getCardNumber());
	}
	
	@Test public void maxColorValidTest() {
		newGame.changeColor(Card.Color.BLUE);
		newGame.changeNumber(Card.Number.ONE);
		handCard.remove(add4Card);
		handCard.remove(wildCard);
		handCard.remove(redAdd2Card);
		handCard.remove(redSkipCard);
		handCard.remove(blueReverseCard);
		player = new Player("John",handCard,0,true);
		playerAI = new playerAI(player, newGame);
		Card played = playerAI.turn();
		assertEquals(Card.Color.RED, played.getCardColor());
		assertEquals(Card.Number.ONE, played.getCardNumber());
	}

}
