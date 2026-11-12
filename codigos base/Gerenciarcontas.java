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

    public Conta SelecionarConta() {
        if (listaContas.isEmpty()) {
            System.out.println("Nao tem contas salvas");

            return null;
        }

        System.out.println("Escolha uma conta");
        for (int i = 0; i < listaContas.size(); i++) {
            System.out.println((i + 1) + " - " + listaContas.get(i).mostrarNome());
        }

        while (true) {
            int escolha = Leitor.lerInteiro("Opção: ");

            if (escolha == 0) {
                // return null; // cancelou
            }
            if (escolha >= 1 && escolha <= listaContas.size()) {
                return listaContas.get(escolha - 1); // lista começa em 0, o menu em 1
            }
            System.out.println("Opção inválida!");
        }

    }

    public boolean removerConta(Conta conta) {
        return listaContas.remove(conta);
    }
}