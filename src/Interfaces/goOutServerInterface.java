package Interfaces;


import Model.Event;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Vector;
import sun.util.calendar.BaseCalendar.Date;

public interface goOutServerInterface extends Remote {

    //Assinaturas de Admin
    //sem password, qualquer email e aceite sem confirmacao
    //public boolean  RegistrarAdmin(String mail) throws RemoteException;
    public boolean AutenticarAdmin(String mail) throws RemoteException;

    public boolean addEvento(String titulo, String descricao, String local, String data) throws RemoteException;

    public Vector ListarEventosAdmin(String mail) throws RemoteException;

    public Vector DetalhesEvento(String titulo) throws RemoteException; //se nao encontra evento retorna false

    public void removerEvento(String titulo) throws RemoteException; //se nao encontra evento retorna false

    //Assinaturas para User
    public boolean RegistrarUser(String mail, String psw, String nome, int telemovel) throws RemoteException;

    public boolean AutenticarUser(String mail, String psw) throws RemoteException;

    public String getAdminactivo() throws RemoteException;

    public void setAdminactivo(String adminemail) throws RemoteException;

    public void ListarEventos() throws RemoteException;

    public boolean VisualizarDetalhesEvento(String nome) throws RemoteException; //se nao encontra evento retorna false

    public boolean InscreverEvento(String nome) throws RemoteException; //se nao encontra evento retorna false

    public boolean PesquisarEventoPorData(String data) throws RemoteException;//se nao encontra evento retorna false

    public boolean ListarEventoInscrito(int telemovel) throws RemoteException; //se nao encontra eventos retorna false

    public void FecharAplicacao() throws RemoteException;
   
}
