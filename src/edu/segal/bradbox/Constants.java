package edu.segal.bradbox;

import java.awt.Color;
import java.awt.Font;

public interface Constants {
	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 600;
	public static final int CONTACTS_WIDTH = 190;
	
	public static final String CALL_STRING = "Call";
	public static final String END_CALL_STRING = "End Call";
	
	public static final Color GREEN = new Color(0x70, 0xe0, 0x4b);
	public static final Color RED = new Color(0xdd, 0x3a, 0x3a);
	public static final Color BACKGROUND_GRAY = new Color(0xd6, 0xd9, 0xdf);
	public static final Color DARK_GRAY = new Color(0x44, 0x44, 0x44);
	public static final Color BRAD_BLUE = new Color(0x49, 0x8c, 0x86);
	
	public static final Font FONT_40_BOLD = new Font("SansSerif", Font.BOLD, 40);
	public static final Font FONT_26_BOLD = new Font("SansSerif", Font.BOLD, 26);
	public static final Font FONT_20_BOLD = new Font("SansSerif", Font.BOLD, 20);
	public static final Font FONT_26_PLAIN = new Font("SansSerif", Font.PLAIN, 26);
	public static final Font FONT_20_PLAIN = new Font("SansSerif", Font.PLAIN, 20);
	public static final Font FONT_18_PLAIN = new Font("SansSerif", Font.PLAIN, 18);
	public static final Font FONT_14_PLAIN = new Font("SansSerif", Font.PLAIN, 14);
	public static final Font FONT_12_PLAIN = new Font("SansSerif", Font.PLAIN, 12);
}