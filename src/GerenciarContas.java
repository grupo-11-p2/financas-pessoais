import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GerenciarContas {

    private final List<Conta> listaContas = new ArrayList<Conta>();
    private final GerenciarArquivos gerenciarArquivos = new GerenciarArquivos();
    private transient boolean dadosmudados = false;

    public void criarConta() {

        System.out.println("\nCriação de conta inicializada:");
        String NomeConta;
        Boolean ContaIgual;

        while (true) {
            NomeConta = Leitor.lerNome("\nDigite um nome:");
            ContaIgual = false;

            if (listaContas.isEmpty()) {
                break;
            }

            for (int i = 0; i < listaContas.size(); i++) {
                if (NomeConta.equals(listaContas.get(i).mostrarNome())) {
                    System.out.println("O nome da conta já é existente, por favor digite outro nome!");

                    ContaIgual = true;
                    break;
                }
            }

            if (ContaIgual == false) {
                break;
            }
        }

        Conta conta = new Conta();
        conta.aplicarNome(NomeConta);

        listaContas.add(conta);
        dadosmudados = true;

        System.out.println(listaContas.size());
    }

    public List<Conta> mostrarContas() {
        return new ArrayList<Conta>(listaContas);
    }

    public Conta puxarContaPeloNome(String nomeConta) {
        if (nomeConta == null) {
            return null;
        }
        for (Conta conta : listaContas) {
            if (nomeConta.equals(conta.mostrarNome())) {
                return conta;
            }
        }
        return null;
    }

    public Conta selecionarConta() {
        if (listaContas.isEmpty()) {
            throw new ExcecoesFinanceiras.ContaNaoEncontradaException("Nao tem contas salvas");
        }

        System.out.println("Escolha uma conta");
        for (int i = 0; i < listaContas.size(); i++) {
            System.out.println((i + 1) + " - " + listaContas.get(i).mostrarNome());
        }

        int escolha;

        while (true) {
            escolha = Leitor.lerInteiro("Opção: ");
            if (escolha == 0) {
                return null;
            }
            if (escolha >= 1 && escolha <= listaContas.size()) {
                return listaContas.get(escolha - 1);
            }
            System.out.println("Opção inválida!");
        }
    }

    public Conta selecionarConta(Conta contaExcluir) {
        List<Conta> contasDisponiveis = new ArrayList<>();
        for (Conta conta : listaContas) {
            if (conta != contaExcluir) {
                contasDisponiveis.add(conta);
            }
        }
        if (contasDisponiveis.isEmpty()) {
            System.out.println("É necessário ter outra conta para realizar uma transferência.");
            return null;
        }

        System.out.println("Escolha a conta de destino (0 para cancelar):");
        for (int i = 0; i < contasDisponiveis.size(); i++) {
            System.out.println((i + 1) + " - " + contasDisponiveis.get(i).mostrarNome());
        }

        int escolha;

        while (true) {
            escolha = Leitor.lerInteiro("Opção: ");
            if (escolha == 0) {
                return null;
            }
            if (escolha >= 1 && escolha <= contasDisponiveis.size()) {
                return contasDisponiveis.get(escolha - 1);
            }
            System.out.println("Opção inválida!");
        }
    }

    public boolean removerConta(Conta conta) {
        if (!listaContas.contains(conta)) {
            return false;
        }

        List<Transferencia> transferencias = new ArrayList<>();
        Map<Conta, Double> saldosAposCancelamento = new HashMap<>();
        for (Transacao transacao : conta.puxarExtrato().puxarListaTransacao()) {
            if (!(transacao instanceof Transferencia)) {
                continue;
            }

            Transferencia transferencia = (Transferencia) transacao;
            Conta origem = puxarContaPeloNome(transferencia.puxarNomeConta());
            Conta destino = puxarContaPeloNome(transferencia.puxarNomeContaDestino());
            if (origem == null || destino == null || origem == destino
                    || (origem != conta && destino != conta)) {
                throw new IllegalStateException("Não foi possível localizar as contas da transferência.");
            }

            double saldoDestino = saldosAposCancelamento.containsKey(destino)
                    ? saldosAposCancelamento.get(destino)
                    : destino.mostrarSaldo();
            if (saldoDestino - transferencia.puxarValor() < 0) {
                throw new ExcecoesFinanceiras.SaldoInsuficienteException(
                        "A conta não pode ser excluída: cancelar a transferência deixaria a conta "
                                + destino.mostrarNome() + " com saldo negativo.");
            }

            saldosAposCancelamento.put(destino, saldoDestino - transferencia.puxarValor());
            double saldoOrigem = saldosAposCancelamento.containsKey(origem)
                    ? saldosAposCancelamento.get(origem)
                    : origem.mostrarSaldo();
            saldosAposCancelamento.put(origem, saldoOrigem + transferencia.puxarValor());
            transferencias.add(transferencia);
        }

        for (Transferencia transferencia : transferencias) {
            Conta origem = puxarContaPeloNome(transferencia.puxarNomeConta());
            Conta destino = puxarContaPeloNome(transferencia.puxarNomeContaDestino());
            transferencia.cancelar(origem, destino);
        }

        boolean removida = listaContas.remove(conta);
        if (removida) {
            dadosmudados = true;
        }
        return removida;
    }

    public void iniciarSalvamento() {
        gerenciarArquivos.salvarDados(listaContas);
    }

    public boolean verificarDados() {
        boolean dadosmodificados = dadosmudados;

        for (int i = 0; i < listaContas.size(); i++) {
            Conta contaselecionada = listaContas.get(i);
            Extrato extrato = contaselecionada.puxarExtrato();

            if (contaselecionada.mostrarDados() || extrato.mostrarDados()) {
                dadosmodificados = true;
            }
        }

        return dadosmodificados;
    }

    public void recuperarDados() {
        List<Conta> contas = gerenciarArquivos.puxarDados();
        listaContas.clear();
        listaContas.addAll(contas);
        dadosmudados = false;
    }
}