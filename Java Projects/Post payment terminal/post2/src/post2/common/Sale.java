/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post2.common;

import java.util.ArrayList;
import java.text.*;
import java.util.Date;
import java.io.Serializable;


/**
 *
 * @author lev
 */
public class Sale implements Serializable{
    Customer customer;
    ArrayList<PurchaseItem> items;
    Payment payment;
    
    public Sale(){
        items = new ArrayList<PurchaseItem>();
    }
    
    public void registerItem(PurchaseItem _pi){
        items.add(_pi);
    }
    
    public double getCurrentTotal()
    {
       double total = 0;
       for(PurchaseItem p: items)
       {    
           total += p.getSubtotal();
       }
       return total;
    }
    
    public Customer getCustomer(){
        return customer;
    }
    
    public void setCustomer(Customer _cust){
        customer = _cust;
    }
    
    public Payment getPayment(){
        return payment;
    }
    
    public void setPayment(Payment _payment){
        payment = _payment;
    }
    
    public String printReceipt(String storeName, Catalog storeCatalog){
        String rec = storeName + "\n";
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//format the date like so
        Date date = new Date();//which date? this date
        
        //customer and date
        rec = rec + customer.getName() + "   " + dateFormat.format(date) + "\n";
        
        DecimalFormat twoDecimals = new DecimalFormat("0.00");//use for getting the correct number of decimals for price.
        
        for(PurchaseItem pi : items){
            Product relevantProduct = storeCatalog.fetchProductByUPC(pi.getUPC());
            rec = rec + relevantProduct.getName() + " " +
                    pi.getQuantity() + " @ $" +
                    twoDecimals.format(relevantProduct.getPrice()) + " $" + twoDecimals.format(pi.getSubtotal()) + "\n";
        }
        
        rec = rec + "Total: $" + twoDecimals.format(payment.getAmount()) + "\n";
        rec = rec + payment.makeReport() + "\n";
        
        
        return rec;
    }
    
}
