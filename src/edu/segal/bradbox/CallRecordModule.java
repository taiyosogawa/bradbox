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

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CallRecordModule extends JPanel {
	private static final long serialVersionUID = 1L;
	SuperFrame superframe;
	JLabel nameLabel = new JLabel();
	JLabel numberLabel = new JLabel();
	JLabel timeLabel = new JLabel();
	JButton callButton = new JButton("Call");
	JButton addContactButton = new JButton("Save as Contact");
	
	CallRecordModule (SuperFrame sf, String nm, String no, String t) {
		superframe = sf;
		if(nm == null) {
			nm = superframe.getKeypad().getNameByNumber(no);
		} else {
			setName(nm);
		}
		setNumber(no);
		setTime(t);
		
		setLayout(new GridLayout(1, 0));
		setPreferredSize(new Dimension(800, 60));
		setBorder(new LineBorder(Constants.DARK_GRAY));
		timeLabel.setFont(Constants.FONT_20_PLAIN);

		nameLabel.setFont(Constants.FONT_20_BOLD);
		numberLabel.setFont(Constants.FONT_20_PLAIN);
		
		callButton.setBackground(Constants.GREEN);

		callButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.getKeypad().initiateCall(numberLabel.getText());
				superframe.showKeypad();
			}
		});
		
		addContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.openEditContact(nameLabel.getText(), numberLabel.getText(), false, 1);
			}
		});
		
		callButton.setFont(Constants.FONT_20_BOLD);
		callButton.setPreferredSize(new Dimension(180, 60));
		
		JPanel addContainer = new JPanel();
		addContainer.setBorder(new EmptyBorder(0, 0, 0, 30));
		addContactButton.setFont(Constants.FONT_20_BOLD);
		addContactButton.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		add(timeLabel);
		if(nameLabel.getText().equals("")) {
			addContainer.add(addContactButton);
			add(addContainer);
		} else {
			add(nameLabel);
		}
		add(numberLabel);
		add(callButton);
	}
	
	public void setName(String nm) {
		nameLabel.setText(nm);
		nameLabel.setToolTipText(nm);
	}

	public void setNumber(String no) {
		numberLabel.setText(no);
		numberLabel.setToolTipText(no);
	}
	
	public void setTime(String t) {
		timeLabel.setText(t);
		timeLabel.setToolTipText(t);
	}

}
