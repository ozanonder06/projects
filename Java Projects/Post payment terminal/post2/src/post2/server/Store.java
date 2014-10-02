/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post2.server;
import post2.common.Catalog;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import post2.interfaces.StoreInterface;
import post2.common.TransactionWriter;
import post2.common.Sale;
/**
 *
 * @author Marco Chin
 */
public class Store extends UnicastRemoteObject implements StoreInterface{
    private boolean storeStatus = false;
    private String storeName;
    private Catalog cat;
    private TransactionWriter tw;
    
    
    public Store(String name)throws RemoteException{
        super();
        storeName = name;
        tw = new TransactionWriter(System.getProperty("user.dir") + "/output/server.log");
    }
    
    @Override
    public String getName()throws RemoteException{
        return storeName;
    }
    
    @Override
    public void setName(String name)throws RemoteException{
        storeName = name;
    }
    
    @Override
    public void setStoreStatus(boolean status)throws RemoteException{
        storeStatus = status;
    }
    
    @Override
    public boolean getStoreStatus()throws RemoteException{
        return storeStatus;
    }
    
    @Override
    public void setCatalog(Catalog _cat)throws RemoteException{
        cat = _cat;
    }
    
    @Override
    public Catalog getCatalog()throws RemoteException{
        return cat;
    }
    
    @Override
    public boolean recordSale(Sale sale) throws RemoteException{
        tw.writeContent(sale.printReceipt(storeName, cat));
        return true;
    }
}
