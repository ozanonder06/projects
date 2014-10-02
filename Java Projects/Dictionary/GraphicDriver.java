import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.lang.*;

// USER CANN ADD NAME AND NUMBER TO THE PHONEBOOK
// THE DATA WILL NOT BE LOST AFTER CLOSING THE PROGRAM
//BECAUSE EACH TIME PROGRAM OPENS, THE DATA FROM THE NOTEPAD
// WILL BE IMPORTED
public class GraphicDriver extends JPanel 
{
   private JLabel inputLabel, outputLabel, resultLabel,addLabel;
   private JTextField textToEnter, keyText, valueText;
	private DictionaryInterface<String, String> phoneBook;
	private JButton EnterButton, AddButton;//button
	String name, number;

	

   //-----------------------------------------------------------------
   //  Constructor: Sets up the main GUI components.
   //-----------------------------------------------------------------
   public GraphicDriver()
   {  
	   phoneBook = new SortedLinkedDictionary<String, String>();
      try
		{
		/*opens the file and stores the data to phoneBook
		key stores the string of very next value and value
		reads the rest of the line. Two strings are separated
		by a whitespace delimeter*/
		FileReader fin = new FileReader("burda.txt");
      Scanner src = new Scanner(fin);
		src.useDelimiter(" ");
		while (src.hasNextLine())
	   {
		  String key = src.next();
		  String value = src.nextLine();

		  phoneBook.add(key,value);
			 	  
		} 
		src.close();
      }// end of try
	  catch(IOException e)
	  {
    	e.printStackTrace();
  	  }	
		  		  
      inputLabel = new JLabel ("Enter a Name");
      outputLabel = new JLabel ("Phone Number: ");
      resultLabel = new JLabel ("_________");
		addLabel = new JLabel("Add name and Phone Number: ");

		
      EnterButton = new JButton ("Enter");
		AddButton = new JButton("ADD");
      // User enters the word to the textToEnter
      textToEnter = new JTextField (5);//number indicates the size of textBox 
		keyText = new JTextField (5);
		valueText = new JTextField (5);
		
		//LISTENERS
      textToEnter.addActionListener (new WordListener());
		// We do not need to implement anything for the EnterButton
		// Because its function will be the enter button
		EnterButton.addActionListener (new WordListener());
		
		AddButton.addActionListener (new ButtonListener());
      
		//ADDING TO THE PANEL
      add (inputLabel);
      add (textToEnter);
		add (EnterButton);
		System.out.println();
      add (outputLabel);
      add (resultLabel);
		add (addLabel);
		add (keyText);
		add (valueText);
		add (AddButton);
		
		//SETTING COLORS
		resultLabel.setForeground(Color.white); //changes resultLabel's text color
		inputLabel.setForeground(Color.blue);
		outputLabel.setForeground(Color.blue);
		addLabel.setForeground(Color.red);
		EnterButton.setForeground(Color.blue);//sets enterButtons text to blue
		EnterButton.setBackground(Color.black);// set enterButtons Background color
		
		AddButton.setBackground(Color.red);


      
      setPreferredSize (new Dimension(500, 200));
      setBackground (Color.black);
   }

   //*****************************************************************
   //  Represents an action listener for the temperature input field.
   //*****************************************************************
   private class WordListener implements ActionListener
   {
      //--------------------------------------------------------------
      //  Performs the conversion when either the enter key or the button is pressed  in
      //  the text field.
      //--------------------------------------------------------------
      public void actionPerformed (ActionEvent event)
      {
         
         String text = textToEnter.getText();
			
			String result = phoneBook.getValue(text);
			
			if (result != null)
			{
			   resultLabel.setText (phoneBook.getValue(text));
		   }
			else
			   resultLabel.setText ("Data can not be found");

   
		
		}
   }//end of Word Listener
	
	  //ButtonListener is set to ADD button so it will add the values to phone book
	 private class ButtonListener implements ActionListener
   {
 
      public void actionPerformed (ActionEvent event)
      {
         
         name = keyText.getText();// name as key
			
		   number = valueText.getText();// number as value
					
			if (name != null && number != null)
			{
			   phoneBook.add(name,number);
				//calls the textFile Class and adds the name
				// and number values to BUNA BAK.text
				// it appends the data, which means
				// it doesnt remove the current data when 
				// a new data is added, and is added to the next line instead
				textFile writer = new textFile(name, number);
				writer.addToFile (name, number);
				
				//After adding the name and number, the text fields
				// clear up.
				keyText.setText(" ");
				valueText.setText(" ");
         }

   
		
		}
   }//end of button Listener
			
 
}// end of the class