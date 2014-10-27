/* GUI class will initialize all required fields, labels, Buttons, Frames and Panels. 
   GUI accepts Loan Value, Loan Term , Rate Percentage, Start Date from the user. 
   GUI class also validates the given data in the fields.
   It generates errors when any of the field is not in range of the input. 
   It has two buttons "Loan Table" and "Loan Graph"
   
   Depending on the given values and button pressed, either Table or Graph or both is are generated in separate frame
   
   GUI class will also display EMI , Number of Installments and End Date on the home frame.
   
   When Table Button is pressed, values in the fields are passed to CalculatorDemo class.
   CalculatorDemo class will calculate the required data and passes it to TableExample class which generates a Table.
   
   When Graph Button is pressed, values in the fields are passed to CalculatorDemo class.
   CalculatorDemo class will calculate the required data and passes it to GraphExample class which draw a graph.
   GUI class will generate a frame for Graph. */


/* Press TAB or use mouse to enter values for next field, Press Enter after providing all required Values */

import javax.swing.*;
import CalculateData.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;    /* Importing classes for validating and displaying Date on Home Frame */
import java.text.SimpleDateFormat;
import java.util.Calendar;
import LoanGraph.*;
public class GUI implements  ActionListener{

    JPanel textPanel, panelForTextFields, completionPanel;
    JLabel titleLabel,LoanAmntLabel,LoanTermLabel,DateLabel1, RateLabel, DateLabel,EMIlabel,InstallmentsLabel;
    JTextField LoanAmntField,LoanTermField,RatePercentageField, DateField;
    JButton TableButton , GraphButton;
   
    
    
    String Loan ;
    String Loan1 = null;
    double LoanValue;
    
    String Term ;
    String Term1 = null;
    double TermValue;
   
    String Rate ;
    String Rate1 = null;
    double RateValue;
   
    String Date ;
    double EMI;
    double Installments;
    
