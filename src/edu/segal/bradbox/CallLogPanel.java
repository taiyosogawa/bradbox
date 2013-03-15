package edu.segal.bradbox;

import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JPanel;

public class CallLogPanel extends PanelSkeleton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CallLogPanel (SuperFrame sf) {
		super(sf, "Call Log");
		updateCallLogDB();
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(0, 1));
		addContent(content);
		
		CallRecordModule testRecord = new CallRecordModule(superframe, "frank", "4252758461", "3:00pm");
		content.add(testRecord);
		
	}
	
	public void updateCallLogDB() {
		try {
			Runtime.getRuntime().exec("/platform-tools/copycalllog.exe");
		} catch (IOException e) {
			System.out.println("Error: IOException when calling copycalllog.exe");
			e.printStackTrace();
		}
	}

}
