package PasswordManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.TableModel;

import org.apache.poi.ss.formula.functions.DMin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 * This is the main panel class which exstends JPanel and implements ActionListener.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class WelcomePanel extends JPanel implements ActionListener{
	
	JPanel panel1 = new JPanel();
	JFrame frame = new JFrame();
	protected static BufferedImage image;// Image for background image
	protected static BufferedImage Passwordicon;// Patient Icon 

	//******************* JLabel's ************************
    JLabel CurrentDateLabel = new JLabel(getCurrentDateString(), SwingConstants.CENTER);
    JLabel CurrentTimeLabel = new JLabel(getCurrentTimeString(), SwingConstants.CENTER);
	
	//******************* Button's ************************
	
	JButton ExitButton = new JButton("Exit");
	JButton HelpButton = new JButton("Help");
	JButton LogoutButton = new JButton("Logout");
	JButton addPasswordButton = new JButton("Add New Password");
	JButton RemovePasswordButton = new JButton("Remove Password");
	JButton LoginToSiteButton = new JButton("Login to site");
	JButton OpenSite = new JButton("Open site");
	JButton DecryptPassword = new JButton("Decrypt Password");
	JButton SimulateEntrance = new JButton("Simulate Entrance");
	
	ExcelFile excelfile = new ExcelFile();
	AES aes = new AES();
	
	String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
	
	String UserName;
	String WebsiteUrl;
	String Username ;
	String Password;
	
	WelcomeFrame Frame;
	Table table;
	int tableSelectedRow;
	/**
	 * constructor of the main panel
	 * @param userID - the id of the user
	 */
	WelcomePanel(String userID,WelcomeFrame frame){
		//setting main Panel
		super();
		setLayout(null);
		 
		this.UserName=userID;
		this.Frame = frame;
		
		addPasswordButton.setFont(new Font("Tahoma",Font.BOLD, 13));
		addPasswordButton.setBounds(810,204,185,35);
		addPasswordButton.setForeground(Color.BLACK);
		addPasswordButton.setBackground(Color.lightGray);
		addPasswordButton.setFocusable(false);
		addPasswordButton.addActionListener(this);
		
		RemovePasswordButton.setFont(new Font("Tahoma",Font.BOLD, 13));
		RemovePasswordButton.setBounds(810,246,185,35);
		RemovePasswordButton.setForeground(Color.BLACK);
		RemovePasswordButton.setBackground(Color.lightGray);
		RemovePasswordButton.setFocusable(false);
		RemovePasswordButton.addActionListener(this);
		
		OpenSite.setFont(new Font("Tahoma",Font.BOLD, 13));
		OpenSite.setBounds(810,288,185,35);
		OpenSite.setForeground(Color.BLACK);
		OpenSite.setBackground(Color.lightGray);
		OpenSite.setFocusable(false);
		OpenSite.addActionListener(this);
		
		LoginToSiteButton.setFont(new Font("Tahoma",Font.BOLD, 13));
		LoginToSiteButton.setBounds(810,330,185,35);
		LoginToSiteButton.setForeground(Color.BLACK);
		LoginToSiteButton.setBackground(Color.lightGray);
		LoginToSiteButton.setFocusable(false);
		LoginToSiteButton.addActionListener(this);
		
		DecryptPassword.setFont(new Font("Tahoma",Font.BOLD, 13));
		DecryptPassword.setBounds(810,372,185,35);
		DecryptPassword.setForeground(Color.BLACK);
		DecryptPassword.setBackground(Color.lightGray);
		DecryptPassword.setFocusable(false);
		DecryptPassword.addActionListener(this);
		
		SimulateEntrance.setFont(new Font("Tahoma",Font.BOLD, 13));
		SimulateEntrance.setBounds(810,414,185,35);
		SimulateEntrance.setForeground(Color.BLACK);
		SimulateEntrance.setBackground(Color.lightGray);
		SimulateEntrance.setFocusable(false);
		SimulateEntrance.addActionListener(this);
		
		ExitButton.setBounds(1000,621,70,30);
		ExitButton.setForeground(Color.BLACK);
		ExitButton.setBackground(Color.lightGray);
		ExitButton.setFocusable(false);
		ExitButton.addActionListener(this);
		
		HelpButton.setBounds(900,621,70,30);
		HelpButton.setForeground(Color.BLACK);
		HelpButton.setBackground(Color.lightGray);
		HelpButton.setFocusable(false);
		HelpButton.addActionListener(this);
		
		LogoutButton.setBounds(789,621,80,30);
		LogoutButton.setForeground(Color.BLACK);
		LogoutButton.setBackground(Color.lightGray);
		LogoutButton.setFocusable(false);
		LogoutButton.addActionListener(this);
		
        //Creating the Date Label
        CurrentDateLabel.setBounds(800,65,195,45);
        CurrentDateLabel.setFont(new Font("Tahoma",Font.BOLD,29));
        CurrentDateLabel.setForeground(Color.BLACK);
        CurrentDateLabel.setBackground(Color.ORANGE);
        CurrentDateLabel.setOpaque(true);
        
        //Creating the clock Label
        CurrentTimeLabel.setBounds(820,120,160,45);
        CurrentTimeLabel.setFont(new Font("Tahoma",Font.BOLD,30));
        CurrentTimeLabel.setForeground(Color.BLACK);
        CurrentTimeLabel.setBackground(Color.ORANGE);
        CurrentTimeLabel.setOpaque(true);

        Thread t1 = new Thread(runnable);
        t1.start();  // Activating of the thread

		JLabel welcomeLabel = new JLabel("Hello  "+ userID,SwingConstants.CENTER);
		welcomeLabel.setBounds(750,10,285,45);
		welcomeLabel.setFont(new Font("Serif",Font.BOLD,36));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBackground(Color.ORANGE);
		welcomeLabel.setOpaque(true);

		table = new Table();
		table.setBounds(40,60,680,530);

		table.table.addMouseListener(new MouseAdapter(){
			public void mouseReleased(MouseEvent arg0) {
					tableSelectedRow = table.table.getSelectedRow();
					
					//get table model
					DefaultTableModel model =(DefaultTableModel)table.table.getModel();
					
					WebsiteUrl = (String) model.getValueAt(tableSelectedRow,0);
					Username = (String) model.getValueAt(tableSelectedRow,1);
					Password = (String) model.getValueAt(tableSelectedRow,2);	
			}
		});

		AddDataToTable();
		
		//Adding all components:
		add(welcomeLabel);
		add(CurrentDateLabel);
		add(CurrentTimeLabel);
		add(ExitButton);
		add(addPasswordButton);
		add(RemovePasswordButton);
		add(HelpButton);
		add(LogoutButton);
		add(table);
		add(OpenSite);
		add(LoginToSiteButton);
		add(DecryptPassword);
		add(SimulateEntrance);
		
		try{    
	    	image = ImageIO.read(new File(CurrentPath +"\\src\\test\\resources\\background1.jpg"));//main panel image
	    	Passwordicon = ImageIO.read(new File(CurrentPath +"\\src\\test\\resources\\password (1).png"));//registration image

	    }
	    catch (IOException e1){
	    	UIManager.put("OptionPane.minimumSize",new Dimension(400,50)); //setting the preferred size of JOptionPane
	    	JOptionPane.showMessageDialog(null,"There is a problem with finding the background image"
	    			,"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	/**
	 * A function that paints the background of the main panel
	 */
	public void paintComponent(Graphics g){	
	 	super.paintComponent(g);
	 	if(this.image != null) {
            Graphics2D g1 = (Graphics2D) g;
            g1.drawImage(this.image,0,0,getWidth(),getHeight(),this);
            repaint();
	 	}
	 	
	 	g.drawRect(30,50,700,550);//left rectangle
	 	g.drawRect(800,195,200,262);//right rectangle
	 	
        g.drawImage(Passwordicon,850,470,90,90,this); //setting the icon of the registration
	}
	
	/**
	 * A function that getting the currnet Date in format :"dd/MM/yyyy".
	 * @return DateString - a String with the current date.
	 */
    private String getCurrentDateString() { 
        LocalDateTime ldt = LocalDateTime.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); 
        String DateString = ldt.format(formatter);
        return DateString; 
    } 
	/**
	 * A function that getting the currnet Time in format :"HH:mm:ss".
	 * @return TimeString - a String with the current Time.
	 */
    private String getCurrentTimeString() { 
        LocalDateTime ldt = LocalDateTime.now(); 
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); 
        String TimeString = ldt.format(formatter);
        return TimeString; 
    } 
	
    private void AddDataToTable(){
    	
    	File file = new File(CurrentPath+"\\Data_base"+"\\"+UserName+"-Data.xlsx");
		int Result;
		
		if(file.isFile()) { 
			try {
				excelfile.ImportExcelFileToJtable(UserName, table);
			} catch (IOException e) {
				e.printStackTrace();
			}	
		}
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == ExitButton){ //EXIT
			JFrame ExitFrame = new JFrame ("Exit");
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
			if(JOptionPane.showConfirmDialog(ExitFrame, " You want to Exit  ? ","Exit",JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_NO_OPTION) {
				System.exit(0);
			}
		}
		else if(e.getSource() == addPasswordButton){
			
			// open a dialog to add the New Password 
			AddPasswordDialog AddDialog=new AddPasswordDialog(UserName,table);
		}
		else if(e.getSource() == RemovePasswordButton) {
			
			if(table.table.getRowCount()>0) {
				RemovePasswordDialog RemoveDialog = new RemovePasswordDialog(UserName,table);
			}
			else {
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null, "There is currently nothing to delete .");
			}
		}
		else if (e.getSource() == LogoutButton) {
			JFrame ExitFrame = new JFrame ("Logout");
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
			UIManager.put("OptionPane.minimumSize",new Dimension(340,50)); //setting the preferred size of JOptionPane
			if(JOptionPane.showConfirmDialog(ExitFrame, "Are you sure you want to logout? ","Exit",JOptionPane.YES_NO_OPTION)
					== JOptionPane.YES_NO_OPTION) {
				Frame.setVisible(false); //User can't see the frame window
				new LoginPage(); //activation of the login page 
				Frame.dispose(); //Destroy the JFrame object
			}	 
		}
		else if (e.getSource() == LoginToSiteButton) {
	
			if(table.table.getSelectedRowCount() == 1) {
				
				UrlOpenerWithAutoLogin();

				DefaultListSelectionModel selectionModel = new DefaultListSelectionModel(); //table selection model
				table.table.setSelectionModel(selectionModel);
				selectionModel.removeSelectionInterval(tableSelectedRow,tableSelectedRow); // Removing selection for desired row
			}
			else {
				if(table.table.getRowCount() == 0 ) {
					//if table is empty
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
					UIManager.put("OptionPane.minimumSize",new Dimension(380,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"There is currently nothing in\nthe table to open .");
				}else {
					//if row is not selected or multiple row is selected
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
					UIManager.put("OptionPane.minimumSize",new Dimension(380,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"Please select a Single website to Login .");
				}
			}
		}
		else if (e.getSource() == OpenSite) {
			if(table.table.getSelectedRowCount() == 1) {
				UrlOpener();

				DefaultListSelectionModel selectionModel = new DefaultListSelectionModel(); //table selection model
				table.table.setSelectionModel(selectionModel);
				selectionModel.removeSelectionInterval(tableSelectedRow,tableSelectedRow); // Removing selection for desired row
			}
			else {
				if(table.table.getRowCount() == 0 ) {
					//if table is empty
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
					UIManager.put("OptionPane.minimumSize",new Dimension(380,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"There is currently nothing in\nthe table to open .");
				}else {
					//if row is not selected or multiple row is selected
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
					UIManager.put("OptionPane.minimumSize",new Dimension(380,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"Please select a Single website to Login .");
				}
			}
		}
		else if (e.getSource() == DecryptPassword) {
			if(table.table.getSelectedRowCount() == 1) {
				
				String decryptPassword=null;
				try {
					decryptPassword = aes.decrypt(Password);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				if(decryptPassword!= null) {
					UIManager.put("OptionPane.messageFont", new Font("Tahoma", Font.BOLD,17));
					UIManager.put("OptionPane.minimumSize",new Dimension(435,110)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"The decrypted password for:\n\n"
							+ "    * Website - "+ WebsiteUrl+"\n"
							+ "    * UserName -"+ Username+"\n\n"
							+ "is the following password:  "+ decryptPassword);
				}
				else {
					UIManager.put("OptionPane.minimumSize",new Dimension(350,50)); //setting the preferred size of JOptionPane
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
					JOptionPane.showMessageDialog(null, "Unfortunately there was a problem deciphering the password.\\nPlease contact creators.","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
				}
				
				DefaultListSelectionModel selectionModel = new DefaultListSelectionModel(); //table selection model
				table.table.setSelectionModel(selectionModel);
				selectionModel.removeSelectionInterval(tableSelectedRow,tableSelectedRow); // Removing selection for desired row
			}
			else {
				if(table.table.getRowCount() == 0 ) {
					//if table is empty
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
					UIManager.put("OptionPane.minimumSize",new Dimension(340,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"There is currently nothing in\nthe table to decrypt .");
				}else {
					//if row is not selected or multiple row is selected
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
					UIManager.put("OptionPane.minimumSize",new Dimension(340,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"Please select a Single Password to decrypt .");
				}
			}
		}
		else if(e.getSource() == SimulateEntrance) {
			if(table.table.getSelectedRowCount() == 1) {

				String decryptPassword = null;
				try {
					decryptPassword = aes.decrypt(Password);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				SimulateEntranceDialog simulateentrance = new SimulateEntranceDialog(WebsiteUrl,Username,decryptPassword);

				DefaultListSelectionModel selectionModel = new DefaultListSelectionModel(); //table selection model
				table.table.setSelectionModel(selectionModel);
				selectionModel.removeSelectionInterval(tableSelectedRow,tableSelectedRow); // Removing selection for desired row
			}
			else {
				if(table.table.getRowCount() == 0 ) {
					//if table is empty
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
					UIManager.put("OptionPane.minimumSize",new Dimension(340,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"There is currently nothing in\nthe table .");
				}else {
					//if row is not selected or multiple row is selected
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,14));
					UIManager.put("OptionPane.minimumSize",new Dimension(340,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"Please select a *Single* Website \nto simulate an entrance .");
				}
			}
		}
		else if (e.getSource() == HelpButton) { //Helps user to understand the program.

			String infoMessage = "Our Password Manager performs a couple of options: "
					+ "\nIt allows easy Registration for new users ,and easy Login/Logout for existing users."
					+ "\nIt allows passwords to be stored for the user in an encrypted form using AES encryption in an excel file. "
					+ "\nThe main window of the program has on the top right side a local date and time for user convenience."
					+"\n"
					+"\nIt also has the following options:"
					+ "\n1.Add a new Password to the system and an option to add a strong random password that the system creates for the user."
					+ "\n2.Remove a Password by Website and UserName."
					+ "\n3.Open any site that the user desires from the list of passwords stored in the system (Main Table)."
					+ "\n4.Option to Login automatically with google-chrome only to the following sites: Facebook and Linkedin."
					+ "\n5.The option to Decrypt (Reveal) any Password the user wants from the table."
					+ "\n6.The option to Simulate an Entrance to any Website the user wants from the table."
					+ "\n"
					+ "\n5.Thank you :)"
					;

			UIManager.put("OptionPane.messageForeground", Color.black);//setting text Color to black
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 14));
			UIManager.put("OptionPane.minimumSize",new Dimension(1080,350)); //setting the preferred size of JOptionPane
			JOptionPane.showMessageDialog(null, infoMessage, "HELP", JOptionPane.INFORMATION_MESSAGE);
		}		
	}

	private void UrlOpener() {
		// Get the Desktop instance of the computer
	    Desktop desktop = Desktop.getDesktop();
	    try {
	        // checks if the desktop action is supported
	        if (desktop.isSupported(Desktop.Action.OPEN)) {
	            // if supported, open the URL using the browse() method
	            desktop.browse(java.net.URI.create(WebsiteUrl));
	        } else {
	            // if the desktop is not supported, proceed to other method
	            // using the command
	            Process p;
	            try {
	                p = Runtime.getRuntime().exec("cmd /c start " + WebsiteUrl);
	            } catch (IOException ex) {
	                Logger.getLogger(DMin.class.getName()).log(Level.SEVERE, null, ex);
	            }
	        }
	    } catch (IOException ex) {
	        Logger.getLogger(DMin.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	
	private void UrlOpenerWithAutoLogin() {
		
		try {
			//facebook
			if(WebsiteUrl.equals("https://www.facebook.com/")) {
				System.setProperty("webdriver.chrome.whitelistedIps","");
				System.setProperty("webdriver.chrome.driver",CurrentPath+"\\chromedriver.exe");  
				WebDriver driver = new ChromeDriver();
				
				driver.get(WebsiteUrl);
				driver.findElement(By.id("email")).sendKeys(Username);
				driver.findElement(By.id("pass")).sendKeys(aes.decrypt(Password));
				
				WebDriverWait myDynamicElement = (new WebDriverWait(driver,8));
				driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div/div/div[2]/div/div[1]/form/div[2]/button")).click();
				
				String url = driver.getCurrentUrl();
				
			}
				
			//linkedin
			else if(WebsiteUrl.equals("https://www.linkedin.com/login")) {
				System.setProperty("webdriver.chrome.whitelistedIps","");
				System.setProperty("webdriver.chrome.driver",CurrentPath+"\\chromedriver.exe");  
				WebDriver driver = new ChromeDriver();
				
				driver.get(WebsiteUrl);
				driver.findElement(By.id("username")).sendKeys(Username);
				driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(aes.decrypt(Password));
				
				WebDriverWait myDynamicElement = (new WebDriverWait(driver,10));
				
				driver.findElement(By.xpath("/html/body/div/main/div[2]/div[1]/form/div[3]/button")).click();
			}
			else {
				
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,17));
				UIManager.put("OptionPane.minimumSize",new Dimension(385,80)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,"The program supports automatic login only for:\n\n1.Facebook\n2.Linkedin\n\n Thanks.");
			}
		}
		catch (Exception ex) {
			UIManager.put("OptionPane.minimumSize",new Dimension(385,80)); //setting the preferred size of JOptionPane
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
			JOptionPane.showMessageDialog(null, "Unfortunately there is a problem logging in to the site.\nPlease report to the creators.\n Thanks","ERROR_MESSAGE",JOptionPane.ERROR_MESSAGE);
		} 
	}
	
	/**
	 * A function that creats the clock at the main panel.
	 */
    Runnable runnable = new Runnable() { 
        @Override
        public void run() {
          while (true) {

        	  CurrentTimeLabel.setText(getCurrentTimeString());//getting current time every sec.
            try {
              Thread.sleep(1000);//Delay of a second
            }
            catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
    };
}
