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

        String id, registro, data;

        Scanner leitor = new Scanner(System.in);

        System.out.println("Id: ");
        id = leitor.nextLine();

        System.out.println("Relato: ");
        registro = leitor.nextLine();

        System.out.println("Data (dd-mm-yyyy): ");
        data = leitor.nextLine();

        Registro novoRegistro = new Registro(id, registro, data);
        this.registros.add(novoRegistro);

    }

    static Paciente achePorId(ArrayList<Paciente> lista, String id) {
        for(Paciente psicologo: lista) {
            if(psicologo.id.equals(id)) return psicologo;
        }
        return null;
    }
}