/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package post2.server;
import post2.common.Catalog;
import post2.common.*;
import java.rmi.RemoteException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author redyoonnk1
 */
public class StoreServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws RemoteException, AlreadyBoundException
    {
        Store store = new Store("The Rumble Grocery");
        
        String workingDir = System.getProperty("user.dir");
        String productsFile = workingDir + "/src/resources/products.txt";
        ProductReader pr = new ProductReader(productsFile);
        Catalog cat = new Catalog();
        while(pr.hasMoreProducts()){
            Product nextP = pr.getNextProduct();
            cat.addProduct(nextP);
        }
        
        
        store.setCatalog(cat);
        
        Manager manager = new Manager("Morgan", "Freeman");
        manager.openStore(store);//opens store
        
        
        //Actual Server Part
        Registry registry = LocateRegistry.createRegistry(2222);//create a registry server listening to port 2222
        registry.bind("StoreObj", store); //bind object to registry
        System.out.println("Server has started");
        
//        TransactionWriter tw = new TransactionWriter(workingDir + "/src/resources/transaction.txt");//testing
//        tw.write();//testing
    }
}
