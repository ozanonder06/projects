//Author: Michele

package post2.common;

import java.text.DecimalFormat;


public class CashPayment extends Payment {
  public CashPayment(double _amt) {
    super(_amt);
  }
  
  @Override
  public boolean makePayment(){
      return true;
  }
  
  @Override
  public String makeReport(){
      return "Paid by cash";
  }
  
}
