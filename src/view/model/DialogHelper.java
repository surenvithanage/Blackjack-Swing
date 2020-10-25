package view.model;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;

/**
 * This class is a mediator between the view and GameEngine.
 * 
 * @author abir
 *
 */

public class DialogHelper {

	private AppFrame frame;
	private GameEngine engine;

	public DialogHelper(AppFrame frame, GameEngine engine) {

		this.frame = frame;
		this.engine = engine;
	}

	/**
	 * Prompts player for details.
	 */
	public Player addPlayerDialog() {
		JTextField name = new JTextField();
		JTextField id = new JTextField();
		JTextField pnts = new JTextField();
		String playerName = "";
		String playerID = "";
		String points = "";
		Object[] message = { "Enter player name: ", name, "Enter player id: ", id,
				"How many points would you like(Integers)? ", pnts };
		int option = JOptionPane.showConfirmDialog(null, message, "Please enter values", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION) {
			playerName = name.getText();
			playerID = id.getText();
			points = pnts.getText();
		}

		boolean doAgain = true;
		int playerPoints = 0;
		do {
			try {
				playerPoints = Integer.parseInt(points);
				doAgain = false;

			} catch (NumberFormatException e) {
				System.out.println(e);
				points = JOptionPane.showInputDialog("Invalid! Please enter points in integers!");
			} catch (Exception e) {
				System.out.println(e);
				points = JOptionPane.showInputDialog("Invalid! Please enter points in integers!");
			}

		} while (doAgain);

		Player player = new SimplePlayer(playerID, playerName, playerPoints);
		return player;
	}

	/**
	 * Prompts player for a bet and updates gameEngine
	 */
	public void placeBetDialog() {
		String b = JOptionPane.showInputDialog("Place bet");

		int bet = Integer.parseInt(b);

		// update GameEngine with valid bet
		boolean check = engine.placeBet(frame.getSelectedPlayer(), bet);
		boolean doAgain = true;
		do {
			if (check == false) {
				String invalid = JOptionPane.showInputDialog("Invalid Bet! Place valid bet!");
				check = engine.placeBet(frame.getSelectedPlayer(), Integer.parseInt(invalid));
			} else {
				engine.placeBet(frame.getSelectedPlayer(), bet);
				doAgain = false;
			}
		} while (doAgain);

	}

}
