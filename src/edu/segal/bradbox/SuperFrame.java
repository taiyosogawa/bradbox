package edu.segal.bradbox;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SuperFrame extends JFrame{
	private JavaMonkey monkey;
	private KeypadPanel keypad;

	/**
	 *  Required field for extending the JFrame, set to default value
	 */
	private static final long serialVersionUID = 1L;

	// Constructor function
	public SuperFrame(JavaMonkey m) {
		initJavaMonkey(m);
		keypad = new KeypadPanel(monkey);
	    initWindow();
		initMenu();
		getContentPane().add(keypad);
	}
	
	private final void initJavaMonkey(JavaMonkey m) {
		// Initialize the JavaMonkey
	    if ( m == null ) {
            throw new IllegalStateException("JavaMonkey is not initialized in SuperFrame.");
	    }
	    this.monkey = m;
	}
	
	private final void initWindow() {
		 // Initialize the UI look and feel
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setTitle("Brad Box");
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	

	public final void initMenu() {
		JToolBar toolbar = new JToolBar();

		ImageIcon favoritesIcon = new ImageIcon(this.getClass().getResource("../../../img/star_icon.png"));
		
		ImageIcon keypadIcon = new ImageIcon(getClass().getResource("../../../img/keypad_icon.png"));
		ImageIcon contactsIcon = new ImageIcon(getClass().getResource("../../../img/folder_contacts_icon.png"));
		ImageIcon addIcon = new ImageIcon(getClass().getResource("../../../img/add_icon.png"));
		
		JButton favoritesButton = new JButton(favoritesIcon);
		
		JButton keypadButton = new JButton(keypadIcon);
		JButton contactsButton = new JButton(contactsIcon);
		JButton addButton = new JButton(addIcon);
		
		keypadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Place keypad in the viewed area
			}
		});
		
		toolbar.add(favoritesButton);
		toolbar.add(keypadButton);
		toolbar.add(contactsButton);
		toolbar.add(addButton);
		
		add(toolbar, BorderLayout.NORTH);
	}
}
