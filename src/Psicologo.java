import java.util.ArrayList;

public class Psicologo extends Usuario{
    String crp;

    public Psicologo(String id, String name, String password, String cpf, String crp){
        super(id, name, password, cpf);
        this.crp = crp;
    }

    public void MenuPsicologo(ArrayList<Paciente> listaPacientes)
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

    static Psicologo achePorId(ArrayList<Psicologo> lista, String id) {
        for(Psicologo psicologo: lista) {
            if(psicologo.id.equals(id)) 
                return psicologo;
        }
        return null;
    }
}
