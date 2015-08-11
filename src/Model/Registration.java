package Model;

import java.io.Serializable;
import java.util.Date;

public class Registration implements Serializable{
    String Evento;
    User user;
    Date dataRegistro;
    
     public Registration(String Evento, User user, Date dataRegistro) {
        this.Evento = Evento;
        this.user = user;
        this.dataRegistro = dataRegistro;
    }

    public String getEvento() {
        return Evento;
    }

    public void setEvento(String Evento) {
        this.Evento = Evento;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

   

   
    
}
