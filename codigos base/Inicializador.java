import java.util.Scanner;

public class Inicializador {
    public static void main(String[] args) {
        String opcao;

        System.out.println("\n===== FINANÇAS PESSOAIS =====");
        System.out.println("Seja bem-vindo ao Sistema de Finanças Pessoais!");
        System.out.println("\nPrimeiramente, registre uma nova conta.");
        System.out.println("\nPressione Y para continuar.");
        
        do {
            opcao = Leitor.lerTexto();
        } while (!opcao.equalsIgnoreCase("Y"));

        System.out.println("Vamos começar!");
        
    }
}