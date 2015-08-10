
package Implement;

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import Utilizadores.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class goOutServer extends Thread{
    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int idSessao;
    
    //Construtor para conexoes RMI
    goOutServer(){
    
    }
    
    //construtor para conexoes tcp

    public static void main(String args[]){
       /* System.getProperties().put("java.security.policy", "security.policy");
        System.setSecurityManager(new RMISecurityManager());*/
        ServerSocket ss;
        System.out.print("Inicializando servidor...\n ");
        try {
            
            //tratar rmi
            Registry reg = LocateRegistry.createRegistry(1099);
            GoOutServerImpl _goOutServer = new GoOutServerImpl();
            reg.rebind("goOutServer", _goOutServer);
            System.out.println("SERVIDOR --> Escuta RMI.....\t[OK]");
            //fim rmi

            //tratar tcp
            ss = new ServerSocket(2015);
            System.out.println("SERVIDOR --> Escuta TCP.....\t[OK]");

            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nova conexao iniciada: "+socket);
                ((Servidorthread) new Servidorthread(socket)).start();
            
            }
   
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(goOutServer.class.getName()).log(Level.SEVERE, null, e);
        }    
    }
  
}
