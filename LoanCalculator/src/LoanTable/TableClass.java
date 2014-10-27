package LoanTable;
import javax.swing.*;
public class TableClass { 
	
	JTable jTable1 ;

	Object[][] data1;
	String[] columnNames1;
	
	 

	 public TableClass(Object[][] data, String[] columnNames) // TableClass is a constructor used to generate frame for Table.
	 {
		// TODO Auto-generated method stub
		
		
		
		
		data1 = data;
		columnNames1 = columnNames;
	 }
		public void showTable(){
			 jTable1 = new JTable(data1, columnNames1);  // creates frame for Table
		        
		        JFrame frame = new JFrame();
		        frame.add(  new JScrollPane(jTable1) ); // adds scrollpane to Table Frame
		        frame.pack();
		        frame.setVisible( true );
		}
	 
       
	 
   
    
	
}

