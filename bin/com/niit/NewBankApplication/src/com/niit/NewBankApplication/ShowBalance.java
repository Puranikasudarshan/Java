package com.niit.NewBankApplication;

import java.awt.BorderLayout;
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

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class ShowBalance extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShowBalance frame = new ShowBalance();
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
	private JTable table;
	private JTable table_1;
	
	public ShowBalance() {
		con  = SqlClass.databaseConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblAvailableBalance = new JLabel("Available balance");
		lblAvailableBalance.setBounds(21, 76, 111, 23);
		contentPane.add(lblAvailableBalance);
		
		table = new JTable();
		table.setBounds(173, 76, 158, 23);
		contentPane.add(table);
		
		
		table_1 = new JTable();
	    table_1.setBounds(173, 121, 158, 23);
	    contentPane.add(table_1);
		
		JButton btnGenerateBalance = new JButton("Generate balance");
		btnGenerateBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    
				//query for generating balance
				String sql = "select balance from informations,bank where userid = ? and customerid = userid";
				//query for generating last transaction
				String sql1 = "select logindate,logintime from informations,bank where userid = ? and customerid = userid";
				try {
					//for generating balance
					 ps = con.prepareStatement(sql);
					ps.setString(1, LoginPage.userid);
					 rs = ps.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					table.setEnabled(false);
					
					//for generating last login details
					ps = con.prepareStatement(sql1);
					ps.setString(1, LoginPage.userid);
					rs = ps.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					table_1.setEnabled(false);
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			}
		});
		btnGenerateBalance.setBounds(137, 227, 148, 23);
		contentPane.add(btnGenerateBalance);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Welcome w = new Welcome();
				dispose();
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("");
		label.setBounds(290, 15, 134, 14);
		contentPane.add(label);
		
		//to display date and time
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date d = new Date();
	    label.setText(s.format(d));
	    
	    JLabel lblLastTransaction = new JLabel("Last Transaction");
	    lblLastTransaction.setBounds(21, 130, 111, 14);
	    contentPane.add(lblLastTransaction);
	    
	    JLabel lblNewLabel = new JLabel("New label");
	    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\puranik\\Downloads\\bank.jpg"));
	    lblNewLabel.setBounds(0, 0, 434, 261);
	    contentPane.add(lblNewLabel);
	    
	    
		
		
		setVisible(true);
		
		
	}
}
