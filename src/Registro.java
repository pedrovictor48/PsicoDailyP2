import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Registro {
    String id, registro, data;

    public Registro(String id, String registro) {
        this.id = id;
        this.registro = registro;

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        this.data = formatter.format(new Date());
    }

    public static Registro achePorId(ArrayList<Registro> registros, String id) {
        for(Registro registro : registros) {
            if(registro.id.equals(id)) {
                return registro;
            }
        }
        return null;
    }

    public void show() {
        System.out.println("Data: " + this.data);
        System.out.println("Id: " + this.id);
        System.out.println("Registro: " + this.registro);
    }

    public void edit(String novoRegistro) {
        registro = novoRegistro;
    }
}
