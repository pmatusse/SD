package Implement;

import Model.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidorthread extends Thread {

    private Socket socket;
    ObjectInputStream serverInputStream;
    ObjectOutputStream serverOutputStream;

    public Servidorthread(Socket socket) {

        this.socket = socket;
        try {
            serverInputStream = new ObjectInputStream(socket.getInputStream());
            serverOutputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(goOutServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Servidorthread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {

        int operacao;//variavel que recebe o numero da operacao
        Object objecto;// variavel que recebe o objecto a ser tratado na operacao seleccionada
        
        try {
        while(true){
            operacao = (Integer) serverInputStream.readObject();
            objecto = serverInputStream.readObject();

            switch (operacao) {
                case 1:
                    System.out.println("Registar user's");
                    System.out.println((Event) objecto);
                    Event e = (Event) objecto;
                    e.setData(new Date().toString());
                    serverOutputStream.writeUnshared(e);
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

                default:

                    break;
            }

        }
        } catch (IOException ex) {
            Logger.getLogger(Servidorthread.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidorthread.class.getName()).log(Level.SEVERE, null, ex);
        }
       // desconnectar();
    }

}
