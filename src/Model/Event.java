package Model;

import java.io.Serializable;
import java.util.ArrayList;
import sun.util.calendar.BaseCalendar.Date;




public class Event implements Serializable{
    
   private String titulo,descricao,local,tipo,ListaInscritos[],mailCriador;
   private String data;
   private ArrayList<Registration> Registrations = new ArrayList<Registration>();
   
    public Event(String titulo, String descricao, String local, String data, String criador) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.mailCriador = criador;
    }

    public String getMailCriador() {
        return mailCriador;
    }

    public void setMailCriador(String mailCriador) {
        this.mailCriador = mailCriador;
    }

  


    public String[] getListaInscritos() {
        return ListaInscritos;
    }

    public void setListaInscritos(String[] ListaInscritos) {
        this.ListaInscritos = ListaInscritos;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getNome() {
        return titulo;
    }

    public void setNome(String nome) {
        this.titulo = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Titulo:" + titulo + "| Local: " + local + "| data: " + data+"\n\n";
    }
    
    
 
}
