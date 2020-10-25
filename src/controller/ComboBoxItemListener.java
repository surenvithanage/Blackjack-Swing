package controller;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import model.interfaces.Player;
import view.AppFrame;
import view.model.ViewModel;

public class ComboBoxItemListener implements ItemListener {

	private AppFrame frame;
	private ViewModel vm;

	public ComboBoxItemListener(AppFrame frame, ViewModel vm) {
		this.frame = frame;
		this.vm = vm;
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		frame.clearPanel("PlayerPanel");
		frame.revalidate();

		if (event.getStateChange() == ItemEvent.SELECTED) {
			Player selectedPlayer = (Player) event.getItem();

			vm.showPlayerCards(selectedPlayer, frame);

			if (selectedPlayer.getBet() <= 0) // If player has not placed bet he can't be dealt to
			{
				frame.updateDealBetbtns(false, true);
			} else {
				frame.updateDealBetbtns(true, false);
				frame.showBet(selectedPlayer.getBet());
			}
			
			// If player has already been dealt, disable deal btn
			if(vm.getDealtPlayerMap().get(frame.getSelectedPlayer()) != null)
			{
				frame.getDealbtn().setEnabled(false);
			}
		}
	}

}
