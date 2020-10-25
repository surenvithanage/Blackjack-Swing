package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Contains housePanel and summaryPanel.
 * 
 * @author abir
 *
 */
@SuppressWarnings("serial")
public class EastPanel extends JPanel {
	private HousePanel housePanel;
	private SummaryPanel summaryPanel;

	public EastPanel() {
		setLayout(new BorderLayout());
		housePanel = new HousePanel();
		add(housePanel, BorderLayout.NORTH);

		summaryPanel = new SummaryPanel();
		add(summaryPanel, BorderLayout.SOUTH);
	}

	protected HousePanel getHousePanel() {
		return housePanel;
	}

	protected SummaryPanel getSummaryPan() {
		return summaryPanel;
	}
}
