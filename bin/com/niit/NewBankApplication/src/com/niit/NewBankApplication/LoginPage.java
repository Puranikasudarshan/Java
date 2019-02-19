package com.niit.NewBankApplication;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

public class LoginPage {

	private JFrame frame;
	JTextField textField;
	 JPasswordField passwordField;
	
static	String userid,password;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	//change here to connect to database
	
	Connection con = null;
	
	PreparedStatement ps = null;
	
	public LoginPage() {
		initialize();
		con = SqlClass.databaseConnector();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUserId = new JLabel("USER ID ");
		lblUserId.setBounds(53, 59, 62, 14);
		frame.getContentPane().add(lblUserId);
		
		textField = new JTextField();
		textField.setBounds(184, 56, 131, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(53, 111, 72, 14);
		frame.getContentPane().add(lblPassword);
		
		// for login button
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				try
				{   
					//query for selecting existing user
					String sql = "select userid,password from bank where userid = ? and password = ?";
					
					 ps = con.prepareStatement(sql);
					
					userid = textField.getText();
					
					ps.setString(1, userid);// checking UserName to database
					
					password = passwordField.getText();
					
					ps.setString(2, password);// checking password to database
					
					ResultSet rs = ps.executeQuery();
					
					int flag = 0;
					
					while(rs.next())
					{
						flag++;
					}
					
					if(flag==1)//if user exists go to next page
					{
						Welcome w = new Welcome();
						
						frame.dispose();
					}
					
					else//if not display the error message
					{
						JOptionPane.showMessageDialog(frame,"invalid username or password");
					}
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			}
		});
		btnLogin.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 11));
		btnLogin.setForeground(Color.BLACK);
		btnLogin.setBounds(10, 200, 89, 23);
		frame.getContentPane().add(btnLogin);
		
		JButton btnCancel = new JButton("CANCEL");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//change here to make cancel button working   
				frame.dispose();
			}
		});
		btnCancel.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 11));
		btnCancel.setBounds(165, 200, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		
		// for new user
		
		JButton btnNewUser = new JButton("Register");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				NewUser n = new NewUser();
				frame.dispose();
				
				}
		});
		btnNewUser.setFont(new Font("Comic Sans MS", Font.BOLD | Font.ITALIC, 11));
		btnNewUser.setBounds(316, 200, 89, 23);
		frame.getContentPane().add(btnNewUser);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(184, 111, 131, 17);
		frame.getContentPane().add(passwordField);
		
		JLabel label = new JLabel("");
		label.setBounds(281, 11, 124, 14);
		frame.getContentPane().add(label);
		
		//for date and time
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		Date d = new Date();
		label.setText(s.format(d));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\puranik\\Downloads\\bank.jpg"));
		lblNewLabel.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(lblNewLabel);
		
		
	
	}
}
