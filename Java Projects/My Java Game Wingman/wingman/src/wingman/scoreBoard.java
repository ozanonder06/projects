/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package wingman;

import javax.swing.JPanel;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;
import javax.swing.JLabel;
import java.util.Arrays;
import java.lang.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;



public class scoreBoard extends JPanel 
{
    public static gm1942 gm = new gm1942();
    public int[] scoreList;
    Image img;
    int x, y;
    boolean set;
    public scoreBoard()
    {
        img = gm.getSprite("Resources/gameOver.png");
        scoreList = new int[5];
        x = 230;
        y = 50;
        set = false;
        //READ THE FILE
        try
        {
	
		FileReader fin = new FileReader("data.txt");
                Scanner src = new Scanner(fin);
                int index = 0;
                while (src.hasNext())
	        { 
                  if(index<5)  
                  {
                    scoreList[index]=src.nextInt();
                    index++;
                  }  
                }
                
        }
        //if the file can can not be opened
        //then the program  initialize all the values
        //to zero
        catch(IOException e)
        {
       
            System.out.println("File open error");
        } 

        Arrays.sort(scoreList);
                 
 
        //each time the program drops the
        //lowest score, adds the new score and sorts the array
        //to display your rank
        

        
    }//end of Constructor
    
        public void draw(Graphics g, ImageObserver obs)
        {
            if(!set)
            {
             
             Arrays.sort(scoreList);
             for(int i = 0; i < scoreList.length-1; i++)
             {
                 if(gm1942.score < scoreList[i+1])
                 {    
                     scoreList[i]=gm1942.score;
                     break;
                 }    
             }    
             
             //saves the scores data to a text file
            writeToFile(scoreList, "data.txt");
            set = true;
            }
            
            g.setColor(Color.white);
            g.fillRect(x-130, 10, 140, 300);
            
            
            g.drawImage(img, x, y, obs);
            g.setColor(Color.black);
            g.drawString("YOUR SCORE: "+ gm1942.score, x-130, y+10);
            g.drawString("1ST Player: "+ scoreList[4], x-130, y+40);
            g.drawString("2ND Player: "+ scoreList[3], x-130, y+70);
            g.drawString("3RD Player: "+ scoreList[2], x-130, y+100);
            g.drawString("4TH Player: "+ scoreList[1], x-130, y+130);
            g.drawString("5TH Player: "+ scoreList[0], x-130, y+160);
            
            
            
        }      
    
    
    public void writeToFile(int values[], String text)
        {
                String fileName = text;
		PrintWriter toFile = null;
                try
		{
		  toFile = new PrintWriter(fileName);
		}
		catch (FileNotFoundException e)
		{
		  System.out.println("PrintWriter error openinng the file" + fileName);
		  
		  System.exit(0);
		}
                for(int count = 0; count < values.length; count++)
		{
		  toFile.println(values[count]); //writes to the file
		}
                toFile.close();
                
        }    
}
