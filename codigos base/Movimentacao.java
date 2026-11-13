public class Movimentacao {
    private Gerenciarcontas gerenciarContas;
    private static int opcao;
    private Conta conta;

    public Movimentacao(Gerenciarcontas gerenciarContas) {
        this.gerenciarContas = gerenciarContas;
    }

    public void IniciarLancamento() {
        conta = gerenciarContas.SelecionarConta();
        if (conta == null) {
            return;
        }

        do {
            System.out.println("1- Receita");
            System.out.println("2- Despesa");
            System.out.println("3- Transferência");
            System.out.println("4- Editar transação");
            System.out.println("5- Cancelar transação");
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
                case 4:
                    editarTransacao();
                    break;
                case 5:
                    cancelarTransacao();
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

    private void cancelarTransacao() {
        if (conta == null) {
            System.out.println("Não há movimentação na conta!");
        } else {
            Extrato extrato = conta.getExtrato();
            extrato.exibirTransacoes();

            System.out.println("\nDigite o Id da transacao que deseja cancelar:");
            int id = Leitor.lerId(extrato);

            Transacao transacao = extrato.buscarPorId(id);

            try {
                transacao.cancelar();
                System.out.println("Transação cancelada com sucesso.");
            } catch (IllegalStateException e) {
                System.out.println("Não foi possível cancelar a transação: " + e.getMessage());
            }
        }
    }

    public void editarTransacao() {
        if (conta == null) {
            System.out.println("Não há movimentação na conta!");
        } else {
            Extrato extrato = conta.getExtrato();
            extrato.exibirTransacoes();

            System.out.println("\nDigite o Id da transacao que deseja editar:");
            int id = Leitor.lerId(extrato);

            double novoValor = Leitor.lerDouble("\nDigite o novo valor:");

            Transacao transacao = extrato.buscarPorId(id);

            try {
                if (transacao.editar(novoValor)) {
                    System.out.println("Transação editada com sucesso.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Não foi possível editar a transação: " + e.getMessage());
            }
        }
    }
}

/*
 * int numero =
 * Leitor.lerInteiro("\nDigite o Id da transacao que deseja modificar:");
 * extrato.DeletarExtrato(numero);
 * 
 * do{
 * numero = Leitor.lerInteiro();
 * }while (extrato.VerificarId(numero));
 */
