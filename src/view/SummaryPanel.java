package view;

import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Displays each player's details and points along with results for player and
 * the house.
 * 
 * @author abir
 *
 */

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {

	private JTextArea text;

	public SummaryPanel() {
		text = new JTextArea("Summary\n");
		text.setFont(new Font("Courier New", Font.PLAIN, 17));
		text.setEditable(false);
		add(text);
	}

	protected void addResults(String results) {
		text.append(results);
	}

	protected void clearSummary() {
		text.setText("");
	}

}
