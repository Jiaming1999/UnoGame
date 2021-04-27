package unitTest;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import unoMain.*;

public class unoPlayersTests {
	@Test public void ValidcCardConstructor() throws Exception{
		String[] playerIDs = {"John","Martin","Bob"};
		ArrayList<ArrayList<Card>> playersHandCard = new ArrayList<ArrayList<Card>>();
		Card[] handCard = {new Card(Card.Color.BLUE,Card.Number.EIGHT)};
		for (int i = 0; i < 2; i++) {
			playersHandCard.add(new ArrayList<Card>(Arrays.asList(handCard)));
		}
		Player John = new Player("John",playersHandCard.get(0),0,false);
		assertEquals(John.getPlayerId(),"John");
		playersHandCard.add(new ArrayList<Card>());
		Players players = new Players(3,0,playerIDs,playersHandCard,0);
		assertEquals(players.getPlayerNumber(),3);
		assertEquals(players.getCurrentPlayerID(),"John");
		assertEquals(players.getPlayerHandCard(1),Arrays.asList(handCard));
		assertEquals(players.getPlayerSize(0),1);
		assertEquals(players.isPlayerHandEmpty(2),true);
		players.setDirection(false);
		players.nextPlayer();
		assertEquals(players.getCurrentPlayerID(),"Bob");
		
	}
}
