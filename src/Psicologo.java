import java.util.ArrayList;
import java.util.Scanner;

public class Psicologo extends Usuario{
    String crp;

    public Psicologo(String id, String name, String password, String cpf, String crp){
        super(id, name, password, cpf);
        this.crp = crp;
    }

    public void MenuPsicologo(ArrayList<Paciente> listaPacientes)
    {
        int escolha = -1;
        Scanner leitor = new Scanner(System.in);
        do  { 
            System.out.println("\nDigite a operacao que voce deseja fazer: ");
            System.out.println("[1] - Exibir pacientes.");
            System.out.println("[2] - Mostrar registros.");
            System.out.println("[3] - Visualizar registros.");
            System.out.println("[4] - Vincular paciente.");
            System.out.println("[5] - Desvincular paciente.");
            System.out.println("[-1] - Sair do menu do psicólogo.");
            
            
            escolha = leitor.nextInt();
            if(escolha == 1) {
                ListarPacientes(listaPacientes);
            }
            else if(escolha == 2) {
                ExibirRegistros(listaPacientes);
            }else if(escolha == 3){
                ExibirRegistros(listaPacientes);
            }else if(escolha == 4){
                vincularPaciente(listaPacientes);
            }else if(escolha == 5){
                desvincularPaciente(listaPacientes);
            }
            else if(escolha != -1){
                System.out.println("Digite uma opcao valida!");
            }
        } while(escolha != -1);
    }

    public void ListarPacientes(ArrayList<Paciente> listaPacientes)
    {
        Boolean achou = false;
    
        for (Paciente cliente:listaPacientes)
        {
            if (cliente.id_psico.equals(this.id))
            {
                achou = true;
                
                System.out.println("PACIENTE " + cliente.name + ":");
                System.out.println("ID: " + cliente.id);
                System.out.println("CPF: " + cliente.cpf);
            }
        }

        if (!achou)
        {
            System.out.println("\nVOCÊ NÃO TEM NENHUM PACIENTE.");
        }
    }

    public void ExibirRegistros(ArrayList<Paciente> listaPacientes)
    {
        System.out.println("Digite o id do paciente que voce deseja acessar os registros: ");
        Scanner leitor = new Scanner(System.in);
        String id_paciente = leitor.nextLine();

        Paciente paciente = Paciente.achePorId(listaPacientes, id_paciente);
        if(paciente == null) {
            System.out.println("Paciente não encontrado.");
        }
        else if (!paciente.id_psico.equals(id)) {
            System.out.println("Paciente não associado.");
        }
        else {
            paciente.perfil();
        }
    }

    public void vincularPaciente(ArrayList<Paciente> listaPacientes){
        for(Paciente p : listaPacientes){
            System.out.println(p.id + p.name);
        }

        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o id do paciente que voce deseja vincular:");
        String id_paciente = leitor.nextLine();

        Paciente paciente = Paciente.achePorId(listaPacientes, id_paciente);

        if (paciente == null) {
            System.out.println("Paciente nao encontrado.");
            return;
        } else if (paciente.id_psico.equals("null")) {
            paciente.id_psico = id;
            System.out.println("Paciente vinculado.");
        } else {
            System.out.println("Paciente ja possui psicologo vinculado.");
        }
    }

    public void desvincularPaciente(ArrayList<Paciente> listaPacientes){
        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o id do paciente que voce deseja desvincular:");
        String id_paciente = leitor.nextLine();

        Paciente paciente = Paciente.achePorId(listaPacientes, id_paciente);

        if (paciente == null) {
            System.out.println("Paciente nao encontrado.");
            return;
        }else if (paciente.id_psico != id) {
            System.out.println("Ele nao e seu paciente.");
            return;
        }

        paciente.id_psico = "null";
    }

    static Psicologo achePorId(ArrayList<Psicologo> lista, String id) {
        for(Psicologo psicologo: lista) {
            System.out.println(psicologo.id + psicologo.name);
            if(psicologo.id.equals(id)){
                System.out.println(psicologo.id + psicologo.name);
                return psicologo;
            }
        }
        return null;
    }
}
