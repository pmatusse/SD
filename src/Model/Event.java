package Model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Event implements Serializable {

    private String titulo, descricao, local, tipo, ListaInscritos[], mailCriador;
    private Date data;
    private ArrayList<Registration> Registrations = new ArrayList<Registration>();
    static DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
    public Event(String titulo, String descricao, String local, Date data, String criador) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.data = data;
        this.mailCriador = criador;
    }

    public Event() {

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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTitulo() {
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

    public String Detalhes() {
        return "Event{" + "titulo=" + titulo + ", descricao=" + descricao + ", local=" + local + ", tipo=" + tipo + ", mailCriador=" + mailCriador + ", data=" + data + '}';
    }

    @Override
    public String toString() {
        return "Titulo:" + titulo + "| Local: " + local + "| data: " +data + "\n\n";
    }

   

}
