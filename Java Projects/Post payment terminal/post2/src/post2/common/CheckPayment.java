//Author: Michele

package post2.common;

/**
 *
 * @author mic
 */
public class CheckPayment extends Payment {
  public CheckPayment(double _amt) {
      super(_amt);
  }

  @Override
  public boolean makePayment(){
      return true;
  }
  
  @Override
  public String makeReport(){
      return "Paid by Check";
  }
    
}
