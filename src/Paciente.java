import java.util.ArrayList;
import java.util.Scanner;

public class Paciente extends Usuario{
    ArrayList<Registro> registros;
    String id_psico;

    public Paciente(String id, String name, String password, String cpf, String id_psico){
        super(id, name, password, cpf);
        this.registros = new ArrayList<Registro>();
        this.id_psico = id_psico;
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

        System.out.println("Registros");
        for(Registro registro : this.registros) {
            registro.show();
        }
    }

    static Paciente achePorId(ArrayList<Paciente> lista, String id) {
        for(Paciente psicologo: lista) {
            if(psicologo.id.equals(id)) return psicologo;
        }
        return null;
    }
}
