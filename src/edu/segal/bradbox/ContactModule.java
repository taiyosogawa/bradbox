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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ContactModule extends JPanel {
	private static final long serialVersionUID = 1L;
	SuperFrame superframe;
	JLabel nameLabel = new JLabel();
	JLabel numberLabel = new JLabel();
	JButton callButton = new JButton("Call");
	JButton smsButton = new JButton("Text");
	JButton editButton = new JButton("Edit");
	boolean fav;
	int rank;
	String name;
	String number;

	ContactModule(SuperFrame sf, String nm, String no, boolean f, int r) {
		superframe = sf;
		setName(nm);
		setNumber(no);
		fav = f;
		rank = r;

		callButton.setFont(Constants.FONT_18_PLAIN);
		callButton.setBackground(Constants.GREEN);
		smsButton.setFont(Constants.FONT_18_PLAIN);
		editButton.setFont(Constants.FONT_18_PLAIN);
		
		callButton.setPreferredSize(new Dimension(140, 50));
		editButton.setPreferredSize(new Dimension(80, 50));
		
		JPanel namePane = new JPanel();
		namePane.setPreferredSize(new Dimension(110, 50));
		namePane.setLayout(new BoxLayout(namePane, BoxLayout.Y_AXIS));
		JPanel buttonPane = new JPanel();

		nameLabel.setFont(Constants.FONT_14_PLAIN);
		numberLabel.setFont(Constants.FONT_12_PLAIN);
		namePane.add(nameLabel);
		namePane.add(numberLabel);
		
		this.add(namePane);
		this.add(buttonPane);
			buttonPane.add(callButton);
			//buttonPane.add(smsButton);
			buttonPane.add(editButton);
		
		callButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.getKeypad().initiateCall(numberLabel.getText());
			}
		});
		
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.openEditContact(nameLabel.getText(), numberLabel.getText(), fav, rank);
			}
		});
	}
	
	public void setName(String nm) {
		nameLabel.setText(nm);
		nameLabel.setToolTipText(nm);
	}

	public void setNumber(String no) {
		String number = superframe.prettifyNumber(no);
		numberLabel.setText(number);
		numberLabel.setToolTipText(number);
	}
	
	public void setFav(boolean f) {
		fav = f;
	}
}
