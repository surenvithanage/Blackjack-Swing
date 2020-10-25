package view;

import java.awt.FlowLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.interfaces.PlayingCard;

/**
 * House panel and player panel extends this class. A CardPanel can only be for
 * the player or the house,so this is abstract.
 * 
 * @author abir
 *
 */

@SuppressWarnings("serial")
public abstract class CardPanel extends JPanel {

	private JLabel pCard;

	public CardPanel() {
		setLayout(new FlowLayout());
	}

	protected void addCard(PlayingCard card) {
		ImageIcon icon = new ImageIcon(getCardFileName(card));
		// Make image smaller
		Image img = icon.getImage();
		Image newImg = img.getScaledInstance(130, 210, java.awt.Image.SCALE_SMOOTH);

		pCard = new JLabel(new ImageIcon(newImg));
		add(pCard);
	}

	private String getCardFileName(PlayingCard card) {
		return "cardImgs" + File.separator + card.getValue().name() + "_" + card.getSuit().name() + ".png";
	}

}
