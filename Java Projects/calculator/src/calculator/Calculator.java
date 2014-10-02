//Ozan Gazi Onder
//Extra Credit Project
//Calculator Applet
package calculator;

import java.applet.Applet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Calculator extends javax.swing.JApplet implements ActionListener
{
        float f1, f2; //operands for calculation
        String operand;
        boolean isFloat, done = false;
        
        //Creates the components
        Panel p = new Panel();
	Button b1 = new Button("1");
	Button b2 = new Button("2");
	Button b3 = new Button("3");
	Button b4 = new Button("4");
	Button b5 = new Button("5");
	Button b6 = new Button("6");
	Button b7 = new Button("7");
	Button b8 = new Button("8");
	Button b9 = new Button("9");
	Button b0 = new Button("0");
	Button bplus = new Button("+");
	Button bminus = new Button("-");
	Button bdivide = new Button("/");
	Button bmult = new Button("*");
	Button bequal = new Button("=");
	Button bdec = new Button(".");
	TextField t = new TextField();

  public void init()
  {             
                //Intializes and adds the componenets
                isFloat = false;
		setLayout(new BorderLayout());
		add(t, BorderLayout.NORTH);
		add(p, BorderLayout.CENTER);
		p.setLayout(new GridLayout(4,4));
		p.add(b7);
		p.add(b8);
		p.add(b9);
		p.add(bplus);
		p.add(b4);
		p.add(b5);
		p.add(b6);
		p.add(bminus);
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(bmult);
		p.add(b0);
		p.add(bdec);
		p.add(bequal);
		p.add(bdivide);
                
                b0.addActionListener(this);
                b1.addActionListener(this);
                b2.addActionListener(this);
                b3.addActionListener(this);
                b4.addActionListener(this);
                b5.addActionListener(this);
                b6.addActionListener(this);
                b7.addActionListener(this);
                b8.addActionListener(this);
                b9.addActionListener(this);
                bmult.addActionListener(this);
                bequal.addActionListener(this);
                bdivide.addActionListener(this);
                bplus.addActionListener(this);
                bdec.addActionListener(this);
                bminus.addActionListener(this);

                
                setBackground (Color.BLACK);
              //setPreferredSize (new Dimension(400, 300));
               
             
	}
  public void actionPerformed(ActionEvent arg0) 
  {
      //Event is driven depending on what button is pressed
      if (arg0.getSource()==b0)
      {   
            if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"0");
      }   
      if (arg0.getSource()==b1)
      {    
            if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"1");
      } 
      if (arg0.getSource()==b2)
      {    
            if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"2");
      }   
      if (arg0.getSource()==b3)
      {    
             if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"3");
      } 
      if (arg0.getSource()==b4)
      {    
             if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"4");
      }   
      if (arg0.getSource()==b5)
      {    
            if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"5");
      } 
      if (arg0.getSource()==b6)
      {    
            if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"6");
      } 
      if (arg0.getSource()==b7)
      {    
            if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"7");
      }   
      if (arg0.getSource()==b8)
      {    
            if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"8");
      } 
      if (arg0.getSource()==b9)
      {    
             if(done)
            {
                done = false;
                t.setText("");
            }    
            t.setText(t.getText()+"9");
      } 
      
      if (arg0.getSource()==bplus)
      {    
  
            f1 = Float.parseFloat(t.getText());
            t.setText("");
            operand = "plus";
            isFloat = false;
      } 
      
      if (arg0.getSource()==bminus)
      {    
            f1 = Float.parseFloat(t.getText());
            t.setText("");
            operand = "minus";
            isFloat = false;
      } 
      
      if (arg0.getSource()==bmult)
      {    
            f1 = Float.parseFloat(t.getText());
            t.setText("");
            operand = "mult";
            isFloat = false;
      } 
      if (arg0.getSource()==bdivide)
      {    
            f1 = Float.parseFloat(t.getText());
            t.setText("");
            operand = "divide";
            isFloat = false;
      }
      
      if (arg0.getSource()==bdec)
      {    
          if(!isFloat)//checks the number of dots 
          {    
            t.setText(t.getText()+".");
            isFloat = true;
          }  
      } 
      //After pressing eqaul
      //The program shows the result
      //the clears the text field after
      //pressing a button
      if (arg0.getSource()==bequal)
      {
        f2 = Float.parseFloat(t.getText());
        if(operand=="plus")
          t.setText(Float.toString(f1+f2));
        else if(operand=="minus")
          t.setText(Float.toString(f1-f2));
        else if(operand=="mult")
          t.setText(Float.toString(f1*f2));
        else if(operand=="divide")
          t.setText(Float.toString(f1/f2));
        else
            t.setText("");
        
        done = true;
        isFloat = false;

          
            
        
}


		

	
  }
}
