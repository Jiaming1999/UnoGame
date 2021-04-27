package unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import unoMain.Card;

public class unoCardTests {
	Card.Color color = Card.Color.BLUE;
	Card.Number number = Card.Number.ADD2;
	Card card = new Card(color, number);
	@Test public void ValidcCardConstructor() throws Exception{
		assertEquals(color,card.getCardColor());
	}
	
	@Test public void validGetter() {
		assertEquals(card.getCard(),"BLUE:ADD2");
	}
	
	@Test public void validPrinter() {
		assertEquals(" BLUE",Card.printColor(color));
		assertEquals(" ADD2",Card.printNumber(number));
	}
}
