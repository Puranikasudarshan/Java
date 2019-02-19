package com.niit.NewBankApplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Update extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Update frame = new Update();
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
	public Update() {
		
		//getting connection
		con = SqlClass.databaseConnector(); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPassword = new JLabel("New Password");
		lblPassword.setBounds(43, 115, 86, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(157, 112, 121, 20);
		contentPane.add(passwordField);
		
		
		JLabel label = new JLabel("");
		label.setBounds(268, 22, 134, 14);
		contentPane.add(label);
		
		//to display date and time
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date d = new Date();
	    label.setText(s.format(d));
	    
	    JButton btnBack = new JButton("Back");
	    btnBack.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		Welcome w = new Welcome();
	    		dispose();
	    	}
	    });
	    btnBack.setBounds(313, 227, 89, 23);
	    contentPane.add(btnBack);
	    
	    JButton btnSubmit = new JButton("Submit");
	    btnSubmit.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		
	    		//checking whether the oldpassword matches the password entered 
	    		if(passwordField_1.getText().equals(LoginPage.password))
	    		{
	    			//to update the password
	    			String sql = "update bank,informations set password = ? where userid = ?";
		    		try {
						 ps = con.prepareStatement(sql);
						 //password constraint
						if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=.\\S+$).{6,20}$", passwordField.getText()))
						{						
							ps.setString(1, passwordField.getText());//password
							ps.setString(2, LoginPage.userid);
						}
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null,"successfully updated");
						JOptionPane.showMessageDialog(null,"please login once again");
						
						LoginPage l = new LoginPage();
						dispose();
					}
	    		catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
	         }
	    	else
	         {
	        	 JOptionPane.showMessageDialog(null,"invalid password");
	         }
	    		
	    		
	    			
	    }
	    });
	    btnSubmit.setBounds(43, 227, 89, 23);
	    contentPane.add(btnSubmit);
	    
	    JLabel lblOldPassword = new JLabel("Old Password");
	    lblOldPassword.setBounds(46, 79, 86, 14);
	    contentPane.add(lblOldPassword);
	    
	    passwordField_1 = new JPasswordField();
	    passwordField_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	}
	    });
	    passwordField_1.setBounds(157, 76, 121, 20);
	    contentPane.add(passwordField_1);
	    
	    JLabel lblNewLabel = new JLabel("New label");
	    lblNewLabel.setIcon(new ImageIcon("C:\\Users\\puranik\\Downloads\\bank.jpg"));
	    lblNewLabel.setBounds(0, 0, 434, 261);
	    contentPane.add(lblNewLabel);
		
	    setVisible(true);
	}
}
