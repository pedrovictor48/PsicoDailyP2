public class Registro {
    String id, registro, data;
    public Registro(String id, String registro, String data) {
        this.id = id;
        this.registro = registro;
        this.data = data;
    }

    public static achePorId(ArrayList<Registro> registros, String id) {
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
        System.out.println(this.register);
    }
}
