
package Implement;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;


public class goOutServer {
    
    
    public static void main(String args[]){
       /* System.getProperties().put("java.security.policy", "security.policy");
        System.setSecurityManager(new RMISecurityManager());*/
        ServerSocket server = null;
        try {
            
            Registry reg = LocateRegistry.createRegistry(1099);
            GoOutServerImpl _goOutServer = new GoOutServerImpl();
            reg.rebind("goOutServer", _goOutServer);
            System.out.println("Servidor a escuta...");
            
            
            
            
            //tratar tcp
            server = new ServerSocket(1111);
            System.out.println("Server is now runnign at  port 1111");
            
            while (true) {
			try {
				Socket clientSocket = server.accept();
		/*		Client client = new Client(clientSocket);

				Thread clientThread = new Thread(client);
				clientThread.start();*/
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
        } catch (Exception e) {
            e.printStackTrace();
        }    
    }
    
}
