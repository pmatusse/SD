package Admin;

import Interfaces.goOutServerInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sun.util.calendar.BaseCalendar.Date;

public class GoOutAdmin {

    public static Registry adminReg;
    public static goOutServerInterface admin;

    public static void main(String[] args) throws RemoteException {

        try {

            adminReg = LocateRegistry.getRegistry("127.0.0.1", 1099);
            admin = (goOutServerInterface) adminReg.lookup("goOutServer");
            int op, tentativas = 3;
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            Scanner in;
            boolean loop = true;

            do {
                System.out.print("Digite seu e-mail: ");
                String email = stdin.readLine();
                //autenticar administrador
                if (new GoOutAdmin().validEmail(email)) {
                    System.out.println("Bem vindo ao goOut");


                    while (loop) {
                        // visualizar menu
                        System.out.println("1 --> Adicionar evento");
                        System.out.println("2 --> Listar Eventos");
                        System.out.println("3 --> Ver Detalhes de evento");
                        System.out.println("4 --> Remover evento");
                        System.out.println("Digite sua opcao:");
                        in = new Scanner(System.in);

                        //capturar escolha
                        op = in.nextInt();
                        switch (op) {
                            case 1:
                                if (admin.addEvento("Festa da Coca-cola",
                                        "Festa alusiva ao aniversario da coca-cola",
                                        "Campos do Isctem",
                                        "13/9/2015")) {
                                    
                                    System.out.println("evento adicionado com sucesso");
                                }
                                else{
                                    System.out.println("ERRO! evento nao adicionado");
                                }
                                break;
                                
                                

                            case 2:
                                break;

                            case 3:
                                admin.ListarEventosAdmin(email);
                                break;

                            case 0:
                                loop = false;
                                break;

                            default:
                                System.out.println("opcao invalida");
                        }
                    }


                } else {

                    System.out.println("Accesso negado");
                }
            } while ((tentativas--) > 0);

            System.out.println("Adeus, volte sempre");

        } catch (Exception e) {
            e.printStackTrace();
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
