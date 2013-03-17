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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

public class VolumePanel extends PanelSkeleton {
	private static final long serialVersionUID = 1L;
	JPanel addContactPanel = new JPanel();	
	JPanel volumePanel = new JPanel();
	
	String LOUDSPEAKER_ON = "Turn Loudspeaker On";
	String LOUDSPEAKER_OFF = "Turn Loudspeaker Off";
	// Loudspeaker control button
	JButton loudspeakerButton = new JButton(LOUDSPEAKER_ON);
	JButton muteButton = new JButton("Mute");
	JButton fullVolumeButton = new JButton("Full Volume");
	

	
	VolumePanel(SuperFrame sf) {
		super(sf, "Volume Options");
		
		JPanel content = new JPanel();
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

		addContent(content);
		
		JPanel loudspeakerPanel = new JPanel();
		loudspeakerPanel.setLayout(new BorderLayout());
		loudspeakerPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Constants.DARK_GRAY), 
				BorderFactory.createEmptyBorder(15, 15, 15, 15)), "Loudspeaker", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP, Constants.FONT_18_PLAIN));
		loudspeakerButton.setPreferredSize(new Dimension(400, 100));
		
		
		JPanel phonePanel = new JPanel(new GridLayout(2, 2));
		phonePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Constants.DARK_GRAY), 
				BorderFactory.createEmptyBorder(15, 15, 15, 15)), "Phone Ringer Volume", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.ABOVE_TOP, Constants.FONT_18_PLAIN));
		
		//JPanel volumePanel = new JPanel(new GridLayout(2, 2));
		ImageIcon upIcon = new ImageIcon(this.getClass().getResource("/img/up_arrow.png"));
		ImageIcon downIcon = new ImageIcon(this.getClass().getResource("/img/down_arrow.png"));
		JButton upButton = new JButton(upIcon);
		JButton downButton = new JButton(downIcon);
		
		muteButton.setFont(Constants.FONT_26_PLAIN);
		fullVolumeButton.setFont(Constants.FONT_26_PLAIN);
		
		content.add(loudspeakerPanel);
			loudspeakerPanel.add(loudspeakerButton, BorderLayout.CENTER);
		content.add(Box.createVerticalStrut(40));
		content.add(phonePanel);
			phonePanel.add(upButton);
			phonePanel.add(fullVolumeButton);
			phonePanel.add(downButton);
			phonePanel.add(muteButton);

		loudspeakerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(loudspeakerButton.getText() == LOUDSPEAKER_ON) {
					serialio.LoudspeakerOn();
					loudspeakerButton.setText(LOUDSPEAKER_OFF);
				} else {
					serialio.LoudspeakerOff();
					loudspeakerButton.setText(LOUDSPEAKER_ON);
				}
			}
		});
		loudspeakerButton.setFont(Constants.FONT_26_PLAIN);
		
		muteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				monkey.unlock();
				for(int i = 0; i < 10; i++) {
					monkey.press("KEYCODE_VOLUME_DOWN");
				}
			}
		});
		
		fullVolumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				monkey.unlock();
				for(int i = 0; i < 10; i++) {
					monkey.press("KEYCODE_VOLUME_UP");
				}
			}
		});
		
		upButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				monkey.unlock();
				monkey.press("KEYCODE_VOLUME_UP");
			}
		});
		
		downButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				monkey.unlock();
				monkey.press("KEYCODE_VOLUME_DOWN");
			}
		});
	}
}

