package PasswordManager;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.Window.Type;
import java.awt.Font;
import java.awt.Image;
import java.awt.Dimension;

/**
 * This is the SplashScreen class who extends JFrame 
 * and Responsible for the Loading screen when entering to the program.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class SplashScreen  extends JFrame {
	
		//*********** Initialization of the components ***********
	JPanel Panel = new JPanel();
	JProgressBar LoadingBar = new JProgressBar();
	JLabel LoadingValue = new JLabel("0 %");
	JLabel BackGroundImage = new JLabel("");
	JLabel DoctorsIcon = new JLabel("");
	JLabel LoadingLabel = new JLabel("Loading...");
	JLabel HospitalName = new JLabel("Password Manager");
	WelcomeFrame MainPage ;
	JLabel IconImage ;
		
	static String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
	/**
	 * constructor
	 * setting the components
	 *
	 * @param userID - the id of the user
	 */
	public SplashScreen(String userID) {
		super();
		setLayout(new BorderLayout());
		setUndecorated(true);
		setSize(900, 500);
		setLocationRelativeTo(null);//open the window in the center of the screen
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);			
		setVisible(true);
		    
		Panel.setLayout(null);
			
		ImageIcon imageIcon = new ImageIcon(CurrentPath+"\\src\\test\\resources\\SplashScreenBackground.jpg"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(900,500,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		IconImage = new JLabel(imageIcon);
		IconImage.setBounds(0,0,900,500);

		ImageIcon imageIcon2 = new ImageIcon(CurrentPath+"\\src\\test\\resources\\password-manager-icon.jpg"); // load the image to a imageIcon
		Image image2 = imageIcon2.getImage(); // transform it 
		Image newimg2 = image2.getScaledInstance(425,311,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon2 = new ImageIcon(newimg2);  // transform it back
		DoctorsIcon = new JLabel(imageIcon2);
		DoctorsIcon.setBounds(215,29,425,311);
			
		Panel.add(DoctorsIcon);
		
		LoadingBar.setBounds(0, 479, 900, 21);
		LoadingBar.setForeground(Color.ORANGE);
		Panel.add(LoadingBar);
			
			
		LoadingLabel.setForeground(Color.WHITE);
		LoadingLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		LoadingLabel.setBounds(12, 455,200, 21);
		Panel.add(LoadingLabel);
		
		HospitalName.setForeground(Color.WHITE);
		HospitalName.setFont(new Font("Segoe UI", Font.BOLD, 60));
		HospitalName.setBounds(180,360,900,80);
		Panel.add(HospitalName);
			
		LoadingValue.setFont(new Font("Segoe UI", Font.PLAIN,15));
		LoadingValue.setForeground(Color.WHITE);
		LoadingValue.setBounds(846, 455, 60, 17);
		Panel.add(LoadingValue);
		
		Panel.add(IconImage);
			
		add(Panel);
		
		Thread t = new Thread(runnable);
	    t.start();//starting the thread
	    
		MainPage = new WelcomeFrame(userID);	
	}
	/**
	 * A function that responsible for the running of the loading screen.
	 */
	 Runnable runnable = new Runnable() {
		   @Override
		   public void run() {
		        try {
					for(int i =0;i<=100;i++) {
						Thread.sleep(50);
								
						LoadingValue.setText(i  + "%");
								
						if(i==10) {
							LoadingLabel.setText("Turning On Modules...");
							}
						if(i==20) {
							LoadingLabel.setText("Loading Modules...");
							}
						if(i==50) {
							LoadingLabel.setText("Connecting to Database...");
							}
						if(i==70) {
							LoadingLabel.setText("Connection Successful...");
							}
						if(i==80) {
							LoadingLabel.setText("Launching Application...");
							}
								
							LoadingBar.setValue(i);
						}	
		          }
		          catch (InterruptedException e) {
		           e.printStackTrace();
		           }
		    	 
		        setVisible(false);
		        MainPage.setVisible(true);
		        dispose();
		        }     
		  };
}
