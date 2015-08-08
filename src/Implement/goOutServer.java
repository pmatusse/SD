
package Implement;

import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class goOutServer {
    
    
    public static void main(String args[]){
       /* System.getProperties().put("java.security.policy", "security.policy");
        System.setSecurityManager(new RMISecurityManager());*/
        try {
            
            
            Registry reg = LocateRegistry.createRegistry(1099);
            GoOutServerImpl _goOutServer = new GoOutServerImpl();
            reg.rebind("goOutServer", _goOutServer);
            System.out.println("Servidor a escuta...");
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
        
    
    }
    
}
