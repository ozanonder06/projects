/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evaluator;


//Driver class that creates an instance of MainEvaluator
//with a string of experssions to test it.
public class EvaluatorTester 
{
    public static void main(String[] args) 
    {
        String expr = "2*5+1*2";
        Evaluator evaluator = new Evaluator();
        System.out.println("2*5+1*2: "+ evaluator.eval(expr));
    }
}
