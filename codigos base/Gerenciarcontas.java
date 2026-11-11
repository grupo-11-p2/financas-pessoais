import java.util.ArrayList;
import java.util.List;

public class Gerenciarcontas {
    private final List<Conta> listaContas = new ArrayList<Conta>();

    public void criarConta(String NomeConta) {
        Conta conta = new Conta();
        
    }

    public List<Conta> mostrarContas() {
        return new ArrayList<Conta>(listaContas);
    }

    public boolean removerConta(Conta conta) {
        return listaContas.remove(conta);
    }
}