package Utilizadores;

import Model.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import java.util.logging.*;
import javax.swing.JOptionPane;

class Client {

    protected Socket sk;
    int operacao;
ObjectOutputStream cos;
ObjectInputStream cis;
    public Client() {

    }

    public void run() throws Exception {
        System.out.println("inicio do run");
        try {
            sk = new Socket("127.0.0.1", 2015);

            //manipuladores de input output
            menu();
 
            cos.close();
            cis.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void menu() throws IOException, ClassNotFoundException {
        cos = new ObjectOutputStream(sk.getOutputStream());
        cis = new ObjectInputStream(sk.getInputStream());
        do {

            operacao = Integer.parseInt(JOptionPane.showInputDialog(
                    "1 --> Registar user's\n"
                    + "2 --> Login\n"
                    + "3 --> Listar Eventos\n"
                    + "4 --> Ver Detalhes de evento\n"
                    + "5 --> Inscrever-se num evento\n"
                    + "6 --> Pesquisar e listar\n"
                    + "6 --> Listar eventos por Utilizador\n"
                    + "7 --> Sair\n"
            ));

            switch (operacao) {
                case 1:
                    cos.writeObject(operacao);
                    cos.writeObject(new Event("desafio Total", "Eu consegui", "em casa", "24/09/25", "paulo"));

                    JOptionPane.showMessageDialog(null, (Event) cis.readObject());
                    
                    break;
                case 2:

                    System.out.println("Login");

                case 3:
                    System.out.println("Listar Eventos");
                    break;
                case 4:
                    System.out.println("Ver Detalhes de evento");
                    break;
                case 5:
                    System.out.println("Inscrever-se num evento");

                case 6:
                    System.out.println("Pesquisar e listar");
                    break;
                case 7:
                    System.out.println("Sair");
                    break;
                default:
                    System.out.println("Operacao Invalida");

                    break;
            }

        } while (operacao == 0);

    }

}
