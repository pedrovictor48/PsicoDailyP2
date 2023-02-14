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
            System.out.println("Digite a operacao que voce deseja fazer: ");
            System.out.println("1 - Exibir pacientes.");
            System.out.println("2 - Mostrar registros.");
            System.out.println("-1 - Sair do menu do psicólogo.");
            
            
            escolha = leitor.nextInt();
            if(escolha == 1) {
                ListarPacientes(listaPacientes);
            }
            else if(escolha == 2) {
                ExibirRegistros(listaPacientes);
            }
            else if(escolha == -1) {
                return;
            }
        } while(escolha != -1);
        leitor.close();
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
            return;
        }
        else if (!paciente.id_psico.equals(id)) {
            System.out.println("Paciente não associado.");
            return;
        }
        else {
            for(Registro r : paciente.registros) {
                System.out.println("Registro:");
                System.out.println(r.registro);
                System.out.println("Data: " + r.data);
            }
        }
    }

    static Psicologo achePorId(ArrayList<Psicologo> lista, String id) {
        for(Psicologo psicologo: lista) {
            if(psicologo.id.equals(id)) 
                return psicologo;
        }
        return null;
    }
}
