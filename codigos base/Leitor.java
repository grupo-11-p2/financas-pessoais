import java.util.Scanner;

public class Leitor {
    private static final Scanner scanner = new Scanner(System.in);

    public static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public static int lerInteiro(String mensagem) {
        System.out.print(mensagem);
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer de memória
        return valor;
    }

    public static String lerTexto() {
        return scanner.nextLine();
    }

    public static int lerInteiro() {
        int valor = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer de memória
        return valor;
    }
}