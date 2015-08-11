/*
 * Decompiled with CFR 0_101.
 * 
 * Could not load the following classes:
 *  Model.Event
 *  Model.Tabelas
 *  Model.User
 */
package Utilizadores;

import Model.Event;
import Model.Tabelas;
import Model.User;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.Socket;
import java.util.ArrayList;

public class GoOutUser
implements Runnable {
    private Socket clientSocket;
    private static Object mutex = new Object();
    private static int numberOfClients;
    String operacao;
    Tabelas tabelas = new Tabelas();
    ObjectOutputStream cos;
    ObjectInputStream cis;

    public GoOutUser(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(this.clientSocket.getOutputStream());
            Object object = mutex;
            synchronized (object) {
                System.out.println("Cliente N. " + ++numberOfClients + " Activo!");
            }
            this.operacao = "";
            this.menu(out);
            while (!this.operacao.equals("0")) {
                String password;
                String email;
                this.operacao = in.readLine();
                System.out.println("Client " + this.clientSocket + " said: \n   " + this.operacao);
                if (this.operacao.equals("1")) {
                    out.println("Digite seu Email");
                    out.flush();
                    email = in.readLine();
                    out.println("Digite sua Password");
                    out.flush();
                    password = in.readLine();
                    out.println("Digite seu nome");
                    out.flush();
                    String nome = in.readLine();
                    out.println("Digite Numero de telefone");
                    out.flush();
                    String _telefone = in.readLine();
                    int telefone = Integer.parseInt(_telefone);
                    if (Tabelas.Users.add(new User(email, password, nome, telefone))) {
                        out.print("Utilizador registrado com sucesso");
                        out.flush();
                        this.menuReestrito(out);
                        continue;
                    }
                    out.print("Utilizador nao registrado! ocorreu um erro");
                    out.flush();
                    continue;
                }
                if (this.operacao.equals("2")) {
                    out.println("Digite seu Email");
                    out.flush();
                    email = in.readLine();
                    out.println("Digite sua Password");
                    out.flush();
                    password = in.readLine();
                    boolean login = false;
                    for (User user : Tabelas.Users) {
                        if (!user.getEmail().equalsIgnoreCase(email) || !user.getPassword().equals(password)) continue;
                        this.menu(out);
                        login = true;
                    }
                    if (login) continue;
                    out.print("Credenciais invalidas, tente mais uma vez");
                    out.flush();
                    this.menuReestrito(out);
                    continue;
                }
                if (this.operacao.equals("3")) {
                    System.out.println("ha " + Tabelas.Events.size() + "eventos");
                    out.println("\u001b[2J");
                    out.flush();
                    out.println("LISTA DE EVENTOS");
                    out.flush();
                    if (Tabelas.Events.size() > 0) {
                        for (Event evento : Tabelas.Events) {
                            out.println(evento.getTitulo());
                            out.flush();
                        }
                    } else {
                        out.println("Nao ha eventos registrados no sistema, tente mais tarde");
                        out.flush();
                    }
                    out.println("pressione qualquer ENTER para voltar ao menu...");
                    out.flush();
                    in.readLine();
                    this.menu(out);
                    continue;
                }
                if (this.operacao.equals("4")) {
                    System.out.println("dentro de " + this.operacao);
                    out.println("Ver Detalhes de evento");
                    out.flush();
                    this.menu(out);
                    continue;
                }
                if (this.operacao.equals("5")) {
                    System.out.println("dentro de " + this.operacao);
                    out.println("Inscrever-se num evento");
                    out.flush();
                    this.menu(out);
                    continue;
                }
                if (this.operacao.equals("5")) {
                    System.out.println("dentro de " + this.operacao);
                    out.println("Pesquisar e listar");
                    out.flush();
                    this.menu(out);
                    continue;
                }
                if (this.operacao.equals("6")) {
                    System.out.println("dentro de " + this.operacao);
                    out.println("Listar eventos por Utilizador");
                    out.flush();
                    this.menu(out);
                    continue;
                }
                if (this.operacao.equals("7")) {
                    System.out.println("dentro de " + this.operacao);
                    out.println("Listar eventos por Utilizador");
                    out.flush();
                    this.menu(out);
                    continue;
                }
                out.print("Mensagem nao enviada");
            }
            in.close();
            out.close();
            this.clientSocket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void menuReestrito(PrintWriter out) {
        out.println("\u001b[2J");
        out.println("1 --> Registar user's");
        out.println("2 --> Login");
        out.println("0 --> Sair");
        out.println("Seleccione sua opcao:");
        out.flush();
    }

    private void menu(PrintWriter out) {
        out.println("\u001b[2J");
        out.println("3 --> Listar Eventos");
        out.println("4 --> Ver Detalhes de evento");
        out.println("5 --> Inscrever-se num evento");
        out.println("6 --> Pesquisar e listar");
        out.println("7 --> Listar eventos por Utilizador");
        out.println("0 --> Sair");
        out.println("Seleccione sua opcao:");
        out.flush();
    }
}
