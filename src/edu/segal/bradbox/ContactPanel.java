package edu.segal.bradbox;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

public class ContactPanel extends PanelSkeleton {
	private static final long serialVersionUID = 1L;
	// Add contact fields
	JCheckBox favCheckbox = new JCheckBox("Add as Favorite");
	JLabel nameLabel = new JLabel("Full Name");
	JTextField nameField = new JTextField(15);
	JLabel phoneNumberLabel = new JLabel("Phone Number");
	JTextField numberField = new JTextField(15);
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
		content.setBorder(BorderFactory.createTitledBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createLineBorder(Constants.DARK_GRAY), 
				BorderFactory.createEmptyBorder(15, 15, 15, 15))));
		content.add(nameLabel);
		content.add(nameField);
		content.add(phoneNumberLabel);
		content.add(numberField);
		content.add(favCheckbox);
		if(editContact) {
			JPanel buttonPanel = new JPanel(new GridLayout(1, 0));
			content.add(buttonPanel);
			buttonPanel.add(deleteButton);
			buttonPanel.add(saveButton);
			saveButton.setPreferredSize(new Dimension(190, 67));
		} else {
			content.add(saveButton);
			saveButton.setPreferredSize(new Dimension(400, 67));
		}
		
		nameLabel.setFont(Constants.FONT_26_BOLD);
		nameField.setFont(Constants.FONT_26_PLAIN);
		
		phoneNumberLabel.setFont(Constants.FONT_26_BOLD);
		
		numberField.setFont(Constants.FONT_26_PLAIN);
		
		favCheckbox.setFont(Constants.FONT_26_BOLD);
		favCheckbox.setIcon(new ImageIcon(this.getClass().getResource("/img/empty_checkbox.png")));
		favCheckbox.setRolloverIcon(new ImageIcon(this.getClass().getResource("/img/empty_checkbox.png")));
		favCheckbox.setSelectedIcon(new ImageIcon(this.getClass().getResource("/img/checked_checkbox.png")));
		favCheckbox.setPressedIcon(new ImageIcon(this.getClass().getResource("/img/checked_checkbox.png")));
		favCheckbox.setRolloverSelectedIcon(new ImageIcon(this.getClass().getResource("/img/checked_checkbox.png")));
		
		deleteButton.setFont(Constants.FONT_26_BOLD);
		deleteButton.setBackground(Constants.RED);
		
		saveButton.setFont(Constants.FONT_26_BOLD);
		saveButton.setBackground(Constants.BRAD_BLUE);
		
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

