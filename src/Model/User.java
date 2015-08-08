package Model;

import java.io.Serializable;

public class User implements Serializable{

    private String email, password, nome;
    private int telemovel;
    

    public User(String email, String password, String nome, int telemovel) {
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.telemovel = telemovel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(int telemovel) {
        this.telemovel = telemovel;
    }
    
    
    
}
