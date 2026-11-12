import java.util.ArrayList;
import java.util.List;

public class Gerenciarcontas {
    private final List<Conta> listaContas = new ArrayList<Conta>();

    public void criarConta() {

        System.out.println("\nCriação de conta inicializada:");
        String NomeConta = Leitor.LerNome("\nDigite um nome:");

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
                return null;
            }
            if (escolha >= 1 && escolha <= listaContas.size()) {
                return listaContas.get(escolha - 1);
            }
            System.out.println("Opção inválida!");
        }
    }

    public Conta SelecionarConta(Conta contaExcluir) {
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
        while (true) {
            int escolha = Leitor.lerInteiro("Opção: ");
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
        return listaContas.remove(conta);
    }
}