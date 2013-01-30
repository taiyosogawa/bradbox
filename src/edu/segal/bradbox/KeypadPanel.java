package edu.segal.bradbox;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class KeypadPanel extends JPanel{
	JavaMonkey monkey;
	final static private String[] keypadLabels = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "*", "0", "#"};
	final static private Map<String, String> keyCodeMap = new HashMap<String, String>();
	
	/**
	 *  Required for a JPanel
	 */
	private static final long serialVersionUID = 1L;
	
	public KeypadPanel(JavaMonkey m) {
		initJavaMonkey(m);
	    initKeyCodeMap();
	    initLookandFeel();
	    initKeypad();
	}

	private final void initJavaMonkey(JavaMonkey m) {
		// Initialize the JavaMonkey
	    if ( m == null ) {
            throw new IllegalStateException("JavaMonkey is not initialized in KeypadPanel.");
	    }
	    this.monkey = m;
	}
	
	private final void initLookandFeel() {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	private final void initKeyCodeMap() {
		keyCodeMap.put("0", "KEYCODE_0");	
		keyCodeMap.put("1", "KEYCODE_1");
		keyCodeMap.put("2", "KEYCODE_2");
		keyCodeMap.put("3", "KEYCODE_3");
		keyCodeMap.put("4", "KEYCODE_4");
		keyCodeMap.put("5", "KEYCODE_5");
		keyCodeMap.put("6", "KEYCODE_6");
		keyCodeMap.put("7", "KEYCODE_7");
		keyCodeMap.put("8", "KEYCODE_8");
		keyCodeMap.put("9", "KEYCODE_9");
		keyCodeMap.put("#", "KEYCODE_POUND");
		keyCodeMap.put("*", "KEYCODE_STAR");
		keyCodeMap.put("Del", "KEYCODE_DEL");
		keyCodeMap.put("Call", "KEYCODE_CALL");
		keyCodeMap.put("End Call", "KEYCODE_ENDCALL");
	}
	
	public void initKeypad(){	
		// Set the orientation of the keypad Panel
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Add a text field to the panel
		JPanel numberFieldPanel = new JPanel();
		final JTextField numberField = new JTextField(12);
		numberFieldPanel.add(numberField);
		
		// Customize the text field font
		Font numberFont = new Font("SansSerif", Font.BOLD, 40);
		numberField.setFont(numberFont);
		
		// Create backspace button
				JButton delButton = new JButton("Del");
				numberFieldPanel.add(delButton);
				
		// Create a listener for the backspace button
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				monkey.press("KEYCODE_DEL");	
				String number = numberField.getText();
				if(number.length() > 0) {
					number = number.substring(0, number.length() - 1);
					numberField.setText(number);
				}
			}
		});
		
		this.add(numberFieldPanel);

		// Add the keypad
		JPanel keypad = new JPanel();
		this.add(keypad);
		keypad.setLayout(new GridLayout(4,3));
		keypad.setSize(500, 500);

		// Create all buttons with listeners attached
		for(int i = 0; i < 12; i++){
			JButton numberKey = new JButton(keypadLabels[i]);
			numberKey.setFont(numberFont);
			numberKey.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent event) {
					JButton clickedButton = (JButton) event.getSource();
					numberField.setText(numberField.getText() + clickedButton.getText());
					monkey.press(keyCodeMap.get(clickedButton.getText()));
				}
			});
			keypad.add(numberKey);
		}
		
		// Create a panel for the calling button
		JPanel callingPanel = new JPanel();
		
		// Customize the calling panel font
		Font callingFont = new Font("SansSerif", Font.BOLD, 20);
		
		// Create a call button
		JButton callButton = new JButton("Call");
		callButton.setFont(numberFont);
		callButton.setBackground(Constants.GREEN);
		
		delButton.setFont(callingFont);
		delButton.setBackground(Constants.RED);
		
		callingPanel.add(callButton);
		this.add(callingPanel);
		callingPanel.setLayout(new GridLayout(1,0));
		
		// Create a calling Label to display when a call is initiated
		final JLabel callingLabel = new JLabel();
		callingLabel.setFont(callingFont);
		callingPanel.add(callingLabel);
				
				
		// Create a listener for the callButton
		callButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				JButton callButton = (JButton) event.getSource();
				
				if(callButton.getText().equals("Call")) {
					callButton.setText("End Call");
					callButton.setBackground(Constants.RED);
					String number = numberField.getText();
					numberField.setText("");
					callingLabel.setText("    Calling " + number + " . . .");
					monkey.press("KEYCODE_CALL");	
				} else {
					callButton.setText("Call");
					callButton.setBackground(Constants.GREEN);
					numberField.setText("");
					callingLabel.setText("");
					monkey.press("KEYCODE_ENDCALL");
				}
			}
		});
		
	}

}
