package Utilizadores;

import Interfaces.goOutServerInterface;
import Model.Event;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.util.calendar.BaseCalendar.Date;

public class GoOutAdmin {

    public static Registry adminReg;
    public static goOutServerInterface admin;
    private static Scanner scan = new Scanner(System.in);
    private static String email, password, nome;
    private static int telemovel;
    private static String titulo, descricao, local, data, mailCriador;

    public static void main(String[] args) throws RemoteException {
        menuPrincipal();
    }

    /*Inicia o meu principa;*/
    public static void menuPrincipal() {
        int opcao = 0;
        System.out.println("****BEM VINDO A GOOUT EVENT****");
        System.out.println("********1 --> login************");
        System.out.println("********2 --> sair*************");
        System.out.println("********GOOUT EVENT************");

        opcao = scan.nextInt();
        operacoes1(opcao);
    }
    /*menu das operacoes de administrador*/

    public static void menuAdministrado() throws RemoteException {
        int opcao = 0;
        System.out.println("\n");
        System.out.println("1 --> Adicionar user's");
        System.out.println("2 --> Adicionar evento");
        System.out.println("3 --> Listar Eventos por administradores");
        System.out.println("4 --> Ver Detalhes de evento");
        System.out.println("5 --> Remover evento");
        System.out.println("6 --> Menu Principal");
        System.out.println("7 --> Sair");
        opcao = scan.nextInt();
        operacoesaAdministrador(opcao);
    }

    public static void operacoes1(int operacao) {
        try {
            adminReg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            admin = (goOutServerInterface) adminReg.lookup("goOutServer");
            int op, tentativas = 3;
            Scanner in;
            boolean loop = true;

            switch (operacao) {
                case 1:
                    System.out.print("Digite seu e-mail: \n");
                    String email = scan.next();
                    //autenticar administrador
                    if (new GoOutAdmin().validEmail(email)) {
                        System.out.println("Bem vindo ao goOut:" + email);
                        admin.setAdminactivo(email);
                        menuAdministrado();

                    }else{
                        System.out.println("O usuario nao existe");
                    }
                    menuPrincipal();
                    break;
                case 2:
                    System.out.println("GOODBYE VOLTE SEMPRE");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao nao valida");
                    menuPrincipal();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Ocorreu  um erro \n exception: " + e.getMessage());
        }
        //invoca o menu
        menuPrincipal();
    }

    public static void operacoesaAdministrador(int op) throws RemoteException {

        try {
            adminReg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            admin = (goOutServerInterface) adminReg.lookup("goOutServer");
            //capturar escolha
            System.out.println(op);
            switch (op) {
                case 1:

                    System.out.println("Introduza a password:\n");
                    password = scan.next();

                    System.out.println("Introduza o nome : \n");
                    nome = scan.next();

                    System.out.println("Introduza o numero de telefone:\n");
                    telemovel = scan.nextInt();
                    if (admin.RegistrarUser(email, nome, password, telemovel)) {
                        System.out.println("Registado novo user:\t Emai:" + email);
                        menuAdministrado();
                    } else {
                        System.out.println("Usuario nao cadastrado");
                        menuAdministrado();
                    }
                    break;

                case 2:
//                    (String titulo, String descricao, String local, String data, String mailCriador)
                    System.out.println("Introduza o titulo do evento:\n");
                    titulo = scan.next();
                    System.out.println("Introduza a descricao:\n");
                    descricao = scan.next();

                    System.out.println("Introduza o local: \n");
                    local = scan.next();

                    System.out.println("Introduza a data:\n");
                    data = scan.next();
                    if (admin.addEvento(titulo, descricao, local, data)) {
                        System.out.println("Registado novo Evento:\t Evento:" + titulo);
                        menuAdministrado();
                    } else{
                     System.out.println("O evento nao foi addicionado");
                         menuAdministrado();
                    }
                    break;
                case 3:
                    Vector t = admin.ListarEventosAdmin(admin.getAdminactivo());
                    for (int i = 0; i < (int) t.size(); i++) {
                        Event evento = (Event) t.get(i);
                        System.out.println(evento);
                    }

                    menuAdministrado();
                    break;

                case 4:

                    System.out.println("Introduza o titulo do evento :\n");
                    titulo = scan.next();
                    Vector a = admin.DetalhesEvento(titulo);
                    for (int i = 0; i < (int) a.size(); i++) {
                        Event evento = (Event) a.get(i);
                        System.out.println(evento);

                        menuAdministrado();
                    }
                    break;

                case 5:

                    System.out.println("Introduza o titulo do evento :\n");
                    titulo = scan.next();
                    if(admin.removerEvento(titulo)==true)
                    {
                     System.out.println("evento removido com sucesso");
                      menuAdministrado();
                    }else{
                     System.out.println("Evento nao removido");
                    menuAdministrado();
                    }
                    break;
                default:
                    System.out.println("opcao invalida");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Ocorreu  um erro \n exception: " + e.getMessage());
        }
    }

    public boolean validEmail(String email) {
        //System.out.println("Metodo de validacao de email");
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (m.find()) {
            //System.out.println("O email "+email+" e valido");
            return true;
        } else {
            System.out.println("O E-mail " + email + " é inválido");
            return false;
        }
    }
}
