package unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import unoMain.*;

public class unoTests {
	Game newGame;
	Card notValidCard = new Card(Card.Color.RED,Card.Number.FIVE);
	Card greenTwoCard = new Card(Card.Color.GREEN,Card.Number.TWO);
	Card redOneCard = new Card(Card.Color.RED,Card.Number.ONE);
	Card wildCard = new Card(Card.Color.WILD,Card.Number.CHANGECOLOR);
	Card add4Card = new Card(Card.Color.WILD,Card.Number.ADD4);
	Card greenAdd2Card = new Card(Card.Color.GREEN,Card.Number.ADD2);
	Card redAdd2Card = new Card(Card.Color.RED,Card.Number.ADD2);
	Card redSkipCard = new Card(Card.Color.RED,Card.Number.SKIP);
	Card blueReverseCard = new Card(Card.Color.BLUE,Card.Number.REVERSE);


	@Before public void setupGame() {
		newGame = new Game(3,0,true);
		newGame.start(newGame);
	}
	
	/**
	 * Part1: Test for game constructor
	 */	
	@Test public void validGameConstructor() {
		assertEquals(true,newGame.getPlayers().getDirection());
		assertEquals(3,newGame.getPlayers().getAllPlayers().length);
	}
	
	/**
	 * Part2: Test for game start
	 */
	@Test public void validStartGame() {
		for (int i = 0; i < 3;i++) {
			assertEquals(newGame.getPlayers().getPlayerHandCard(i).size(),7);
		}
	}

	/**
	 * Part3: Skip
	 */
	@Test public void validSkipPlayer() {
		newGame.skipPlayer();
		assertEquals(newGame.getPlayers().getCurrentPlayerID(),"Player3");
	}
	
	/**
	 * Part4: Reverse
	 */
	@Test public void validReversePlayer() {
		newGame.reversePlayer();
		assertEquals(newGame.getPlayers().getDirection(),false);
		assertEquals(newGame.getPlayers().getCurrentPlayerID(),"Player3");
		assertEquals(newGame.getPlayers().getNextPlayer(),"Player2");
	}
	
	/**
	 * Part5: consecutive stacking
	 */
	@Test public void validConsecutiveStacking() {
		newGame.add2Player();
		newGame.add2Player();
		newGame.add4Player();
		newGame.drawPenalty(1);
		assertEquals(newGame.getPlayers().getPlayerHandCard(1).size(),15);
		newGame.add2Player();
		newGame.add4Player();

		newGame.drawPenalty(0);
		assertEquals(newGame.getPlayers().getPlayerHandCard(0).size(),13);	
	}
	
	/**
	 * Part6: play invalid card and take back
	 */
	@Test public void validPlayAndTakeBack() {

		newGame.playCard(0, 0);
		assertEquals(newGame.getPlayers().getPlayerHandCard(0).size(),6);
		newGame.takeBackCard(0);
		assertEquals(newGame.getPlayers().getPlayerHandCard(0).size(),7);
	}
	
	/**
	 * Part7 is the card valid and change color test 
	 */
	@Test public void validCardAll() {
		newGame.changeColor(Card.Color.GREEN);
		newGame.changeNumber(Card.Number.ONE);
		assertEquals(newGame.getValidColor(),Card.Color.GREEN);
		assertEquals(newGame.isCardValid(notValidCard),false);
		assertEquals(newGame.isCardValid(greenTwoCard),true);
		assertEquals(newGame.isCardValid(redOneCard),true);
		assertEquals(newGame.isCardValid(wildCard),true);
		assertEquals(newGame.isCardValid(greenAdd2Card),true);
		assertEquals(newGame.isCardValid(add4Card),true);
		assertEquals(newGame.isCardValid(redAdd2Card),false);
	}
	
	/**
	 * Test for play wrong card penalty
	 */
	@Test public void validWrongCardPenalty() {
		newGame.changeColor(Card.Color.GREEN);
		newGame.changeNumber(Card.Number.ONE);
		newGame.getPlayers().getPlayerHandCard(0).add(notValidCard);
		Card played = newGame.playCard(0, 7);
		assertEquals(newGame.isCardValid(played),false);
		newGame.penaltyOnWrongCard(0);
		assertEquals(newGame.getPlayers().getPlayerHandCard(0).size(),9);	
	}
	
	@Test public void validCardWildDraw4() {
		//if add4 previously played
		newGame.setColor(Card.Color.WILD);
		newGame.changeNumber(Card.Number.ADD4);
		assertEquals(newGame.isCardValid(notValidCard),false);
		assertEquals(newGame.isCardValid(greenTwoCard),false);
		assertEquals(newGame.isCardValid(redOneCard),false);
		assertEquals(newGame.isCardValid(wildCard),false);
		assertEquals(newGame.isCardValid(greenAdd2Card),false);
		assertEquals(newGame.isCardValid(add4Card),true);
		assertEquals(newGame.isCardValid(redAdd2Card),false);

	}
	
	@Test public void validCardDraw2() {
		//if red add2 previously played
		newGame.setColor(Card.Color.RED);
		newGame.changeNumber(Card.Number.ADD2);
		assertEquals(newGame.isCardValid(greenTwoCard),false);
		assertEquals(newGame.isCardValid(wildCard),false);
		assertEquals(newGame.isCardValid(add4Card),true);
		assertEquals(newGame.isCardValid(redAdd2Card),true);
		assertEquals(newGame.isCardValid(greenAdd2Card),true);
	}
	
