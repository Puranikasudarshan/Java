package com.niit.NewBankApplication;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class NewUser extends JFrame {
	
	
	ButtonGroup bg = new ButtonGroup();//for gender selection
    ButtonGroup bg1 = new ButtonGroup();//for language selection
	
	private JPanel contentPane;
	private JTextField textField;//name
	private JTextField textField_1;//id
	private JTextField textField_2;//date of birth
	private JTextField textField_3;//phone
	private JPasswordField passwordField;//password
	private JTextArea textArea = new JTextArea();//address
	
private	JRadioButton rdbtnMale = new JRadioButton("Male");//radio button male
private	JRadioButton rdbtnFemale = new JRadioButton("Female");//radio button female
private JRadioButton rdbtnHindi = new JRadioButton("Hindi");//radio button hindi
private JRadioButton rdbtnEnglish = new JRadioButton("English");//radio button english

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewUser frame = new NewUser();
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
	
	Connection con = null;//object of connection class
	PreparedStatement ps = null;
	
	
	public NewUser() {
		con = SqlClass.databaseConnector();//getting connection
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 522, 411);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//label for name
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(48, 102, 46, 14);
		contentPane.add(lblName);
		
		//label for password
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(48, 171, 59, 14);
		contentPane.add(lblPassword);
		
		//label for phone
		JLabel lblNewLabel = new JLabel("Phone");
		lblNewLabel.setBounds(49, 209, 58, 14);
		contentPane.add(lblNewLabel);
		
		//lable for userid
		JLabel lblNewLabel_1 = new JLabel("User Id");
		lblNewLabel_1.setBounds(49, 137, 71, 14);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField.setBounds(149, 99, 86, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		textField_1.setBounds(149, 134, 86, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(149, 206, 86, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		setVisible(true);//to see new user tab when login is clicked
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				try 
				{ 
					//to select gender
					
					String gender = null;
					
					if(rdbtnMale.isSelected())
					{
						gender = "male";
					}
					else if(rdbtnFemale.isSelected())
					{
						gender = "female";
					}
						
					
					
					
					//query for the insertion of values to database
					
					String sql = "insert into bank(name,userid,gender,phone,address,password,dateofbirth) values(?,?,?,?,?,?,?)";
					
					ps = con.prepareStatement(sql);
					
					ps.setString(1, textField.getText());//name
					ps.setString(2, textField_1.getText());//id
					ps.setString(3,gender);//gender
					
					if(textField_3.getText().length() == 10)
					{
						ps.setString(4, textField_3.getText());//phone
					}
					
					ps.setString(5, textArea.getText());//address
					
					// to check the password is valid or not
					if(Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=.\\S+$).{6,20}$", passwordField.getText()))
					{						
						ps.setString(6, passwordField.getText());//password
					}
					ps.setString(7, textField_2.getText());//dateofbirth
					
					//instruction for the above query to get executed
					ps.executeUpdate();
		              
					//if successfully registered this message to be displayed
					JOptionPane.showMessageDialog(null,"Successfully registered");
					
					
					
				} 
				//if username matches with the existing userid
				catch (SQLIntegrityConstraintViolationException s)
				{
					
					JOptionPane.showMessageDialog(null, "username already taken");
				}
				
				
				catch(Exception e)
				{    //if wrong inputs are given
					JOptionPane.showMessageDialog(null,"invalid format");
				}
			
			}
		});
		btnSubmit.setBounds(43, 294, 89, 23);
		contentPane.add(btnSubmit);
		
		JButton btnProfile = new JButton("Profile");
		btnProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				LoginPage l = new LoginPage();
				dispose();
				
			}
		});
		btnProfile.setBounds(360, 294, 89, 23);
		contentPane.add(btnProfile);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 168, 86, 20);
		contentPane.add(passwordField);
		
		
		//adding button buttongroup
		bg.add(rdbtnMale);
		
		rdbtnMale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		rdbtnMale.setBounds(320, 110, 59, 23);
		contentPane.add(rdbtnMale);
		
		
		//adding button to buttongroup
		bg.add(rdbtnFemale);
		
		rdbtnFemale.setBounds(320, 133, 71, 23);
		contentPane.add(rdbtnFemale);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(284, 89, 46, 14);
		contentPane.add(lblGender);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(285, 183, 71, 14);
		contentPane.add(lblAddress);
		
		
		textArea.setBounds(283, 220, 148, 40);
		contentPane.add(textArea);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(234, 78, -76, -10);
		contentPane.add(separator);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth");
		lblDateOfBirth.setBounds(49, 246, 83, 14);
		contentPane.add(lblDateOfBirth);
		
		textField_2 = new JTextField();
		textField_2.setBounds(149, 240, 86, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(254, 22, 16, 238);
		contentPane.add(separator_1);
		
		JLabel lblChangeLanguage = new JLabel("Change Language");
		lblChangeLanguage.setBounds(48, 22, 110, 14);
		contentPane.add(lblChangeLanguage);
		
		
			
		//adding to button group
		
		bg1.add(rdbtnEnglish);
		
		rdbtnEnglish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(rdbtnEnglish.isSelected())
				{
					String lang = "en";
					
					String country = "In";
					
					//changing language to english
					
					Locale l = new Locale(lang,country);
					
					ResourceBundle r = ResourceBundle.getBundle("MessageBundle_En_IN",l);
					
                    lblName.setText(r.getString("name"));
					
					lblPassword.setText(r.getString("password"));
					
					lblGender.setText(r.getString("Gender"));
					
					lblAddress.setText(r.getString("Address"));
					
					lblNewLabel.setText(r.getString("phone"));
					
					lblNewLabel_1.setText(r.getString("userId"));
					
					lblDateOfBirth.setText(r.getString("dateOfBirth"));
					
					rdbtnMale.setText(r.getString("male"));
					
					rdbtnFemale.setText(r.getString("female"));
					
					rdbtnEnglish.setText(r.getString("english"));
					
					btnSubmit.setText(r.getString("submit"));
					
					btnProfile.setText(r.getString("profile"));
					
					lblChangeLanguage.setText(r.getString("lang"));

					
					
				}
			}
		});
		
		
		
		
		rdbtnEnglish.setBounds(48, 45, 109, 23);
		contentPane.add(rdbtnEnglish);
		
		
		//adding hindi button to group
		
		bg1.add(rdbtnHindi);
		rdbtnHindi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(rdbtnHindi.isSelected())
				{
					String lang = "hi";
					
					String country ="In";
					
					//changing language to hindi
					
					Locale locale = new Locale(lang,country);
					
					ResourceBundle r = ResourceBundle.getBundle("MessageBundle_hi_IN",locale);
					
					lblName.setText(r.getString("name"));
					
					lblPassword.setText(r.getString("password"));
					
					lblGender.setText(r.getString("Gender"));
					
					lblAddress.setText(r.getString("Address"));
					
					lblNewLabel.setText(r.getString("phone"));
					
					lblNewLabel_1.setText(r.getString("userId"));
					
					lblDateOfBirth.setText(r.getString("dateOfBirth"));
					
					rdbtnMale.setText(r.getString("male"));
					
					rdbtnFemale.setText(r.getString("female"));

					rdbtnHindi.setText(r.getString("hindi"));
					
					btnSubmit.setText(r.getString("submit"));
					
					btnProfile.setText(r.getString("profile"));
					
					lblChangeLanguage.setText(r.getString("lang"));
					
				}
				
			}
		});
		
		rdbtnHindi.setBounds(162, 45, 86, 23);
		contentPane.add(rdbtnHindi);
		
		//for date and time
		JLabel label = new JLabel("");
		label.setBounds(320, 22, 165, 14);
		contentPane.add(label);
		
		//to add date and time
		SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy HH:mm:SS");
		Date d = new Date();
		label.setText(s.format(d));
		
		JLabel lblNewLabel_2 = new JLabel("Format (YYYYMMDD)");
		lblNewLabel_2.setBounds(48, 269, 187, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\puranik\\Downloads\\bank.jpg"));
		lblNewLabel_3.setBounds(0, 0, 506, 372);
		contentPane.add(lblNewLabel_3);
		
		
		
	}
}
