package edu.segal.bradbox;

import javax.swing.SwingUtilities;


interface Window {
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
}


public class BradBox{
	private final JavaMonkey monkey = new JavaMonkey();
	SerialListener listener = new SerialListener(monkey);
	private Keypad keypad;
	
	public void startKeypad() {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                keypad = new Keypad(monkey);
                keypad.setVisible(true);
            }
		});
	}

	public static void main(String[] args) {
		BradBox bradbox = new BradBox();
		bradbox.startKeypad();
	}
}
