import java.util.ArrayList;
import java.util.List;

public class Gerenciarcontas {
    private final List<Conta> listaContas = new ArrayList<Conta>();

    public void criarConta() {

        System.out.println("\nCriação de conta inicializada:");
        String NomeConta = Leitor.lerTexto("\nDigite um nome:");

        System.out.println("Nome da conta sera: " + NomeConta);

        Conta conta = new Conta();
        conta.aplicarNome(NomeConta);

        listaContas.add(conta);

        System.out.println(listaContas.size());
    }

    public List<Conta> mostrarContas() {
        return new ArrayList<Conta>(listaContas);
    }

    public boolean removerConta(Conta conta) {
        return listaContas.remove(conta);
    }
}