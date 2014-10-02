/*Ozan Gazi Onder
 * gets double numbers from the user calculates
 * their basic statistical measurements and 
 * draws a box plot
 */



import java.lang.Math;
import java.util.*;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.text.DecimalFormat;




public class boxPlotMaker
{
    int max;
    int index;
    ArrayList<Double> inputList;
    double[] list;
    boxPlotComponent component;
    DecimalFormat fmt;
    
    public boxPlotMaker(int max)
    {
        this.max = max;//max number of data can be entered
        inputList = new ArrayList<Double>();
        component = new boxPlotComponent();
        index = 0;
        fmt = new DecimalFormat("#.##");//print format
        
    }
    
    //adds data to the list
    public void add(double data)
    {
        if(index<max&&!inputList.contains(data))
        {
            inputList.add(data);
            index++;
        }    
    }
    
    //removes data from the list
    public void remove(double data)
    {
        if(index>0)
        {
            inputList.remove(data);
            index--;
        }    
    }
    
    
    //compuies the inpputList into a double[] array and returns it
    //the array is not sorted
    public double[] getArray()
    {
        double[] data = new double[inputList.size()];
        for(int i=0; i<data.length; i++)
            data[i] = inputList.get(i);
        
        return data;
    }        
    
    //display all the data
    public void display(double list[])
    {
        for(int i=0; i<list.length; i++)
        {
            System.out.print(list[i]+" ");
        }    
    }        
    
    

        
        //draws a box plot whose origin is x and y with width = w and height = h
        //name is the name of the data and the array list must be sorted
        public void draw(Graphics g, int x, int y, int w, int h, double[] data, String name)
        {
            double lowerF, upperF, fs, median, sd;
            int width, distance = x;
            boolean lower=false, upper=false, med=false;
            int lowerX=0, upperX=0, medianX=0;//x position of upper and lower fourths
            
            
            lowerF = component.getLowerFourth(data);//beginning point of box
            upperF = component.getUpperFourth(data);//end point of the box
            fs = upperF - lowerF; //the width of the box
            median = component.getMedian(data);
            sd = component.StandardDeviation(data, component.getMean(data));
            width = w/(data.length); //width of each interval
            
            
            g.setColor(Color.white);
            g.drawString(name, x+w+10, y);
            g.drawLine(x, y, x+w, y);//draw horizonral axis
            g.drawLine(x, y, x, y-h);//draw the vertical axis
            
            Font font = new Font("ARIAL", Font.BOLD, 9);
            g.setFont(font);

            //draw the intervals in the horizontal axis
            for(int i=0; i<data.length; i++)
            {
                int tempX;
                //
                g.drawLine(distance+width, y-5, distance+width, y+5);
                
                //display the list of data on the horizontal axis
                g.drawString(""+data[i], distance+width-10, y+20);
                
                //draws the lowerFourth
                if((data[i]<=lowerF)&&(data[i+1]>lowerF)&&!lower)
                {
                    lower=true;
                    double dist = (lowerF-data[i])/(data[i+1]-data[i]);          
                    lowerX = (int)(distance+width + (width*dist));
                    
                }
                
                //draws the median Value
                if((data[i]<=median)&&(data[i+1]>median)&&!med)
                {   
                    med=true;
                    double dist = (median-data[i])/(data[i+1]-data[i]);           
                    medianX = (int)(distance+width + (width*dist));
                }
           
                
                //draws the upperFourth
                if((data[i]<=upperF)&&(data[i+1]>upperF)&&!upper)
                {
                    upper=true;
                    double dist = (upperF-data[i])/(data[i+1]-data[i]);
                    upperX = (int)(distance+width + (width*dist));
                }

                
                distance += width;
              }//end of for
            
              //draw the box
              g.setColor(Color.white);
              g.fillRect(x, (y-h/2)-1, w, 2);
              g.setColor(Color.BLUE);
              g.fillRect(lowerX, y-(3*h/4), upperX-lowerX, h/2);
              g.setColor(Color.white);
              g.fillRect(medianX-1, y-(3*h/4), 2, h/2);
              
              g.setColor(Color.green);
              g.setFont(new Font("ARIAL", Font.PLAIN, 12));
              int textHeight = y-h-(h/4);
              g.drawString("Min: "+ data[0]+ "   Max: "+ data[data.length-1], upperX-w/8, textHeight);
              g.drawString("Standart Deviation: "+ fmt.format(sd), upperX-w/8, textHeight += 15);
              g.drawString("Median: "+ median + "  Lower Fourth: "+ lowerF+"   Upper Fourth: "
                      + upperF , upperX-w/8, textHeight +=15);
              g.fillOval(lowerX-5, y, 10, 10);
              g.fillOval(medianX-5, y, 10, 10);
              g.fillOval(upperX-5, y, 10, 10);
            
            
            
        } //end of draw method       
    
   
    
}
