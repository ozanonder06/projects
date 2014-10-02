//Author: Michele

package post2.common;
import java.io.Serializable;

/** an abstract class representing a payment of some kind */
abstract public class Payment implements Serializable{
  public Payment(double _amt) { 
      amount = _amt;
  }
   // Declare variables.
   
  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  protected double amount;
  
  //returns true if payment succeeds, false if fails
  public boolean makePayment(){
      return false;
  }
  
  public String makeReport(){
      return "";
  }
}     

