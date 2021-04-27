package unoMain;

import java.util.ArrayList;

/**
 * Player class storing player information
 * @author 11043
 *
 */
public class Player {
	private String id;
	private ArrayList<Card> handCard;
	private int index;
	private boolean isAI;

	
	
	public Player(String id, ArrayList<Card> handCard, int index, boolean isAI) {
		 this.handCard = handCard;
		 this.id = id;
		 this.index = index;
		 this.isAI = isAI;
	}
	
	public String getPlayerId() {
		return this.id;
	}
	
	public ArrayList<Card> getPlayerCard() {
		return this.handCard;
	}
	
	public int getPlayerIndx() {
		return this.index;
	}
	
	public boolean getIsAI() {
		return this.isAI;
	}
}
