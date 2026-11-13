import java.util.InputMismatchException;
import java.util.Scanner;

public class Leitor {
    private static final Scanner scanner = new Scanner(System.in);

    public static String lerTexto() {
        String texto = scanner.nextLine();

        return texto;
    }

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);

        String texto = scanner.nextLine();
        return texto;
    }

    public static String lerNome(String mensagem) {
        System.out.print(mensagem);
        String nome;

        while (true) {
            nome = scanner.nextLine().trim().toUpperCase();

            if (nome.length() < 4) {
                System.out.println("O nome deve conter pelo menos 4 caracteres");
            } else {
                return nome;
            }
        }
    }

    public static int lerInteiro() {
        int entrada;

        while (true) {
            try {
                entrada = scanner.nextInt();
                scanner.nextLine();
                return entrada;
            } catch (InputMismatchException e) {
                System.out.println("O valor digitado não é valido");
                scanner.next();
            }
        }
    }

    public static int lerInteiro(String mensagem) {
        System.out.println(mensagem);
        int entrada;

        while (true) {
            try {
                entrada = scanner.nextInt();
                scanner.nextLine();
                return entrada;
            } catch (InputMismatchException e) {
                System.out.println("Digite um número inteiro válido.");
                scanner.next();
            }
        }
    }

    public static double lerDouble(String mensagem) {
        System.out.println(mensagem);
        double entrada;

        while (true) {
            try {
                entrada = scanner.nextDouble();
                scanner.nextLine();
                return entrada;
            } catch (InputMismatchException e) {
                System.out.println("O valor digitado não é válido. Digite um número decimal:");
                scanner.next();
            }
        }
    }

    public static int lerId(Extrato extrato) {
        while (true) {
            int entrada = lerInteiro();

            if (entrada > 0 && extrato.VerificarId(entrada)) {
                return entrada;
            }

            System.out.println("O id digitado não é válido");
        }
    }
}
