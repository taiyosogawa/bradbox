package edu.segal.bradbox;

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

public class ContactPanel extends PanelSkeleton {
	private static final long serialVersionUID = 1L;
	// Add contact fields
	JCheckBox favCheckbox = new JCheckBox("Add as Favorite");
	JLabel nameLabel = new JLabel("Full Name");
	JTextField nameField = new JTextField(10);
	JLabel phoneNumberLabel = new JLabel("Phone Number");
	JTextField numberField = new JTextField(10);
	JButton saveButton = new JButton("Save");
	JButton deleteButton = new JButton("Delete");
	String newName;
	String oldName;
	boolean fav = false;
	boolean editContact;
	int rank;
	
	ContactPanel(SuperFrame sf) {
		super(sf, "Add Contact");
		editContact = false;
		initLayout();
	}
	
	ContactPanel(SuperFrame sf, String nm, String no, boolean f, int r) {
		super(sf, "Edit Contact");
		oldName = nm;
		fav = f;
		rank = r;
		
		nameField.setText(oldName);
		numberField.setText(no);
		if(fav) favCheckbox.setSelected(true);
		
		editContact = true;
		initLayout();
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				
				SwingUtilities.invokeLater(new Runnable() {
			        public void run() {
			            ConfirmDeleteFrame cdf = new ConfirmDeleteFrame(superframe, oldName, fav, rank);
			            cdf.setVisible(true);
			        }
				});
			}
		});

	}
	
	void initLayout () {
		JPanel content = new JPanel();
		addContent(content);

		content.setLayout(new GridLayout(0, 1));
		content.add(nameLabel);
		content.add(nameField);
		content.add(phoneNumberLabel);
		content.add(numberField);
		content.add(favCheckbox);
		if(editContact) {
			content.add(deleteButton);
			content.add(saveButton);
		} else {
			content.add(saveButton);
		}
		nameLabel.setFont(Constants.FONT_26_BOLD);
		nameField.setFont(Constants.FONT_26_PLAIN);
		phoneNumberLabel.setFont(Constants.FONT_26_BOLD);
		numberField.setFont(Constants.FONT_26_PLAIN);
		favCheckbox.setFont(Constants.FONT_26_BOLD);
		deleteButton.setFont(Constants.FONT_26_BOLD);
		deleteButton.setBackground(Constants.RED);
		saveButton.setFont(Constants.FONT_26_BOLD);
		
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				newName = nameField.getText();
				if(editContact) monkey.shell("am broadcast -a edu.segal.androidbradbox.deletecontact -e name '" + oldName + "'");
				monkey.shell("am broadcast -a edu.segal.androidbradbox.addcontact -e name '" + newName + "' -e number '"+ superframe.uglifyNumber(numberField.getText()) + "'");
				
				superframe.runExecutable("/platform-tools/copycontacts.exe");
				
				if(fav) {
					superframe.updateFavorite(nameField.getText(), Integer.toString(rank));
				} else if(favCheckbox.isSelected()) {
					SwingUtilities.invokeLater(new Runnable() {
				        public void run() {
				            EditFavoriteFrame ff = new EditFavoriteFrame(superframe, newName);
				            ff.setVisible(true);
				        }
					});
				}
				nameField.setText("");
				numberField.setText("");
				favCheckbox.setSelected(false);
				superframe.showKeypad();

			}
		});
		
	}
}

