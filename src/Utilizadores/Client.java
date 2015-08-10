package Utilizadores;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Client implements Runnable {
	private Socket clientSocket;
        
	private static Object mutex = new Object();
	private static int numberOfClients;

	// //////////////////////////////////////////////////

	public Client(Socket clientSocket, ArrayList<Socket> socketsClients) {
		this.clientSocket = clientSocket;
	}

	////////////////////////////////////////////////////

	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream());

			synchronized (mutex) {
				++numberOfClients;
				System.out.println("Cliente " + numberOfClients + " Activo!");
			}

			int op;
			do{
                            
                            out.println("1 --> Registar user's");
                            out.println("2 --> Login");
                            out.println("3 --> Listar Eventos");
                            out.println("4 --> Ver Detalhes de evento");
                            out.println("5 --> Inscrever-se num evento");
                            out.println("6 --> Pesquisar e listar");
                            out.println("6 --> Listar eventos por Utilizador");
                            out.println("7 --> Sair"); 
                            op = Integer.parseInt(in.readLine());

				out.println("escolhida a opcao " + op);
				
                            out.flush();
			}while(op!=0);

			in.close();
			out.close();
			clientSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}