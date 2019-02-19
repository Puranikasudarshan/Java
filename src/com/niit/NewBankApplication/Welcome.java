package com.niit.NewBankApplication;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Welcome extends JFrame {

	private JPanel contentPane;
	
	/* for only one radio button to get clicked 
	 * The object for this class is to be created*/

	ButtonGroup bg = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcome frame = new Welcome();
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
	public Welcome() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 493, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToOur = new JLabel("Welcome To Our Bank");
		lblWelcomeToOur.setBounds(101, 39, 260, 39);
		lblWelcomeToOur.setFont(new Font("Verdana", Font.PLAIN, 20));
		contentPane.add(lblWelcomeToOur);
		
	

		
		JRadioButton rdbtnPrintPassbook = new JRadioButton("Print Passbook");
		rdbtnPrintPassbook.setBounds(62, 106, 127, 23);
		
		bg.add(rdbtnPrintPassbook);// changes are done here
		
		rdbtnPrintPassbook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//for passbook print option to select
				
				
			}
		});
		contentPane.add(rdbtnPrintPassbook);
		
		JRadioButton rdbtnShowBalance = new JRadioButton("Show Balance");
		rdbtnShowBalance.setBounds(62, 132, 109, 23);
		
		bg.add(rdbtnShowBalance);
		contentPane.add(rdbtnShowBalance);
		
		JRadioButton rdbtnUpdateValues = new JRadioButton("Update informations");
		rdbtnUpdateValues.setBounds(62, 158, 146, 23);
		
		bg.add(rdbtnUpdateValues);
		contentPane.add(rdbtnUpdateValues);
		
		JButton btnBack = new JButton("Log Out");
		btnBack.setBounds(281, 249, 89, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginPage l = new LoginPage();
				dispose();
			}
		});
		contentPane.add(btnBack);
		
		JRadioButton rdbtnWithdrawAmount = new JRadioButton("Withdraw Amount");
		rdbtnWithdrawAmount.setBounds(62, 184, 127, 23);
		
		bg.add(rdbtnWithdrawAmount);
		contentPane.add(rdbtnWithdrawAmount);
		
		JRadioButton rdbtnAddMoney = new JRadioButton("Add Money");
		rdbtnAddMoney.setBounds(62, 210, 109, 23);
		
		bg.add(rdbtnAddMoney);
		contentPane.add(rdbtnAddMoney);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(82, 249, 89, 23);
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				// for passbook
				if(rdbtnPrintPassbook.isSelected())
				{
					Passbook p = new Passbook();
					dispose();
				}
				
				//for updation
				if(rdbtnUpdateValues.isSelected())
				{
					Update u = new Update();
					dispose();
				}
				
				//for available balance
				if(rdbtnShowBalance.isSelected())
				{
					ShowBalance s = new ShowBalance();
					dispose();
				}
				
				//to add money
				if(rdbtnAddMoney.isSelected())
				{
	                AddMoney a =new AddMoney();
	                dispose();
				}
				
				//to withdraw money
				if(rdbtnWithdrawAmount.isSelected())
				{
					Withdraw w = new Withdraw();
					dispose();
				}
				
				
			}
		});
		contentPane.add(btnSubmit);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(340, 14, 127, 14);
		contentPane.add(label_1);
		//to display date and time
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
		Date d = new Date();
		label_1.setText(s.format(d));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\puranik\\Downloads\\bank.jpg"));
		lblNewLabel.setBounds(0, 0, 477, 304);
		contentPane.add(lblNewLabel);
		
		setVisible(true);
	}
}
