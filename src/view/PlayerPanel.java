package view;

import java.awt.Font;

import javax.swing.JLabel;

import model.interfaces.PlayingCard;

/**
 * Displays playing cards to player
 * 
 * @author abir
 *
 */
@SuppressWarnings("serial")
public class PlayerPanel extends CardPanel {

	private JLabel displayBet;
	private JLabel pCard;

	public PlayerPanel() {
		add(new JLabel("Player's hand"));
	}

	protected void addCard(PlayingCard card) {
		super.addCard(card);
	}

	protected void showBet(int bet) {
		add(displayBet = new JLabel("Player's bet :" + bet));
		displayBet.setFont(new Font("Courier New", Font.BOLD, 17));
	}

	protected JLabel getCardLabel() {
		return pCard;
	}
}
