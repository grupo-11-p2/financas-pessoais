public class Movimentacao {
    private GerenciarContas gerenciarContas;
    private static int opcao;
    private Conta conta;

    public Movimentacao(GerenciarContas gerenciarContas) {
        this.gerenciarContas = gerenciarContas;
    }

    public void iniciarLancamento() {
        conta = gerenciarContas.selecionarConta();
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
                    Conta destino = gerenciarContas.selecionarConta(conta);
                    if (destino != null) {
                        registrarTransacao(new Transferencia(conta, destino), destino);
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
            transacao.registrar(conta);
            System.out.println("Transação registrada com sucesso.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível registrar a transação: " + e.getMessage());
        }
    }

    private void registrarTransacao(Transacao transacao, Conta contaDestino) {
        try {
            transacao.registrar(conta, contaDestino);
            System.out.println("Transação registrada com sucesso.");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Não foi possível registrar a transação: " + e.getMessage());
        }
    }

    private void cancelarTransacao() {
        if (conta == null) {
            System.out.println("Não há movimentação na conta!");
        } else {
            Extrato extrato = conta.puxarExtrato();
            extrato.exibirTransacoes();

            if (extrato.puxarListaTransacao().isEmpty()) {
                return;
            }

            System.out.println("\nDigite o Id da transacao que deseja cancelar:");
            int id = Leitor.lerId(extrato);

            Transacao transacao = extrato.buscarPorId(id);

            try {
                if (transacao instanceof Transferencia) {
                    Transferencia transferencia = (Transferencia) transacao;
                    Conta contaDestino = gerenciarContas.puxarContaPeloNome(
                            transferencia.puxarNomeContaDestino());
                    transacao.cancelar(conta, contaDestino);
                } else {
                    transacao.cancelar(conta);
                }
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
            Extrato extrato = conta.puxarExtrato();
            extrato.exibirTransacoes();

            if (extrato.puxarListaTransacao().isEmpty()) {
                return;
            }

            System.out.println("\nDigite o Id da transacao que deseja editar:");
            int id = Leitor.lerId(extrato);

            double novoValor = Leitor.lerDouble("\nDigite o novo valor:");

            Transacao transacao = extrato.buscarPorId(id);

            try {
                boolean editada;
                if (transacao instanceof Transferencia) {
                    Transferencia transferencia = (Transferencia) transacao;
                    Conta contaDestino = gerenciarContas.puxarContaPeloNome(
                            transferencia.puxarNomeContaDestino());
                    editada = transacao.editar(conta, contaDestino, novoValor);
                } else {
                    editada = transacao.editar(conta, novoValor);
                }
                if (editada) {
                    System.out.println("Transação editada com sucesso.");
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println("Não foi possível editar a transação: " + e.getMessage());
            }
        }
    }

    public Conta puxarConta() {
        return conta;
    }
}

/*
 * int numero =
 * Leitor.lerInteiro("\nDigite o Id da transacao que deseja modificar:");
 * extrato.deletarExtrato(numero);
 * 
 * do{
 * numero = Leitor.lerInteiro();
 * }while (extrato.verificarId(numero));
 */
