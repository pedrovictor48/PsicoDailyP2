import java.util.ArrayList;
import java.util.Scanner;

public class Psicologo extends Usuario{
    String crp;

    public Psicologo(String id, String name, String password, String cpf, String crp){
        super(id, name, password, cpf);
        this.crp = crp;
    }

    public void MenuPsicologo(ArrayList<Paciente> listaPacientes, ArrayList<Consulta> consultas)
    {
        int escolha = -1;
        Scanner leitor = new Scanner(System.in);
        do  { 
            System.out.println("MENU DO PSICOLOGO\n");
            System.out.println("[1] - EXIBIR PACIENTES");
            System.out.println("[2] - EXIBIR REGISTROS");
            System.out.println("[3] - MARCAR CONSULTA");
            System.out.println("[4] - VINCULAR PACIENTE");
            System.out.println("[5] - DESVINCULAR PACIENTE");
            System.out.println("[6] - EXIBIR CONSULTAS");
            System.out.println("[-1] - SAIR DO MENU");
            System.out.print("DIGITE A OPERACAO: ");
            
            
            escolha = leitor.nextInt();
            if(escolha == 1) {
                ListarPacientes(listaPacientes);
            }
            else if(escolha == 2) {
                ExibirRegistros(listaPacientes);
            }else if(escolha == 3){
                marcarConsulta(listaPacientes, consultas);
            }else if(escolha == 4){
                vincularPaciente(listaPacientes);
            }else if(escolha == 5){
                desvincularPaciente(listaPacientes);
            }
            else if(escolha == 6) {
                this.exibirConsultas(listaPacientes, consultas);
            }
            else if(escolha != -1){
                System.out.println("\n\nERRO: OPERACAO INVALIDA!\nTENTE NOVAMENTE\n");
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
            System.out.println("\nERRO: VOCÊ NÃO POSSUI NENHUM PACIENTE VINCULADO!");
        }
    }

    public void ExibirRegistros(ArrayList<Paciente> listaPacientes)
    {
        System.out.print("DIGITE O ID DO PACIENTE: ");
        Scanner leitor = new Scanner(System.in);
        String id_paciente = leitor.nextLine();

        Paciente paciente = Paciente.achePorId(listaPacientes, id_paciente);
        if(paciente == null) {
            System.out.println("\nERRO: PACIENTE NAO ENCONTRADO!\nTENTE NOVAMENTE\n");
        }
        else if (!paciente.id_psico.equals(id)) {
            System.out.println("\nERRO: PACIENTE NAO VINCULADO!\nTENTE NOVAMENTE\n");
        }
        else {
            paciente.perfil();
        }
    }

    public void vincularPaciente(ArrayList<Paciente> listaPacientes){

        Scanner leitor = new Scanner(System.in);
        System.out.print("DIGITE O ID DO PACIENTE:");
        String id_paciente = leitor.nextLine();

        Paciente paciente = Paciente.achePorId(listaPacientes, id_paciente);

        if (paciente == null) {
            System.out.println("\nERRO: PACIENTE NAO ENCONTRADO!\nTENTE NOVAMENTE\n");
            return;
        } else if (paciente.id_psico.equals("null")) {
            paciente.id_psico = id;
            System.out.println("\nPACIENTE VINCULADO COM SUCESSO.\n");
        } else {
            System.out.println("\nERRO: O PACIENTE JA POSSUI VINCULO COM OUTRO PSICOLOGO!\nTENTE NOVAMENTE\n");
        }
    }

    public void desvincularPaciente(ArrayList<Paciente> listaPacientes){
        Scanner leitor = new Scanner(System.in);
        System.out.print("DIGITE O ID DO PACIENTE:");
        String id_paciente = leitor.nextLine();

        Paciente paciente = Paciente.achePorId(listaPacientes, id_paciente);

        if (paciente == null) {
            System.out.println("\nERRO: PACIENTE NAO ENCONTRADO!\nTENTE NOVAMENTE\n");
            return;
        }else if (paciente.id_psico != id) {
            System.out.println("\nERRO: PACIENTE NAO VINCULADO!\nTENTE NOVAMENTE\n");
            return;
        }

        paciente.id_psico = "null";
    }

    public void marcarConsulta(ArrayList<Paciente> listaPaciente, ArrayList<Consulta> consultas) {
        String id_paciente;
        Scanner leitor = new Scanner(System.in);
        System.out.print("DIGITE O ID DO PACIENTE: ");
        id_paciente = leitor.nextLine();
        Paciente paciente = Paciente.achePorId(listaPaciente, id_paciente);
        if(paciente == null) {
            System.out.println("\nERRO: PACIENTE NAO VINCULADO!\nTENTE NOVAMENTE\n");
        }
        else if(!paciente.id_psico.equals(this.id)) {
            System.out.println("\nERRO: PACIENTE NAO VINCULADO!\nTENTE NOVAMENTE\n");
        }
        else {
            System.out.print("DIGITE O ID DA CONSULTA: ");
            String id_consulta = leitor.nextLine();

            Consulta consulta = new Consulta(id_paciente, this.id, id_consulta);
            boolean flag = false;
            do {
                flag = consulta.setHorario(consultas);
                if(!flag) {
                    System.out.println("\nDESEJA TENTAR NOVAMENTE? (s/n): ");
                    String ans = leitor.nextLine();
                    if(!ans.equals("s")) {
                        System.out.println("\nCONSULTA NAO FOI MARCADA\n");
                        return;
                    }
                }
            } while(!flag);

            consultas.add(consulta);
        }
    }

    public void exibirConsultas(ArrayList<Paciente> paciente, ArrayList<Consulta> consultas) {
        for(Consulta c : consultas) {
            if(c.id_psicologo.equals(this.id)) {
                c.printarDoPsicologo(paciente);
            }
        }
    }

    static Psicologo achePorId(ArrayList<Psicologo> lista, String id) {
        for(Psicologo psicologo: lista) {
            if(psicologo.id.equals(id)){
                return psicologo;
            }
        }
        return null;
    }
}
