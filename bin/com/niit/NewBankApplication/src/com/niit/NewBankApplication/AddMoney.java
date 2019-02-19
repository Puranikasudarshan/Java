package com.niit.NewBankApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ImageIcon;

public class AddMoney extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddMoney frame = new AddMoney();
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
	//connection variable
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	private JTable table;
	
	public AddMoney() {
		con = SqlClass.databaseConnector();//getting connection
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		textField.setBounds(171, 77, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterTheAmount = new JLabel("Enter the amount ");
		lblEnterTheAmount.setBounds(38, 80, 133, 14);
		contentPane.add(lblEnterTheAmount);
		
		JButton btnDeposit = new JButton("Deposit");
		btnDeposit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				//query for inserting amount
				String sql = "insert into informations(customerid,balance,logindate,logintime) values((select userid from bank where bank.userid = ?),?,?,?)";
				//query for updating the balance for the existing user
				String sql1 = "update informations,bank set balance = balance + ?  where userid = ? and customerid = userid";
				//query for updating the login details
				String sql11 = "update informations ,bank set logindate = ?,logintime = ? where userid = ? and customerid = userid";
		     	//query for checking whether the user exists are not
				String sql2 = "select customerid from informations,bank where userid = ? and customerid = userid ";
		     	//query for displaying the amount after inserting amount
				String sql3 = "select balance from informations,bank where userid = ? and customerid = userid";
				
				try 
				{   //checking whether the user exists are not
					ps = con.prepareStatement(sql2);
					ps.setString(1, LoginPage.userid);
					 rs = ps.executeQuery();
					
					if(rs.next())//if exists
					{	
						//update the balance
					    ps = con.prepareStatement(sql1);
					    double balance = Double.parseDouble(textField.getText());
					    if(balance>0)
					    {
					    	ps.setString(1, textField.getText());
							ps.setString(2, LoginPage.userid);
							ps.executeUpdate();
							
							//update the login details
							ps = con.prepareStatement(sql11);
							ps.setDate(1,java.sql.Date.valueOf(java.time.LocalDate.now()));
							ps.setTimestamp(2, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
							ps.setString(3, LoginPage.userid);
							ps.executeUpdate();
							
							//display the amount after insertion 
							ps = con.prepareStatement(sql3);
							ps.setString(1, LoginPage.userid);
							rs = ps.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(rs));
							table.setEnabled(false);
							JOptionPane.showMessageDialog(null, "Successfully deposited");
					    }
					    else
					    	JOptionPane.showMessageDialog(null,"Enter the proper amount");
						}
					else 
					{
						//if new user
						//inserting his details to database with customerid as foreign key
						ps.close();
						ps = con.prepareStatement(sql);
						ps.setString(1, LoginPage.userid);
						ps.setString(2, textField.getText());
						ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
						ps.setTimestamp(4, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
						ps.executeUpdate();
						
						//displaying the total amount after insertion
						rs.close();
						ps = con.prepareStatement(sql3);
						ps.setString(1, LoginPage.userid);
						rs = ps.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(rs));
						table.setEnabled(false);
						JOptionPane.showMessageDialog(null, "Successfully deposited");
					}
						
					
					
					
				} 
				
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		btnDeposit.setBounds(171, 204, 89, 23);
		contentPane.add(btnDeposit);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Welcome w = new Welcome();
				dispose();
			}
		});
		btnBack.setBounds(21, 11, 89, 23);
		contentPane.add(btnBack);
		setVisible(true);
		
		JLabel label = new JLabel("");
		label.setBounds(290, 15, 134, 14);
		contentPane.add(label);
		
		//displaying date and time
		SimpleDateFormat s = new SimpleDateFormat("dd:mm:yyyy HH:MM:ss");
		Date d = new Date();
	    label.setText(s.format(d));
	    
	    JLabel lblAvailable = new JLabel("Available");
	    lblAvailable.setBounds(38, 126, 72, 14);
	    contentPane.add(lblAvailable);
	    
	    table = new JTable();
	    table.setBounds(171, 126, 86, 20);
	    contentPane.add(table);
	    
	    JLabel lblNewLabel = new JLabel("New label");
	    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\puranik\\Downloads\\bank.jpg"));
	    lblNewLabel.setBounds(0, -3, 434, 264);
	    contentPane.add(lblNewLabel);
	}
}
