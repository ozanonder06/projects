//Creates a Hash Map and stores a number and its corresponding frequency
//Then finds its relative Frequency values and intervals on X coordinate

import java.lang.Math;
import java.util.*;
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import java.text.DecimalFormat;


public class histogramMaker
{
    java.util.HashMap<Double, Integer> inputList;
    java.util.HashMap<Double, Double> sortedInputList;
    int totalFreq;
    double[] numbers;
    int max;
    public int index;
    DecimalFormat fmt = new DecimalFormat("#.##");//print format
    
    public histogramMaker(int max)
    {
        //Inputs are the values and corresponding frequencies
        inputList = new java.util.HashMap<Double, Integer>();
        sortedInputList = new java.util.HashMap<Double, Double>();
        this.max = max;
        numbers = new double[max];//MAX DATA LIMIT (WE LL USE 100 IN OUR TEST CASE)
        totalFreq = 0;
        index=0;
    }
    
    public void addInput(double number, int frequency)
    {
        if(index<max && !inputList.containsKey(number))
        {    
          inputList.put(number, frequency);
          totalFreq += frequency;
          numbers[index]= number;
          index++;
        }
    }   
    
    //creates a new version sorted HashMap from the input List
    //whose keys are the data and values are relative frequency
    public void process(double[] list)
    {    
    
        for(int i=0; i<list.length; i++)
        {
            double relFreq = inputList.get(list[i])/(double)totalFreq;
            sortedInputList.put(list[i], relFreq);
        } 
    } 
    
    public double[] getArray()
    {      
            double[] list = new double[index];
            
            for(int i=0; i<list.length; i++)
                list[i]=numbers[i];
            
            return list;
    }
    
    
    
    public  double[] sort(double list[])
    {
        boolean needNextPass = true;
        for (int k = 1; k < list.length && needNextPass; k++)
        {
            needNextPass = false;
            for (int i = 0; i < list.length - k; i++) 
            {
              if (list[i] > list[i + 1]) 
              {
                double temp = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp;
                needNextPass = true;
              }
             }
       }
       return list;
      
    }    
    //display all data and its correponding value in an ascending order
    //Do not use this method before using process()
    public void display(double[] list)
    {
        for(int i=0; i<list.length; i++)
        {
           System.out.println(list[i]+"   "+ sortedInputList.get(list[i])); 
          
        }    
        
    }        
    
    //draws the histogram with specified dimensions.
    //x and y values will be the origin of the coordinate system
    // w is the width from x=0 to x=n and h is height from h=0 to h=n
    // name is the name of the data user wants to see on the screen
    public void draw(Graphics g, int x, int y, int w, int h,double[] list, String name)
    {
        //width of each interval
        int width = w/list.length;
        
        g.setColor(Color.white);
        //g.fillOval(h, h, h, h);
        g.drawLine(x, y, x+w, y); //draw horizontal axis
        g.drawLine(x, y, x, y-h);//draw vertical line
        g.drawString(name, x+w+15, y+15);
        g.drawString("Rel. Frequency", x-50, y-h);
        
        
        for(int i=0; i<list.length; i++)
        {
            //height of each rectangle of the histogram
            //corresponds to the relative frequency
            double temp =  sortedInputList.get(list[i])*h;
            int height = (int) temp;
            
            //draws the rectangles
            g.setColor(Color.blue);
            g.fillRect(x, y-height, width, height);
            
            //draw the border of each rectangle
            //and displays relative frequency
            g.setColor(Color.white);
            g.drawRect(x, y-height, width, height);
            
            double rel = sortedInputList.get(list[i]);
            g.drawString(""+fmt.format(rel), x+width/3, y-height);
            
            
            
            //prints the data of each histogram value
            g.setColor(Color.blue);
            g.drawString(""+list[i], x+(width/3), y+15);
            

            
            x += width;
            
        }    
        
        
    }
    
    
    
}
