package edu.segal.bradbox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContactModule extends JPanel {
	/**
	 * Required for JPanel
	 */
	private static final long serialVersionUID = 1L;
	
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
		
		Font buttonFont = new Font("SansSerif", Font.PLAIN, 18);
		Font labelFont = new Font("SansSerif", Font.PLAIN, 12);
		callButton.setFont(buttonFont);
		smsButton.setFont(buttonFont);
		editButton.setFont(buttonFont);
		
		// May need to initialize look and feel here
		
		JPanel namePane = new JPanel();
		namePane.setPreferredSize(new Dimension(110, 40));
		namePane.setLayout(new BoxLayout(namePane, BoxLayout.Y_AXIS));
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new GridLayout(1, 0));
		nameLabel.setFont(labelFont);
		numberLabel.setFont(labelFont);
		namePane.add(nameLabel);
		namePane.add(numberLabel);
		
		this.add(namePane);
		this.add(buttonPane);
			buttonPane.add(callButton);
			//buttonPane.add(smsButton);
			buttonPane.add(editButton);
		
		callButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				keypad.initiateCall(numberLabel.getText());
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				keypad.openEditContact(nameLabel.getText(), numberLabel.getText());
			}
		});
	}
	
	public void setName(String nm) {
		nameLabel.setText(nm);
	}

	public void setNumber(String no) {
		numberLabel.setText(no);
	}
}
