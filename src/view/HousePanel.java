package view;

import javax.swing.JLabel;

import model.interfaces.PlayingCard;

@SuppressWarnings("serial")
public class HousePanel extends CardPanel {

	public HousePanel() {
		add(new JLabel("House's hand"));
	}

	protected void addCard(PlayingCard card) {
		super.addCard(card);
	}

}
