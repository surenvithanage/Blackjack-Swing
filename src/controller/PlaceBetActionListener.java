package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.AppFrame;
import view.model.DialogHelper;

public class PlaceBetActionListener implements ActionListener {

	private AppFrame frame;
	private GameEngine engine;

	public PlaceBetActionListener(AppFrame frame, GameEngine engine) {
		this.frame = frame;
		this.engine = engine;
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		new DialogHelper(frame, engine).placeBetDialog();
		if (frame.getSelectedPlayer().getBet() > 0) {
			frame.updateDealBetbtns(true, false);
		}
		frame.clearPanel("PlayerPanel");
		frame.showBet(frame.getSelectedPlayer().getBet());
	}

}
