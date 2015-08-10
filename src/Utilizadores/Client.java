package Utilizadores;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class Client implements Runnable {

    private Socket clientSocket;
    private static Object mutex = new Object();
    private static int numberOfClients;
    String operacao;
    ObjectOutputStream cos;
    ObjectInputStream cis;

	// //////////////////////////////////////////////////
    public Client(Socket clientSocket) {
        this.clientSocket = clientSocket;
       
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

            operacao = "";
             out.println("1 --> Registar user's");
            out.println("2 --> Login");
            out.println("3 --> Listar Eventos");
            out.println("4 --> Ver Detalhes de evento");
            out.println("5 --> Inscrever-se num evento");
            out.println("6 --> Pesquisar e listar");
            out.println("6 --> Listar eventos por Utilizador");
            out.println("7 --> Sair");
            out.flush();
            while (!operacao.equals("0")) {
                operacao = in.readLine();

                System.out.println("Client " + clientSocket + " said: \n   " + operacao);

                if (operacao.equalsIgnoreCase("1")) {
                    out.print("registar");

                } else if (operacao.equalsIgnoreCase("2")) {
                    out.print("Login");
                } else if (operacao.startsWith("3")) {
                    out.print("listar");
                } else {
                    out.print("Mensagem nao enviada");

                }

                out.println("Voce Disse: " + operacao);
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
