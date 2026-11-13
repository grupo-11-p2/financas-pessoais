import java.util.ArrayList;
import java.util.List;

public class Extrato {
    private final List<Transacao> listaTransacao = new ArrayList<>();

    public void aplicarExtrato(Transacao transacao) {
        if (transacao == null) {
            throw new IllegalArgumentException("A transação não pode ser nula.");
        }
        listaTransacao.add(transacao);
    }

    public void DeletarExtrato(int IdExistente) {
        for (int i = 0; i < listaTransacao.size(); i++) {
            if (listaTransacao.get(i).getId() == IdExistente) {
                listaTransacao.remove(i);
                return;
            }
        }
    }

    public List<Transacao> getListaTransacao() {
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
            System.out.println("Nenhuma transação encontrada.");
            return;
        }

        System.out.println("\n");
        for (Transacao transacao : listaTransacao) {
            String nomeCategoria = transacao.getCategoria() == null
                    ? "Sem categoria"
                    : transacao.getCategoria().getNome();
            System.out.printf("ID: %d | %s | %s | R$ %.2f | %s | Categoria: %s%n",
                    transacao.getId(), transacao.getData(), transacao.getTipo(),
                    transacao.getValor(), transacao.getDescricao(), nomeCategoria);
        }
    }

    public boolean VerificarId(int numero) {
        for (int i = 0; i < listaTransacao.size(); i++) {
            int idselecionado = listaTransacao.get(i).getId();

            if (numero == idselecionado) {
                return true;
            }
        }

        return false;
    }

    public Transacao buscarPorId(int id) {
        for (Transacao transacao : listaTransacao) {
            if (transacao.getId() == id) {
                return transacao;
            }
        }
        return null;
    }
}
