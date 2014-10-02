/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package post2.client;

import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;

import post2.interfaces.*;

/**
 *
 * 
 */
public class PostClient {
    public static void main(String args[]) {
        //get remote store
        try{
            Registry registry = LocateRegistry.getRegistry(2222);
            StoreInterface si = (StoreInterface)registry.lookup("StoreObj");
            
            //setup post
            Post post = new Post(si);
            post.launchUI();
            
        }catch(RemoteException remEx){
            System.out.println("failed to connect to store " + remEx.getMessage());
        }catch(Exception ex){
            System.out.println("connection not established " + ex.getMessage());
        }        
        
    }
}
