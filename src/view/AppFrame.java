package view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.model.ViewModel;

@SuppressWarnings("serial")
public class AppFrame extends JFrame {

	private GameToolBar toolBar;
	private GameStatusBar statusBar;
	private PlayerPanel playerPanel;
	private EastPanel eastPanel;

	public AppFrame(GameEngine engine, ViewModel vm) {
		super("CardGame");

		setBounds(100, 100, 1200, 890);

		setJMenuBar(new MainMenuBar(this, engine));

		setLayout(new BorderLayout());

		toolBar = new GameToolBar(this, engine, vm);
		add(toolBar, BorderLayout.NORTH);

		playerPanel = new PlayerPanel();
		playerPanel.setBackground(new Color(0, 122, 0));
		add(playerPanel, BorderLayout.CENTER);

		eastPanel = new EastPanel();
		add(eastPanel, BorderLayout.EAST);

		statusBar = new GameStatusBar();
		add(statusBar, BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	protected GameStatusBar getStatusBar() {
		return statusBar;
	}

	public DefaultComboBoxModel<Player> getComboBoxModel() {
		return toolBar.getComboBoxModel();
	}

	public JButton getBetbtn() {
		return toolBar.getBetbtn();
	}

	public JButton getDealbtn() {
		return toolBar.getDealbtn();
	}

	/**
	 * Clears the panel of all cards and repaints the panel.
	 * 
	 * @param panelToClear
	 *            enter playerpanel or housepanel to clear them.
	 */
	public void clearPanel(String panelToClear) {
		if (panelToClear.equalsIgnoreCase("PlayerPanel")) {
			playerPanel.removeAll();
			playerPanel.repaint();
		} else {
			eastPanel.getHousePanel().removeAll();
			eastPanel.getHousePanel().repaint();
		}
	}

	/**
	 * Displays the card being dealt to the AppFrame via HousePanel
	 * 
	 * @param card
	 *            to be added and displayed
	 */
	protected void addHouseCard(PlayingCard card) {
		eastPanel.getHousePanel().addCard(card);
	}

	public void showBet(int bet) {
		playerPanel.showBet(bet);
	}

	/**
	 * Enables and disables deal and bet buttons respectively true -> enables, false
	 * -> disables
	 * 
	 * @param deal
	 * 
	 * @param bet
	 * 
	 */
	public void updateDealBetbtns(boolean deal, boolean bet) {
		toolBar.getDealbtn().setEnabled(deal);
		toolBar.getBetbtn().setEnabled(bet);
	}

	/**
	 * Check if the card that is added to the view belongs to selected player, and
	 * revalidates the frame.
	 * 
	 * @param player
	 *            to be dealt
	 * @param card
	 *            to be added
	 */
	public void validateCardAddition(Player player, PlayingCard card) {
		if (toolBar.getComboBox().getSelectedItem().equals(player)) {
			playerPanel.addCard(card);
			playerPanel.revalidate();
		}
	}

	/**
	 * Player selected via the combobox in toolbar.
	 * 
	 * @return player
	 */
	public Player getSelectedPlayer() {
		return (Player) toolBar.getComboBoxModel().getSelectedItem();
	}

	public void addToSummary(String results) {
		eastPanel.getSummaryPan().addResults(results);
	}

	public void clearSummary() {
		eastPanel.getSummaryPan().clearSummary();
	}

	public void updateDealHouseBtn(boolean x) {
		toolBar.getDealHouseBtn().setEnabled(x);
	}

}
