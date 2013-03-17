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

import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.JPanel;

public class CallLogPanel extends PanelSkeleton {
	private static final long serialVersionUID = 1L;
	JPanel content = new JPanel();

	CallLogPanel (SuperFrame sf) {
		super(sf, "Call Log");
		
		content.setLayout(new GridLayout(0, 1));
		addContent(content);
		updateCallLog();	
	}
	
	public void updateCallLog() {
		superframe.runExecutable("/platform-tools/copycalllog.exe");
		content.removeAll();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connection = null;
		    try {
		      // create a database connection
		      connection = DriverManager.getConnection("jdbc:sqlite:/platform-tools/logs.db");
		      Statement statement = connection.createStatement();
		      statement.setQueryTimeout(30);  // set timeout to 30 sec. 
		      ResultSet rs = statement.executeQuery("select * from logs order by _id desc limit 8");
		      // put a for loop here that iterates for each search result panel available
		      //for(int i = 0; i < 8; i++) {
			      while (rs.next()) {  
			          // read the result set
			    	  java.util.Date callTime = new java.util.Date((long)Long.parseLong(rs.getString("date")));
			    	  SimpleDateFormat dt = new SimpleDateFormat("EEE MM/dd hh:mm aaa");
			    	  String callTimeString = dt.format(callTime);
			    	  content.add(new CallRecordModule(superframe, rs.getString("name"), superframe.prettifyNumber(rs.getString("number")), callTimeString));
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
