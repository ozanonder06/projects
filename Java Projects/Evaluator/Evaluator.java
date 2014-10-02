
package evaluator;
import java.util.*;
import java.lang.*;
//Evaluator class has eval method that takes a string as an argument
//of expression and evalutaes them. It returns the the result an an integer
public class Evaluator 
{

    private Stack<Operand> opdStack;
    private Stack<Operator> oprStack;
    String delimiters;
    int result;//integer value to be returned
 
    
    public Evaluator()//Constructor 
    {
        opdStack = new Stack<Operand>();
        oprStack = new Stack<Operator>();
    }

    public int eval(String expr) 
    {
        String tok;

        HashMap operators = new HashMap();//HashMap for Subclass Operators
        operators.put("+", new AdditionOperator());
        operators.put("-", new SubtractionOperator());
        operators.put("*", new MultiplicationOperator());
        operators.put("/", new DivisionOperator());
        operators.put("#", new InitOperator());
        //Modulo Operator is added for the extra credit
        operators.put("%", new ModuloOperator());
        
        oprStack.push((Operator)operators.get("#"));
        
        //Modulo Operator is added for the extra credit
        delimiters = "+-*/#% ";

        StringTokenizer st = new StringTokenizer(expr,delimiters,true);
	// the 3rd arg is true to indicate to use the delimiters as tokens, too
	// but we'll filter out spaces

        while (st.hasMoreTokens()) 
        {
            if ( !(tok = st.nextToken()).equals(" ")) 
            {          // filter out spaces
                Operand myOperand = new Operand(tok);
                if (myOperand.check(tok)) 
                {   
                    // check if tok is an operand
                    opdStack.push(new Operand(tok));
                } 
                else 
                {
                    //Creating an instance of the sub-class of the operator class
                    //to perform the check operator
                    Operator myOperator = (Operator)operators.get(tok);
                    if (!myOperator.check(tok)) 
                    {
                        System.out.println("*****invalid token******");
                        System.exit(1);
                    }

                    Operator newOpr = (Operator)operators.get(tok);         // POINT 1

                    while ( ((Operator)oprStack.peek()).priority() >= newOpr.priority()) 
                    {
			// note that when we eval the expression 1 - 2 we will
			// push the 1 then the 2 and then do the subtraction operation
			// This means that the first number to be popped is the
			// second operand, not the first operand - see the following code
                        Operator oldOpr = ((Operator)oprStack.pop());
                        if(oldOpr != (Operator)operators.get("#"))
                        {
                         Operand op2 = (Operand)opdStack.pop();
                         Operand op1 = (Operand)opdStack.pop();
                         opdStack.push(oldOpr.execute(op1,op2));  
                        }   
                        
                    }

                    oprStack.push(newOpr);
                }//end of else
            }//end of outer if statement
      }//end of while
        while((Operator)oprStack.peek()!=operators.get("#"))
        {
            //Now the expression is scanned completely,leaving
            //all the operators in the priority order on stack.
            //The program pops them out from the stack one by one
            //and evaluates them with the operands
            Operator oldestOp = ((Operator)oprStack.pop());
            Operand final2 = (Operand)opdStack.pop();
            Operand final1 = (Operand)opdStack.pop();
            opdStack.push(oldestOp.execute(final1,final2)); 
        }      
        result = opdStack.pop().getValue();
        return result;
    }//end of eval
    
    
    //I created a little long spaces between classes
    //intentionally to make the code more readeble for
    //for the graders.
    
    class Operand 
    {
    //String tok;
    int value;
    //Constructions for Operand
     Operand(String tok)
    {
            if(check(tok))//evaulates as an integer
            this.value = Integer.parseInt(tok);
    }        
    public Operand(int value)
    {
        this.value = value;
    }   
    
    int getValue()
    {
        return value;
    }
    //by using static, it will be accessed by
    //the MainEvaluator class
      boolean check(String token)
    {
        boolean result = true;
        int number;
        //the method tries to convert the string 
        //to an integer. If it becomes unsuccesful,
        //throws and exception and returns false value
        try  {    
          number = Integer.parseInt(token);}
        catch(NumberFormatException e)
        {
            result = false;
        }    
                
        return result;
    }//end of check        
       
}//end of Operand Class
    
    
    
 
 abstract class Operator 
 {
   
