package model;

import model.interfaces.Player;

public class SimplePlayer implements Player {

	private String id;
	private String playerName;

	/**
	 * points are for betting
	 */
	private int points;

	/**
	 * result is from the player's hand.
	 */
	private int result = 0;
	private int bet;

	public SimplePlayer(String id, String playerName, int points) {
		this.id = id;
		this.playerName = playerName;
		this.points = points;
	}

	@Override
	public String getPlayerName() {
		return playerName;
	}

	@Override
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public int getPoints() {
		return points;
	}

	@Override
	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String getPlayerId() {
		return id;
	}

	@Override
	public boolean placeBet(int bet) {
		if (bet >= 0 && points >= bet) {
			this.bet = bet;
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int getBet() {
		return bet;
	}

	@Override
	public void resetBet() {
		bet = 0;
	}

	@Override
	public int getResult() {
		return result;
	}

	@Override
	public void setResult(int result) {
		this.result = result;
	}

	public String toString() {
		return String.format("Player: id=%s, name=%s, points=%d", id, playerName, points);
	}

}
