package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;
import view.model.DialogHelper;

public class AddPlayerActionListener implements ActionListener {

	private AppFrame frame;
	private GameEngine engine;

	public AddPlayerActionListener(AppFrame frame, GameEngine engine) {
		this.frame = frame;
		this.engine = engine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Player player = new DialogHelper(frame, engine).addPlayerDialog();
		engine.addPlayer(player);
		frame.getComboBoxModel().addElement(player);
		
		frame.addToSummary(player.toString() + "\n");
		if (frame.getSelectedPlayer().getBet() <= 0) {
			frame.getBetbtn().setEnabled(true);
		}
	}

}
