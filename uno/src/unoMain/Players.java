package unoMain;

import java.util.ArrayList;

public class Players {
	private String[] playerIDs;
	private int currentPlayIndx;
	private boolean isClockwised;
	private Player[] players;

	/**
	 * Constructor to construct all players in the game. Usage of ArrayList for all
	 * players hand card inspired by Tutorial
	 * https://www.youtube.com/watch?v=_6ZdGGou6pM&t=72s By Code Clique
	 * 
	 * @param playerIDs
	 * @param playersHandCard
	 * @param currentPlayIndx
	 */
	public Players(int playerNum, int AInum, String[] playerIDs, ArrayList<ArrayList<Card>> playersHandCard,
			int currentPlayIndx) {
		this.players = new Player[playerIDs.length];
		for (int i = 0; i < playerNum; i++) {
			this.players[i] = new Player(playerIDs[i], playersHandCard.get(i), i, false);
		}
		for (int i = playerNum; i < playerNum + AInum; i++) {
			this.players[i] = new Player(playerIDs[i], playersHandCard.get(i), i, true);
		}
		this.playerIDs = playerIDs;
		this.currentPlayIndx = currentPlayIndx;
	}

	/**
	 * set order of play
	 * 
	 * @param isClockWised
	 */
	public void setDirection(boolean isClockWised) {
		this.isClockwised = isClockWised;
	}

	/**
	 * Turn for next player in order to play
	 */
	public void nextPlayer() {
		if (isClockwised == true) {
			currentPlayIndx = (currentPlayIndx + 1) % playerIDs.length;
		} else {
			currentPlayIndx = (currentPlayIndx - 1) % playerIDs.length;
			if (currentPlayIndx < 0) {
				currentPlayIndx = playerIDs.length - 1;
			}
		}
	}

	/**
	 * Turn for next player in order to play
	 */
	public String getNextPlayer() {
		int nextPlayer;
		if (isClockwised == true) {
			nextPlayer = (currentPlayIndx + 1) % playerIDs.length;
		} else {
			nextPlayer = (currentPlayIndx - 1) % playerIDs.length;
			if (nextPlayer < 0) {
				nextPlayer = playerIDs.length - 1;
			}
		}
		return playerIDs[nextPlayer];
	}

	/**
	 * 
	 * @return the list of all players in game.
	 */
	public String[] getAllPlayers() {
		return this.playerIDs;
	}

	/**
	 * get current player's index
	 * 
	 * @return
	 */
	public int getCurrentPlayerIndx() {
		return this.currentPlayIndx;
	}

	/**
	 * get current turn player's ID
	 * 
	 * @return currentplayer's ID number
	 */
	public String getCurrentPlayerID() {
		if (this.playerIDs.length == 0) {
			return null;
		}
		return this.playerIDs[this.currentPlayIndx];
	}
	
	public Player getCurrentPlayer() {
		return this.players[this.currentPlayIndx];
	}

	/**
	 * get player's hand card
	 * 
	 * @param index of players' position in the queue
	 * @return list of player's hand card
	 */
	public ArrayList<Card> getPlayerHandCard(int index) {
		return this.players[index].getPlayerCard();
	}

	/**
	 * check if player pid play all cards out
	 * 
	 * @param pid player id given
	 * @return true if no card left; false otherwise
	 */
	public boolean isPlayerHandEmpty(int i) {
		return this.getPlayerHandCard(i).isEmpty();
	}

	/**
	 * return current number of cards player pid holds
	 * 
	 * @param id player id to be searched
	 * @return number of cards pid holds
	 */
	public int getPlayerSize(int i) {
		return getPlayerHandCard(i).size();
	}

	public int getPlayerNumber() {
		return this.playerIDs.length;
	}

	public boolean getDirection() {
		return this.isClockwised;
	}

	public Player getPlayer(int id) {
		return this.players[id];
	}

}
