package edu.segal.bradbox;

import javax.swing.JButton;

public class FavoriteButton extends JButton {
	String rank;

	FavoriteButton(String text, String r) {
		setText(text);
		setFont(Constants.FONT_26_PLAIN);
		rank = r;
	}
	
	public String getRank() {
		return rank;
	}

}
