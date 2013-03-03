package edu.segal.bradbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class OptionsPanel extends JPanel{
	SuperFrame superframe;
	JavaMonkey monkey;
	Serialio serialio;
	
	// Loudspeaker control button
	JButton loudspeakerButton = new JButton("Turn Loudspeaker On");
	
	// Navigation button
	JButton homeButton = new JButton("Home");
	
	// Add contact fields
	JTextField nameField = new JTextField(30);
	JTextField numberField = new JTextField(12);

	
	OptionsPanel(SuperFrame sf, JavaMonkey m, Serialio sio) {
		superframe = sf;
		monkey = m;
		serialio = sio;
		add(loudspeakerButton);
		add(homeButton);
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
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.showKeypad();
			}
		});
		
			
		/*
		 *  Adding Contacts
		 */
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
				try {
					System.out.println("about to copy contacts");
					Runtime.getRuntime().exec("/platform-tools/copycontacts.exe");
					/*
					//we might consider using pr.destroy(); after a certain amount of time. The following will print output from copycontacts.exe
					BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));	 
	                String line=null;
	                while((line=input.readLine()) != null) {
	                    System.out.println(line);
	                }
	                */
				} catch (IOException e) {
					System.out.println("Error: IOException when calling copycontacts.exe");
					e.printStackTrace();
				}
			}
		});
	}
}

