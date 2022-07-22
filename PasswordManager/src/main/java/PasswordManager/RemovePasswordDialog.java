package PasswordManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class RemovePasswordDialog extends JDialog implements ActionListener{

	//************** JLabel's *******************
	JLabel lbTitle = new JLabel("Remove Password");
	JLabel lbWebsite = new JLabel("Website: ");
	JLabel Username = new JLabel("Username:");
	JLabel lbIformation = new JLabel("please enter the Website and Username you want to remove.");
	
	//******************* Text Field's ************************
	JTextField WebsiteTxt = new JTextField();
	JTextField PasswordTxt = new JTextField();
	JTextField UsernameTxt = new JTextField();
	
	//******************* Buttons Field's ************************
	JButton submit,cancel;
	
	// create a separator
    JSeparator separator = new JSeparator();
		
	static String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
	
	ExcelFile excelfile = new ExcelFile();
	
	String username ;
	Table table;
	JLabel IconImage,IconImage2;
	
	RemovePasswordDialog(String Userid,Table table){

		//settings AddPasswordDialog
		setTitle("Remove Password");//Set title
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

		lbTitle.setBounds(129,20,350,40);
		lbTitle.setFont(new Font("Tahoma",Font.BOLD,35));
		lbTitle.setForeground(Color.BLACK);
		separator.setBounds(120,65,340,15);
		lbIformation.setBounds(15,130,560,30);
		lbIformation.setFont(new Font("Verdana",Font.BOLD,15));
		lbIformation.setForeground(Color.BLACK);	
		lbWebsite.setBounds(15,215,300,30);
		lbWebsite.setFont(new Font("Verdana",Font.BOLD,15));
		lbWebsite.setForeground(Color.BLACK);
		WebsiteTxt.setBounds(150,215,180,30);
		WebsiteTxt.setFont(new Font("Ariel",Font.BOLD,15));
		Username.setBounds(15,300,300,30);
		Username.setFont(new Font("Verdana",Font.BOLD,15));
		Username.setForeground(Color.BLACK);
		UsernameTxt.setBounds(150,300,180,30);
		UsernameTxt.setFont(new Font("Ariel",Font.BOLD,15));

		//adding components
		add(submit);
		add(cancel);
		add(lbTitle);
		add(separator);
		add(IconImage);
		add(lbWebsite);
		add(lbIformation);
		add(Username);
		add(WebsiteTxt);
		add(PasswordTxt);
		add(UsernameTxt);
		add(IconImage2);

		// creating button events
		submit.addActionListener(this);
		cancel.addActionListener(this);
		
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
     * A function that activated when a button is pressed.
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancel) {
			dispose(); //Close the dialog window
		}
		else if(e.getSource()==submit) {
			//--------- Saving the user input ---------//
			String Website = WebsiteTxt.getText();
			String Username = UsernameTxt.getText();
			
			try {
				if(!isURLCorrect(Website)) {
					throw new Exception("The URL of the website is not in a valid form,\nPlease try again.");
				}
				int RowNumToDelete;
				RowNumToDelete = excelfile.RemoveFromExcelFile(Website,Username,username);
				
				if(RowNumToDelete!=0) {
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 16));
					UIManager.put("OptionPane.minimumSize",new Dimension(390,60)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"The Password has been deleted\n successfully, thanks.");
					
					DefaultTableModel model = (DefaultTableModel) table.table.getModel();
					model.removeRow(RowNumToDelete-1);
					
				}
				else {
					UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
					UIManager.put("OptionPane.minimumSize",new Dimension(390,50)); //setting the preferred size of JOptionPane
					JOptionPane.showMessageDialog(null,"Password not found, please try again !","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				}
				
				dispose();//Close the dialog window
			}
			catch(Exception e1){
				UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
				UIManager.put("OptionPane.minimumSize",new Dimension(390,50)); //setting the preferred size of JOptionPane
				JOptionPane.showMessageDialog(null,e1.getMessage(),"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
