package post2.common;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Serializable;
import java.util.*;

/**
 *
 * @author lev
 */
public class Catalog implements Serializable{
    private ArrayList<Product> products;
    
    
    public Catalog(){
        products = new ArrayList();
    }
    
    public ArrayList<Product> getProducts(){
        return products;
    }
    
    public void addProduct(Product toAdd)
    {
        Product existenceCheck = fetchProductByUPC(toAdd.getUPC());
        if(existenceCheck == null)
        {
            products.add(toAdd);
        }
    }
    
    public Product fetchProductByUPC(String upc){
        for(Product p : products){
            if(p.getUPC().equals(upc)){
                return p;
            }
        }
        
        return null;
    }
}