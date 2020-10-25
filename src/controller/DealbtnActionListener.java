package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AppFrame;
import view.model.ViewModel;

public class DealbtnActionListener implements ActionListener {

	private AppFrame frame;
	private GameEngine engine;
	private ViewModel vm;

	public DealbtnActionListener(AppFrame frame, GameEngine engine, ViewModel vm) {
		this.frame = frame;
		this.engine = engine;
		this.vm = vm;
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		final Player player = frame.getSelectedPlayer();
		frame.getDealbtn().setEnabled(false);
		frame.updateDealHouseBtn(true);

		final int DELAY = 1000;
		new Thread() { // Need new thread to display cards one by one.
			public void run() {
				engine.dealPlayer(player, DELAY);

				if (vm.allPlayersDealt()) {
					engine.dealHouse(DELAY);
				}
				if (frame.getSelectedPlayer().getBet() >= 0) {
					frame.updateDealBetbtns(false, true);
				} else {
					frame.updateDealBetbtns(true, false);
				}

			}
		}.start();

		frame.clearPanel("housePanel");
	}

}
