/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post2.interfaces;
import java.rmi.Remote;
import java.rmi.RemoteException;
import post2.common.Catalog;
import post2.common.Sale;
/**
 *
 * @author redyoonnk1
 */
public interface StoreInterface extends Remote{
    
    public String getName()throws RemoteException;
    
    public void setName(String name)throws RemoteException;
    
    public void setStoreStatus(boolean status)throws RemoteException;
    
    public boolean getStoreStatus()throws RemoteException;
    
    public void setCatalog(Catalog _cat)throws RemoteException;
    
    public Catalog getCatalog()throws RemoteException;
    
    public boolean recordSale(Sale sale) throws RemoteException;
}
