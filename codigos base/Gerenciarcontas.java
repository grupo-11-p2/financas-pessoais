import java.util.ArrayList;
import java.util.List;

public class Gerenciarcontas {
    private final List<Conta> listaContas = new ArrayList<Conta>();

    public void criarConta(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta não pode ser nula.");
        }
        listaContas.add(conta);
    }

    public List<Conta> mostrarContas() {
        return new ArrayList<Conta>(listaContas);
    }

    public boolean removerConta(Conta conta) {
        return listaContas.remove(conta);
    }
}