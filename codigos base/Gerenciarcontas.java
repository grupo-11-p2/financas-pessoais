import java.util.ArrayList;
import java.util.List;

public class Gerenciarcontas {
    private final List<Conta> listaContas = new ArrayList<Conta>();

    public void criarConta() {

        System.out.println("\nCriação de conta inicializada:");
        String NomeConta;
        Boolean ContaIgual;

        while (true) {
            NomeConta = Leitor.LerNome("\nDigite um nome:");
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
        return listaContas.remove(conta);
    }
}