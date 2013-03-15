package edu.segal.bradbox;

import java.io.IOException;

import javax.swing.JPanel;

public class CallLogPanel extends PanelSkeleton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	CallLogPanel (SuperFrame sf) {
		super(sf, "Call Log");
		//updateCallLogDB();
		
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
