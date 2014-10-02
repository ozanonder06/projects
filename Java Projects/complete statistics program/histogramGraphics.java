/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.Applet;

public class histogramGraphics extends JPanel
{
   public static JPanel panel, p1, p2, p3;
   public static JLabel label1, label2, label3;//Texts describes what to write
   public static JTextField dataField, freqField, dataName;//Textfields
   public static JButton addButton,enterButton, resetButton, menuButton;
   
   public histogramGraphics()
   {
        panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setPreferredSize (new Dimension(650, 120));
        
        
        label1 = new JLabel("Enter Value(in numbers)");
        label1.setForeground(Color.white);
        
        label2 = new JLabel("Enter Frequency(number of Occurence)");
        label2.setForeground(Color.white);
        
        label3 = new JLabel("Name of the Data");
        label3.setForeground(Color.white);

       
        
        addButton = new JButton("ADD");
        addButton.setBackground(Color.LIGHT_GRAY);
        
        enterButton = new JButton("DRAW HISTOGRAM");
        enterButton.setBackground(Color.LIGHT_GRAY);
        
        resetButton = new JButton("RESET");
        resetButton.setBackground(Color.LIGHT_GRAY);
        resetButton.setForeground(Color.red);
        
        menuButton = new JButton("MAIN MENU");

        
        
        dataField = new JTextField(10);
        dataField.setBackground(Color.BLACK);
        dataField.setForeground(Color.green);
        dataField.setCaretColor(Color.green);
        
        freqField= new JTextField(10);
        freqField.setBackground(Color.BLACK);
        freqField.setForeground(Color.green);
        freqField.setCaretColor(Color.green);
        
        dataName = new JTextField(10);
        dataName.setBackground(Color.BLACK);
        dataName.setForeground(Color.green);
        dataName.setCaretColor(Color.green);
        dataName.setText("DATA");
        
        p1 = new JPanel();
        p1.setBackground(Color.GRAY);
        p2 = new JPanel();
        p2.setBackground(Color.GRAY);
        p3 = new JPanel();
        p3.setBackground(Color.GRAY);
        
      
        p1.add(label1);
        p1.add(dataField);
        p2.add(label2);
        p2.add(freqField);
        p2.add(addButton);
        p3.add(label3);
        p3.add(dataName);
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(enterButton);
        panel.add(resetButton);
        panel.add(menuButton);
   }        
    
}
