package view;

import java.util.logging.Level;
import java.util.logging.Logger;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;

/**
 * 
 * Skeleton/Partial example implementation of GameEngineCallback showing Java
 * logging behaviour
 * 
 * @author Caspar Ryan
 * @see view.interfaces.GameEngineCallback
 * 
 */
public class GameEngineCallbackImpl implements GameEngineCallback {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	public GameEngineCallbackImpl() {
		// FINE shows dealing output, INFO only shows result
		logger.setLevel(Level.FINE);
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		StringBuilder sb = new StringBuilder();
		sb.append("Card dealt to " + player.getPlayerName() + " .. ");
		sb.append(card.toString());
		logger.log(Level.INFO, sb.toString()); //FINE
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		// final results logged at Level.INFO
		StringBuilder sb = new StringBuilder();
		sb.append(player.getPlayerName());
		sb.append(" ,final result=" + result);
		logger.log(Level.INFO, sb.toString());
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {

		StringBuilder sb = new StringBuilder();
		sb.append("Card dealt to " + player.getPlayerName() + " .. ");
		sb.append(card.toString() + "... YOU BUSTED!");
		logger.log(Level.INFO, sb.toString()); //FINE
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {

		StringBuilder sb = new StringBuilder();
		sb.append("Card dealt to the House .. " + card.toString());
		logger.log(Level.INFO, sb.toString()); //FINE
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {

		StringBuilder sb = new StringBuilder();
		sb.append("Card dealt to the House .. " + card.toString() + "... HOUSE BUSTED!");
		logger.log(Level.INFO, sb.toString()); //FINE
	}

	@Override
	public void houseResult(int result, GameEngine engine) {

		StringBuilder sb = new StringBuilder();
		sb.append("House, final result=" + result);
		logger.log(Level.INFO, sb.toString());
		StringBuilder sbr = new StringBuilder();
		sbr.append("Final Player Results\n");
		for (Player players : engine.getAllPlayers()) {
			sbr.append(players.toString() + "\n");
		}
		logger.log(Level.INFO, sbr.toString());
	}

}
