package Model;

import java.io.Serializable;
import java.util.Date;

public class Registration implements Serializable{
    String Evento;
    int User_telefone;
    Date dataRegistro;

    public Registration(String Evento, int User_telefone, Date dataRegistro) {
        this.Evento = Evento;
        this.User_telefone = User_telefone;
        this.dataRegistro = dataRegistro;
    }

    public String getEvento() {
        return Evento;
    }

    public void setEvento(String Evento) {
        this.Evento = Evento;
    }

    public int getUser_telefone() {
        return User_telefone;
    }

    public void setUser_telefone(int User_telefone) {
        this.User_telefone = User_telefone;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
    
    
}
