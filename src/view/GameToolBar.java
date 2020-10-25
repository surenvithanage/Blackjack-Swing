package view;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;

import controller.ComboBoxItemListener;
import controller.DealHouseActionListener;
import controller.DealbtnActionListener;
import controller.PlaceBetActionListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.model.ViewModel;

@SuppressWarnings("serial")
public class GameToolBar extends JToolBar {

	private JButton dealBtn;
	private JButton betBtn;
	private JButton dealHouseBtn;
	private JComboBox<Player> box;
	private DefaultComboBoxModel<Player> model;

	public GameToolBar(AppFrame frame, GameEngine engine, ViewModel vm) {
		dealBtn = new JButton("Deal Player");
		add(dealBtn);
		dealBtn.setEnabled(false);

		dealHouseBtn = new JButton("Deal House");
		add(dealHouseBtn);
		dealHouseBtn.setEnabled(false);

		betBtn = new JButton("Place Bet");
		add(betBtn);
		betBtn.setEnabled(false);

		betBtn.addActionListener(new PlaceBetActionListener(frame, engine));
		dealBtn.addActionListener(new DealbtnActionListener(frame, engine, vm));
		dealHouseBtn.addActionListener(new DealHouseActionListener(engine));

		model = new DefaultComboBoxModel<Player>();
		box = new JComboBox<Player>(model);

		box.addItemListener(new ComboBoxItemListener(frame, vm));
		add(box);
	}

	protected DefaultComboBoxModel<Player> getComboBoxModel() {
		return model;
	}

	protected JComboBox<Player> getComboBox() {
		return box;
	}

	protected JButton getBetbtn() {
		return betBtn;
	}

	protected JButton getDealbtn() {
		return dealBtn;
	}

	protected JButton getDealHouseBtn() {
		return dealHouseBtn;
	}

}
