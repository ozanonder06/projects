/*
 * Ozan Gazi Onder
 * Reads data(numberd) and frequency from the user
 * then draws a histogram
 */

import java.applet.Applet;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Histogram extends Applet implements ActionListener
{
    histogramMaker h;
    boxPlotMaker b;
    boxGraphics boxplot;
    histogramGraphics hist;
    double[] list;
    String data;
    boolean reset, isDrawn;
    String condition;
     
     JPanel emptyPanel,introPanel;//mainPanel that all the componenets are added
     JButton  buttonH, buttonB;//Buttons

     final int MAX =30;
    
    public void init()
    {
        setBackground(Color.black);
        h = new histogramMaker(MAX);
        b = new boxPlotMaker(MAX);
        condition ="none";
        
        boxplot = new boxGraphics();
        hist = new histogramGraphics();
        

        
        emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.black);
        emptyPanel.setPreferredSize (new Dimension(640, 320));
        

        
        introPanel = new JPanel();
        introPanel.setBackground(Color.BLACK);
        introPanel.setLayout(new BorderLayout());
        introPanel.setPreferredSize (new Dimension(300, 50));

               
        buttonH = new JButton("HISTOGRAM");
        buttonH.setBackground(Color.green);
        buttonH.setForeground(Color.black);
        
        buttonB = new JButton("BOX PLOT");
        buttonB.setBackground(Color.green);
        buttonB.setForeground(Color.black);

        
        introPanel.add(buttonH, BorderLayout.EAST);
        introPanel.add(buttonB, BorderLayout.WEST);
        
        add(emptyPanel);
        add(boxGraphics.panel);
        boxGraphics.panel.setVisible(false);
        add(histogramGraphics.panel);
        histogramGraphics.panel.setVisible(false);
        add(introPanel);
        
        //Setting up button Listeners
        histogramGraphics.addButton.addActionListener(this);
        histogramGraphics.enterButton.addActionListener(this);
        histogramGraphics.resetButton.addActionListener(this);
        boxGraphics.addButton.addActionListener(this);
        boxGraphics.enterButton.addActionListener(this);
        boxGraphics.resetButton.addActionListener(this);
        buttonH.addActionListener(this);
        buttonB.addActionListener(this);
        
        histogramGraphics.menuButton.addActionListener(this);
        boxGraphics.menuButton.addActionListener(this);
        
        reset = false;
        isDrawn = false;
          

    } 
    

    
    
    public void actionPerformed(ActionEvent arg0) 
    {
        
        //buttonH and buttonB in intro panel
        //they will display histogram or boxplot components
        //depending on what button is pressed
        if (arg0.getSource()==buttonH)
        {
           introPanel.setVisible(false);
           histogramGraphics.panel.setVisible(true);
        } 
        if (arg0.getSource()==buttonB)
        {
           introPanel.setVisible(false);
           boxGraphics.panel.setVisible(true);
        } 
        //Event is driven depending on what button is pressed
         if (arg0.getSource()==histogramGraphics.addButton)
         {   
             try//in case if the input datas are not in the desired format
             {    
               double number = Double.parseDouble(histogramGraphics.dataField.getText());
               int frequency = Integer.parseInt(histogramGraphics.freqField.getText());
               //add the data to the system
               h.addInput(number, frequency);
               histogramGraphics.dataField.setText("");
               histogramGraphics.freqField.setText("");
             }
             catch(Exception e)
             {
               System.out.println("Incompatible data!!!");
             }    
           
         }
         
          if (arg0.getSource()==histogramGraphics.enterButton)
          {
              try
              {    
                list = h.getArray();
                list = h.sort(list);
                h.process(list);
                //panel.setVisible(false);
                data = histogramGraphics.dataName.getText();
                //draws the histogram
                condition="histogram";
                drawGraphics();

                
                
              }
              catch(Exception e)
              {}    
          } 
          
          if (arg0.getSource()==histogramGraphics.resetButton)
          { 
                 try
                 {    
                  reset = true; 
                  drawGraphics();
                  histogramGraphics.dataName.setText("DATA");
                 }
                 catch(Exception e){}
          } 
          
                   
          //IMPLMENTION OF THE COMPONENTS OF BOXPLOT
         if (arg0.getSource()==boxGraphics.addButton)
         {   
             try//in case if the input datas are not in the desired format
             {    
               double number = Double.parseDouble(boxGraphics.dataField.getText());
               //add the data to the system
               b.add(number);
               boxGraphics.dataField.setText("");
             }
             catch(Exception e)
             {
               System.out.println("Incompatible data!!!");
             }    
           
         }
         
         if (arg0.getSource()==boxGraphics.enterButton)
          {
              try
              {    
                list = b.getArray();
                list = h.sort(list);
                data = boxGraphics.dataName.getText();
                //draws the histogram 
                condition="boxplot";
                drawGraphics();
                                
              }
              catch(Exception e)
              {}    
          }
         
         if (arg0.getSource()==boxGraphics.resetButton)
          { 
                 try
                 {    
                  reset = true; 
                  drawGraphics();
                  boxGraphics.dataName.setText("DATA");
                 }
                 catch(Exception e){}
          }
         
         if (arg0.getSource()==boxGraphics.menuButton || arg0.getSource()==histogramGraphics.menuButton)
          { 
               list = null;
               introPanel.setVisible(true);
               histogramGraphics.panel.setVisible(false);
               boxGraphics.panel.setVisible(false);
               h = new histogramMaker(MAX);
               b = new boxPlotMaker(MAX);
               condition = "none";
               boxGraphics.dataName.setText("DATA");
               histogramGraphics.dataName.setText("DATA");
               drawGraphics();
          }
          
    }//end of ActionPerformed
    
    
    public void drawGraphics()
    {
        Graphics g = getGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, 640, 320);
        if(!reset||!isDrawn)
        {    
         if(condition.equalsIgnoreCase("histogram"))    
             h.draw(g,150, 290, 375, 250, list, data); 
         if(condition.equalsIgnoreCase("boxplot")) 
             if(list.length>2)
             b.draw(g,25, 300, 450, 200, list, data);
         
         isDrawn = true;
        }
        else if(reset)
        {    
           try
          {              
            g.setColor(Color.black);
            g.fillRect(0, 0, 640, 320);
            if(condition.equalsIgnoreCase("histogram"))   
                h = new histogramMaker(MAX); 
            if(condition.equalsIgnoreCase("boxplot"))   
                b = new boxPlotMaker(MAX);
            list = null;
            reset = false;
            isDrawn = false;
          }
          catch(Exception e){}
        }    
    }    
    
    public static void main(String argv[])
    {
        Histogram demo = new Histogram();
        demo.init();
        JFrame f = new JFrame("STATISTICS");
        f.addWindowListener(new WindowAdapter() {});
        f.getContentPane().add("Center", demo);
        
        f.pack();
        f.setSize(new Dimension(640, 480));
        f.setVisible(true);
        f.setResizable(false);
        demo.start();
    }
    
    

}
