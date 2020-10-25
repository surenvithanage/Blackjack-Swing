package view;

import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controller.AddPlayerActionListener;
import controller.ExitActionListener;
import model.interfaces.GameEngine;

@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar {

	public MainMenuBar(AppFrame frame, GameEngine engine) {
		JMenu fileMenu = new JMenu("Game");

		fileMenu.setMnemonic(KeyEvent.VK_I);
		this.add(fileMenu);

		JMenuItem addPlayer = new JMenuItem("Add player");
		addPlayer.addActionListener(new AddPlayerActionListener(frame, engine));

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitActionListener());

		fileMenu.add(addPlayer);
		fileMenu.addSeparator();
		fileMenu.add(exit);
	}

}
