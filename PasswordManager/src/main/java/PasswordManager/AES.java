package PasswordManager;

import java.awt.Dimension;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.commons.codec.binary.Base64;

public class AES {

	private static final String ALGORITHM = "AES";
    private static byte[] keyValue =null;
    private static Key key;
    private String SecurityKey;
    
	AES(){
		
		try {	
			
			// File path is passed as parameter
	        File file = new File("D:\\KeyAES.txt");
	        if (!file.canRead()) 
	        	 throw new Exception("please put the USB with the Key !");

	        // Creating an object of BufferedReader class
	        BufferedReader br = new BufferedReader(new FileReader(file));
	 
	        // Declaring a string variable
	       
	        // Condition holds true till
	        // there is character in a string
	        while ((SecurityKey = br.readLine()) != null) 
	 

	        keyValue = SecurityKey.getBytes();	
			this.key = generateKey();
			
		} catch (Exception e) {
			UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD,16));
			UIManager.put("OptionPane.minimumSize",new Dimension(390,50)); //setting the preferred size of JOptionPane
			JOptionPane.showMessageDialog(null,e.getMessage(),"ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
	}
	
    private static Key generateKey() throws Exception {
        Key key = new SecretKeySpec(keyValue, ALGORITHM);
        return key;
    }
    public static String encrypt(String valueToEnc) throws Exception {
    	 
        
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
  
        byte[] encValue = cipher.doFinal(valueToEnc.getBytes());
        byte[] encryptedByteValue = new Base64().encode(encValue);
        System.out.println("Encrypted Value :: " + new String(encryptedByteValue));
  
        return new String(encryptedByteValue);
    }
    
    public static String decrypt(String encryptedValue) throws Exception {
        // Key key = generateKey();
         Cipher cipher = Cipher.getInstance(ALGORITHM);
         cipher.init(Cipher.DECRYPT_MODE, key);
          
         byte[] decodedBytes = new Base64().decode(encryptedValue.getBytes());
  
         byte[] enctVal = cipher.doFinal(decodedBytes);
         
         System.out.println("Decrypted Value :: " + new String(enctVal));
         return new String(enctVal);
     }
	
}
