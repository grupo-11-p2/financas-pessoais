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
        if (conta != null) {
            Extrato extrato = conta.getExtrato();
            extrato.exibirTransacoes();

            System.out.println("\nDigite o Id da transacao que deseja modificar:");
            int id = Leitor.lerId(extrato);

            
            extrato.DeletarExtrato(id);
        }else{
            System.out.println("Não a movimentação na conta!");
        }
    }

    public void editarTransacao() {
        Extrato extrato = conta.getExtrato();
        extrato.exibirTransacoes();

        System.out.println("\nDigite o Id da transacao que deseja modificar:");
        int id = Leitor.lerId(extrato);

        Sytem.out.println("")
    }
}

/*
 * int numero =
 * Leitor.lerInteiro("\nDigite o Id da transacao que deseja modificar:");
 * extrato.DeletarExtrato(numero);
 * 
 *  do{
                numero = Leitor.lerInteiro();
            }while (extrato.VerificarId(numero));
 */
