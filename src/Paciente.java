import java.util.ArrayList;
import java.util.Scanner;

public class Paciente extends Usuario{
    ArrayList<Registro> registros;
    String id_psico;

    public Paciente(String id, String name, String password, String cpf){
        super(id, name, password, cpf);
        this.registros = new ArrayList<Registro>();
        this.id_psico = "null";
    }

    public void MenuPaciente(ArrayList<Psicologo> psicologos, ArrayList<Consulta> consultas){
        int escolha = -1;
        Scanner leitor = new Scanner(System.in);

        do {
            System.out.println("\nDigite a operacao que voce deseja fazer:");
            System.out.println("[1] - Adicionar registro.");
            System.out.println("[2] - Editar registro.");
            System.out.println("[3] - Excluir registro.");
            System.out.println("[4] - Visualizar registros.");
            System.out.println("[5] - Vincular Psicologo.");
            System.out.println("[6] - Desvincular Psicologo.");
            System.out.println("[7] - Exibir consultas.");
            System.out.println("[-1] - Sair do menu.");

            escolha = leitor.nextInt();

            if(escolha == 1){
                addRegistro();
            }else if(escolha == 2){
                editarRegistro();
            }else if(escolha == 3){
                excluirRegistro();
            }else if(escolha == 4){
                perfil();
            }else if(escolha == 5){
                vincularPsico(psicologos);
            }else if(escolha == 6){
                desvincularPsicologo();
            }
            else if(escolha == 7) {
                this.exibirConsultas(psicologos, consultas);
            }
            else if(escolha != -1){
                System.out.println("Digite uma opcao valida!");
            }
            
        } while (escolha != -1);

    }

    public void addRegistro() {

        String id, registro;

        Scanner leitor = new Scanner(System.in);

        System.out.println("Id do registro: ");
        id = leitor.nextLine();

        System.out.println("Relato: ");
        registro = leitor.nextLine();

        Registro novoRegistro = new Registro(id, registro);
        this.registros.add(novoRegistro);
    }

    public void editarRegistro() {
        String id_reg;

        System.out.println("Insira o id do registro: ");
        Scanner leitor = new Scanner(System.in);
        id_reg = leitor.nextLine();

        for(int i = 0; i < this.registros.size(); i++) {
            Registro registro = registros.get(i);
            if(registro.id.equals(id_reg)) {
                System.out.println("Digite o novo registro: ");
                String novoRegistro = leitor.nextLine();
                registros.get(i).edit(novoRegistro);
                System.out.println("Registro alterado.");
                return;
            }
        }

        System.out.println("Registro não encontrado.");
    }

    public void excluirRegistro() {
        String id_reg;

        System.out.println("Insira o id do registro: ");
        Scanner leitor = new Scanner(System.in);
        id_reg = leitor.nextLine();

        for(int i = 0; i < this.registros.size(); i++) {
            Registro registro = registros.get(i);
            if(registro.id.equals(id_reg)) {
                registros.remove(i);
                System.out.println("Registro removido");
                return;
            }
        }

        System.out.println("Registro não encontrado");
    }

    public void perfil() {
        System.out.println("Nome: " + this.name);

        System.out.println("Registros: ");
        for(Registro registro : this.registros) {
            registro.show();
        }
    }

    public void vincularPsico(ArrayList<Psicologo> psicologos){
        Scanner leitor = new Scanner(System.in);

        if(!this.id_psico.equals("null")){
            System.out.println("Voce ja tem um Psicologo");
            return;
        }

        System.out.println("ID do psicologo: ");
        String id_psicologo = leitor.nextLine();
        
        Psicologo psicologo = Psicologo.achePorId(psicologos, id_psicologo);

        if (psicologo == null) {
            System.out.println("Psicologo nao encontrado.");
            return;
        } else {
            this.id_psico = psicologo.id;
            System.out.println("Paciente vinculado.");
        }
    }

    static boolean existPsicologo(ArrayList<Psicologo> lista, String id_psico){
        for(Psicologo psicologo : lista) {
            if(psicologo.id.equals(id_psico)) 
                return true;
        }
        return false;
    }

    public void desvincularPsicologo(){
        if (this.id_psico.equals("null")) {
            System.out.println("Voce nao tem psicologo.");
            return;
        }
        System.out.println("Psicologo desvinculado.");
        this.id_psico = "null";
    }

    public void exibirConsultas(ArrayList<Psicologo> psicologos, ArrayList<Consulta> consultas) {
        for(Consulta c : consultas) {
            if(c.id_paciente.equals(this.id)) {
                c.printarDoPaciente(psicologos);
            }
        }
    }

    public static Paciente achePorId(ArrayList<Paciente> lista, String id) {
        for(Paciente paciente: lista) {
            if(paciente.id.equals(id))
                return paciente;
        }
        return null;
    }
}