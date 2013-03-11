package edu.segal.bradbox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ConfirmDeleteFrame extends JFrame{
	JLabel messageLabel = new JLabel("Are you sure you want to delete this contact?");
	JButton cancelButton = new JButton("Cancel");
	JButton deleteButton = new JButton("Delete");
	JFrame frame = this;
	String name;
	SuperFrame superframe;
	JavaMonkey monkey;
	boolean fav;
	int rank;
	
	
	ConfirmDeleteFrame(SuperFrame sf, String n, boolean f, int r) {
		name = n;
		superframe = sf;
		fav = f;
		rank = r;
		monkey = superframe.getMonkey();
		messageLabel.setText("Are you sure you want to delete " + name + "?");
		initWindow();
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new GridLayout(0, 1));
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 0));
		add(contentPanel);
		contentPanel.add(messageLabel);
		contentPanel.add(buttonPanel);
			buttonPanel.add(cancelButton);
			buttonPanel.add(deleteButton);
			
		cancelButton.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent event) {
				frame.dispose();
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				monkey.shell("am broadcast -a edu.segal.androidbradbox.deletecontact -e name '" + name + "'");
				System.out.println("attempting to copy contacts");
				try {
					Runtime.getRuntime().exec("/platform-tools/copycontacts.exe");
				} catch (IOException e) {
					System.out.println("Error: IOException when calling copycontacts.exe");
					e.printStackTrace();
				}
				superframe.updateFavorite("Favorite " + Integer.toString(rank), Integer.toString(rank));
				frame.dispose();
			}
		});
		
		
	}
	
	private final void initWindow() { 
		 // Initialize the UI look and feel
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setTitle("Edit Favorite");
		setSize(300, 200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
