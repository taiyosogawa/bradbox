package edu.segal.bradbox;

import javax.swing.SwingUtilities;

public class BradBox{
	final JavaMonkey monkey = new JavaMonkey();
	SerialListener listener = new SerialListener(this);
	public SuperFrame gui;
	
	public void startKeypad() {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui = new SuperFrame(monkey);
                gui.setVisible(true);
            }
		});
	}

	public static void main(String[] args) {
		BradBox bradbox = new BradBox();
		bradbox.startKeypad();
	}
}
