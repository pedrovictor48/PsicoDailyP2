import java.util.Scanner;
import java.util.ArrayList;

public class App {
    static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    static ArrayList<Psicologo> psicologos = new ArrayList<Psicologo>();
    static ArrayList<Consulta> consultas = new ArrayList<Consulta>();
    //static Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        
        int escolha = -1;
        Scanner leitor = new Scanner(System.in);
        do  {
            
            // for (Paciente x : pacientes)
            // {
            //     System.out.println("\nPacientes:");
            //     System.out.println(x.name);
                
            //     for(Registro r : x.registros) {
            //         System.out.println("Registro:");
            //         System.out.println(r.registro);
            //         System.out.println("Data: " + r.data);
            //     }
            // }

            // for (Psicologo y : psicologos)
            // {
            //     System.out.println("Psicologos:");
            //     System.out.println(y.name);
            // }
            
            System.out.println("\nDigite a operacao que voce deseja fazer: ");
            System.out.println("[1] - Criar usuario.");
            System.out.println("[2] - Excluir usuario.");
            System.out.println("[3] - Login.");
            System.out.println("[-1] - Sair do programa");
            
            
            escolha = leitor.nextInt();
            if(escolha == 1) {
                addUser();

                // printando lista de usuários:
                // System.out.println("\nPrintando Psicologos");
                // for(Psicologo p : psicologos) {
                //     System.out.println(p.name);
                // }
                // System.out.println("\nPrintando Pacientes");
                // for(Paciente p : pacientes){
                //     System.out.println(p.name);
                // }
            }
            else if(escolha == 2){
                excluirUser();
            }
            else if(escolha == 3) {
                login();
            }else if(escolha != -1){
                System.out.println("Opecao invalida.");
            }
        } while(escolha != -1);
        leitor.close();
    }

    public static void addUser() {
        Scanner leitor = new Scanner(System.in);
        do {
            System.out.println("\n[1] - para paciente \n[2] - para psicólogo");
            int escolha = leitor.nextInt();

            if(escolha == 1) {
                addPaciente();
                break;
            }
            else if(escolha == 2){
                addPsicologo();
                break;
            }else{
                System.out.println("\nDigite um numero valido");
            }
        } while (true);
        
    }

    public static void addPaciente() {
        String id, name, password, cpf;
 

        Scanner leitor = new Scanner(System.in);
        System.out.println("\nId: ");
        id = leitor.nextLine();

        System.out.println("Nome: ");
        name = leitor.nextLine();

        System.out.println("Senha: ");
        password = leitor.nextLine();

        System.out.println("CPF: ");
        cpf = leitor.nextLine();

        Paciente novoPaciente = new Paciente(id, name, password, cpf);
        pacientes.add(novoPaciente);
    }

    public static void addPsicologo() {
        String id, name, password, cpf, crp;

        Scanner leitor = new Scanner(System.in);
        System.out.println("\nId: ");
        id = leitor.nextLine();

        System.out.println("Nome: ");
        name = leitor.nextLine();

        System.out.println("Senha: ");
        password = leitor.nextLine();

        System.out.println("CPF: ");
        cpf = leitor.nextLine();
        
        System.out.println("CRP: ");
        crp = leitor.nextLine();

        Psicologo novoPsicologo = new Psicologo(id, name, password, cpf, crp);
        psicologos.add(novoPsicologo);
    }

    static void login(){
        Scanner leitor = new Scanner(System.in);
        System.out.println("\n[1] - para paciente \n[2] - para psicólogo \n[-1] - Retornar");
        int escolha = leitor.nextInt();
        do {
            if(escolha == 1){
                loginPaciente();
                break;
            }else if(escolha == 2){
                loginPsicologo();
                break;
            }else if(escolha == -1){
                break;
            }
        } while (true);   
    }

    static void loginPaciente(){
        Scanner leitorPaci = new Scanner(System.in);

        System.out.println("Informe ID do paciente");
        String id_paci = leitorPaci.nextLine();

        Paciente paciente = Paciente.achePorId(pacientes, id_paci);
        if(paciente == null){
            //erro
            System.out.println("Paciente nao cadastrado");
        }else{
            System.out.println("Informe sua senha:");
            String senha_paci = leitorPaci.nextLine();
            if(paciente.password.equals(senha_paci)){
                paciente.MenuPaciente(psicologos, consultas);
            }else{
                System.out.println("Senha incorreta:");
            }
        }
    }

    static void loginPsicologo(){
        Scanner leitorPsico = new Scanner(System.in);
        
        System.out.println("Informe o ID do psicólogo");
        String id_psico = leitorPsico.nextLine();
        Psicologo psicologo = Psicologo.achePorId(psicologos, id_psico);
        if(psicologo == null) {
            //erro
            System.out.println("Psicologo nao cadastrado");
        }
        else {
            System.out.println("Informe sua senha:");
            String senha_psico = leitorPsico.nextLine();
            if(psicologo.password.equals(senha_psico)){
                psicologo.MenuPsicologo(pacientes, consultas);
            }else{
                System.out.println("Senha incorreta:");
            }  
        }   
    }

    static void excluirUser() {
        String id;
        Scanner leitor = new Scanner(System.in);
        System.out.println("Id do usuário: ");
        id = leitor.nextLine();

        // achando por id
        for(int i = 0; i < pacientes.size(); i++) {
            Paciente paciente = pacientes.get(i);
            if(paciente.id.equals(id)) {
                pacientes.remove(i);
                System.out.println("Usuário excluido.");
                return;
            }
        }

        for(int i = 0; i < psicologos.size(); i++) {
            Psicologo psicologo = psicologos.get(i);
            if(psicologo.id.equals(id)) {
                psicologos.remove(i);
                System.out.println("Usuário excluido.");
                return;
            }
        }

        System.out.println("Usuário não encontrado.");
    }

    static boolean existPaciente(ArrayList<Paciente> lista, String id){
        for(Paciente paciente : lista) {
            if(paciente.id.equals(id)) 
                return true;
        }
        return false;
    }
}