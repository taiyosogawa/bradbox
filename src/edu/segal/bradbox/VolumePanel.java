package edu.segal.bradbox;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class VolumePanel extends PanelSkeleton {
	JPanel addContactPanel = new JPanel();	
	JPanel volumePanel = new JPanel();
	
	String LOUDSPEAKER_ON = "Turn Loudspeaker On";
	String LOUDSPEAKER_OFF = "Turn Loudspeaker Off";
	// Loudspeaker control button
	JButton loudspeakerButton = new JButton(LOUDSPEAKER_ON);
	JButton ringerButton = new JButton("Silence Ringer");

	
	VolumePanel(SuperFrame sf) {
		super(sf, "Volume Options");
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(0, 1));

		addContent(content);
		content.add(loudspeakerButton);
		content.add(ringerButton);

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
		loudspeakerButton.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
		
		ringerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
			}
		});
		ringerButton.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		
	}
}

