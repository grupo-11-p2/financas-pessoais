import java.util.ArrayList;
import java.util.List;

public class Extrato {
    private List<Transacao> ListaTransacao = new ArrayList<>();

    public void aplicarExtrato(Transacao transacao) {
        ListaTransacao.add(transacao);
    }

    public void DeletarExtrato(int IdExistente) {
        for (i = 0; i < (ListaTransacao.size()); i++) {
            Transacao TransacaoSelecionada = ListaTransacao.get(i);

            if (TransacaoSelecionada.id == IdExistente) {
                ListaTransacao.remove(i);
                break;
            }
        }
    }
}
