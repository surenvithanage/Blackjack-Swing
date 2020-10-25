package view;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.interfaces.GameEngineCallback;
import view.model.ViewModel;

/**
 * Writes to the GUI
 * 
 * @author abir
 */

public class GameEngineCallbackGUI implements GameEngineCallback {

	private AppFrame frame;
	private ViewModel vm;

	public GameEngineCallbackGUI(AppFrame frame, ViewModel vm) {
		this.frame = frame;
		this.vm = vm;
	}

	@Override
	public void nextCard(Player player, PlayingCard card, GameEngine engine) {
		StringBuilder sb = new StringBuilder();
		sb.append("Card dealt to " + player.getPlayerName() + " .. ");
		sb.append(card.toString());

		frame.getStatusBar().setStatus1(sb.toString());

		// update view model -> keep track of cards been dealt to player
		vm.addDealtPlayers(player, card);

		if (frame.getSelectedPlayer().equals(player)) {
			frame.validateCardAddition(player, card);
		}
	}

	@Override
	public void bustCard(Player player, PlayingCard card, GameEngine engine) {
		StringBuilder sb = new StringBuilder();
		sb.append("Card dealt to " + player.getPlayerName() + " .. ");
		sb.append(card.toString() + "... YOU BUSTED!");

		vm.addDealtPlayers(player, card);
		if (frame.getSelectedPlayer().equals(player)) {
			frame.validateCardAddition(player, card);
		}
		frame.getStatusBar().setStatus2(sb.toString());
	}

	@Override
	public void result(Player player, int result, GameEngine engine) {
		StringBuilder sb = new StringBuilder();
		sb.append(player.getPlayerName());
		sb.append(" ,final result=" + result);
		frame.getStatusBar().setStatus3(sb.toString());
		frame.addToSummary(sb.toString() + "\n");
	}

	@Override
	public void nextHouseCard(PlayingCard card, GameEngine engine) {
		frame.addHouseCard(card);
		frame.revalidate();
	}

	@Override
	public void houseBustCard(PlayingCard card, GameEngine engine) {
		StringBuilder sb = new StringBuilder();
		sb.append("Card dealt to the House .. " + card.toString() + "... HOUSE BUSTED!");
		frame.addHouseCard(card);
		frame.revalidate();
	}

	@Override
	public void houseResult(int result, GameEngine engine) {
		StringBuilder sb = new StringBuilder();
		sb.append("House, final result=" + result);

		StringBuilder sbr = new StringBuilder();
		sbr.append("Final Player Results\n");
		for (Player players : engine.getAllPlayers()) {
			sbr.append(players.toString() + "\n");
		}
		frame.addToSummary(sb.toString() + "\n" + sbr.toString());
		vm.getDealtPlayerMap().clear(); // Round ends so clear dealtPlayers hashmap.
	}

}
