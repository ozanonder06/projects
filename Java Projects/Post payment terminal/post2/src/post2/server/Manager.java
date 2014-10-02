package post2.server;


import post2.common.*;
import java.rmi.RemoteException;
/**
 *
 * @author Ozan Onder
 */
public class Manager extends Person
{
    public Manager(String firstName, String lastName)
    {
        //inherit the constructor from person class
        super(firstName, lastName);
    }
    
    public void openStore(Store store) throws RemoteException{
        store.setStoreStatus(true);
    }
    
    public void closeStore(Store store) throws RemoteException{
        store.setStoreStatus(false);
    }
}
