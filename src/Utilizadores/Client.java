package Utilizadores;

import Model.Event;
import Model.Tabelas;
import Model.User;
import java.io.*;
import java.net.*;
import java.util.StringTokenizer;
import java.util.Vector;

public class Client implements Runnable {

    private Socket clientSocket;
    private static Object mutex = new Object();
    private static int numberOfClients;
    String operacao;
    Tabelas tabelas = new Tabelas();
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
            //menuReestrito(out);
            menu(out);

            while (!operacao.equals("0")) {
                operacao = in.readLine();

                System.out.println("Client " + clientSocket + " said: \n   " + operacao);

                if (operacao.equalsIgnoreCase("1")) {
                    out.println("Digite seu Email");
                    out.flush();
                    String email = in.readLine();
                    out.println("Digite sua Password");
                    out.flush();
                    String password = in.readLine();
                    out.println("Digite seu nome");
                    out.flush();
                    String nome = in.readLine();
                    out.println("Digite Numero de telefone");
                    out.flush();
                    String _telefone = in.readLine();
                    int telefone = Integer.parseInt(_telefone);
                    if (tabelas.Users.add(new User(email, password, nome, telefone))) {
                        out.print("Utilizador registrado com sucesso");
                        out.flush();
                        menuReestrito(out);
                    } else {
                        out.print("Utilizador nao registrado! ocorreu um erro");
                        out.flush();
                    }

                    //Autenticar   
                } else if (operacao.equalsIgnoreCase("2")) {
                    out.println("Digite seu Email");
                    out.flush();
                    String email = in.readLine();
                    out.println("Digite sua Password");
                    out.flush();
                    String password = in.readLine();
                    boolean login =false;
                    for (User user : tabelas.Users) {
                        if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                            menu(out);
                            login=true;
                        }
                    }
                    
                    if(login==false){
                        out.print("Credenciais invalidas, tente mais uma vez");
                        out.flush();
                        menuReestrito(out);
                    }

                    
                } 
                else if (operacao.equalsIgnoreCase("3")) {
                    out.println("Digite seu Email");
                    out.println("obrigado");
                    out.flush();
                    menu(out);
                }
                
                else {
                    out.print("Mensagem nao enviada");

                }

                //out.println("Voce Disse: " + operacao);
                out.flush();
            }

            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void menuReestrito(PrintWriter out) {
        out.println("\u001B[2J");
        out.println("1 --> Registar user's");
        out.println("2 --> Login");
        out.println("0 --> Sair");
        out.println("Seleccione sua opcao:");
        out.flush();
    }

    private void menu(PrintWriter out) {
        out.println("\u001B[2J");
        out.println("3 --> Listar Eventos");
        out.println("4 --> Ver Detalhes de evento");
        out.println("5 --> Inscrever-se num evento");
        out.println("6 --> Pesquisar e listar");
        out.println("6 --> Listar eventos por Utilizador");
        out.println("0 --> Sair");
        out.println("Seleccione sua opcao:");
        out.flush();
    }

}
