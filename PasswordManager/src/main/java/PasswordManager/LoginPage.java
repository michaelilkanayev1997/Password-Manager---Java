package PasswordManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * This is the LoginPage class who implements ActionListener 
 * and Responsible for the user's entry into the program.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class LoginPage implements ActionListener{
	//*********** Initialization of the components ***********
	JFrame frame = new JFrame();
	JButton loginButton = new JButton("Log in");
	JButton resetButton = new JButton("Reset");
	JButton ExitButton = new JButton("Exit");
	JButton CreateNewAccountButton = new JButton("New user? Register");
	JTextField userIDField = new JTextField();
	JPasswordField userPasswordField = new JPasswordField();
	JLabel userIDLabel = new JLabel("Username:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel messageLabel = new JLabel();
	JLabel HelloLabel = new JLabel("Hello");
	JLabel SignInLabel = new JLabel("Sign in or create an account");
	JLabel IconImage ,IconImage2;

	JSeparator separator = new JSeparator();
	
	ExcelFile excelfile = new ExcelFile();
	AES aes = new AES();
		
	/**
	 * constructor
	 * setting the components
	 */
	LoginPage() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(440,420);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);//open the window in the center of the screen
		frame.setTitle("Login");
		frame.setResizable(false);
		
		String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
		Image icon = Toolkit.getDefaultToolkit().getImage(CurrentPath+"\\src\\test\\resources\\padlock.png");    
		frame.setIconImage(icon);    
		
		ImageIcon imageIcon = new ImageIcon(CurrentPath+"\\src\\test\\resources\\reset-password.png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(60,60,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		IconImage2 = new JLabel(imageIcon);
		IconImage2.setBounds(10,10,60,60);
			
		separator.setBounds(10,80,405,55);
			
		userIDLabel.setBounds(50, 100,75,25);
		userIDLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		userIDLabel.setForeground(Color.BLACK);
			
		userPasswordLabel.setBounds(50, 150,75,25);
		userPasswordLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		userPasswordLabel.setForeground(Color.BLACK);
			
		messageLabel.setBounds(115, 250, 250, 40);
		messageLabel.setFont(new Font("Tahoma",Font.BOLD,20));
		
		HelloLabel.setBounds(150,5, 107,35);
		HelloLabel.setFont(new Font("Serif",Font.BOLD,46));
		HelloLabel.setForeground(Color.BLACK);

		SignInLabel.setBounds(115,55,200,20);
		SignInLabel.setFont(new Font("Tahoma",Font.LAYOUT_LEFT_TO_RIGHT,15));
				
		userIDField.setBounds(125, 100,200, 25);
		userIDField.setFont(new Font("Tahoma",Font.BOLD,15));
		userPasswordField.setBounds(125, 150,200, 25);
		userPasswordField.setFont(new Font("Tahoma",Font.BOLD,15));
			
		loginButton.setBounds(70,200,137,42);
		loginButton.setFocusable(false);
		loginButton.addActionListener(this);
		loginButton.setFont(new Font("Tahoma",Font.BOLD,30));
			
		ExitButton.setBounds(355,350,60,25);
		ExitButton.setFocusable(false);
		ExitButton.addActionListener(this);
		ExitButton.setFont(new Font("Tahoma",Font.BOLD,12));
			
		resetButton.setBounds(235,200,137,42);
		resetButton.setFocusable(false);
		resetButton.addActionListener(this);
		resetButton.setFont(new Font("Tahoma",Font.BOLD,30));
			
		CreateNewAccountButton.setBounds(125, 300,170, 30);
		CreateNewAccountButton.setFocusable(false);
		CreateNewAccountButton.addActionListener(this);
		CreateNewAccountButton.setFont(new Font("Tahoma",Font.BOLD,12));
		
		ImageIcon imageIcon2 = new ImageIcon(CurrentPath+"\\src\\test\\resources\\background1.jpg"); // load the image to a imageIcon
		Image image2 = imageIcon2.getImage(); // transform it 
		Image newimg2 = image2.getScaledInstance(440,420,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon2 = new ImageIcon(newimg2);  // transform it back
		IconImage = new JLabel(imageIcon2);
		IconImage.setBounds(0,0,440,420);
			
		//****** adding components to the frame *******
		frame.add(userIDLabel);
		frame.add(userPasswordLabel);
		frame.add(messageLabel);
		frame.add(userIDField);
		frame.add(userPasswordField);
		frame.add(loginButton);
		frame.add(resetButton);
		frame.add(CreateNewAccountButton);
		frame.add(ExitButton);
		frame.add(separator);
		frame.add(HelloLabel);
		frame.add(SignInLabel);
		frame.add(IconImage2);
		frame.add(IconImage);
		
		frame.setVisible(true);
		}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==resetButton) { //reset fields
			userIDField.setText("");
			userPasswordField.setText("");
			
		}
		
		else if(e.getSource()==loginButton) { //login
			int ResultOfExcel = 0;
			String userID = userIDField.getText();
			String password = String.valueOf(userPasswordField.getPassword());
			
			String encryptPassword = null;
			try {
				encryptPassword = aes.encrypt(password);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			
			try {
				ResultOfExcel = excelfile.ReadExcelFile(userID, encryptPassword);
				
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			

			if(ResultOfExcel==1) { //Login is successful
			
				messageLabel.setForeground(Color.green);
				messageLabel.setText("Login successful");
				
				frame.dispose();
				
				SplashScreen sp = new SplashScreen(userID);
			}
			else if(ResultOfExcel ==-1) { //"Wrong Password
				
					messageLabel.setForeground(Color.red);
					messageLabel.setText("Wrong password");
			}
			else if(ResultOfExcel ==0) { // Wrong Username 
				messageLabel.setForeground(Color.red);
				messageLabel.setText("Username not found");
			}
		}
		else if(e.getSource()==CreateNewAccountButton) {
			frame.dispose();
			CreateAccount createAccount = new CreateAccount();
			
		}
		else if(e.getSource()==ExitButton) { //EXIT
			JFrame ExitFrame = new JFrame ("Exit");
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
			if(JOptionPane.showConfirmDialog(ExitFrame, " You want to Exit  ? ","Exit",JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}			
		}	
	}
	
	public static void main(String[] args) {
		LoginPage loginpage =new LoginPage();
	}	
}
