
package CalculateData;
import LoanGraph.GraphingData;
import LoanTable.TableClass;
public class CalculatorClass { 
	
	double[] GraphData ;
	
	String[] columnNames = {"Payment","Interest","Balance"}; // Column Names for the Table
	Object[][] data ;
	 
	 Double Interest;
     Double Amount;
     Double Balance;
     Double EMI;
	 Double Term1;
	 public void initComponents(){
    }
    public void TableData(Double Principle, Double Term, Double Rate) {
            initComponents();
             
             data = new Object[(int) (Term*12)][3];
             GraphData = new double[(int) (Term*12)];
             Term1=Term;
             // Calculate EMI for given values.
             EMI= Principle*(Rate/1200)*(Math.pow((1+Rate/1200),(Term*12))/((Math.pow((1+Rate/1200),(Term*12))-1)));
             
                      
            int k =0; 
           
            for(int i =0; i<(Term*12);i++)
            {

            	Interest = Principle*(Rate/1200);         // Calculating Interest for each month.
            	Interest= Math.round(Interest*100)/100.0d;// Round off the values to 2 digits after decimal

            	Amount = EMI - Interest;                   // To find part of Loan Amount to be paid each month.  
            	Amount= Math.round(Amount*100)/100.0d;

            	Balance = Principle - Amount;               // Balance left in each month
            	Balance= Math.round(Balance*100)/100.0d;

            	k=k+1;
            	Principle = Balance;                      // Here , we use concept of Compound Interest so we equate Balance to Principle 
            	Principle= Math.round(Principle*100)/100.0d;

            	if(i==(Term*12-1)) // final month Balance will be zero. I did manually because all values are type Double. So final Month will not be exactly zero. 
            	{
            		Balance=0.0;
	
            		Principle=0.0;
            	}
           

            	String Interest_str = Interest.toString();
            	String Balance_str = Balance.toString();
            	data[i][0] =  k;
            	data[i][1] =  Interest_str;
            	data[i][2] =  Balance_str;
            	
            	GraphData[i] = Balance; // Y - axis  Values of the Graph
            	
          
            }  
           
    }
    
      public double[] getGraphData() // Obtaining values to be plotted in the Graph.
     	{
    	  initComponents();
    	  return GraphData;
     	}
      
      public String getEMI() // returns EMI to be displayed on Home Frame
   	{
  	  initComponents();
  	  EMI= Math.round(EMI*100)/100.0d;
  	  String S1 =Double.toString(EMI);
  	  String S2 = " EMI is $ " + S1;
  	  return S2;
   	}
   
      public void callTable()
     {
    	  initComponents();
    	  TableClass te = new TableClass(data,columnNames); // TableClass is a constructor used to generate frame for Table. 
    	  te.showTable(); 													// data -> contains element values of the Table
          
     }
      public void callGraph()
      {
    	  initComponents();
    	  GraphingData gd = new GraphingData(GraphData,Term1*12); // GraphingData is a constructor used to initialize values to be plotted in the graph to a new Variable . 
    	  												//GraphData -> contains values to be plotted in the Graph.
          
      }
}