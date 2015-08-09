package Implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import Interfaces.goOutServerInterface;
import Model.Tabelas;
import java.util.ArrayList;
import sun.util.calendar.BaseCalendar.Date;
import Model.*;

public class GoOutServerImpl extends UnicastRemoteObject implements goOutServerInterface {

    Tabelas tabelas = new Tabelas();
    public String adminActivo; //armazena o e-mail do admin que esta activo no momento
    //constructor

    GoOutServerImpl() throws RemoteException {
    }

    @Override
    public boolean addEvento(String titulo, String descricao, String local, String data) throws RemoteException {
        if (tabelas.Events.add(new Event(titulo, descricao, local, data, adminActivo))) {
            return true;
        }

        return false;
    }

    @Override
    public void ListarEventosAdmin(String mail) throws RemoteException {
        for (Event evento : tabelas.Events) {
            if (evento.getMailCriador().equalsIgnoreCase(mail)) {
                //evento localizado , sera impresso
                System.out.println(evento);
                
            }
        }
        

    }

    @Override
    public boolean Detalhes_ListaEventos(String titulo) throws RemoteException {
        return false;
    }

    @Override
    public void removerEvento(String titulo) throws RemoteException {
    }

    @Override
    public boolean RegistrarUser(String mail, String psw, String nome, int telemovel) throws RemoteException {
        return false;
    }

    @Override
    public boolean AutenticarUser(String mail, String psw) throws RemoteException {
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
        String emailDefinido = "paulo.matusse@ine.gov.mz";

        if (mail.equalsIgnoreCase(emailDefinido)) {
            return true;
        }

        return false;
    }

    @Override
    public String getAdminactivo() throws RemoteException {
       
        System.out.println("adminiActivo"+adminActivo);
      return adminActivo;
    }

    @Override
    public void setAdminactivo(String adminemail) throws RemoteException {
      adminActivo=adminemail;
    }

    @Override
    public ArrayList<Event>lista(String email) throws RemoteException {
    
         
              System.out.println("dados"+tabelas.Events.toString());
//           
      return tabelas.Events;
        
//         for(Event evento :tabelas.Events){
//          
//             if(evento.getMailCriador().equalsIgnoreCase(adminActivo)){
//        
//                System.out.println(evento.toString());
//                 return tabelas.Events;
//                  // System.out.println(evento.toString());
//   
//             }
//             }
//         
//         return null;
    }
}
