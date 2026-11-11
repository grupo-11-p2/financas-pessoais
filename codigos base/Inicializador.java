import java.util.Scanner;

public class Inicializador {
    public static void main(String[] args) {
        Gerenciarcontas gerenciarContas = new Gerenciarcontas();
        int opcao = -1;

        do{
            if (gerenciarContas.mostrarContas().isEmpty()) {
            System.out.println("Olá, seja bem-vindo ao sistema!");
            System.out.println("\nPor favor, escolha uma das opções abaixo:");

            System.out.println("1 - Criar conta");
            System.out.println("0 - Sair");

            opcao = Leitor.lerInteiro();

            gerenciarContas.criarConta(null);

            }else {
             System.out.println("Ja existe uma conta criada!");
            }

        }while(opcao != 0);
    }
}