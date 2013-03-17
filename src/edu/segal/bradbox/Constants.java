/*
 * BradBox
 * Copyright 2013 Taiyo Sogawa
 * taiyo <at> u <dot> northwestern <dot> edu
 * Last Revised: March 16, 2013
 * 
 * This file is part of BradBox.

    BradBox is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    BradBox is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with BradBox.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.segal.bradbox;

import java.awt.Color;
import java.awt.Font;

public interface Constants {
	public static final int WINDOW_WIDTH = 600;
	public static final int WINDOW_HEIGHT = 600;
	public static final int CONTACTS_WIDTH = 190;
	
	public static final String CALL_STRING = "Call";
	public static final String END_CALL_STRING = "End Call";
	
	public static final Color GREEN = new Color(0x59, 0xb7, 0x3a);
	public static final Color RED = new Color(0xca, 0x27, 0x27);
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