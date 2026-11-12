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
}
