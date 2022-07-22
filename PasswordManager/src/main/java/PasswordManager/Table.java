package PasswordManager;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Table extends JPanel{


	JTable table;           
	JScrollPane scrollpane;  
	DefaultTableModel model;
	
	Table(){
		//setting main Panel
		super();
		setLayout(null);
		setSize(625,530);
		setVisible(true);
		
		scrollpane = new JScrollPane();
		scrollpane.setBounds(0,0,680,530);
		
		table = new JTable() {
			//Disable user edit in JTable
			public boolean isCellEditable(int row, int column){ 
			     return false;
			}
		};
		table.setFont(new Font("Tahoma", Font.BOLD,15));
		table.setBackground(new Color(176,196,222));
		table.setRowHeight(35);
	    table.setBackground(Color.white);
	    table.setForeground(Color.black);
	    table.getTableHeader().setReorderingAllowed( false );
	    //fixed.getTableHeader().setResizingAllowed( false );
	    
		model = new DefaultTableModel();
		Object[] header = {"Website","Username","Password"};
	    
		model.setColumnIdentifiers(header);
		table.setModel(model);
		scrollpane.setViewportView(table);
		
		JTableHeader tableHeader = table.getTableHeader();
	    tableHeader.setBackground(new Color(250,250,222));
	    tableHeader.setForeground(Color.BLACK);
	    Font headerFont = new Font("Tahoma", Font.PLAIN,20);
	    tableHeader.setFont(headerFont);
		
		add(scrollpane);
	}
	
	
}
