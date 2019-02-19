package com.niit.NewBankApplication;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Passbook extends JFrame {

	private JPanel contentPane;
	String userid,password;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Passbook frame = new Passbook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private JTable table_1;
	private JButton btnBack;
	private JTable table;
	private JTable table_2;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JLabel lblNewLabel;
	
		public Passbook() {
		
		getContentPane().setLayout(null);
		con = SqlClass.databaseConnector();
		setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{   
					
					 String query6  = "select customerid from informations,bank where userid = ? and customerid = userid";
					 ps = con.prepareStatement(query6);
					 ps.setString(1, LoginPage.userid);
					 rs = ps.executeQuery();
					 if(rs.next())
					 {
						 //query for generating name customerid is foreignkey
					     String query = "select name from bank,informations where userid = ? and password = ? and customerid = userid";
						 ps = con.prepareStatement(query);
						ps.setString(1, LoginPage.userid);
						ps.setString(2, LoginPage.password);
						 rs = ps.executeQuery();
						table_1.setModel(DbUtils.resultSetToTableModel(rs));
						table_1.setEnabled(false);
						
						//query for generating gender customerid is foreignkey
						 String query1 = "select gender from bank,informations where userid = ? and password = ? and customerid = userid";
							 ps = con.prepareStatement(query1);
							ps.setString(1, LoginPage.userid);
							ps.setString(2, LoginPage.password);
							 rs = ps.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
							table.setEnabled(false);
							
							//query for generating phone number customerid is foreignkey
							 String query2 = "select phone from bank,informations where userid = ? and password = ? and customerid = userid";
								 ps = con.prepareStatement(query2);
								ps.setString(1, LoginPage.userid);
								ps.setString(2, LoginPage.password);
							    rs = ps.executeQuery();
								table_2.setModel(DbUtils.resultSetToTableModel(rs));
								table_2.setEnabled(false);
								
								//query for generating address customerid is foreignkey
								 String query3 = "select address from bank,informations where userid = ? and password = ? and customerid = userid";
									 ps = con.prepareStatement(query3);
									ps.setString(1, LoginPage.userid);
									ps.setString(2, LoginPage.password);
									 rs = ps.executeQuery();
									table_3.setModel(DbUtils.resultSetToTableModel(rs));
									table_3.setEnabled(false);
									
									//query for generating balance customerid is foreignkey
									 String query4 = "select balance from bank,informations where userid = ? and password = ? and customerid = userid";
										 ps = con.prepareStatement(query4);
										ps.setString(1, LoginPage.userid);
										ps.setString(2, LoginPage.password);
										 rs = ps.executeQuery();
										table_4.setModel(DbUtils.resultSetToTableModel(rs));
										table_4.setEnabled(false);
						
										//query for generating logindate and time customerid is foreignkey
										 String query5 = "select logindate,logintime from bank,informations where userid = ? and password = ? and customerid = userid";
											 ps = con.prepareStatement(query5);
											ps.setString(1, LoginPage.userid);
											ps.setString(2, LoginPage.password);
											 rs = ps.executeQuery();
											table_5.setModel(DbUtils.resultSetToTableModel(rs));
											table_5.setEnabled(false);
					                  }
					 else
					 {
						 JOptionPane.showMessageDialog(null,"deposit some amount for printing the passbook");
						 AddMoney a = new AddMoney();
						 dispose();
					 }
					
					
					
					
					
				    
					
				}
				
				catch (Exception e) {
					JOptionPane.showMessageDialog(null, e);
				}
				
				
				
			}
		});
		btnPrint.setBounds(202, 30, 89, 23);
		contentPane.add(btnPrint);
		
		table_1 = new JTable();
		table_1.setBounds(164, 90, 288, 23);
		contentPane.add(table_1);
		
		 table = new JTable();
		    table.setBounds(164, 124, 288, 23);
		    contentPane.add(table);
		    
		    table_2 = new JTable();
		    table_2.setBounds(164, 158, 288, 23);
		    contentPane.add(table_2);
		    
		    table_3 = new JTable();
		    table_3.setBounds(164, 192, 288, 23);
		    contentPane.add(table_3);
		    
		    table_4 = new JTable();
		    table_4.setBounds(164, 226, 288, 23);
		    contentPane.add(table_4);
		    
		    table_5 = new JTable();
		    table_5.setBounds(164, 260, 288, 23);
		    contentPane.add(table_5);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Welcome w  = new Welcome();
				dispose();
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("");
		label.setBounds(382, 15, 134, 14);
		contentPane.add(label);
		
		//displaying date and time
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date d = new Date();
	    label.setText(s.format(d));
	    
	   
	    
	    JLabel lblName = new JLabel("Name");
	    lblName.setBounds(29, 90, 70, 14);
	    contentPane.add(lblName);
	    
	    JLabel lblAddress = new JLabel("Gender");
	    lblAddress.setBounds(29, 124, 59, 14);
	    contentPane.add(lblAddress);
	    
	    JLabel lblPhone = new JLabel("Phone ");
	    lblPhone.setBounds(29, 158, 46, 14);
	    contentPane.add(lblPhone);
	    
	    JLabel lblAddress_1 = new JLabel("Address");
	    lblAddress_1.setBounds(29, 192, 70, 14);
	    contentPane.add(lblAddress_1);
	    
	    JLabel lblBalance = new JLabel("Balance");
	    lblBalance.setBounds(29, 226, 46, 14);
	    contentPane.add(lblBalance);
	    
	    JLabel lblLastLogin = new JLabel("Last Login");
	    lblLastLogin.setBounds(29, 260, 70, 14);
	    contentPane.add(lblLastLogin);
	    
	    lblNewLabel = new JLabel("New label");
	    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\puranik\\Downloads\\bank.jpg"));
	    lblNewLabel.setBounds(0, 0, 644, 299);
	    contentPane.add(lblNewLabel);
	}
}
