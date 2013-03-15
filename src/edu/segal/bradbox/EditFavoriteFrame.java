package edu.segal.bradbox;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class EditFavoriteFrame extends JFrame{
	SuperFrame superframe;
	String newName;
	JPanel contentPanel = new JPanel();
	JFrame frame = this;
	
	
	EditFavoriteFrame(SuperFrame sf, String n) {
		superframe = sf;
		newName = n;
		initWindow();
		add(contentPanel);
		contentPanel.setLayout(new GridLayout(0, 1));
		contentPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		JLabel promptLabel = new JLabel("Choose the favorite you would like to replace:");
		promptLabel.setFont(Constants.FONT_18_PLAIN);
		contentPanel.add(promptLabel);
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
			try {
			  // create a database connection
			  connection = DriverManager.getConnection("jdbc:sqlite:/platform-tools/favorites.db");
			  Statement statement = connection.createStatement();
			  statement.setQueryTimeout(30);  // set timeout to 30 sec.
			  ResultSet rs = statement.executeQuery("select * from names order by rank");
			  // put a for loop here that iterates for each search result panel available
			      while (rs.next()) {  
			    	  FavoriteButton favButton = new FavoriteButton(rs.getString("name"), rs.getString("rank"));

			    	  favButton.addActionListener(new ActionListener() {
			  			public void actionPerformed(ActionEvent event) {
			  				FavoriteButton clickedButton = (FavoriteButton) event.getSource();
							superframe.updateFavorite(newName, clickedButton.getRank());
							frame.dispose();
						}
					});
			    	  contentPanel.add(favButton);
			      }

			}
		catch(SQLException e) {
		  // if the error message is "out of memory", 
		  // it probably means no database file is found
		  System.err.println(e.getMessage());
		}
		finally {
		  try {
		    if(connection != null)
		      connection.close();
		  }
		  catch(SQLException e) {
		    // connection close failed.
		    System.err.println(e);
		  }
		}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
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
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
}
