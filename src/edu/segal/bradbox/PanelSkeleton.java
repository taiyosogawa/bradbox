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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PanelSkeleton extends JPanel {
	private static final long serialVersionUID = 1L;
	SuperFrame superframe;
	JavaMonkey monkey;
	Serialio serialio;
	JButton homeButton;
	JPanel contentPanel = new JPanel();

	PanelSkeleton(SuperFrame sf, String title) {
		superframe = sf;
		monkey = superframe.getMonkey();
		serialio = superframe.getSerialio();
		
		setLayout(new BorderLayout());
		setBackground(Constants.BACKGROUND_GRAY);
		setPreferredSize(new Dimension(900, 650));
		
		ImageIcon homeIcon = new ImageIcon(this.getClass().getResource("/img/bradbox_logo.png"));
		homeButton = new JButton(homeIcon);
		homeButton.setBackground(Constants.BRAD_BLUE);

		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.showKeypad();
			}
		});
	
		JLabel titleLabel = new JLabel(title);
		titleLabel.setBorder(new EmptyBorder(0, 25, 0, 0));
		titleLabel.setFont(Constants.FONT_40_BOLD);
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		titlePanel.setLayout(new BorderLayout());
		
		add(titlePanel, BorderLayout.PAGE_START);
			titlePanel.add(homeButton, BorderLayout.LINE_START);
			titlePanel.add(titleLabel, BorderLayout.CENTER);
		add(contentPanel, BorderLayout.CENTER);
	}
	
	public void addContent(JPanel content) {
		contentPanel.add(content);
	}
}
