package post2.server;


import java.io.*;
import post2.common.*;

/**
 *
 * @author lev
 */
public class ProductReader 
{
    private BufferedReader reader;
    String currentLine;
    
    ProductReader(String productsFile){
        try{
            FileReader fr = new FileReader(productsFile);
            reader = new BufferedReader(fr);
        }catch(Exception e){
            reader = null;
        }
    }
    
    boolean hasMoreProducts(){
        if(reader == null){
            return false;
        }
        
        if(currentLine != null){
            return true;
        }
        
        try{
            currentLine = reader.readLine();
            if(currentLine == null){
                reader.close();
                reader = null;
            }
        }catch(Exception e){
            currentLine = null;
        }
        
        return (currentLine != null);
    }
    
    public Product getNextProduct(){ 
        if(this.hasMoreProducts()){
            return productFromLine(currentLine);
        }else{
            return null;
        }
    }
    
    private Product productFromLine(String pl){//turns current line into a product object
//        String [] productElements = pl.split("\\s+");
//        String upc = productElements[0];
//        String desc = productElements[1];
//        double price = Double.parseDouble(productElements[2]);
        String upc = pl.substring(0, 4);
        String desc = pl.substring(9, 20);
        double price = Double.parseDouble(pl.substring(34));
        
        Product p = new Product(upc, desc, price);
        currentLine = null;
        return p;
    }
}
