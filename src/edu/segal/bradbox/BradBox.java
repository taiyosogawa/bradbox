package edu.segal.bradbox;

import javax.swing.SwingUtilities;

public class BradBox{
	private final JavaMonkey monkey = new JavaMonkey();
	SerialListener listener = new SerialListener(monkey);
	private SuperFrame keypad;
	
	public void startKeypad() {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                keypad = new SuperFrame(monkey);
                keypad.setVisible(true);
            }
		});
	}

	public static void main(String[] args) {
		BradBox bradbox = new BradBox();
		bradbox.startKeypad();
	}
}
