import java.util.Scanner;

public class Leitor {
    private static final Scanner scanner = new Scanner(System.in);

    public static String lerTexto() {
        return scanner.nextLine();
    }

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static String LerNome() {
        while (true) {
            String nome = scanner.nextLine().trim();

            int letras = 0;
            for (char c : nome.toCharArray()) {
                if (Character.isLetter(c)) {
                    letras++;
                }
            }

            if (letras < 4) {
                System.out.println("O nome deve ter pelo menos 4 letras.");
            } else {
                return nome;
            }
        }
    }

    public static String LerNome(String mensagem) {
        System.out.print(mensagem);

        while (true) {
            String nome = scanner.nextLine().trim();

            int letras = 0;
            for (char c : nome.toCharArray()) {
                if (Character.isLetter(c)) {
                    letras++;
                }
            }

            if (letras < 4) {
                System.out.println("O nome deve ter pelo menos 4 letras.");
            } else {
                return nome;
            }
        }
    }

    public static int lerInteiro() {
        while (true) {
            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    public static int lerInteiro(String mensagem) {
        System.out.println(mensagem);

        while (true) {
            String entrada = scanner.nextLine();

            try {
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    public static double lerDouble() {

        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer de memória
        return valor;
    }

    public static double lerDouble(String mensagem) {
        System.out.print(mensagem);
        double valor = scanner.nextDouble();
        scanner.nextLine(); // Limpa o buffer de memória
        return valor;
    }
}
