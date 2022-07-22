package PasswordManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class SimulateEntranceDialog extends JDialog implements ActionListener{
	
	//************** JLabel's *******************
	JLabel lbTitle = new JLabel("Simulate Entrance");
	JLabel lbPassword = new JLabel("Password:");
	JLabel Username = new JLabel("Username:");
	JLabel lbsubtitle = new JLabel();
	JLabel lbWebsite = new JLabel();
	
	//******************* Text Field's ************************
	JTextField PasswordTxt = new JTextField();
	JTextField UsernameTxt = new JTextField();
	
	//******************* Buttons Field's ************************
	JButton submit,cancel;
	
	// create a separator
    JSeparator separator = new JSeparator();
		
	static String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
	
	ExcelFile excelfile = new ExcelFile();
	AES aes = new AES();
	
	Table table;
	JLabel IconImage,IconImage2;
	String UserName,Password;
	
	SimulateEntranceDialog(String Website,String UserName,String Password){
		//settings AddPasswordDialog
		setTitle("Simulate Entrance");//Set title
		setSize(560,560); //setting size
		setLayout(null);
		setLocationRelativeTo(null);//Creates the window in the center of the panel
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);// Release all resources when JDialog is closed 

		this.UserName = UserName;
		this.Password = Password;
		
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

		lbTitle.setBounds(125,20,550,40);
		lbTitle.setFont(new Font("Tahoma",Font.BOLD,35));
		lbTitle.setForeground(Color.BLACK);
		
		lbWebsite.setBounds(15,110,550,40);
		lbWebsite.setFont(new Font("Tahoma",Font.BOLD,17));
		lbWebsite.setForeground(Color.BLACK);
		lbWebsite.setText("The Website is : " + Website);
		
		lbsubtitle.setBounds(15,155,550,40);
		lbsubtitle.setFont(new Font("Tahoma",Font.BOLD,17));
		lbsubtitle.setForeground(Color.BLACK);
		lbsubtitle.setText("Please enter your username and password:");

		separator.setBounds(120,65,340,15);
		
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
		add(lbTitle);
		add(lbWebsite);
		add(lbsubtitle);
		add(separator);
		add(IconImage);
		add(lbPassword);
		add(Username);
		add(PasswordTxt);
		add(UsernameTxt);
		add(IconImage2);

		//creating button events
		submit.addActionListener(this);
		cancel.addActionListener(this);

		setVisible(true);
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==submit) {

			//--------- Saving the user input ---------//
			String Username = UsernameTxt.getText();
			String PassWord = PasswordTxt.getText();

			if(Username.equals(UserName)) {
				
				if(PassWord.equals(Password)) {
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,20));
					UIManager.put("OptionPane.minimumSize",new Dimension(340,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"      Signed in !");
					dispose();//Close the dialog window
				}
				else {
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,18));
					UIManager.put("OptionPane.minimumSize",new Dimension(340,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"     Wrong Password !");
				}	 
			}else {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,18));
				UIManager.put("OptionPane.minimumSize",new Dimension(340,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"     Wrong UserName !");
			}
		}
		else if (e.getSource() == cancel) {
			dispose(); //Close the dialog window
		}
	}
}
