
package Implement;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import Utilizadores.*;


public class goOutServer {
    
    
    public static void main(String args[]){
       /* System.getProperties().put("java.security.policy", "security.policy");
        System.setSecurityManager(new RMISecurityManager());*/
        ServerSocket server = null;
        try {
            
            //tratar rmi
            Registry reg = LocateRegistry.createRegistry(1099);
            GoOutServerImpl _goOutServer = new GoOutServerImpl();
            reg.rebind("goOutServer", _goOutServer);
            System.out.println("Servidor Com implementacao RMI a escuta...");
            //fim rmi
            
            
            
            
            //tratar tcp
            server = new ServerSocket(2015);
            System.out.println("Servidor com implementacao TCP a escuta no porto 2015");
            
            while (true) {
			try {
				Socket GoOutUserSocket = server.accept();
				GoOutUser client = new GoOutUser(GoOutUserSocket);
				Thread clientThread = new Thread(client);
				clientThread.start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
    
}
