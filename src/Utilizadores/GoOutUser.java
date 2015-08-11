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
import Model.Registration;

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
import java.util.Date;

public class GoOutUser
        implements Runnable {

    private Socket clientSocket;
    private static Object mutex = new Object();
    private static int numberOfClients;
    String operacao;
    Tabelas tabelas = new Tabelas();
    User user;//serve para identificar unicamente o utilizador activo

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

//REGISTAR USER  ############################################################ 
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
//LOGIN  ############################################################                
                if (this.operacao.equals("2")) {
                    out.println("Digite seu Email");
                    out.flush();
                    email = in.readLine();
                    out.println("Digite sua Password");
                    out.flush();
                    password = in.readLine();
                    boolean login = false;
                    for (User user : Tabelas.Users) {
                        if (!user.getEmail().equalsIgnoreCase(email) || !user.getPassword().equals(password)) {
                            this.user = user;
                            menu(out);
                            continue;
                        }
                        this.menu(out);
                        login = true;
                    }
                    if (login) {
                        continue;
                    }
                    out.print("Credenciais invalidas, tente mais uma vez");
                    out.flush();
                    this.menuReestrito(out);
                    continue;
                }

//LISTAR EVENTOS ############################################################
                if (this.operacao.equals("3")) {
                    System.out.println("ha " + Tabelas.Events.size() + "eventos");
                    out.println("\u001b[2J");
                    out.flush();
                    out.println("LISTA DE EVENTOS");
                    out.flush();
                    if (Tabelas.Events.size() > 0) {
                        for (Event evento : Tabelas.Events) {
                            out.println("\t" + evento.getTitulo());
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
//VER DETALHES DE EVENTO############################################################                 
                if (this.operacao.equals("4")) {
                    System.out.println("ha " + Tabelas.Events.size() + "eventos");
                    out.println("Digite o titulo do evento");
                    out.flush();
                    String titulo = in.readLine();
                    out.println("\u001b[2J");
                    out.flush();
                    out.println("DETALHES DE EVENTO");
                    out.flush();
                    boolean controler = false;
                    if (Tabelas.Events.size() > 0) {

                        for (Event evento : Tabelas.Events) {
                            if (evento.getTitulo().equalsIgnoreCase(titulo)) {
                                out.println("TITULO:\t" + evento.getTitulo());
                                out.println("DATA:\t" + evento.getData());
                                out.println("LOCAL:\t" + evento.getLocal());
                                out.println("TIPO:\t" + evento.getTipo());
                                out.println("CRIADOR:\t" + evento.getMailCriador());
                                out.flush();

                                out.println("REGISTADOS");
                                if (evento.getRegistrations().size() > 0) {
                                    out.println("Numero de registados:\t" + evento.getRegistrations().size());
                                    out.flush();
                                    /*for (Registration reg : evento.getRegistrations()) {
                                        out.println("\t" + reg.getUser().getNome());
                                        out.flush();
                                    }*/
                                } else {
                                    out.println("Nenhum registro de participantes ...");
                                    out.flush();
                                }
                                controler = true;
                            }
                        }

                        if (!controler) {
                            out.println("O evento pesquisado Nao consta no sitema, reveja a lista de eventos");
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
//INSCREVRE_SE em EVENTO############################################################                 
                if (this.operacao.equals("5")) {
                    System.out.println("ha " + Tabelas.Events.size() + "eventos");
                    out.println("Digite o titulo do evento");
                    out.flush();
                    String titulo = in.readLine();
                    boolean controler = false;
                    if (Tabelas.Events.size() > 0) {
                        for (Event evento : Tabelas.Events) {
                            if (evento.getTitulo().equalsIgnoreCase(titulo)) {
                                evento.getRegistrations().add(
                                        new Registration(titulo, user, new Date())
                                );
                                controler = true;
                            }
                        }

                        if (!controler) {
                            out.println("erro durante o registro, reveja a lista de eventos");
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

                out.print("Mensagem nao enviada");
            }

            in.close();
            out.close();
            this.clientSocket.close();
        } catch (Exception e) {
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
