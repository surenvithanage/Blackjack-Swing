package view.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.SwingUtilities;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.AppFrame;

/**
 * Stores information relating to the view.
 * 
 * @author abir
 *
 */
public class ViewModel {

	private GameEngine engine;

	/**
	 * Stores a list of playing cards that are dealt to a particular player.
	 */
	private Map<Player, List<PlayingCard>> dealtPlayers = new HashMap<>();

	public ViewModel(GameEngine engine) {
		this.engine = engine;
	}

	public void addDealtPlayers(Player player, PlayingCard card) {
		List<PlayingCard> dealtCards = dealtPlayers.get(player);
		// If arraylist does not exist, then make it.
		if (dealtCards == null) {
			dealtCards = new ArrayList<>();
			dealtCards.add(card);
			dealtPlayers.put(player, dealtCards);
		} else {
			if (!dealtCards.contains(card)) {
				dealtCards.add(card);
			}
		}
	}

	public Map<Player, List<PlayingCard>> getDealtPlayerMap() {
		return dealtPlayers;
	}

	/**
	 * Checks if all the players that have been added, have been dealt their cards.
	 * 
	 * @return
	 */
	public boolean allPlayersDealt() {
		return (engine.getAllPlayers().size() == dealtPlayers.size());
	}

	/**
	 * Gets the player's list of cards from the hashmap. Iterates through that list
	 * re-adding the cards to the view in the EDT thread.
	 * 
	 * @param player
	 *            selected from combobox
	 * @param frame
	 */
	public void showPlayerCards(Player player, AppFrame frame) {
		if (dealtPlayers.values() != null && dealtPlayers != null && dealtPlayers.get(player) != null) {
			for (PlayingCard pCard : dealtPlayers.get(player)) {
				if (pCard != null) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							frame.validateCardAddition(player, pCard);
						}

					});
				}
			}
		}
	}

}
