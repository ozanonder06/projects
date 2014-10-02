/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package post2.client;
import post2.common.*;
import post2.interfaces.StoreInterface;
import java.rmi.RemoteException;

/**
 *
 * @author ozan
 */
public class Post
{   
    private StoreInterface store;
    private String storeName;
    private Catalog cat;
    private TransactionWriter tw;
    
    public Post(StoreInterface si){
        store = si;
        tw = new TransactionWriter(System.getProperty("user.dir") + "/output/client.log");
    }
    
    public void launchUI(){
        try{
            cat = store.getCatalog();
            storeName = store.getName();
            ClientInterface.launch(this, store.getCatalog(), storeName);
        }catch(RemoteException remEx){
            System.out.println("failed to get catalog " + remEx.getMessage());
        }
    }
    
    public boolean applySale(Sale sale){
        try{
            boolean success = store.recordSale(sale);
            if(!success){
                return recordSaleLocally(sale);
            }else{
                return true;
            }
        }catch(RemoteException remEx){
            remEx.printStackTrace();
            return recordSaleLocally(sale);
        }
    }
    
    private boolean recordSaleLocally(Sale sale){
        tw.writeContent(sale.printReceipt(storeName, cat));
        return true;
    }
}

