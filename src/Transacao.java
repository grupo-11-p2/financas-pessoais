import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Transacao {
    private static int contadorId;

    private final int id;
    private final String nomeConta;
    private final String tipo;
    private LocalDate data;
    private double valor;
    private String descricao;
    private Categoria categoria;

    protected Transacao(Conta conta, String tipo) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta não pode ser nula.");
        }
        nomeConta = conta.mostrarNome();
        this.tipo = tipo;
        this.id = ++contadorId;
    }

    public final void registrar(Conta conta) {
        registrar(conta, null);
    }

    public final void registrar(Conta conta, Conta contaDestino) {
        definirDados();
        validar(conta, contaDestino);
        aplicarSaldo(conta, contaDestino);
        for (Conta contaAfetada : contasAfetadas(conta, contaDestino)) {
            contaAfetada.puxarExtrato().aplicarExtrato(this);
        }
    }

    private void definirDados() {
        System.out.println("Id definido é: " + id);

        while (true) {
            try {
                data = LocalDate.parse(Leitor.lerTexto("Digite a data (AAAA-MM-DD): "));
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato AAAA-MM-DD.");
            }
        }

        do {
            valor = Leitor.lerDouble("Digite o valor: ");
            if (!Double.isFinite(valor) || valor <= 0) {
                throw new ExcecoesFinanceiras.ValorInvalidoException("O valor deve ser maior que zero.");
            }
        } while (!Double.isFinite(valor) || valor <= 0);

        descricao = Leitor.lerTexto("Digite a descrição: ");
        String nomeCategoria = Leitor.lerTexto("Digite a categoria (ou deixe em branco): ");
        if (nomeCategoria.trim().isEmpty()) {
            categoria = null;
        } else {
            categoria = new Categoria();
            categoria.definirNome(nomeCategoria.trim());
        }
    }

    protected abstract void validar(Conta conta);

    protected abstract void aplicarSaldo(Conta conta);

    public abstract void cancelar(Conta conta);

    public abstract boolean editar(Conta conta, double novoValor);

    protected void validar(Conta conta, Conta contaDestino) {
        validar(conta);
    }

    protected void aplicarSaldo(Conta conta, Conta contaDestino) {
        aplicarSaldo(conta);
    }

    protected Conta[] contasAfetadas(Conta conta, Conta contaDestino) {
        return contasAfetadas(conta);
    }

    public void cancelar(Conta conta, Conta contaDestino) {
        cancelar(conta);
    }

    public boolean editar(Conta conta, Conta contaDestino, double novoValor) {
        return editar(conta, novoValor);
    }

    protected Conta[] contasAfetadas(Conta conta) {
        return new Conta[] { conta };
    }

    protected final void creditar(Conta conta, double valor) {
        validarOperacaoSaldo(conta, valor);
        conta.aplicarSaldo(conta.mostrarSaldo() + valor);
    }

    protected final void debitar(Conta conta, double valor) {
        validarOperacaoSaldo(conta, valor);
        if (valor > conta.mostrarSaldo()) {
            throw new IllegalStateException("Saldo insuficiente.");
        }
        conta.aplicarSaldo(conta.mostrarSaldo() - valor);
    }

    private void validarOperacaoSaldo(Conta conta, double valor) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta não pode ser nula.");
        }
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }

    public final int puxarId() {
        return id;
    }

    public final LocalDate puxarData() {
        return data;
    }

    public final int puxarMes() {
        return data == null ? 0 : data.getMonthValue();
    }

    public final int puxarAno() {
        return data == null ? 0 : data.getYear();
    }

    public final double puxarValor() {
        return valor;
    }

    protected void aplicarValor(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public final String puxarDescricao() {
        return descricao;
    }

    public final Categoria puxarCategoria() {
        return categoria;
    }

    public final String puxarTipo() {
        return tipo;
    }
}
