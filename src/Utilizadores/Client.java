package Utilizadores;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class Client implements Runnable {
	private Socket clientSocket;
	private static Object mutex = new Object();
	private static int numberOfClients;
        private Vector<Socket> clientes  = new Vector(); //vector que guarda cada conexao criada
        String comando, nome,linha1;
       

	// //////////////////////////////////////////////////

	public Client(Socket clientSocket) {
		this.clientSocket = clientSocket;
                this.clientes  = clientes ;
	}

	// //////////////////////////////////////////////////

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

			synchronized (mutex) {
				++numberOfClients;
				System.out.println("Cliente N. " + numberOfClients + " Activo!");
			}

			String line = "";
			while (!line.equals("0")) {
				line = in.readLine();
                                
				System.out.println("Client " + clientSocket + " said: \n   " + line);
                                
                                
                                if(line.equalsIgnoreCase("1")){
                                    out.print("registar");
                                    
                                }
                                else if(line.equalsIgnoreCase("2")){
                                   out.print("Login");
                                }
                                else if(line.startsWith("3")){
                                    out.print("listar");
                                }
                                else{
                                out.print("Mensagem nao enviada");
                                
                                }
                                
				out.println("Voce Disse: " + line);
				out.flush();
			}

			in.close();
			out.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
      
}