package edu.segal.bradbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OptionsPanel extends JPanel{
	JavaMonkey monkey;
	Serialio serialio;
	JButton loudspeakerButton = new JButton("Turn Loudspeaker On");
	
	OptionsPanel(JavaMonkey m, Serialio s) {
		monkey = m;
		serialio = s;
		add(loudspeakerButton);
		loudspeakerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				if(loudspeakerButton.getText() == "Turn Loudspeaker On") {
					serialio.LoudspeakerOn();
					loudspeakerButton.setText("Turn Loudspeaker Off");
				} else {
					serialio.LoudspeakerOff();
					loudspeakerButton.setText("Turn Loudspeaker On");
				}
			}
		});
	}
}

