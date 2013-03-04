package edu.segal.bradbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class TextingPanel extends JPanel {
	/**
	 * Required for JPanel
	 */
	private static final long serialVersionUID = 1L;
	private JavaMonkey monkey;
	JTextField numberField = new JTextField(12);
	JTextField messageField = new JTextField(30);

	
	TextingPanel(JavaMonkey m) {
		monkey = m;
		
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		JLabel numberLabel = new JLabel("Phone Number");
		add(numberLabel);
		
		add(numberField);
		
		JLabel messageLabel = new JLabel("Message");
		add(messageLabel);
		
		add(messageField);
		
		JButton smsButton = new JButton("Send SMS");
		add(smsButton);
		
		smsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String number = numberField.getText();
				numberField.setText("");
				String message = messageField.getText();
				messageField.setText("");
				monkey.shell("am broadcast -a edu.segal.androidbradbox.smsbroadcast -e number '+1" + number + "' -e message '"+ message + "'");
			}
		});
	}
}
