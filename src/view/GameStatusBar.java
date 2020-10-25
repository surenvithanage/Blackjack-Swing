package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameStatusBar extends JPanel {

	private JLabel statusLabel1 = new JLabel("Game -> Add Player", JLabel.LEFT);
	private JLabel statusLabel2 = new JLabel("status 2", JLabel.CENTER);
	private JLabel statusLabel3 = new JLabel("Please giff marks", JLabel.RIGHT);

	public GameStatusBar() {
		setLayout(new GridLayout(1, 3));

		statusLabel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		statusLabel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		statusLabel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		add(statusLabel1);
		add(statusLabel2);
		add(statusLabel3);
	}

	protected void setStatus1(String update) {
		statusLabel1.setText(update);
	}

	protected void setStatus2(String update) {
		statusLabel2.setText(update);
	}

	protected void setStatus3(String update) {
		statusLabel3.setText(update);
	}
}
