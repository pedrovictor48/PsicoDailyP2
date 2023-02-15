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
            System.out.println("MENU DO PACIENTE\n");
            System.out.println("[1] - ADICIONAR REGISTRO");
            System.out.println("[2] - EDITAR REGISTRO");
            System.out.println("[3] - EXCLUIR REGISTRO");
            System.out.println("[4] - VISUALIZAR REGISTROS");
            System.out.println("[5] - VINCULAR PSICOLOGO");
            System.out.println("[6] - DESVINCULAR PSICOLOGO");
            System.out.println("[7] - EXIBIR CONSULTAS");
            System.out.println("[-1] - SAIR DO MENU");
            System.out.print("DIGITE A OPERACAO: ");

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
                System.out.println("\n\nERRO: OPERACAO INVALIDA!\nTENTE NOVAMENTE\n");
            }
            
        } while (escolha != -1);

    }

    public void addRegistro() {

        String id, registro;

        Scanner leitor = new Scanner(System.in);

        System.out.print("ID DO REGISTRO: ");
        id = leitor.nextLine();

        System.out.print("\nRELATO: ");
        registro = leitor.nextLine();

        Registro novoRegistro = new Registro(id, registro);
        this.registros.add(novoRegistro);
    }

    public void editarRegistro() {
        String id_reg;

        System.out.print("DIGITE O ID DO REGISTRO: ");
        Scanner leitor = new Scanner(System.in);
        id_reg = leitor.nextLine();

        for(int i = 0; i < this.registros.size(); i++) {
            Registro registro = registros.get(i);
            if(registro.id.equals(id_reg)) {
                System.out.print("NOVO REGISTRO: ");
                String novoRegistro = leitor.nextLine();
                registros.get(i).edit(novoRegistro);
                System.out.println("\nREGISTRO ALTERADO COM SUCESSO.\n");
                return;
            }
        }

        System.out.println("\nERRO: REGISTRO NAO ENCONTRADO!\nTENTE NOVAMENTE\n");
    }

    public void excluirRegistro() {
        String id_reg;

        System.out.print("DIGITE O ID DO REGISTRO: ");
        Scanner leitor = new Scanner(System.in);
        id_reg = leitor.nextLine();

        for(int i = 0; i < this.registros.size(); i++) {
            Registro registro = registros.get(i);
            if(registro.id.equals(id_reg)) {
                registros.remove(i);
                System.out.println("\nREGISTRO EXCLUIDO COM SUCESSO.\n");
                return;
            }
        }

        System.out.println("\nERRO: REGISTRO NAO ENCONTRADO!\nTENTE NOVAMENTE\n");
    }

    public void perfil() {
        System.out.println("NOME: " + this.name);

        System.out.println("REGISTROS: ");
        for(Registro registro : this.registros) {
            registro.show();
        }
    }

    public void vincularPsico(ArrayList<Psicologo> psicologos){
        Scanner leitor = new Scanner(System.in);

        if(!this.id_psico.equals("null")){
            System.out.println("\nERRO: VOCE JA POSSUI UM PSICOLOGO VINCULADO!\n");
            return;
        }

        System.out.print("ID DO PSICOLOGO: ");
        String id_psicologo = leitor.nextLine();
        
        Psicologo psicologo = Psicologo.achePorId(psicologos, id_psicologo);

        if (psicologo == null) {
            System.out.println("\nERRO: PSICOLOGO NAO ENCONTRADO!\nTENTE NOVAMENTE\n");
            return;
        } else {
            this.id_psico = psicologo.id;
            System.out.println("\nPSICOLOGO VINCULADO COM SUCESSO.\n");
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
            System.out.println("\nERRO: VOCE NAO POSSUI UM PSICOLOGO VINCULADO!\n");
            return;
        }
        System.out.println("\nPSICOLOGO DESVINCULADO COM SUCESSO.\n");
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