package edu.segal.bradbox;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContactsPanel extends JPanel {
	private JLabel label = new JLabel();
	
	ContactsPanel() {
		label.setText("Contacts Panel");
		add(label);
	}
}
