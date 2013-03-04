package edu.segal.bradbox;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;
import javax.swing.ButtonGroup;
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
	public JavaMonkey monkey;
	public KeypadPanel keypadPanel;
	public TextingPanel textingPanel;
	public OptionsPanel optionsPanel;
	JPanel displayedPanel = new JPanel();

	/**
	 *  Required field for extending the JFrame, set to default value
	 */
	private static final long serialVersionUID = 1L;

	// Constructor function
	public SuperFrame(JavaMonkey m, Serialio s) {
		initJavaMonkey(m);
		keypadPanel = new KeypadPanel(monkey);
		textingPanel = new TextingPanel(monkey);
		optionsPanel = new OptionsPanel(this, monkey, s);
	    initWindow();
		initMenu();

		add(displayedPanel);
		keypadPanel.setBackground(Constants.RED);
		displayedPanel.add(keypadPanel);
		displayedPanel.add(textingPanel);
		displayedPanel.add(optionsPanel);
		displayedPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		displayedPanel.setBackground(Constants.GREEN);
		((FlowLayout)displayedPanel.getLayout()).setVgap(0);
		((FlowLayout)displayedPanel.getLayout()).setHgap(0);

		hidePanels();
		showKeypad();
	}
	
	private final void initJavaMonkey(JavaMonkey m) {
		// Initialize the JavaMonkey and Serialio
	    if ( m == null ) {
            throw new IllegalStateException("JavaMonkey is not initialized in SuperFrame.");
	    }
	    this.monkey = m;
	}
	
	private final void initWindow() {
		 // Initialize the UI look and feel
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setTitle("Brad Box");
		setSize(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public final void showKeypad() {
		setTitle("Keypad");
		hidePanels();
		monkey.shell("am start -a android.intent.action.DIAL");
		keypadPanel.setVisible(true);
	}

	private final void initMenu() {
		JToolBar toolbar = new JToolBar();

		ImageIcon favoritesIcon = new ImageIcon(this.getClass().getResource("/img/Favorites1.png"));
		ImageIcon keypadIcon = new ImageIcon(getClass().getResource("/img/Keypad1.png"));
		ImageIcon textingIcon = new ImageIcon(getClass().getResource("/img/Texting1.png"));
		
		ImageIcon favoritesIcon2 = new ImageIcon(this.getClass().getResource("/img/Favorites2.png"));
		ImageIcon keypadIcon2 = new ImageIcon(getClass().getResource("/img/Keypad2.png"));
		ImageIcon textingIcon2 = new ImageIcon(getClass().getResource("/img/Texting2.png"));
		
		JToggleButton favoritesButton = new JToggleButton(favoritesIcon);
		favoritesButton.setToolTipText("Favorites");
		favoritesButton.setSelectedIcon(favoritesIcon2);
		JToggleButton keypadButton = new JToggleButton(keypadIcon);
		keypadButton.setToolTipText("Keypad");
		keypadButton.setSelectedIcon(keypadIcon2);
		JToggleButton textingButton = new JToggleButton(textingIcon);
		textingButton.setToolTipText("SMS");
		textingButton.setSelectedIcon(textingIcon2);
		
	    ButtonGroup group = new ButtonGroup();
	    group.add(favoritesButton);
	    group.add(keypadButton);
	    group.add(textingButton);
		
	    favoritesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// Place keypad in the viewed area
				hidePanels();
				setTitle("Options");
				optionsPanel.setVisible(true);
			}
		});
	    
		keypadButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				showKeypad();
			}
		});
		
		textingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				setTitle("Texting");
				hidePanels();
				textingPanel.setVisible(true);
			}
		});
		
		toolbar.add(favoritesButton);
		toolbar.add(keypadButton);
		toolbar.add(textingButton);
		
		add(toolbar, BorderLayout.NORTH);
	}
	
	private final void hidePanels() {
		keypadPanel.setVisible(false);
		textingPanel.setVisible(false);
		optionsPanel.setVisible(false);
	}
}
