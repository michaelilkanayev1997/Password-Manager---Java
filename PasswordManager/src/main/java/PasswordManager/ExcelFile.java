package PasswordManager;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * This is the Excel File class who is responsible for writing and reading from an Excel file.
 * 
 * @authors Michael Ilkanayev - 318216678 , Vladimir Davidzon -  317648632
 */
public class ExcelFile {
	static String fileDictName = "";
	
	static String CurrentPath = System.getProperty("user.dir"); //getting Present Project Directory
	
	/**
	 * Constructor
	 */
	ExcelFile(){

	}
	
	/**
	 * A function that checks if the user exists in the system (in the excel file).
	 * @param Username - user name.
	 * @param Password - password of the user.
	 * @return 1 if Username and Password are correct/-1 if Username correct and Password is incorrect.
	 * @throws IOException
	 */
	public int ReadExcelFile(String Username,String Password ) throws IOException {
		int resultOfreadingExcel =0;
		
		File excelFile = new File(CurrentPath +"\\Users.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//iterate on rows
		Iterator<Row> rowIt = sheet.iterator();
		
		while(rowIt.hasNext()) {
			
			Row row = rowIt.next();
			
			Iterator<Cell> cellIterator = row.cellIterator();
			
			while(cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				
				if((cell.toString()).equals(Username)) {
					cell = cellIterator.next();

					if((cell.toString()).equals(Password))
						resultOfreadingExcel = 1; // if Username and Password are correct return 1
					else
						resultOfreadingExcel = -1; // if Username correct and Password is incorrect return -1
				}		
			}	
		}
		workbook.close();
		fis.close();
		return resultOfreadingExcel;
	}
	
	/**
	 * A function that checks if the user's ID exists in the system (in the excel file).
	 * @param UserID - user id.
	 * 
	 * @return 1 if User ID exists in the system.
	 * @throws IOException
	 */
	public int ReadExcelFileID(String UserID ) throws IOException {
		int resultOfreadingExcel =0;
		
		File excelFile = new File(CurrentPath +"\\Users.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//iterate on rows
		Iterator<Row> rowIt = sheet.iterator();
		
		while(rowIt.hasNext()) {
			
			Row row = rowIt.next();
			
			XSSFCell cell = (XSSFCell) row.getCell(0); //getting first column
			
			if((cell.toString()).equals(UserID)) {
				resultOfreadingExcel = 1;// if ID exists 
			}	
		}
		workbook.close();
		fis.close();
		return resultOfreadingExcel;
	}

	/**
	 * A function that Write into existing excel (xlsx) file.
	 * The function receives data of a new user and creates a new user in the excel file.
	 * @param Username
	 * @param Password
	 * @param UserID
	 * @return int 0
	 */
	public int WriteToExcelFile(String Username,String Password ,String UserID) {

        // Creating file object of existing excel file
        File xlsxFile = new File(CurrentPath +"\\Users.xlsx");

        try {
            //Creating input stream
			FileInputStream inputStream = new FileInputStream(xlsxFile);
             
            //Creating workbook from input stream
            Workbook workbook = WorkbookFactory.create(inputStream);
 
            //Reading first sheet of excel file
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
 
            //Getting the count of existing records
            int rowCount = sheet.getLastRowNum();
            
            //Creating new row from the next row count
            Row row = sheet.createRow(++rowCount);

            
            for(int columnCount = 0;columnCount <3;columnCount++) {
   
	            //Creating new cell and setting the value
	            Cell cell = row.createCell(columnCount);
	            
	            if(columnCount==0)
	            	cell.setCellValue((String)UserID);
	            else if (columnCount==1)
	            	cell.setCellValue((String)Username);
	            else 
	            	cell.setCellValue((String)Password);
            }
            //Close input stream
            inputStream.close();
 
            //Crating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(xlsxFile);
            workbook.write(os);
             
            //Close the workbook and output stream
            workbook.close();
            os.close();
             
            System.out.println("Excel file has been updated successfully.");
            
        } catch (EncryptedDocumentException | IOException e) {
            System.err.println("Exception while updating an existing excel file.");
            e.printStackTrace();
        }
		return 0; 
	}
	public int CreateExcelDataBase(String[] WebsiteDetails,String UserID) throws IOException {
		
		Workbook wb = new XSSFWorkbook();
		org.apache.poi.ss.usermodel.Sheet sheet1 = wb.createSheet("Data base");
		FileOutputStream fileOut = new FileOutputStream(CurrentPath+"\\Data_base"+"\\"+UserID+"-Data.xlsx");
		wb.write(fileOut);
		fileOut.close();
		wb.close();
			
		// Creating file object of existing excel file
		File xlsxFile = new File(CurrentPath+"\\Data_base"+"\\"+UserID+"-Data.xlsx");

		try {
		//Creating input stream
		FileInputStream inputStream = new FileInputStream(xlsxFile);
		             
		//Creating workbook from input stream
		Workbook workbook = WorkbookFactory.create(inputStream);
		 
		//Reading first sheet of excel file
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
		            
		sheet.setDefaultColumnWidth(35);
		 
		//Creating new row from the next row count
		          
		int rowCount =0 ;
		String[] HeadlinesArr = {"Website:","Username:","Password:"};
		            		                        	
		Row row1 = sheet.createRow(rowCount);
		            	
		for(int columnCount = 0;columnCount <HeadlinesArr.length ;columnCount++) {
			//Creating new cell and setting the value
			Cell cell = row1.createCell(columnCount);

			cell.setCellValue(HeadlinesArr[columnCount]);
		} 

		rowCount++;
		Row row2 = sheet.createRow(rowCount);

		for(int columnCount = 0;columnCount <WebsiteDetails.length ;columnCount++) {
			//Creating new cell and setting the value
			Cell cell = row2.createCell(columnCount);

			cell.setCellValue(WebsiteDetails[columnCount]);
		} 

		 //Close input stream
        inputStream.close();

        //Crating output stream and writing the updated workbook
        FileOutputStream os = new FileOutputStream(xlsxFile);
        workbook.write(os);
         
        //Close the workbook and output stream
        workbook.close();
        os.close();
         
        System.out.println("\nExcel file has been updated successfully.");
		
		return 1;
		}
         catch (EncryptedDocumentException | IOException e) {
            System.err.println("\nException while opening a new excel file.");
            e.printStackTrace();
         }
		
		return 0;
	}
	
	public int AddToExistingExcelFile(String[] WebsiteDetails,String UserID) {

        // Creating file object of existing excel file
        File xlsxFile = new File(CurrentPath+"\\Data_base"+"\\"+ UserID+"-Data.xlsx");

        try {
            //Creating input stream
			FileInputStream inputStream = new FileInputStream(xlsxFile);
             
            //Creating workbook from input stream
            Workbook workbook = WorkbookFactory.create(inputStream);
 
            //Reading first sheet of excel file
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
 
            //Getting the count of existing records
            int rowCount = sheet.getLastRowNum();
            
            //Creating new row from the next row count
            Row row = sheet.createRow(++rowCount);

            
            for(int columnCount = 0;columnCount <3;columnCount++) {
   
	            //Creating new cell and setting the value
	            Cell cell = row.createCell(columnCount);
	            
	            cell.setCellValue(WebsiteDetails[columnCount]);
            }
            //Close input stream
            inputStream.close();
 
            //Crating output stream and writing the updated workbook
            FileOutputStream os = new FileOutputStream(xlsxFile);
            workbook.write(os);
             
            //Close the workbook and output stream
            workbook.close();
            os.close();
             
            System.out.println("Excel file has been updated successfully.");
            return 1;
            
        } catch (EncryptedDocumentException | IOException e) {
            System.err.println("Exception while updating an existing excel file.");
            e.printStackTrace();
        }
		return 0; 
	}
	
	public int RemoveFromExcelFile(String Website,String Username,String UserID) throws IOException {
		int resultOfreadingExcel =0;

		// Creating file object of existing excel file
		File xlsxFile = new File(CurrentPath+"\\Data_base"+"\\"+ UserID+"-Data.xlsx");

		try {
			//Creating input stream
			FileInputStream inputStream = new FileInputStream(xlsxFile);

			//Creating workbook from input stream
			Workbook workbook = WorkbookFactory.create(inputStream);

			org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);//Reading first sheet of excel file
			int rowToDeleteIndex = 0;// The row number you want to delete

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {

				Row row = sheet.getRow(i);

				XSSFCell cell1 = (XSSFCell) row.getCell(0); //getting first column
				XSSFCell cell2 = (XSSFCell) row.getCell(1); //getting second column

				if((cell1.toString()).equals(Website)) {

					if((cell2.toString()).equals(Username)) {
						resultOfreadingExcel = 1; // if Username and Password are correct return 1
						rowToDeleteIndex = i;
					}
					else
						resultOfreadingExcel = -1; // if Username correct and Password is incorrect return -1
				}
			}
			if(resultOfreadingExcel==1) {
				int lastRowNum = sheet.getLastRowNum();
				if (rowToDeleteIndex >= 0 && rowToDeleteIndex < lastRowNum) {
					sheet.shiftRows(rowToDeleteIndex + 1, lastRowNum, -1);
				}
				if (rowToDeleteIndex == lastRowNum) {
					XSSFRow removingRow=(XSSFRow) sheet.getRow(rowToDeleteIndex);
					if(removingRow != null) {
						sheet.removeRow(removingRow);
					}
				}
			}
			//Close input stream
			inputStream.close();

			//Crating output stream and writing the updated workbook
			FileOutputStream os = new FileOutputStream(xlsxFile);
			workbook.write(os);

			//Close the workbook and output stream
			workbook.close();
			os.close();

			System.out.println(resultOfreadingExcel);
			System.out.println("Excel file has been updated successfully.");
			return rowToDeleteIndex;
		}
		catch (EncryptedDocumentException | IOException e) {
			System.err.println("Exception while updating an existing excel file.");
			e.printStackTrace();
		}
		return 0;
	}	
	
	
	public void ImportExcelFileToJtable(String UserID ,Table table) throws IOException {
		int resultOfreadingExcel =0;
		
		File excelFile = new File(CurrentPath+"\\Data_base"+"\\"+UserID+"-Data.xlsx");
		FileInputStream fis = new FileInputStream(excelFile);
		
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheetAt(0);
		
		//iterate on rows
		Iterator<Row> rowIt = sheet.iterator();
		rowIt.next();
		while(rowIt.hasNext()) {
			
			Row row = rowIt.next();
			
			XSSFCell cell1 = (XSSFCell) row.getCell(0); //getting first column
			XSSFCell cell2 = (XSSFCell) row.getCell(1); //getting first column
			XSSFCell cell3 = (XSSFCell) row.getCell(2); //getting first column
			
			String Website = cell1.toString();
			String Username = cell2.toString();
			String Password = cell3.toString();
	
			DefaultTableModel model = (DefaultTableModel) table.table.getModel();
		    model.addRow(new Object[]{Website,Username ,Password});
				
		}
		workbook.close();
		fis.close();
		
	}
}