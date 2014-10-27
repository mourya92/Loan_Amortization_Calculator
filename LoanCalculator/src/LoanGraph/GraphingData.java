package LoanGraph;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import javax.swing.*;


public class GraphingData extends JPanel {
    static double[] data =null;
    int Term;
    final int PAD = 20;


     public GraphingData(double[] graphData, double Term1)  // GraphingData is a constructor used to initialize values to be plotted in the graph to a new Variable .
    {
   
		data = graphData;
		Term=(int) Term1;
	}
    
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphic2D = (Graphics2D)g;
        graphic2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        
        // Draw ordinate.
        graphic2D.draw(new Line2D.Double(PAD, PAD, PAD, h-PAD));
       
        // Draw abscissa.
        graphic2D.draw(new Line2D.Double(PAD, h-PAD, w-PAD, h-PAD));
        
        // Draw labels.
        Font font = graphic2D.getFont();
        FontRenderContext frc = graphic2D.getFontRenderContext();
        LineMetrics lm = font.getLineMetrics("0", frc);
        float sh = lm.getAscent() + lm.getDescent();
        
        // Ordinate label.
        String s = "Balance";
        graphic2D.drawString("0", PAD-8, (h-PAD)+5); // Final Value of Balance
        String S1 = Double.toString(data[0]);
        graphic2D.drawString(S1, PAD, PAD);
        
        float sy = PAD + ((h - 2*PAD) - s.length()*sh)/2 + lm.getAscent();
        for(int i = 0; i < s.length(); i++) {
            String letter = String.valueOf(s.charAt(i));
            float sw = (float)font.getStringBounds(letter, frc).getWidth();
            float sx = (PAD - sw)/2;
            graphic2D.drawString(letter, sx, sy);
            sy += sh;
        }
        
        // Abscissa label.
        s = " Installments ";
        String S2 = Integer.toString(Term);
        graphic2D.drawString(S2, (w-PAD)-15, (h-PAD)+10); // Final Value of Term
        
        sy = h - PAD + (PAD - sh)/2 + lm.getAscent();
        float sw = (float)font.getStringBounds(s, frc).getWidth();
        float sx = (w - sw)/2;
        graphic2D.drawString(s, sx, sy);
        
        // Draw lines.
        double xInc = (double)(w - 2*PAD)/(data.length-1);
        double scale = (double)(h - 2*PAD)/getMax();
        graphic2D.setPaint(Color.green.darker());
        for(int i = 0; i < data.length-1; i++) {
            double x1 = PAD + i*xInc;
            double y1 = h - PAD - scale*data[i];
            double x2 = PAD + (i+1)*xInc;
            double y2 = h - PAD - scale*data[i+1];
            graphic2D.draw(new Line2D.Double(x1, y1, x2, y2));
        }
        
        // Mark data points.
        graphic2D.setPaint(Color.red);
        for(int i = 0; i < data.length; i++) {
            double x = PAD + i*xInc;
            double y = h - PAD - scale*data[i];
            graphic2D.fill(new Ellipse2D.Double(x-2, y-2, 4, 4));
       
            
            
            }
       
        
    }
    
 
    	public double getMax() {
        double max = -Integer.MAX_VALUE;
        for(int i = 0; i < data.length; i++) {
            if(data[i] > max)
                max = data[i];
        }
        return max;
    }
    	
   
    
    }
    