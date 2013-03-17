/*
 * BradBox
 * Copyright 2013 Taiyo Sogawa
 * taiyo <at> u <dot> northwestern <dot> edu
 * Last Revised: March 16, 2013
 * 
 * This file is part of BradBox.

    BradBox is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    BradBox is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with BradBox.  If not, see <http://www.gnu.org/licenses/>.
 */

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
