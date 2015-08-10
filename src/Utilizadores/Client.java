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
    public Client() {

    }

    public void run() throws Exception {
        System.out.println("inicio do run");
        try {
            sk = new Socket("127.0.0.1", 2015);

            //manipuladores de input output
            ObjectOutputStream cos = new ObjectOutputStream(sk.getOutputStream());
            ObjectInputStream cis = new ObjectInputStream(sk.getInputStream());

            
            do {
            System.out.println("1 --> Registar user's");
            System.out.println("2 --> Login");
            System.out.println("3 --> Listar Eventos");
            System.out.println("4 --> Ver Detalhes de evento");
            System.out.println("5 --> Inscrever-se num evento");
            System.out.println("6 --> Pesquisar e listar");
            System.out.println("6 --> Listar eventos por Utilizador");
            System.out.println("7 --> Sair");

            

                operacao = Integer.parseInt(JOptionPane.showInputDialog("Seleccione a operacao desejada"));

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
            } while (operacao==0);
            cos.close();
            cis.close();
            sk.close();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
