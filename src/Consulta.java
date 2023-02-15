import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Consulta {
    String id_paciente, id_psicologo, id_consulta;
    Date inicio, termino;

    public Consulta(String id_paciente, String id_psicologo, String id_consulta) {
        this.id_paciente = id_paciente;
        this.id_psicologo = id_psicologo;
        this.id_consulta = id_consulta;
    }

    public boolean batendo(Date ini, Date fin, Consulta consulta) {
        boolean inicioOverlapping = ini.compareTo(consulta.inicio) >= 0 && ini.compareTo(consulta.termino) < 0;
        boolean terminoOverlapping = fin.compareTo(consulta.inicio) >= 0 && fin.compareTo(consulta.termino) < 0;
        
        return (inicioOverlapping || terminoOverlapping);
    }

    public boolean checkHorario(ArrayList<Consulta> consultas, Date ini, Date fin) {
        for(Consulta consulta : consultas) {
            if(consulta.id_paciente == this.id_paciente && consulta.id_psicologo == this.id_psicologo) {
                //checar se o horário bate
                if(this.batendo(ini, fin, consulta)) return false;
            }
        }
        return true;
    }

    public boolean setHorario(ArrayList<Consulta> consultas) {
        Scanner leitor = new Scanner(System.in);
        System.out.println("Digite o início da sessão (hh:mm dd-mm-yyyy)");
        String hora_inicio = leitor.nextLine();

        System.out.println("Digite o fim da sessão (hh:mm dd-mm-yyyy)");
        String hora_termino = leitor.nextLine();

        SimpleDateFormat f = new SimpleDateFormat("HH:mm dd-MM-yyyy");
            Date date_inicio, date_termino;

        try {
            date_inicio = f.parse(hora_inicio);
            date_termino = f.parse(hora_termino);
        }
        catch (Exception e) {
            System.out.println("Horário inválido");
            return false;
        }
        
        if(checkHorario(consultas, date_inicio, date_termino)) {
            this.inicio = date_inicio;
            this.termino = date_termino;
            return true;
        }
        System.out.println("Já existe consulta nesse horário");
        return false;
    }
    
    public void printarDoPsicologo(ArrayList<Paciente> pacientes) {
        Paciente p = Paciente.achePorId(pacientes, this.id_paciente);
        System.out.println("Consulta marcada das " + this.inicio.toString() + "às" + this.termino.toString() + " com o paciente " + p.name);
    }

    public void printarDoPaciente(ArrayList<Psicologo> psicologos) {
        Psicologo p = Psicologo.achePorId(psicologos, this.id_psicologo);
        System.out.println("Consulta marcada das " + this.inicio.toString() + "às" + this.termino.toString() + " com " + p.name);
    }
}
