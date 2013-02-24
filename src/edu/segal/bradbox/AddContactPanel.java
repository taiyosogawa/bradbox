package edu.segal.bradbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class AddContactPanel extends JPanel {
	private JavaMonkey monkey;
	JTextField nameField = new JTextField(12);
	JTextField numberField = new JTextField(30);

	
	AddContactPanel(JavaMonkey m) {
		monkey = m;
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JLabel nameLabel = new JLabel("Full Name");
		add(nameLabel);
		add(nameField);
		
		JLabel numberLabel = new JLabel("Phone Number");
		add(numberLabel);
		add(numberField);
		
		JButton addButton = new JButton("Add Contact");
		add(addButton);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String name = nameField.getText();
				nameField.setText("");
				String number = numberField.getText();
				numberField.setText("");
				monkey.shell("am broadcast -a edu.segal.androidbradbox.addcontact -e name '" + name + "' -e number '"+ number + "'");
			}
		});
	}

}
