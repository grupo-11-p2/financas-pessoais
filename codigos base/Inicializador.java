public class Inicializador {
    public static void main(String[] args) {
        Gerenciarcontas gerenciarContas = new Gerenciarcontas();
        Movimentacao movimentacao = new Movimentacao(gerenciarContas);
        int opcao = -1;

        boolean Exibido = false;

        do {
            if (gerenciarContas.mostrarContas().isEmpty()) {
                if (Exibido == false) {
                    System.out.println("Olá, seja bem-vindo ao sistema!");
                    System.out.println("\nPor favor, escolha uma das opções abaixo:");

                    System.out.println("1 - Criar conta");
                    System.out.println("0 - Sair");

                    Exibido = true;
                }

                opcao = Leitor.lerInteiro();

                if (opcao == 1) {
                    gerenciarContas.criarConta();
                }
            } else {

                System.out.println("Olá, seja bem-vindo ao sistema!");
                System.out.println("\nPor favor, escolha uma das opções abaixo:");

                System.out.println("1- Criar conta");
                System.out.println("2- Lançamento");
                System.out.println("3- Extrato");
                System.out.println("4- Relatorio");

                System.out.println("0 - Sair");

                opcao = Leitor.lerInteiro();
                switch (opcao) {
                    case 1:
                        gerenciarContas.criarConta();
                        break;
                    case 2:
                        movimentacao.IniciarLancamento();
                        break;
                }
            }

        } while (opcao != 0);
    }
}