    //set the format to use as a constructor argument
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    
    public JPanel createContentPane (){

        // We create a bottom JPanel to place everything on.
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        titleLabel = new JLabel("Loan Amortization Calculator");
        titleLabel.setLocation(80,0);
        titleLabel.setSize(290, 30);
        titleLabel.setHorizontalAlignment(0);
        totalGUI.add(titleLabel);

        
        
        // Creation of a Panel to contain the JLabels
        textPanel = new JPanel();
        textPanel.setLayout(null);
        textPanel.setLocation(10, 55);
        textPanel.setSize(200, 240);
        totalGUI.add(textPanel);

        // Loan Amount Label
        LoanAmntLabel = new JLabel("Loan Amount ($)");
        LoanAmntLabel.setLocation(80, 0);
        LoanAmntLabel.setSize(70, 60);
        LoanAmntLabel.setSize( LoanAmntLabel.getPreferredSize() );
        LoanAmntLabel.setHorizontalAlignment(4);
        textPanel.add(LoanAmntLabel);
       
        
        // Loan Term Label
        LoanTermLabel = new JLabel("Loan Term (years)");
        LoanTermLabel.setLocation(80, 60);
        LoanTermLabel.setSize(70, 60);
        LoanTermLabel.setSize(  LoanTermLabel.getPreferredSize() );
        LoanTermLabel.setHorizontalAlignment(4);
        textPanel.add(LoanTermLabel);
        
        // Rate Percentage Label
        RateLabel = new JLabel("Rate Percentage (%)");
        RateLabel.setLocation(80, 120);
        RateLabel.setSize(70, 60);
        RateLabel.setSize( RateLabel.getPreferredSize() );
        RateLabel.setHorizontalAlignment(4);
        textPanel.add(RateLabel);
       
        
        	// Date Label
        DateLabel = new JLabel("Start Date");
        DateLabel1 = new JLabel("(MM-dd-yyyy format)");
        DateLabel.setLocation(80, 180);
        DateLabel1.setLocation(50, 200);
        DateLabel.setSize(70, 60);
        DateLabel1.setSize(70, 60);
        DateLabel.setSize( DateLabel.getPreferredSize() );
        DateLabel1.setSize( DateLabel1.getPreferredSize() );
        DateLabel.setHorizontalAlignment(4);
        DateLabel1.setHorizontalAlignment(4);
        textPanel.add(DateLabel);
        textPanel.add(DateLabel1);
        
        
        // TextFields Panel Container
        panelForTextFields = new JPanel();
        panelForTextFields.setLayout(null);
        panelForTextFields.setLocation(140, 50);
        panelForTextFields.setSize(300, 270);
        totalGUI.add(panelForTextFields);

        // LoanAmnt Text field
        LoanAmntField = new JTextField(8);
        LoanAmntField.setLocation(80, 0);
        LoanAmntField.setSize(100, 30);
        LoanAmntField.addActionListener(this);
        panelForTextFields.add(LoanAmntField);

        // LoanTerm Text field
        LoanTermField = new JTextField(8);
        LoanTermField.setLocation(80, 60);
        LoanTermField.setSize(100, 30);
        LoanTermField.addActionListener(this);
        panelForTextFields.add(LoanTermField);
     
        // RatePercentage Text field
        RatePercentageField = new JTextField(8);
        RatePercentageField.setLocation(80, 120);
        RatePercentageField.setSize(100, 30);
        RatePercentageField.addActionListener(this);
        panelForTextFields.add(RatePercentageField);
       
        // Date Text field
        DateField = new JTextField(8);
        DateField .setLocation(80, 180);
        DateField .setSize(100, 30);
        DateField.addActionListener(this);
        panelForTextFields.add(DateField );

        

        // Button for generating Table 
        TableButton = new JButton("Loan Table");
        TableButton.setLocation(80, 320);
        TableButton.setSize(80, 50);
        TableButton.setSize( TableButton.getPreferredSize() );
        TableButton.addActionListener(this);
        totalGUI.add(TableButton);
        
        // Button for generating Graph
        GraphButton = new JButton("Loan Graph");
        GraphButton.setLocation(220, 320);
        GraphButton.setSize(80, 50);
        GraphButton.setSize( GraphButton.getPreferredSize() );
        GraphButton.addActionListener(this);
        totalGUI.add(GraphButton);
        
        //Initially Table Button and Graph Button are disabled until all fields are valid.
        TableButton.setEnabled(false);
    	GraphButton.setEnabled(false);
    	
        totalGUI.setOpaque(true);    
        return totalGUI;
    }

