import java.util.ArrayList;
import java.util.List;

public class Extrato {
    private final List<Transacao> listaTransacao = new ArrayList<>();
    private transient boolean dadosmudados = false;

    public void aplicarExtrato(Transacao transacao) {
        if (transacao == null) {
            throw new IllegalArgumentException("A transação não pode ser nula.");
        }
        listaTransacao.add(transacao);
        dadosmudados = true;
    }

    public void deletarExtrato(int idExistente) {
        for (int i = 0; i < listaTransacao.size(); i++) {
            if (listaTransacao.get(i).puxarId() == idExistente) {
                listaTransacao.remove(i);
                dadosmudados = true;
                return;
            }
        }
    }

    public List<Transacao> puxarListaTransacao() {
        return new ArrayList<>(listaTransacao);
    }

    public void exibir(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta não pode ser nula.");
        }

        System.out.println("\nExtrato da conta " + conta.mostrarNome());
        exibirTransacoes();
        System.out.printf("Saldo atual: R$ %.2f%n", conta.mostrarSaldo());
    }

    public void exibirTransacoes() {
        if (listaTransacao.isEmpty()) {
            System.out.println("Nenhuma transação encontrada.\n");
            return;
        }

        System.out.println("\n");
        for (Transacao transacao : listaTransacao) {
            String nomeCategoria = transacao.puxarCategoria() == null
                    ? "Sem categoria"
                    : transacao.puxarCategoria().puxarNome();
            System.out.printf("ID: %d | %s | %s | R$ %.2f | %s | Categoria: %s%n",
                    transacao.puxarId(), transacao.puxarData(), transacao.puxarTipo(),
                    transacao.puxarValor(), transacao.puxarDescricao(), nomeCategoria);
        }
    }

    public boolean verificarId(int numero) {
        for (int i = 0; i < listaTransacao.size(); i++) {
            int idselecionado = listaTransacao.get(i).puxarId();

            if (numero == idselecionado) {
                return true;
            }
        }

        return false;
    }

    public Transacao buscarPorId(int id) {
        for (Transacao transacao : listaTransacao) {
            if (transacao.puxarId() == id) {
                return transacao;
            }
        }
        return null;
    }

    public boolean mostrarDados() {
        return dadosmudados;
    }
}
