package edu.segal.bradbox;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ContactModule extends JPanel {
	KeypadPanel keypad;
	JLabel nameLabel = new JLabel();
	JLabel numberLabel = new JLabel();
	JButton callButton = new JButton("Call");
	JButton smsButton = new JButton("Text");
	JButton editButton = new JButton("Edit");

	ContactModule(KeypadPanel k, String nm, String no) {
		keypad = k;
		setName(nm);
		setNumber(no);
		
		// May need to initialize look and feel here
		
		JPanel namePane = new JPanel();
		namePane.setLayout(new BoxLayout(namePane, BoxLayout.Y_AXIS));
		namePane.add(nameLabel);
		namePane.add(numberLabel);
		
		this.add(namePane);
		this.add(callButton);
		this.add(smsButton);
		this.add(editButton);
	}
	
	public void setName(String nm) {
		nameLabel.setText(nm);
	}

	public void setNumber(String no) {
		numberLabel.setText(no);
	}
}
