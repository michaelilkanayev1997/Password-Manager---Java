package PasswordManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class AddPasswordDialog extends JDialog implements ActionListener{
	
	//************** JLabel's *******************
	JLabel lbTitle = new JLabel("Add Password");
	JLabel lbWebsite = new JLabel("Website Url: ");
	JLabel lbPassword = new JLabel("Password:");
	JLabel Username = new JLabel("Username:");
	
	//******************* Text Field's ************************
	JTextField WebsiteTxt = new JTextField();
	JTextField PasswordTxt = new JTextField();
	JTextField UsernameTxt = new JTextField();
	
	//******************* Buttons Field's ************************
	JButton submit,cancel,GenerateButton;
	
	// create a separator
    JSeparator separator = new JSeparator();
		
	static String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
	
	ExcelFile excelfile = new ExcelFile();
	AES aes = new AES();
	
	String username;
	Table table;
	JLabel IconImage,IconImage2;
	
	AddPasswordDialog(String Userid,Table table){
					
		//settings AddPasswordDialog
		setTitle("Add Password");//Set title
		setSize(560,560); //setting size
		setLayout(null);
		setLocationRelativeTo(null);//Creates the window in the center of the panel
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);// Release all resources when JDialog is closed 
		
		this.username = Userid;
		this.table = table;
		        
		// load the image to a imageIcon
		ImageIcon imageIcon = new ImageIcon(CurrentPath+"\\src\\test\\resources\\password-manager-icon-18.jpg"); 
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(70,70,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		IconImage = new JLabel(imageIcon);
		IconImage.setBounds(10,10,70,70);

		ImageIcon imageIcon2 = new ImageIcon(CurrentPath+"\\src\\test\\resources\\5183000.jpg"); // load the image to a imageIcon
		Image image2 = imageIcon2.getImage(); // transform it 
		Image newimg2 = image2.getScaledInstance(560,560,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon2 = new ImageIcon(newimg2);  // transform it back
		IconImage2 = new JLabel(imageIcon2);
		IconImage2.setBounds(0,0,560,560);
				
		Image icon = Toolkit.getDefaultToolkit().getImage(CurrentPath+"\\src\\test\\resources\\padlock (1).png");    
		setIconImage(icon); 
			
		//Initialize ButtonFields
		submit=new JButton("Submit");
		submit.setFocusable(false);
		submit.setForeground(Color.BLACK);
		submit.setFont(new Font("Tahoma",Font.BOLD,14));
		submit.setBounds(180,480,85,33);
				
		cancel=new JButton("Cancel"); 	
		cancel.setFocusable(false);
		cancel.setForeground(Color.BLACK);
		cancel.setFont(new Font("Tahoma",Font.BOLD,14));
		cancel.setBounds(280,480,85,33);	
		
		GenerateButton=new JButton("<html>Generate a random strong password</html>"); 	
		GenerateButton.setFocusable(false);
		GenerateButton.setForeground(Color.BLACK);
		GenerateButton.setFont(new Font("Tahoma",Font.BOLD,14));
		GenerateButton.setBounds(350,280,150,60);
		
		lbTitle.setBounds(150,20,300,40);
		lbTitle.setFont(new Font("Tahoma",Font.BOLD,35));
		lbTitle.setForeground(Color.BLACK);
		separator.setBounds(120,65,340,15);
		lbWebsite.setBounds(15,130,300,30);
		lbWebsite.setFont(new Font("Verdana",Font.BOLD,15));
		lbWebsite.setForeground(Color.BLACK);
		WebsiteTxt.setBounds(150,130,250,30);
		WebsiteTxt.setFont(new Font("Ariel",Font.BOLD,15));
		Username.setBounds(15,215,300,30);
		Username.setFont(new Font("Verdana",Font.BOLD,15));
		Username.setForeground(Color.BLACK);
		UsernameTxt.setBounds(150,215,210,30);
		UsernameTxt.setFont(new Font("Ariel",Font.BOLD,15));
		lbPassword.setBounds(15,300,300,30);
		lbPassword.setFont(new Font("Verdana",Font.BOLD,15));
		lbPassword.setForeground(Color.BLACK);
		PasswordTxt.setBounds(150,300,160,30);
		PasswordTxt.setFont(new Font("Tahoma",Font.BOLD,15));

		//adding components
		add(submit);
		add(cancel);
		add(GenerateButton);
		add(lbTitle);
		add(separator);
		add(IconImage);
		add(lbWebsite);
		add(lbPassword);
		add(Username);
		add(WebsiteTxt);
		add(PasswordTxt);
		add(UsernameTxt);
		add(IconImage2);
		
		//creating button events
		submit.addActionListener(this);
		cancel.addActionListener(this);
		GenerateButton.addActionListener(this);
		
		setVisible(true);
	}
	public boolean isURLCorrect(String url) {
		  try {
			     (new java.net.URL(url)).openStream().close();
			     return true;
			  } catch (Exception ex) { }
		   return false;
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
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==submit) {
			
			//--------- Saving the user input ---------//
			String Website = WebsiteTxt.getText();
			String Username = UsernameTxt.getText();
			String Password = PasswordTxt.getText();
			
			try {
				if(!isURLCorrect(Website)) {
					throw new Exception("The URL of the website is not in a valid form,\nPlease try again.");
				}
				else if(Username.length()<8 || CheckSpecialCharacters(Username)==false) {
					throw new Exception("Username - should Contain at least 8 characters\nand at least one special character (@, #, $ Etc).");
				}
				else if(Password.length()!=8 || CountLetters(Password)<1 || CheckSpecialCharacters(Password)==false ||CountNumsInString(Password)<1 || containsUpperCaseCharacter(Password)==false)  {
					throw new Exception( "Password -Should Contain 8 characters.\nContain at least one capital letter,one number\nand at least one special character (!, #, $ Etc).");
				}
				
				String encrypted_password = aes.encrypt(Password);
				
				
				String[] WebsiteDetails = {Website,Username,encrypted_password};
				File file = new File(CurrentPath+"\\Data_base"+"\\"+username+"-Data.xlsx");
				int Result;
				
				if(!file.isFile()) { 
					Result = excelfile.CreateExcelDataBase(WebsiteDetails, username);
				}
				else {	
					Result = excelfile.AddToExistingExcelFile(WebsiteDetails, username);
				}
				
				if(Result==1) {
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 16));
					UIManager.put("OptionPane.minimumSize",new Dimension(390,60)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"Your Password has been added, thanks.");
					
					DefaultTableModel model = (DefaultTableModel) table.table.getModel();
				    model.addRow(new Object[]{Website,Username ,encrypted_password}); 
				}
				else {
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
					UIManager.put("OptionPane.minimumSize",new Dimension(390,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"Unfortunately there was an error\nentering the new password","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				}

				dispose();//Close the dialog window
			}
			catch(Exception e1){
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				UIManager.put("OptionPane.minimumSize",new Dimension(390,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if (e.getSource() == cancel) {
			dispose(); //Close the dialog window
		}
		else if (e.getSource() == GenerateButton) {
			
			//Geneating a Strong random Password
			String lower_cases = "qwertyuiopasdfghjklzxcvbnm";
			String upper_cases = "QWERTYUIOPASDFGHJKLZXCVBNM";
			String Special_Characters = "~!@#$%^&*()_+[]<>{}:|<>?-=";
			
			String password = "";
			do {
				password = "";
				for(int i =0; i<8; i++) {
					int rand = (int)(4*Math.random());
					
					switch(rand) {
						case 0:
							password += String.valueOf((int)(10 * Math.random()));
							break;
						case 1:
							rand = (int)(lower_cases.length() * Math.random());
							password += String.valueOf(lower_cases.charAt(rand));
							break;
						case 2:
							rand = (int)(upper_cases.length() * Math.random());
							password += String.valueOf(upper_cases.charAt(rand));
							break;
						case 3:
							rand = (int)(Special_Characters.length() * Math.random());
							password += String.valueOf(Special_Characters.charAt(rand));
							break;
					}
				}
			}while(password.length()!=8 || CountLetters(password)<1 || CheckSpecialCharacters(password)==false ||CountNumsInString(password)<1 || containsUpperCaseCharacter(password)==false);
			
			PasswordTxt.setText(password);
		}
	}
}