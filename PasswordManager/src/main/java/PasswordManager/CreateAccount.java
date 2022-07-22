package PasswordManager;

import java.util.Arrays;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * This is the CreateAccount class who implements ActionListener and responsible for
 * creating a new account.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class CreateAccount implements ActionListener{
	
    //*********** Initialization of the components ***********
	ExcelFile excelfile = new ExcelFile();
	JFrame frame = new JFrame();
	JButton SignupButton = new JButton("Sign up");
	JButton ExitButton = new JButton("Exit");
	JButton BackButton = new JButton("Back");
	JTextField UsernameField = new JTextField();
	JPasswordField userPassword = new JPasswordField();
	JPasswordField verificationPassword = new JPasswordField();
	JTextField UserIdtxt = new JTextField();
	JSeparator separator = new JSeparator();
	JLabel UsernameLabel = new JLabel("Username:");
	JLabel userPasswordLabel = new JLabel("Password:");
	JLabel verificationPasswordLabel = new JLabel("Verification:");
	JLabel UserIdLabel = new JLabel("ID:");
	JLabel CreateAnAccountLabel = new JLabel("Create an account");
	JLabel IconImage ;
	
	AES aes = new AES();
	
	String encryptPassword = null;
	
	/**
	 * constructor
	 * setting the components
	 */
	CreateAccount(){
		
		String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
		Image icon = Toolkit.getDefaultToolkit().getImage(CurrentPath+"\\src\\test\\resources\\padlock.png");    
		frame.setIconImage(icon);    
		
		ImageIcon imageIcon = new ImageIcon(CurrentPath+"\\src\\test\\resources\\background2.jpg"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(440,420,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		IconImage = new JLabel(imageIcon);
		IconImage.setBounds(0,0,440,420);
		
		CreateAnAccountLabel.setBounds(55,15,335,40);
		CreateAnAccountLabel.setFont(new Font("Serif",Font.BOLD,43));
		CreateAnAccountLabel.setForeground(Color.BLACK);

		
		separator.setBounds(10,80,405,55);
		
		UsernameLabel.setBounds(40, 150,75,25);
		UsernameLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		UsernameLabel.setForeground(Color.BLACK);
		
		userPasswordLabel.setBounds(40, 200,75,25);
		userPasswordLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		userPasswordLabel.setForeground(Color.BLACK);
		
		verificationPasswordLabel.setBounds(40, 250,85,25);
		verificationPasswordLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		verificationPasswordLabel.setForeground(Color.BLACK);
		
		UserIdLabel.setBounds(40, 100, 75, 25);
		UserIdLabel.setFont(new Font("Verdana", Font.BOLD, 12));
		UserIdLabel.setForeground(Color.BLACK);
		
		
		UsernameField.setBounds(125, 150,200, 25);
		UsernameField.setFont(new Font("Tahoma",Font.BOLD,15));
		userPassword.setBounds(125, 200,200, 25);
		userPassword.setFont(new Font("Tahoma",Font.BOLD,15));
		verificationPassword.setBounds(125,250,200, 25);
		verificationPassword.setFont(new Font("Tahoma",Font.BOLD,15));
		UserIdtxt.setBounds(125,100, 200, 25);
		UserIdtxt.setFont(new Font("Tahoma",Font.BOLD,15));
		
		SignupButton.setBounds(165,290,120,30);
		SignupButton.setFocusable(false);
		SignupButton.addActionListener(this);
		SignupButton.setFont(new Font("Tahoma",Font.BOLD,20));

		ExitButton.setBounds(355,350,60,25);
		ExitButton.setFocusable(false);
		ExitButton.addActionListener(this);
		ExitButton.setFont(new Font("Tahoma",Font.BOLD,12));
		
		BackButton.setBounds(10,350,67,25);
		BackButton.setFocusable(false);
		BackButton.addActionListener(this);
		BackButton.setFont(new Font("Tahoma",Font.BOLD,12));
		
		// adding components to the frame
		frame.add(UsernameLabel);
		frame.add(userPasswordLabel);
		frame.add(verificationPasswordLabel);
		frame.add(UsernameField);
		frame.add(UserIdLabel);
		frame.add(UserIdtxt);
		frame.add(userPassword);
		frame.add(verificationPassword);
		frame.add(SignupButton);
		frame.add(ExitButton);
		frame.add(separator);
		frame.add(CreateAnAccountLabel);
		frame.add(BackButton);
		frame.add(IconImage);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(440,420);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);//open the window in the center of the screen
		frame.setVisible(true);
		frame.getContentPane().setBackground(new Color(153,153,153));
		frame.setTitle("Create Account");
		frame.setResizable(false);
}
	/**
	 * A function that counts the number of letters in a string
	 * 
	 * @param str - the string we want to check.
	 * @return letterCount - the letters count.
	 */
	public int CountLetters(String str) {

		int letterCount = 0;
		char temp;

		for( int i = 0; i < str.length( ); i++ ){
		    temp = str.charAt( i );

		    if( Character.isLetter(temp))
		    	letterCount++;
		}
		System.out.println(letterCount);
		return letterCount;
	}
	
	/**
	 * A function that counts the number of numbers in a string
	 * 
	 * @param str - the string we want to check.
	 * @return count - the numbers count.
	 */
	public int CountNumsInString(String str) {

	    int count=0;
	    for(int i=0;i<str.length();i++){
	    	
	    	if(Character.isDigit(str.charAt(i)))
	  	      count++;	
	    }
	      return count;
	}
	
	/**
	 * A boolean function that checks if a string Contains special letters
	 * 
	 * @param str- the string we want to check.
	 * @return true if str contains special letters/false if not.
	 */
	public boolean CheckSpecialCharacters(String str) {
	     Pattern p = Pattern.compile("[^A-Za-z0-9]");
	     Matcher m = p.matcher(str);
	    // boolean b = m.matches();
	     boolean b = m.find();
	     if (b) {
	    	 System.out.println("There is a special character in my string ");
	     	 return true;
	     }
	     else {
		     System.out.println("There is no special char");
		     return false;
	     }
	}
	
	public boolean containsUpperCaseCharacter(String string) {
		  for (int i = 0; i < string.length(); i++) {
		    if (Character.isUpperCase(string.charAt(i))) {
		      return true;
		    }
		  }
		  return false;
		}

	
    /**
     * A function that activated when a button is pressed.
     */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==SignupButton) { //creating the new account
			
			//--------- Saving the user input ---------//
			String Username = UsernameField.getText();
			String UserID = UserIdtxt.getText();
			String password1 = String.valueOf(userPassword.getPassword());
			String password2 = String.valueOf(verificationPassword.getPassword());
			
			if(!password1.equals(password2)) {
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				JOptionPane.showMessageDialog(null, "The passwords do not match!\nPlease try again.","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
			else if(Username.length()<8 || CountNumsInString(Username)<1 || CheckSpecialCharacters(Username)==false) {
				UIManager.put("OptionPane.minimumSize",new Dimension(390,50)); //setting the preferred size of JOptionPane
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				JOptionPane.showMessageDialog(null, "Username - should Contain at least 8 characters.\nAt least one digits and at least one special character (!, #, $ Etc)\n and the rest letters.","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else if(password1.length()!=8 || CountLetters(password1)<1 || CheckSpecialCharacters(password1)==false ||CountNumsInString(password1)<1 ||containsUpperCaseCharacter(password1)==false)  {
				UIManager.put("OptionPane.minimumSize",new Dimension(460,60)); //setting the preferred size of JOptionPane
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				JOptionPane.showMessageDialog(null, "Password -Should Contain exactly 8 characters.\nContain at least one capital letter,one number\nand at least one special character (!, #, $ Etc).","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else if(UserID.length()!=9 || CountNumsInString(UserID)!=9) {
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				JOptionPane.showMessageDialog(null, "ID - Should Contain 9 digits.\n","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
			}
			else {
				
				int ResultOfIDUserName = 0,ResultOfID=0;
				try {
					ResultOfID = excelfile.ReadExcelFileID(UserID); //checking if user ID already exist in the system
					
					try {
						encryptPassword = aes.encrypt(password1);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					ResultOfIDUserName = excelfile.ReadExcelFile(Username,encryptPassword); //checking if Username already exist in the system
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				if(ResultOfID ==1) { //if ID already exist
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
					UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"This ID already exists. ","Error",JOptionPane.ERROR_MESSAGE);
				}
				else if(ResultOfIDUserName !=1 && ResultOfIDUserName!=-1) { //The user does not exist in the system

					excelfile.WriteToExcelFile(Username,encryptPassword,UserID);

					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
					UIManager.put("OptionPane.minimumSize",new Dimension(380,60)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"New user created successfully ! .");
				}
				else { // User exist
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
					UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"User exist. ","Error",JOptionPane.ERROR_MESSAGE);		
				}
			} 			
		}
		else if(e.getSource()==ExitButton) { //Exit
			JFrame ExitFrame = new JFrame ("Exit");
			UIManager.put("OptionPane.minimumSize",new Dimension(300,50)); //setting the preferred size of JOptionPane
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			if(JOptionPane.showConfirmDialog(ExitFrame, " You want to Exit  ? ","Exit",JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}			
		}
		else if(e.getSource()==BackButton) { //back to the login page
			LoginPage LOGIN = new LoginPage();
			frame.dispose();
		}
	}
}
