package Implement;

import Utilizadores.GoOutUser;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class goOutServer extends Thread {

    //Construtor para conexoes RMI
    goOutServer() {

    }

    //construtor para conexoes tcp
    public static void main(String args[]) {
        /* System.getProperties().put("java.security.policy", "security.policy");
         System.setSecurityManager(new RMISecurityManager());*/

        System.out.print("Inicializando servidor...\n ");
//TRATAR ESCUTA RMI
        try {

            Registry reg = LocateRegistry.createRegistry(1099);
            GoOutServerImpl _goOutServer = new GoOutServerImpl();
            reg.rebind("goOutServer", _goOutServer);
            System.out.println("SERVIDOR --> Escuta RMI.....\t\t\t[OK]");
            //fim rmi
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(goOutServer.class.getName()).log(Level.SEVERE, null, e);
        }

 //TRATAR ESCUTA TCP       
        ServerSocket server = null;
        try {

            Scanner scanner = new Scanner(System.in);
            int port = 2015;
            server = new ServerSocket(port);
            System.out.println("SERVIDOR --> Escuta TCP - porto 2015.....\t[OK]");
        } catch (Exception e) {
            System.out.println("Desculapa, oorreu um erro durante a conexao.");
            e.printStackTrace();
            System.exit(0);
        }

        while (true) {
            try {
                Socket clientSocket = server.accept();
                GoOutUser client = new GoOutUser(clientSocket);
                Thread clientThread = new Thread(client);
                clientThread.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
