package main;

import javax.swing.SwingUtilities;

import model.GameEngineImpl;
import model.interfaces.GameEngine;
import validate.Validator;
import view.AppFrame;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.model.ViewModel;

public class Driver {

	public static void main(String[] args) {

		final GameEngine engine = new GameEngineImpl();
		Validator.validate(false);
		final ViewModel vm = new ViewModel(engine);
		// Add logging
		engine.addGameEngineCallback(new GameEngineCallbackImpl());

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				AppFrame frame = new AppFrame(engine, vm);
				engine.addGameEngineCallback(new GameEngineCallbackGUI(frame, vm));
			}
		});
	}
}
