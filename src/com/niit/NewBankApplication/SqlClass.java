package com.niit.NewBankApplication;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class SqlClass {
	
	
	
	public static Connection databaseConnector()
	{
		try 
		{
			String url = "jdbc:mysql://localhost:3306/newbank";
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url,"root" , "root");
			return con;
		} 
		
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
	}
	

}
