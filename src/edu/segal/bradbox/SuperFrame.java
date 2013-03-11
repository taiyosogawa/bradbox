package edu.segal.bradbox;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;


public class SuperFrame extends JFrame{
	public JavaMonkey monkey;
	public Serialio serialio;
	public KeypadPanel keypadPanel;
	public TextingPanel textingPanel;
	public OptionsPanel optionsPanel;
	public EditContactPanel editContactPanel;
	JPanel displayedPanel = new JPanel();

	/**
	 *  Required field for extending the JFrame, set to default value
	 */
	private static final long serialVersionUID = 1L;

	// Constructor function
	public SuperFrame(JavaMonkey m, Serialio s) {
		initIO(m, s);
		keypadPanel = new KeypadPanel(this);
		textingPanel = new TextingPanel(monkey);
		optionsPanel = new OptionsPanel(this);
		editContactPanel = new EditContactPanel(this, "", "");
	    initWindow();

		
		setSize(950, 550);
		BorderLayout layout = new BorderLayout();
		layout.setVgap(0);
		setLayout(layout);
		add(displayedPanel, BorderLayout.CENTER);
		keypadPanel.setBackground(Constants.GRAY);
		displayedPanel.add(keypadPanel);
		displayedPanel.add(textingPanel);
		displayedPanel.add(optionsPanel);		
		displayedPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		((FlowLayout)displayedPanel.getLayout()).setVgap(0);
		((FlowLayout)displayedPanel.getLayout()).setHgap(0);

		hidePanels();
		showKeypad();
		pack();
	}
	
	public final void openEditContact(String nm, String no) {
		editContactPanel = new EditContactPanel(this, nm, no);
		displayedPanel.add(editContactPanel);
		hidePanels();
		setTitle("Edit Contact " + nm);
		editContactPanel.setVisible(true);
	}
	
	private final void initIO(JavaMonkey m, Serialio s) {
		// Initialize the JavaMonkey and Serialio
	    if ( m == null ) {
            throw new IllegalStateException("JavaMonkey is not initialized in SuperFrame.");
	    }
	    this.monkey = m;
	    if ( s == null ) {
            throw new IllegalStateException("Serialio is not initialized in SuperFrame.");
	    }
	    this.serialio = s;
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
		keypadPanel.searchContacts();
		keypadPanel.setVisible(true);
	}
	
	private final void hidePanels() {
		keypadPanel.setVisible(false);
		textingPanel.setVisible(false);
		optionsPanel.setVisible(false);
		editContactPanel.setVisible(false);
	}
	
	public final void showOptions() {
		hidePanels();
		setTitle("Options");
		optionsPanel.setVisible(true);
	}
}
