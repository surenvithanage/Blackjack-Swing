package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;

public class DealHouseActionListener implements ActionListener {

	private GameEngine engine;

	public DealHouseActionListener(GameEngine engine) {
		this.engine = engine;
	}

	@Override
	public void actionPerformed(ActionEvent eve) {
		new Thread() {
			public void run() {
				engine.dealHouse(1000);
			}
		}.start();
	}

}
