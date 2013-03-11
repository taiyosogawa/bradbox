package edu.segal.bradbox;

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

public class OptionsPanel extends JPanel{
	/**
	 * Required for JPanel
	 */
	private static final long serialVersionUID = 1L;
	SuperFrame superframe;
	JavaMonkey monkey;
	Serialio serialio;
	
	String LOUDSPEAKER_ON = "Turn Loudspeaker On";
	String LOUDSPEAKER_OFF = "Turn Loudspeaker Off";
	// Loudspeaker control button
	JButton loudspeakerButton = new JButton(LOUDSPEAKER_ON);
	
	// Navigation button
	JButton homeButton = new JButton("Home");
	
	// Add contact fields
	JTextField nameField = new JTextField(30);
	JTextField numberField = new JTextField(12);
	JCheckBox favCheckbox = new JCheckBox("Add as favorite");
	
	OptionsPanel(SuperFrame sf) {
		superframe = sf;
		monkey = sf.monkey;
		serialio = sf.serialio;
		setLayout(new GridLayout(0, 1));
		add(homeButton);
		add(loudspeakerButton);
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
		
		add(favCheckbox);
		
		JButton addButton = new JButton("Add Contact");
		add(addButton);
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {	
				monkey.shell("am broadcast -a edu.segal.androidbradbox.addcontact -e name '" + nameField.getText() + "' -e number '"+ numberField.getText() + "'");
				System.out.println("attempting to copy contacts");
				try {
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
				
				if(favCheckbox.isSelected()) {
					SwingUtilities.invokeLater(new Runnable() {
				        public void run() {
				            EditFavoriteFrame ff = new EditFavoriteFrame(superframe, nameField.getText());
				            ff.setVisible(true);
				            nameField.setText("");
							numberField.setText("");
				        }
					});
				} else {	
					nameField.setText("");
					numberField.setText("");
				}
			}
		});
	}
}

