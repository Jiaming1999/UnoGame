package unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import unoMain.Deck;

public class unoDeckTests {
	@Test public void ValidcDeckConstructor() throws Exception{
		Deck deck = new Deck();
		deck.setup();
		deck.shuffle();
		assertEquals(deck.getInDeck(),108);
		assertEquals(deck.getCardSize(),108);
	}
}
