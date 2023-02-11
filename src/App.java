import java.util.Scanner;
import java.util.ArrayList;

public class App {
    static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
    static ArrayList<Psicologo> psicologos = new ArrayList<Psicologo>();
    //static Scanner leitor = new Scanner(System.in);

    public static void main(String[] args) throws Exception{
        
        int escolha = -1;
        do  {
            
            // for (Paciente x : pacientes)
            // {
            //     System.out.println("Pacientes:");
            //     System.out.println(x.name);
                
            //     for(Registro r : x.registros) {
            //         System.out.println("Reg:");
            //         System.out.println(r.registro);
            //     }
            // }

            // for (Psicologo y : psicologos)
            // {
            //     System.out.println("Psicologos:");
            //     System.out.println(y.name);
            // }
            
            System.out.println("Digite a operacao que voce deseja fazer: ");
            System.out.println("1 - Criar usuário.");
            System.out.println("2 - Adicionar registro.");
            System.out.println("3 - Excluir usuário.");
            System.out.println("4 - Menu do psicólogo");
            System.out.println("-1 - Sair do programa");
            
            Scanner leitor = new Scanner(System.in);
            escolha = leitor.nextInt();
            if(escolha == 1) {
                addUser();
                // printando lista de usuários:
                for(Psicologo p : psicologos) {
                    System.out.println(p.id);
                }
            }
            else if(escolha == 2) {
                addRegistro();
            }
            else if(escolha == 3) {
                excluirUser();
            }
            else if(escolha == 4) {
                Scanner leitorPsico = new Scanner(System.in);
                System.out.println("Informe o ID do psicólogo");
                String id_psico = leitorPsico.nextLine();
                Psicologo psicologo = Psicologo.achePorId(psicologos, id_psico);
                if(psicologo == null) {
                    //erro
                    System.out.println("erro");
                }
                else {
                    psicologo.MenuPsicologo(pacientes);
                }
            }
        } while(escolha != -1);
    }

    public static void addUser() {
        Scanner leitor1 = new Scanner(System.in);
        System.out.println("1 para paciente, 2 para psicólogo");
        int escolha = leitor1.nextInt();

        if(escolha == 1) {
            addPaciente();
        }
        else {
            addPsicologo();
        }
    }

    public static void addPaciente() {
        String id, name, password, cpf, id_psico;

        Scanner leitor = new Scanner(System.in);
        System.out.println("Id: ");
        id = leitor.nextLine();

        System.out.println("Nome: ");
        name = leitor.nextLine();

        System.out.println("Senha: ");
        password = leitor.nextLine();

        System.out.println("CPF: ");
        cpf = leitor.nextLine();
        
        System.out.println("ID do psicologo: ");
        id_psico = leitor.nextLine();

        Paciente novoPaciente = new Paciente(id, name, password, cpf, id_psico);
        pacientes.add(novoPaciente);
    }

    public static void addPsicologo() {
        String id, name, password, cpf, crp;

        Scanner leitor = new Scanner(System.in);
        System.out.println("Id: ");
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
    static void addRegistro() {
        String id_pac;

        Scanner leitor = new Scanner(System.in);
        System.out.println("Informe ID do usuario: ");
        id_pac = leitor.nextLine();

        Paciente paciente = Paciente.achePorId(pacientes, id_pac);
        if(paciente == null) {
            System.out.println("Paciente não encontrado");
            return;
        }
        else {
            paciente.addRegistro();
        }
    }

    static void excluirRegistro() {
        
        System.out.println("Insira o id do paciente: ");
        Scanner leitor = new Scanner(System.in);

        String id_pac = leitor.nextLine();
        Paciente paciente = Paciente.achePorId(id_pac);
        if(paciente == null) {
            System.out.println("Paciente não encontrado");
        }
        else {
            paciente.excluirRegistro();
        }
    }
}
