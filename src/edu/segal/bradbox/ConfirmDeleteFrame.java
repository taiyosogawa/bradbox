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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class ConfirmDeleteFrame extends JFrame{
	private static final long serialVersionUID = 1L;
	JLabel messageLabel = new JLabel("Are you sure you want to delete this contact?");
	JButton cancelButton = new JButton("Cancel");
	JButton deleteButton = new JButton("Delete");
	JFrame frame = this;
	String name;
	SuperFrame superframe;
	JavaMonkey monkey;
	boolean fav;
	int rank;
	
	
	ConfirmDeleteFrame(SuperFrame sf, String n, boolean f, int r) {
		name = n;
		superframe = sf;
		fav = f;
		rank = r;
		monkey = superframe.getMonkey();
		messageLabel.setText("Are you sure you want to delete " + name + "?");
		messageLabel.setFont(Constants.FONT_14_PLAIN);
		initWindow();
		JPanel contentPanel = new JPanel();
		
		cancelButton.setFont(Constants.FONT_20_BOLD);
		deleteButton.setFont(Constants.FONT_20_BOLD);
		deleteButton.setBackground(Constants.RED);
		
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		contentPanel.setLayout(new GridLayout(0, 1));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 0));
		add(contentPanel);
		contentPanel.add(messageLabel);
		contentPanel.add(buttonPanel);
			buttonPanel.add(cancelButton);
			buttonPanel.add(deleteButton);
			
		cancelButton.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
				frame.dispose();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				monkey.shell("am broadcast -a edu.segal.androidbradbox.deletecontact -e name '" + name + "'");
				superframe.runExecutable("/platform-tools/copycontacts.exe");
				
				if(fav) {
					superframe.updateFavorite("Favorite " + Integer.toString(rank), Integer.toString(rank));
				}
				frame.dispose();
			}
		});
	}
	
	private final void initWindow() { 
		 // Initialize the UI look and feel
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setTitle("Edit Favorite");
		setSize(400, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
