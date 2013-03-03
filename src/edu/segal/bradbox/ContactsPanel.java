package edu.segal.bradbox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactsPanel extends JPanel {
	private JavaMonkey monkey;
	JTextField lookupField = new JTextField(30);

	ContactsPanel(JavaMonkey m) {
		monkey = m;
		add(lookupField);
		JButton searchButton = new JButton("Search");
		add(searchButton);
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				searchFor(lookupField.getText());
			}
		});
	}
	
	private void searchFor(String searchTerm) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
		    try {
		      // create a database connection
		      connection = DriverManager.getConnection("jdbc:sqlite:/platform-tools/contacts2.db");
		      Statement statement = connection.createStatement();
		      statement.setQueryTimeout(30);  // set timeout to 30 sec.
		      ResultSet rs = statement.executeQuery("select * from contact_entities_view where (display_name like '" + searchTerm + "%' or data1 like '" + searchTerm + "%') and mimetype = 'vnd.android.cursor.item/phone_v2'");
		      while(rs.next()) {
		        // read the result set
		        System.out.println("name = " + rs.getString("display_name"));
		        System.out.println("number = " + rs.getString("data1"));
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
}
