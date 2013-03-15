package edu.segal.bradbox;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
	
	CallRecordModule (SuperFrame sf, String nm, String no, String t) {
		superframe = sf;
		setName(nm);
		setNumber(no);
		setTime(t);
		
		setLayout(new GridLayout(1, 0));
		setPreferredSize(new Dimension(400, 40));
		setBorder(new LineBorder(Constants.DARK_GRAY));
		timeLabel.setFont(Constants.FONT_20_PLAIN);
	
		JPanel namePane = new JPanel();
		namePane.setPreferredSize(new Dimension(140, 50));
		namePane.setLayout(new BoxLayout(namePane, BoxLayout.Y_AXIS));
		
		nameLabel.setFont(Constants.FONT_20_BOLD);
		numberLabel.setFont(Constants.FONT_20_PLAIN);
		//namePane.add(nameLabel);
		//namePane.add(numberLabel);
		
		callButton.setPreferredSize(new Dimension(140, 50));
		
		callButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				superframe.getKeypad().initiateCall(numberLabel.getText());
				superframe.showKeypad();
			}
		});
		
		add(timeLabel);
		add(nameLabel);
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
