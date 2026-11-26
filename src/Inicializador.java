import java.io.FileNotFoundException;

public class Inicializador {
    public static void main(String[] args) {
        GerenciarContas gerenciarContas = new GerenciarContas();
        Movimentacao movimentacao = new Movimentacao(gerenciarContas);
        int opcao = -1;

        boolean Exibido = false;
        try {
            gerenciarContas.recuperarDados();
        } catch (ExcecoesFinanceiras.ArquivoDadosException e) {
            System.out.println(e.getMessage());
            if (!(e.getCause() instanceof FileNotFoundException)) {
                return;
            }
        }

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
                System.out.println("2- Excluir conta");
                System.out.println("3- Lançamento");
                System.out.println("4- Extrato");
                System.out.println("5- Relatorio");

                System.out.println("0 - Sair");

                opcao = Leitor.lerInteiro();
                switch (opcao) {
                    case 1:
                        gerenciarContas.criarConta();
                        break;
                    case 2:
                        excluirConta(gerenciarContas);
                        break;
                    case 3:
                        movimentacao.iniciarLancamento();
                        break;
                    case 4:
                        exibirExtrato(gerenciarContas);
                        break;
                    case 5:
                        gerarRelatorio(gerenciarContas);
                        break;
                    case 0:
                        boolean Verificacao = gerenciarContas.verificarDados();

                        if (Verificacao == true) {
                            System.out.println("Há dados a serem salvos. Deseja salvar?");
                            boolean caso = Leitor.lerBool();

                            if (caso) {
                                gerenciarContas.iniciarSalvamento();
                                System.out.println("Dados salvos com sucesso!");
                            }
                        }

                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }

        } while (opcao != 0);
    }

    private static void exibirExtrato(GerenciarContas gerenciarContas) {
        Conta conta = gerenciarContas.selecionarConta();
        if (conta == null) {
            return;
        }

        conta.puxarExtrato().exibir(conta);
    }

    private static void gerarRelatorio(GerenciarContas gerenciarContas) {
        Conta conta = gerenciarContas.selecionarConta();
        if (conta == null) {
            return;
        }

        new Relatorio(conta.puxarExtrato()).gerarRelatorio(conta.mostrarNome());
    }

    private static void excluirConta(GerenciarContas gerenciarContas) {
        Conta conta = gerenciarContas.selecionarConta();
        if (conta == null) {
            return;
        }

        try {
            if (gerenciarContas.removerConta(conta)) {
                System.out.println("Conta excluída com sucesso.");
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível excluir a conta: " + e.getMessage());
        }
    }
}