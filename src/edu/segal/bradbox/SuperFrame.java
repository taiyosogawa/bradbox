package edu.segal.bradbox;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;


public class SuperFrame extends JFrame{
	private JavaMonkey monkey;
	public KeypadPanel keypadPanel;
	public ContactsPanel contactsPanel;
	JPanel displayedPanel = new JPanel();

	/**
	 *  Required field for extending the JFrame, set to default value
	 */
	private static final long serialVersionUID = 1L;

	// Constructor function
	public SuperFrame(JavaMonkey m) {
		initJavaMonkey(m);
		keypadPanel = new KeypadPanel(monkey);
		contactsPanel = new ContactsPanel(monkey);
	    initWindow();
		initMenu();

		add(displayedPanel);
		keypadPanel.setBackground(Constants.RED);
		contactsPanel.setBackground(Constants.RED);
		displayedPanel.add(keypadPanel);
		displayedPanel.add(contactsPanel);
		displayedPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		displayedPanel.setBackground(Constants.GREEN);
		

		((FlowLayout)displayedPanel.getLayout()).setVgap(0);
		((FlowLayout)displayedPanel.getLayout()).setHgap(0);

		contactsPanel.setVisible(false);
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
	

	private final void initMenu() {
		JToolBar toolbar = new JToolBar();

		ImageIcon favoritesIcon = new ImageIcon(this.getClass().getResource("../../../img/Favorites1.png"));
		ImageIcon keypadIcon = new ImageIcon(getClass().getResource("../../../img/Keypad1.png"));
		ImageIcon contactsIcon = new ImageIcon(getClass().getResource("../../../img/Contacts1.png"));
		ImageIcon addIcon = new ImageIcon(getClass().getResource("../../../img/Add1.png"));
		
		ImageIcon favoritesIcon2 = new ImageIcon(this.getClass().getResource("../../../img/Favorites2.png"));
		ImageIcon keypadIcon2 = new ImageIcon(getClass().getResource("../../../img/Keypad2.png"));
		ImageIcon contactsIcon2 = new ImageIcon(getClass().getResource("../../../img/Contacts2.png"));
		ImageIcon addIcon2 = new ImageIcon(getClass().getResource("../../../img/Add2.png"));
		
		JToggleButton favoritesButton = new JToggleButton(favoritesIcon);
		favoritesButton.setSelectedIcon(favoritesIcon2);
		JToggleButton keypadButton = new JToggleButton(keypadIcon);
		keypadButton.setSelectedIcon(keypadIcon2);
		JToggleButton contactsButton = new JToggleButton(contactsIcon);
		contactsButton.setSelectedIcon(contactsIcon2);
		JToggleButton addButton = new JToggleButton(addIcon);
		addButton.setSelectedIcon(addIcon2);
		
		keypadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Place keypad in the viewed area
				contactsPanel.setVisible(false);
				keypadPanel.setVisible(true);

			}
		});
		
		contactsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Place contacts in the viewed area
				setTitle("contacts");
				keypadPanel.setVisible(false);
				contactsPanel.setVisible(true);
			}
		});
		
		toolbar.add(favoritesButton);
		toolbar.add(keypadButton);
		toolbar.add(contactsButton);
		toolbar.add(addButton);
		
		add(toolbar, BorderLayout.NORTH);
	}
	
}