    public boolean validateData()
    {
    	// Obtaining field values from each field
    	Loan = LoanAmntField.getText();
    	Term = LoanTermField.getText();
    	Rate = RatePercentageField.getText();
    	Date = DateField.getText();
    	
    /* ------------------------------- Validating Loan Value Field  ------------------------------- */		
    	
    	StringBuilder myNumbers = new StringBuilder(); // This is used to obtain only numbers from the given field in the text
        
        for (int i = 0; i < Loan.length(); i++) { 
    	    if (Character.isLetter(Loan.charAt(i)))	
    	    {
    	    	LoanAmntField.setBackground(Color.red); // If user enters value other than number, background is set to red.
        	    
        	    
    	    }
        }
        
	    
     // GUI accepts commas (,) also between the entered values. Example : $10,000 is taken as $10000. 
	    
        for (int i = 0; i < Loan.length(); i++) {
	    	 
	    	 if (Character.isDigit(Loan.charAt(i))) 
	            myNumbers.append(Loan.charAt(i));
	    	 
	    }
	    
	    
	    Loan1 = myNumbers.toString();
	    
	    LoanValue = Double.parseDouble(Loan1);
	   
	    // If LoanValue > 0 & LoanValue <= 999999 then a dialog is generated asking user to enter values between LoanValue > 0 & LoanValue <= 999999
	    
	    if(((LoanValue > 0 & LoanValue <= 999999 ) ))
        {
        	LoanAmntField.setBackground(Color.green);
        }
	    else if(LoanValue < 0 )
	    {
	    	LoanAmntField.setBackground(Color.red);
	    	JFrame frame= new JFrame();
            JOptionPane.showMessageDialog(frame,
            	    "Enter Value Between 0.1 and 999999",
            	    "Inane warning",
            	    JOptionPane.WARNING_MESSAGE);
            LoanAmntField.setBackground(Color.white);
	    }
	    
	    	else
        {
        	LoanAmntField.setBackground(Color.red);
        	
        	
            
            JFrame frame= new JFrame();
            JOptionPane.showMessageDialog(frame,
            	    "Enter Value Between 0.1 and 999999",
            	    "Inane warning",
            	    JOptionPane.WARNING_MESSAGE);
            LoanAmntField.setBackground(Color.white);
        	
            
        	
        }
  
	    /* ------------------------------- Validating Loan Term Field  ------------------------------- */	
	    
	   
	   
        StringBuilder myNumbers1 = new StringBuilder(); // This is used to obtain only numbers from the given field in the text
	   
        for (int i = 0; i < Term.length(); i++) { 
    	    if (Character.isLetter(Term.charAt(i)))	
    	    {
    	    	LoanTermField.setBackground(Color.red); // If user enters value other than number, background is set to red.
        	    
    	    }
        }
       
      
        
        for (int i = 0; i < Term.length(); i++) {
	    	 
	    	 if (Character.isDigit(Term.charAt(i))) 
	            myNumbers1.append(Term.charAt(i)); 
	       
	        	
	    }
	   
        Term1 = myNumbers1.toString();
        TermValue = Double.parseDouble(Term1);
      
      // If TermValue > 0 & TermValue <= 100000 then a dialog is generated asking user to enter values between TermValue > 0 & TermValue <= 100000
        
        if((TermValue > 0 & TermValue <= 100000) & (LoanTermField.getBackground()!=Color.red))
        {
        	LoanTermField.setBackground(Color.green);
        }
       
        else
        {
        	LoanTermField.setBackground(Color.red);
        	
        	JFrame frame= new JFrame();
            JOptionPane.showMessageDialog(frame,
            	    "Enter Value Between 0.1 and 100",
            	    "Inane warning",
            	    JOptionPane.WARNING_MESSAGE);
        }
        
        /* -------------------------------Validating Loan Rate Field  ------------------------------- */	    
       
        
        
	    StringBuilder myNumbers2 = new StringBuilder(); // This is used to obtain only numbers from the given field in the text
	   
	    for (int i = 0; i < Rate.length(); i++) { 
    	    if (Character.isLetter(Rate.charAt(i)))	
    	    {
    	    	RatePercentageField.setBackground(Color.red); // If user enters value other than number, background is set to red.
        	    
    	    }
        }
	    
	    for (int i = 0; i < Rate.length(); i++) {
	    	 
	    	 if (Character.isDigit(Rate.charAt(i))) 
	            myNumbers2.append(Rate.charAt(i));
	       
	        	
	    }
	   
	    Rate1 = myNumbers2.toString();
        RateValue = Double.parseDouble(Rate1);
       
       // If RateValue > 0 & RateValue <= 50 then a dialog is generated asking user to enter values between RateValue > 0 & RateValue <= 50
        
        if((RateValue > 0 & RateValue <= 50 ) & (RatePercentageField.getBackground()!=Color.red))
        {
        	RatePercentageField.setBackground(Color.green);
        }
       
        else
        {
        	RatePercentageField.setBackground(Color.red);
        	
        	JFrame frame= new JFrame();
            JOptionPane.showMessageDialog(frame,
            	    "Enter Value Between 0.1 and 50",
            	    "Inane warning",
            	    JOptionPane.WARNING_MESSAGE);
       
        }
    
       
        
        /* ------------------------------- Validating Date ------------------------------- */
        
        if(isValidDate(Date))
        {
        	DateField.setBackground(Color.green);
        	

        }
        else
        {
        	DateField.setBackground(Color.red);
        	
        	JFrame frame= new JFrame();
            JOptionPane.showMessageDialog(frame,
            	    "Enter Valid Date",
            	    "Inane warning",
            	    JOptionPane.WARNING_MESSAGE);
            DateField.setBackground(Color.white);
        
        }
        
        

       
       
        if((LoanAmntField.getBackground() == Color.green) 
    			&& (LoanAmntField.getBackground() == Color.green)&& (RatePercentageField.getBackground() == Color.green))
        	{
        	TableButton.setEnabled(true);
        	GraphButton.setEnabled(true);
        	return true;
  
        	}
        else
        {
        	TableButton.setEnabled(false);
        	GraphButton.setEnabled(false);
        	return false;
        }
        	
    }
     
