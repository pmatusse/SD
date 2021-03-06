package Implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Interfaces.goOutServerInterface;
import Model.Tabelas;
import java.util.ArrayList;
import java.util.Date;
import Model.*;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Vector;

public class GoOutServerImpl extends UnicastRemoteObject implements goOutServerInterface {

    //tratamento da conexao tcp
    ServerSocket myServerSocket;
    boolean ServerOn = true;
    boolean tcp = false;

    Tabelas tabelas = new Tabelas();
    public String adminActivo; //armazena o e-mail do admin que esta activo no momento

    //constructor
    GoOutServerImpl() throws RemoteException {
        //criando directorio para persistencia
   
    }

    @Override
    public boolean addEvento(String titulo, String descricao, String local, Date data) throws RemoteException {
        if (tabelas.Events.add(new Event(titulo, descricao, local, data, adminActivo))) {
            return true;
        }

        return false;
    }

    public Vector ListarEventosAdmin(String mail) throws RemoteException {
        Vector tEventos = new Vector();
        for (Event evento : tabelas.Events) {
            if (evento.getMailCriador().equalsIgnoreCase(mail)) {
                //evento localizado , sera impresso
                tEventos.add(evento);
            }
        }
        return tEventos;

    }

    @Override
    public Event DetalhesEvento(String titulo) throws RemoteException {

        for (Event evento : tabelas.Events) {
            if (evento.getTitulo().equalsIgnoreCase(titulo)) {
                //evento localizado , sera impresso
                return evento;
            }
        }
        return new Event();
    }

    @Override
    public boolean removerEvento(String titulo) throws RemoteException {
        for (Event evento : tabelas.Events) {
            if (evento.getTitulo().equalsIgnoreCase(titulo)) {
                //evento localizado , removido
                tabelas.Events.remove(evento);
                return true;
            } else {
                return false;
            }
        }
        return removerEvento(titulo);

    }

    @Override
    public boolean RegistrarUser(String mail, String psw, String nome, int telemovel) throws RemoteException {
        tabelas.Users.add(new User(mail, psw, nome, telemovel));
     
        return true;
    }

    @Override
    public boolean AutenticarUser(String mail, String psw) throws RemoteException {
        for (User user : tabelas.Users) {
            if (user.getEmail().equalsIgnoreCase(mail)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void ListarEventos() throws RemoteException {
    }

    @Override
    public boolean VisualizarDetalhesEvento(String nome) throws RemoteException {
        return false;
    }

    @Override
    public boolean InscreverEvento(String nome) throws RemoteException {
        return false;
    }

    @Override
    public boolean PesquisarEventoPorData(String data) throws RemoteException {
        return false;
    }

    @Override
    public boolean ListarEventoInscrito(int telemovel) throws RemoteException {
        return false;
    }

    @Override
    public void FecharAplicacao() throws RemoteException {
    }

    @Override
    public boolean AutenticarAdmin(String mail) throws RemoteException {

        if (mail.equalsIgnoreCase(mail)) {
            return true;
        }

        return false;
    }

    @Override
    public String getAdminactivo() throws RemoteException {

        System.out.println("adminiActivo" + adminActivo);
        return adminActivo;
    }

    @Override
    public void setAdminactivo(String adminemail) throws RemoteException {
        adminActivo = adminemail;
    }

}
