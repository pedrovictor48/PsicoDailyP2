import java.util.Scanner;
import java.util.ArrayList;

public class App {
  static ArrayList<Paciente> pacientes = new ArrayList<Paciente>();
  static ArrayList<Psicologo> psicologos = new ArrayList<Psicologo>();
  static ArrayList<Consulta> consultas = new ArrayList<Consulta>();

  public static void main(String[] args) throws Exception {

    int escolha = -1;
    Scanner leitor = new Scanner(System.in);
    do {

      System.out.println("\nBEM-VINDO AO PSICODAILY!\n");
      System.out.println("[1] - CRIAR USUARIO");
      System.out.println("[2] - EXCLUIR USUARIO");
      System.out.println("[3] - LOGIN");
      System.out.println("[-1] - SAIR DO PROGRAMA");
      System.out.print("\nDIGITE A OPERACAO: ");

      escolha = leitor.nextInt();
      if (escolha == 1) {
        addUser();
      } else if (escolha == 2) {
        excluirUser();
      } else if (escolha == 3) {
        login();
      } else if (escolha != -1) {
        System.out.println("\n\nERRO: OPERACAO INVALIDA!\nTENTE NOVAMENTE\n");
      }

    } while (escolha != -1);

    leitor.close();
  }

  public static void addUser() {
    Scanner leitor = new Scanner(System.in);

    do {
      System.out.println("\n[1] - PACIENTE \n[2] - PSICOLOGO");
      System.out.print("INFORME O TIPO DO USUARIO: ");
      int escolha = leitor.nextInt();

      if (escolha == 1) {
        addPaciente();
        break;
      } else if (escolha == 2) {
        addPsicologo();
        break;
      } else {
        System.out.println("\n\nERRO: OPERACAO INVALIDA!\nTENTE NOVAMENTE\n");
      }

    } while (true);

  }

  public static void addPaciente() {
    String id, name, password, cpf;

    Scanner leitor = new Scanner(System.in);

    System.out.print("\nID: ");
    id = leitor.nextLine();

    System.out.print("NOME: ");
    name = leitor.nextLine();

    System.out.print("SENHA: ");
    password = leitor.nextLine();

    System.out.print("CPF: ");
    cpf = leitor.nextLine();

    Paciente novoPaciente = new Paciente(id, name, password, cpf);
    pacientes.add(novoPaciente);
  }

  public static void addPsicologo() {
    String id, name, password, cpf, crp;

    Scanner leitor = new Scanner(System.in);
    System.out.print("\nID: ");
    id = leitor.nextLine();

    System.out.print("NOME: ");
    name = leitor.nextLine();

    System.out.print("SENHA: ");
    password = leitor.nextLine();

    System.out.print("CPF: ");
    cpf = leitor.nextLine();

    System.out.print("CRP: ");
    crp = leitor.nextLine();

    Psicologo novoPsicologo = new Psicologo(id, name, password, cpf, crp);
    psicologos.add(novoPsicologo);
  }

  static void login() {
    System.out.println("\n[1] - PACIENTE \n[2] - PSICOLOGO");
    System.out.print("INFORME O TIPO DO USUARIO: ");

    Scanner leitor = new Scanner(System.in);

    int escolha = leitor.nextInt();
    do {
      if (escolha == 1) {
        loginPaciente();
        break;
      } else if (escolha == 2) {
        loginPsicologo();
        break;
      } else if (escolha == -1) {
        break;
      }

    } while (true);
  }

  static void loginPaciente() {
    Scanner leitorPaci = new Scanner(System.in);

    System.out.print("\nDIGITE SEU ID: ");
    String id_paci = leitorPaci.nextLine();

    Paciente paciente = Paciente.achePorId(pacientes, id_paci);
    if (paciente == null) {
      // erro
      System.out.println("\nERRO: PACIENTE NAO ENCONTRADO!\nTENTE NOVAMENTE\n");
    } else {
      System.out.print("DIGITE SUA SENHA: ");
      String senha_paci = leitorPaci.nextLine();

      if (paciente.password.equals(senha_paci)) {
        paciente.MenuPaciente(psicologos, consultas);
      } else {
        System.out.println("ERRO: SENHA INCORRETA!\nTENTE NOVAMENTE\n");
      }
    }
  }

  static void loginPsicologo() {
    Scanner leitorPsico = new Scanner(System.in);

    System.out.print("\nDIGITE SEU ID: ");

    String id_psico = leitorPsico.nextLine();
    Psicologo psicologo = Psicologo.achePorId(psicologos, id_psico);

    if (psicologo == null) {
      // erro
      System.out.println("\nERRO: PSICOLOGO NAO ENCONTRADO!\nTENTE NOVAMENTE\n");
    } else {
      System.out.print("DIGITE SUA SENHA: ");
      String senha_psico = leitorPsico.nextLine();

      if (psicologo.password.equals(senha_psico)) {
        psicologo.MenuPsicologo(pacientes, consultas);
      } else {
        System.out.println("ERRO: SENHA INCORRETA!\nTENTE NOVAMENTE\n");
      }
    }
  }

  static void excluirUser() {
    String id;
    Scanner leitor = new Scanner(System.in);

    System.out.print("\nID DO USUARIO: ");

    id = leitor.nextLine();

    // achando por id
    for (int i = 0; i < pacientes.size(); i++) 
    {
      Paciente paciente = pacientes.get(i);

      if (paciente.id.equals(id)) 
      {
        pacientes.remove(i);
        System.out.println("\nUSUARIO EXCLUIDO COM SUCESSO.");
        return;
      }
    }

    for (int i = 0; i < psicologos.size(); i++) 
    {
      Psicologo psicologo = psicologos.get(i);

      if (psicologo.id.equals(id)) 
      {
        psicologos.remove(i);
        System.out.println("\nUSUARIO EXCLUIDO COM SUCESSO.");
        return;
      }
    }

    System.out.println("ERRO: USUARIO NAO ENCONTRADO!");
  }

  static boolean existPaciente(ArrayList<Paciente> lista, String id) 
  {
    for (Paciente paciente : lista) 
    {
      if (paciente.id.equals(id))
        return true;
    }
    return false;
  }
}