    private boolean isValidDate(String inDate) {

        if (inDate == null)
          return false;

       

        if (inDate.trim().length() != dateFormat.toPattern().length())
          return false;

        dateFormat.setLenient(false);

        try {
          //parse the inDate parameter
          dateFormat.parse(inDate.trim());
        }
        catch (ParseException pe) {
          return false;
        }
        return true;
      }
   
   /*--------------- Function used in Calculating End Date --------------- */
    
    String getnewDate(String Dt)
    {
    	Calendar cal = Calendar.getInstance();    
        try {
			cal.setTime( dateFormat.parse(Dt));
		} catch (ParseException e1) {
			
			e1.printStackTrace();
		}    
        cal.add( Calendar.DATE, (int)(TermValue*12*24) );    
        String convertedDate=dateFormat.format(cal.getTime());
        
        return convertedDate;
    }
   
    /*--------------- When a button is pressed it is considered as Action Event --------------- */
    
  
    public void actionPerformed(ActionEvent e) {

       if(validateData())
    	   {
    	   		if(e.getSource() == TableButton  )
    	   
    	   		{
                
    	   		CalculatorClass cd = new CalculatorClass();
                cd.TableData(LoanValue,TermValue,RateValue); // Pass the field values to Calculator class
                String EMI = cd.getEMI();
               
          
                Date = DateField.getText();
                
                Date =  getnewDate(Date);  // Calculates End Date  
                   
               //---------------- Displays EMI, Installment Number and End Date on Home Frame----------- // 
                
                titleLabel.setLocation(20,10);
                titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                titleLabel.setForeground(Color.red); 
                titleLabel.setText(EMI +"/" +"Number of Installments is: " + (int)(TermValue*12) +"/"+"End Date is: " + Date);
                titleLabel.setSize( titleLabel.getPreferredSize() );
               
                cd.callTable();
                
                }
            
    	       if(e.getSource() == GraphButton)
    	       
    	       {
             
                
                CalculatorClass cd = new CalculatorClass();
                cd.TableData(LoanValue,TermValue,RateValue); // Pass the field values to Calculator class
                String EMI = cd.getEMI();
               
                
                Date = DateField.getText();
                
                Date=  getnewDate(Date);   // Calculates End Date   
                
              //---------------- Displays EMI, Installment Number and End Date on Home Frame----------- // 
                
                titleLabel.setLocation(20,10);
                titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
                titleLabel.setForeground(Color.red); 
                titleLabel.setText(EMI +"/" +"Number of Installments is: " + (int)(TermValue*12) +"/"+"End Date is: " + Date);
                titleLabel.setSize( titleLabel.getPreferredSize() );
                cd.callGraph();
               
                double[] data =null;
                data=cd.getGraphData(); // Obtains values to be plotted in the graph
                
              //---------------- Creates frame for displaying graph ----------- // 
                JFrame f1 = new JFrame();
                f1.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                f1.add(new GraphingData(data,TermValue*12));
                f1.setSize(400,400);
                f1.setLocation(200,200);
                f1.setVisible(true);
                
                
              }
           
               
        }
       
       else 
				titleLabel.setText("Give valid input"); // If field data is invalid this message is shown
    }
    


    private static void createAndShowGUI() // This is used to generate Home Frame
    
    { 

    	
    	JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("[=] Loan Amortization Calcutator [=]");
        
        GUI demo = new GUI();
        frame.setContentPane(demo.createContentPane());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setVisible(true);
    }
    
    
    
    public static void main(String[] args) // main() function.
    { 
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}