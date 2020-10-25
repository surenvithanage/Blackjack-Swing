package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import model.interfaces.PlayingCard.Suit;
import model.interfaces.PlayingCard.Value;
import view.interfaces.GameEngineCallback;

/*
 * This is where the main game functionality is contained. All methods from the client are called through 
   this class. Methods in the supporting classes should only be called from.
   GameEngineImpl must maintain a collection (or array) of Players AND a collection 
   (or array) of GameEngineCallbacks.
	
	@author Abir Ishtiaque.
 */

public class GameEngineImpl implements GameEngine {

	private Map<String, Player> playerList = new HashMap<>();
	private Deque<PlayingCard> cardDeck = new LinkedList<PlayingCard>();
	private Collection<GameEngineCallback> callBackCol = new ArrayList<>();

	@Override
	public void dealPlayer(Player player, int delay) {

		int playerHand = deal(player, delay); // Stores the current hand value of the player.

		bustResult(player, playerHand);
		player.setResult(playerHand);
	}

	/**
	 * While player's or house's hand <= 21 keep dealing cards and check for empty
	 * deck with every iteration.
	 * 
	 * @param player
	 *            the current player who will have their result set at the end of
	 *            the hand. Pass null if calling for house.
	 * @param delay
	 *            delay between cards being dealt
	 * @return the current 'hand' of the house or player.
	 */
	private int deal(Player player, int delay) {
		int hand = 0;
		while (hand <= BUST_LEVEL) {
			if (cardDeck.isEmpty()) {
				getShuffledDeck();
			}
			hand += cardDeck.getFirst().getScore(); // Update the current hand with the card about to be dealt.

			// If hand exceeds Bust_Level,21 subtract the card that was about to be dealt
			// and exit loop.
			if (hand > BUST_LEVEL) {
				hand -= cardDeck.getFirst().getScore();
				break;
			}

			// Call nextCard or nextHouseCard depending on if player is passed or a null
			// value.
			for (GameEngineCallback eng : callBackCol) {
				if (player != null) {
					eng.nextCard(player, cardDeck.getFirst(), this);
				} else {
					eng.nextHouseCard(cardDeck.getFirst(), this);
				}
			}

			cardDeck.poll(); // Dealer picks the top card from the deck.

			// Implement delay
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} // End of while loop
		return hand;
	}

	/**
	 * Calls bustCard and result while looping through the GameEngineCallback
	 * collection.
	 * 
	 * @param player
	 *            the current player who will have bustCard and result be called on
	 *            the call backs. Pass null to call for house methods.
	 * @param hand
	 *            the current result of the player's or house's hand
	 */
	private void bustResult(Player player, int hand) {
		for (GameEngineCallback eng : callBackCol) {
			// A null value for player means house version of the callback methods should be
			// called.
			if (player != null) {
				eng.bustCard(player, cardDeck.getFirst(), this);
			} else {
				eng.houseBustCard(cardDeck.getFirst(), this);
			}
		}

		for (GameEngineCallback eng : callBackCol) {
			if (player != null) {
				eng.result(player, hand, this);
			} else {
				eng.houseResult(hand, this);
			}
		}
	}

	@Override
	public void dealHouse(int delay) {
		int houseScore = deal(null, delay);

		// Increment all players' points if they have more score than house. Decrement
		// if players have less than house.
		for (Player players : playerList.values()) {
			if (houseScore < players.getResult()) {
				players.setPoints(players.getPoints() + players.getBet());
			} else if (houseScore > players.getResult()) {
				players.setPoints(-players.getBet() + players.getPoints());
			}
			players.resetBet(); // After each round a player should not have an active bet.

		}
		bustResult(null, houseScore);

	}

	@Override
	public void addPlayer(Player player) {
		playerList.put(player.getPlayerId(), player);
	}

	@Override
	public Player getPlayer(String id) {
		// Iterate through the playerList collection and search for the id.
		for (Player player : playerList.values()) {
			if (player.getPlayerId().equals(id)) {
				return player;
			}
		}
		return null;
	}

	@Override
	public boolean removePlayer(Player player) {
		return playerList.remove(player.getPlayerId(), player);
	}

	@Override
	public void addGameEngineCallback(GameEngineCallback gameEngineCallback) {
		callBackCol.add(gameEngineCallback);
	}

	@Override
	public boolean removeGameEngineCallback(GameEngineCallback gameEngineCallback) {
		return callBackCol.remove(gameEngineCallback);
	}

	@Override
	public Collection<Player> getAllPlayers() {
		return Collections.unmodifiableCollection(playerList.values());
	}

	@Override
	public boolean placeBet(Player player, int bet) {
		if (bet > 0 && player.getPoints() >= bet) {
			player.placeBet(bet);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Deque<PlayingCard> getShuffledDeck() {
		// Populate the deck
		for (Suit suit : Suit.values()) {
			for (Value value : Value.values()) {
				PlayingCardImpl pCard = new PlayingCardImpl(value, suit);
				cardDeck.add(pCard);
			}
		}
		Collections.shuffle((LinkedList<PlayingCard>) cardDeck);
		return cardDeck;
	}

}
