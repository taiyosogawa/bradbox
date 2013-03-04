package edu.segal.bradbox;

import javax.swing.SwingUtilities;

public class BradBox{
	final JavaMonkey monkey = new JavaMonkey();
	Serialio serialio = new Serialio(this);
	public SuperFrame gui;
	
	public void startKeypad() {
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                gui = new SuperFrame(monkey, serialio);
                gui.setVisible(true);
            }
		});
	}

	public static void main(String[] args) {
		BradBox bradbox = new BradBox();
		bradbox.startKeypad();
	}
}