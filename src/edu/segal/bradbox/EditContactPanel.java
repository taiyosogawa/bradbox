package edu.segal.bradbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditContactPanel extends JPanel{
	/**
	 * Required for JPanel
	 */
	private static final long serialVersionUID = 1L;
	SuperFrame superframe;
	JavaMonkey monkey;

	// Navigation button
	JButton homeButton = new JButton("Cancel");
	JButton doneButton = new JButton("Done Editing");
	JButton deleteButton = new JButton("Delete");
	String oldName;
	String oldNumber;
	
	// Edit contact fields
	JLabel nameLabel = new JLabel("Full Name");
	JTextField nameField = new JTextField(30);
	
	JLabel numberLabel = new JLabel("Number");
	JTextField numberField = new JTextField(12);

	
	EditContactPanel(SuperFrame sf, String nm, String no) {
		superframe = sf;
		monkey = sf.monkey;
		oldName = nm;
		oldNumber = no;
		
		nameField.setText(oldName);
		numberField.setText(oldNumber);
		add(homeButton);
		add(nameLabel);
		add(nameField);
		add(numberLabel);
		add(numberField);
		add(deleteButton);
		add(doneButton);
		
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.showKeypad();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				monkey.shell("am broadcast -a edu.segal.androidbradbox.deletecontact -e name '" + oldName + "'");
				System.out.println("attempting to copy contacts");
				try {
					Runtime.getRuntime().exec("/platform-tools/copycontacts.exe");
				} catch (IOException e) {
					System.out.println("Error: IOException when calling copycontacts.exe");
					e.printStackTrace();
				}
				superframe.showKeypad();
			}
		});
		
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				monkey.shell("am broadcast -a edu.segal.androidbradbox.deletecontact -e name '" + oldName + "'");
				monkey.shell("am broadcast -a edu.segal.androidbradbox.addcontact -e name '" + nameField.getText() + "' -e number '"+ numberField.getText() + "'");
				System.out.println("attempting to copy contacts");
				try {
					Runtime.getRuntime().exec("/platform-tools/copycontacts.exe");
				} catch (IOException e) {
					System.out.println("Error: IOException when calling copycontacts.exe");
					e.printStackTrace();
				}
				superframe.showKeypad();
			}
		});
	}

}

