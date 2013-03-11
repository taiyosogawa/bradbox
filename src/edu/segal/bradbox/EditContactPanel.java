package edu.segal.bradbox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class EditContactPanel extends JPanel{
	/**
	 * Required for JPanel
	 */
	private static final long serialVersionUID = 1L;
	SuperFrame superframe;
	JavaMonkey monkey;

	// Navigation button
	JButton homeButton = new JButton("Cancel");
	JButton doneButton = new JButton("Save");
	JButton deleteButton = new JButton("Delete");
	String oldName;
	String oldNumber;
	JCheckBox favCheckbox = new JCheckBox("Save as Favorite");
	// Edit contact fields
	JLabel nameLabel = new JLabel("Full Name");
	JTextField nameField = new JTextField(30);
	
	JLabel numberLabel = new JLabel("Number");
	JTextField numberField = new JTextField(12);
	boolean fav;
	int rank;

	
	EditContactPanel(SuperFrame sf, String nm, String no, boolean f, int r) {
		superframe = sf;
		monkey = sf.monkey;
		oldName = nm;
		oldNumber = no;
		fav = f;
		rank = r;
		
		if(fav) {
			favCheckbox.setSelected(true);
		}
		setLayout(new GridLayout(0, 1));
		nameField.setText(oldName);
		numberField.setText(oldNumber);
		add(homeButton);
		add(nameLabel);
		add(nameField);
		add(numberLabel);
		add(numberField);
		add(deleteButton);
		add(favCheckbox);
		add(doneButton);
		
		homeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.showKeypad();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				SwingUtilities.invokeLater(new Runnable() {
			        public void run() {
			            ConfirmDeleteFrame cdf = new ConfirmDeleteFrame(superframe, oldName, fav, rank);
			            cdf.setVisible(true);
			        }
				});
				
				/*
				monkey.shell("am broadcast -a edu.segal.androidbradbox.deletecontact -e name '" + oldName + "'");
				System.out.println("attempting to copy contacts");
				try {
					Runtime.getRuntime().exec("/platform-tools/copycontacts.exe");
				} catch (IOException e) {
					System.out.println("Error: IOException when calling copycontacts.exe");
					e.printStackTrace();
				}
				superframe.showKeypad();
				*/
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
				
				if(fav) {
					superframe.updateFavorite(nameField.getText(), Integer.toString(rank));
				} else if(favCheckbox.isSelected()) {
					SwingUtilities.invokeLater(new Runnable() {
				        public void run() {
				            EditFavoriteFrame ff = new EditFavoriteFrame(superframe, nameField.getText());
				            ff.setVisible(true);
				        }
					});
				}
			}
		});
	}

}

