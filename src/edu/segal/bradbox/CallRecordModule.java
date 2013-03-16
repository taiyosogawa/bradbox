package edu.segal.bradbox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class CallRecordModule extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SuperFrame superframe;
	JLabel nameLabel = new JLabel();
	JLabel numberLabel = new JLabel();
	JLabel timeLabel = new JLabel();
	JButton callButton = new JButton("Call");
	JButton addContactButton = new JButton("Save as Contact");
	
	CallRecordModule (SuperFrame sf, String nm, String no, String t) {
		superframe = sf;
		if(nm == null) {
			nm = superframe.getKeypad().getNameByNumber(no);
		} else {
			setName(nm);
		}
		setNumber(no);
		setTime(t);
		
		setLayout(new GridLayout(1, 0));
		setPreferredSize(new Dimension(800, 60));
		setBorder(new LineBorder(Constants.DARK_GRAY));
		timeLabel.setFont(Constants.FONT_20_PLAIN);

		nameLabel.setFont(Constants.FONT_20_BOLD);
		numberLabel.setFont(Constants.FONT_20_PLAIN);

		callButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.getKeypad().initiateCall(numberLabel.getText());
				superframe.showKeypad();
			}
		});
		
		addContactButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.openEditContact(nameLabel.getText(), numberLabel.getText(), false, 1);
			}
		});
		
		callButton.setFont(Constants.FONT_20_BOLD);
		callButton.setPreferredSize(new Dimension(180, 60));
		
		JPanel addContainer = new JPanel();
		addContainer.setBorder(new EmptyBorder(0, 0, 0, 30));
		addContactButton.setFont(Constants.FONT_20_BOLD);
		addContactButton.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		add(timeLabel);
		if(nameLabel.getText().equals("")) {
			addContainer.add(addContactButton);
			add(addContainer);
		} else {
			add(nameLabel);
		}
		add(numberLabel);
		add(callButton);
	}
	
	public void setName(String nm) {
		nameLabel.setText(nm);
		nameLabel.setToolTipText(nm);
	}

	public void setNumber(String no) {
		numberLabel.setText(no);
		numberLabel.setToolTipText(no);
	}
	
	public void setTime(String t) {
		timeLabel.setText(t);
		timeLabel.setToolTipText(t);
	}

}
