package edu.segal.bradbox;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CallRecordModule extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SuperFrame superframe;
	String name;
	String number;
	String time;
	JButton callButton = new JButton("Call");
	
	CallRecordModule (SuperFrame sf, String no, String t) {
		superframe = sf;
		number = no;
		time = t;
		
	}

}
