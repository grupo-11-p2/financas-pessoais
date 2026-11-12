public class Movimentacao {
    private Gerenciarcontas gerenciarContas;
    private static int opcao;

    public Movimentacao(Gerenciarcontas gerenciarContas) {
        this.gerenciarContas = gerenciarContas;
    }

    public void IniciarLancamento() {
        Conta conta = gerenciarContas.SelecionarConta();

        System.out.println("1- Receita");
        System.out.println("2- Despesa");
        System.out.println("3- Transferencia");
        System.out.println("0- Voltar");

        do {
            opcao = Leitor.lerInteiro();

            switch (opcao) {
                case 1:
                    Receita receita = new Receita();
                    receita.Registrar();

                    break;
            }
        } while (opcao != 0);
    }

    public void cancelarTransacao(String nomeOrigem, int id) {
    }

    public void editarTransacao(String nome, int id, double valor) {
    }
}