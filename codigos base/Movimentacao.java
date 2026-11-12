public class Movimentacao {
    private Gerenciarcontas gerenciarContas;
    private static int opcao;

    public Movimentacao(Gerenciarcontas gerenciarContas) {
        this.gerenciarContas = gerenciarContas;
    }

    public void IniciarLancamento() {
        Conta conta = gerenciarContas.SelecionarConta();
        if (conta == null) {
            return;
        }

        do {
            System.out.println("1- Receita");
            System.out.println("2- Despesa");
            System.out.println("3- Transferência");
            System.out.println("0- Voltar");
            opcao = Leitor.lerInteiro("Opção: ");
            switch (opcao) {
                case 1:
                    registrarTransacao(new Receita(conta));
                    break;
                case 2:
                    registrarTransacao(new Despesa(conta));
                    break;
                case 3:
                    Conta destino = gerenciarContas.SelecionarConta(conta);
                    if (destino != null) {
                        registrarTransacao(new Transferencia(conta, destino));
                    }
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void registrarTransacao(Transacao transacao) {
        try {
            transacao.registrar();
            System.out.println("Transação registrada com sucesso.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível registrar a transação: " + e.getMessage());
        }
    }

    public void cancelarTransacao(String nomeOrigem, int id) {
    }

    public void editarTransacao(String nome, int id, double valor) {
    }
}