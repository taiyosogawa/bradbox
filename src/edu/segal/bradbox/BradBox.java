package edu.segal.bradbox;

import java.awt.Color;

import javax.swing.SwingUtilities;



interface Constants {
	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 600;
	
	public static final Color GREEN = new Color(0x42, 0xe7, 0x3a);
	public static final Color RED = new Color(0xff, 0x11, 0x11);
}


public class BradBox{
	private final JavaMonkey monkey = new JavaMonkey();
	//SerialListener listener = new SerialListener(monkey);
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
