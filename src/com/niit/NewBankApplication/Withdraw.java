package com.niit.NewBankApplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Withdraw extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Withdraw frame = new Withdraw();
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
	
	public Withdraw() {
		con = SqlClass.databaseConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(278, 24, 146, 14);
		contentPane.add(lblNewLabel);
		
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-ss hh:mm:ss");
		Date d = new Date();
		lblNewLabel.setText(s.format(d));
		
		JLabel lblAmoutNeeded = new JLabel("Amout Needed");
		lblAmoutNeeded.setBounds(29, 77, 92, 14);
		contentPane.add(lblAmoutNeeded);
		
		textField = new JTextField();
		textField.setBounds(172, 74, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setBounds(29, 118, 67, 14);
		contentPane.add(lblAvailable);
		
		table = new JTable();
		table.setBounds(172, 118, 92, 20);
		contentPane.add(table);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//query for updating balance
				String sql = "update informations,bank set balance = balance - ? where userid = ? and customerid = userid";
				//query for displaying the balance after withdrawing
				String sql1 = "Select balance from informations,bank where userid = ? and customerid = userid";
				//query for updating last login credentials
				String sql2 = "update informations,bank set logindate = ?,logintime = ? where userid = ? and customerid = userid";
				
				try {
					//grtting the balance from the user
					ps = con.prepareStatement(sql1);
					ps.setString(1, LoginPage.userid);
					rs = ps.executeQuery();
					if(rs.next())
					{
						double balance = rs.getDouble(1);
						
						//converting string to double
						double enteredAmount = Double.parseDouble(textField.getText());
						if(balance>=2000   &&  enteredAmount < balance && enteredAmount>0)
						{
							//updating the balance
							ps = con.prepareStatement(sql);
							ps.setString(1, textField.getText());
							ps.setString(2, LoginPage.userid);
						    ps.executeUpdate();
							
						    //displaying the available balance
						    ps = con.prepareStatement(sql1);
						    ps.setString(1, LoginPage.userid);
						    rs = ps.executeQuery();
						    table.setModel(DbUtils.resultSetToTableModel(rs));
						    table.setEnabled(false);
						    
						    JOptionPane.showMessageDialog(null, "amount debited");
						    
						  //updating last login details
						    ps = con.prepareStatement(sql2);
						    ps.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
						    ps.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
						    ps.setString(3, LoginPage.userid);
						    ps.executeUpdate();
					    }
						else
						{
							JOptionPane.showMessageDialog(null, "Not sufficient balance");
						}
						if(enteredAmount<0)
						{
							JOptionPane.showMessageDialog(null,"enter proper amount");
						}
					}
			    } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnSubmit.setBounds(29, 183, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Welcome w = new Welcome();
				dispose();
			}
		});
		btnBack.setBounds(270, 183, 89, 23);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\puranik\\Downloads\\bank.jpg"));
		label.setBounds(0, 0, 434, 261);
		contentPane.add(label);
		
		setVisible(true);
	}
}