   public Operator()
   {//empty constructor
       
   }
   //by using static, it will be accessed by
   //the MainEvaluator class
   public abstract boolean check(String token);      
   
   //Abstract classes that will be implemented 
   //in the subclasses of Operator
   public abstract int priority();//returns priority of operator
   
   public abstract Operand execute(Operand opd1, Operand opd2);
   //Processes two operands and returns the result
} //end of Operator class

 
 
 class AdditionOperator extends Operator
 {
     public AdditionOperator()
     {
        //Constructor
     }   
     public int priority()
     {
         return 2;//priority of addition
     }  
     
     public boolean check(String token)
     {
         return (token.equalsIgnoreCase("+"));
     }        
     public Operand execute(Operand opd1, Operand opd2)
     {
         int first = opd1.getValue();
         int second = opd2.getValue();
         //Adds operands and returns the result
         return new Operand(first+second);
     }        
             
  }//end of AdditionOperator
 
 
 
  class SubtractionOperator extends Operator
 {
     public SubtractionOperator()
     {
        //Constructor
     }   
     public int priority()
     {
         return 2;//priority of subtraction
     }   
    
     public boolean check(String token)
     {
         return (token.equalsIgnoreCase("-"));
     }        
     public Operand execute(Operand opd1, Operand opd2)
     {
         int first = opd1.getValue();
         int second = opd2.getValue();
         //Subtracts operands and returns the result
         return new Operand(first-second);
     }        
               
 }//end of SubtractionOperator
 
  
  
 class MultiplicationOperator extends Operator
 {
     public MultiplicationOperator()
     {
        //Constructor
     }   
     public int priority()
     {
         return 3;//priority of multiplication
     }    
     
     public boolean check(String token)
     {
         return (token.equalsIgnoreCase("*"));
     }    
     
     public Operand execute(Operand opd1, Operand opd2)
     {
         int first = opd1.getValue();
         int second = opd2.getValue();
         //multiplies operands and returns the result
         return new Operand(first*second);
     }        
               
}//end of MultiplicationOperator
 
class DivisionOperator extends Operator
{
     public DivisionOperator()
     {
        //Constructor
     }   
     public int priority()
     {
         return 3;//priority of division
     }    
     
     public boolean check(String token)
     {
         return (token.equalsIgnoreCase("/"));
         
     }
     
     public Operand execute(Operand opd1, Operand opd2)
     {
         int first = opd1.getValue();
         int second = opd2.getValue();
         //divides operands and returns the result
         return new Operand(first/second);
     }        
               
}//end of DivisionOperator



 class InitOperator extends Operator
{
     public InitOperator()
     {
        //Constructor
     }   
     public int priority()
     {
         return 0;//priority of init Operator
     }       
     //Init Operator doesnt have an execution functionality
     //because its is used to indicate whther the operator stack is
     //empty  
     public boolean check(String token)
     {
         return (token.equalsIgnoreCase("#"));
     }        
     public Operand execute(Operand opd1, Operand opd2)
     {
         //This method is not going to be execuited
         //because, the calculation will be completed
         //after Init operator is scanned.
         return new Operand(0);
     }     
}//end of InitOperator
 
 
 
   //ModuloOperator for Extra Credit
   class ModuloOperator extends Operator
   {
     public ModuloOperator()
     {
        //Constructor
     }   
     public int priority()
     {
         return 3;//priority of Modulo
     }    
     
     public boolean check(String token)
     {
         return (token.equalsIgnoreCase("%"));
     }
     
     public Operand execute(Operand opd1, Operand opd2)
     {
         int first = opd1.getValue();
         int second = opd2.getValue();
         //divides operands and brings the remaining
         return new Operand(first%second);
     }        
               
   }//end of ModuloOperator
    
    
}//end of the class