	@Test public void validCardWild() {


		//if wild played and change color to green
		newGame.setColor(Card.Color.GREEN);
		newGame.changeNumber(Card.Number.CHANGECOLOR);
		assertEquals(newGame.isCardValid(greenTwoCard),true);
		assertEquals(newGame.isCardValid(wildCard),true);
		assertEquals(newGame.isCardValid(add4Card),true);
		assertEquals(newGame.isCardValid(redAdd2Card),false);
		assertEquals(newGame.isCardValid(greenAdd2Card),true);
	}
	
	@Test public void validPlayLoop() {
		newGame.setColor(Card.Color.BLUE);
		newGame.setNumber(Card.Number.EIGHT);
		newGame.getPlayers().getPlayerHandCard(0).add(notValidCard);
		newGame.playHandler(0, 7);
		assertEquals(newGame.getPlayers().getCurrentPlayerID(),"Player2");
		assertEquals(newGame.getPlayers().getPlayerSize(0),9);
	}
	
	@Test public void validPlayLoopPenalty() {
		newGame.setColor(Card.Color.BLUE);
		newGame.setNumber(Card.Number.EIGHT);
		newGame.getPlayers().getPlayerHandCard(0).add(notValidCard);
		newGame.playHandler(0, 8);
		assertEquals(newGame.getPlayers().getCurrentPlayerID(),"Player2");
		assertEquals(newGame.getPlayers().getPlayerSize(0),9);
	}
	
	@Test public void validPlayLoopPlayed() {
		newGame.setColor(Card.Color.RED);
		newGame.setNumber(Card.Number.EIGHT);
		assertEquals(newGame.getValidNumber(), Card.Number.EIGHT);
		newGame.getPlayers().getPlayerHandCard(0).add(notValidCard);
		newGame.playHandler(0, 7);
		assertEquals(newGame.getPlayers().getCurrentPlayerID(),"Player2");
		assertEquals(newGame.getPlayers().getPlayerSize(0),7);
	}
	
	@Test public void validPlayLoopDraw2() {
		newGame.add2Player();
		newGame.playHandler(0, 7);
		assertEquals(newGame.getPlayers().getCurrentPlayerID(),"Player2");
		assertEquals(newGame.getPlayers().getPlayerSize(0),9);
	}
	
	@Test public void validTakeEffectDraw2() {
		newGame.setColor(Card.Color.GREEN);
		newGame.setNumber(Card.Number.EIGHT);
		newGame.getPlayers().getPlayerHandCard(0).add(greenAdd2Card);
		newGame.playHandler(0,7);
		assertEquals(newGame.getValidColor(), Card.Color.GREEN);
		assertEquals(newGame.getDrawPile(),2);
	}
	@Test public void validTakeEffectRev() {
		newGame.setColor(Card.Color.BLUE);
		newGame.setNumber(Card.Number.EIGHT);
		newGame.getPlayers().getPlayerHandCard(0).add(blueReverseCard);
		newGame.playHandler(0,7);
		assertEquals(newGame.getValidColor(), Card.Color.BLUE);
		assertEquals(newGame.getValidNumber(),Card.Number.REVERSE);
		assertEquals(newGame.getPlayers().getCurrentPlayerID(),"Player3");
	}
	
	@Test public void validTakeEffectDrawWild() {
		newGame.setColor(Card.Color.BLUE);
		newGame.setNumber(Card.Number.EIGHT);
		newGame.getPlayers().getPlayerHandCard(0).add(add4Card);
		newGame.playHandler(0,7);
		assertEquals(newGame.getValidNumber(),Card.Number.ADD4);
		assertEquals(newGame.getDrawPile(),4);
	}
	
	@Test public void validTakeEffectSkip() {
		newGame.setColor(Card.Color.RED);
		newGame.setNumber(Card.Number.EIGHT);
		newGame.getPlayers().getPlayerHandCard(0).add(redSkipCard);
		newGame.playHandler(0,7);
		assertEquals(newGame.getValidColor(), Card.Color.RED);
		assertEquals(newGame.getValidNumber(),Card.Number.SKIP);
		assertEquals(newGame.getPlayers().getCurrentPlayerID(),"Player3");
	}
	
	@Test public void wildCardHandlerTest() {
		newGame.wildCardAIHandler(Card.Color.BLUE);
		assertEquals(newGame.getValidColor(),Card.Color.BLUE);
	}
	
	@Test public void validDeck() throws Exception{
		/*
		 * Part8: When Deck not enough
		 */
		while(!newGame.getDeck().isEmpty()){
			newGame.draw(2);
		}
		newGame.draw(2);
	}
	
	
	@Test public void validGetNextPlayer() {
		assertEquals(newGame.getPlayers().getNextPlayer(),"Player2");
		newGame.getPlayers().setDirection(false);
		assertEquals(newGame.getPlayers().getNextPlayer(),"Player3");
	}
	
	@Test public void validDiscardPile() {
		int discardSize = 108 - 21 - newGame.getDeck().getCardSize();
		assertEquals(discardSize,newGame.getDiscardSize());
	}
	
	/**
	 * Part Final: Game Over
	 */
	@Test public void isGameOver() {
		assertEquals(newGame.isGameOver(),false);
		for (int i = 0; i < 7; i++) {
			newGame.playCard(1, 0);
		}
		assertEquals(newGame.isGameOver(),true);
	}
	


	
}
