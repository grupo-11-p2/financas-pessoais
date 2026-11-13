import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Transacao {
    private static int contadorId;

    private final int id;
    private final Conta conta;
    private final String tipo;
    private LocalDate data;
    private double valor;
    private String descricao;
    private Categoria categoria;

    protected Transacao(Conta conta, String tipo) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta não pode ser nula.");
        }
        this.conta = conta;
        this.tipo = tipo;
        this.id = ++contadorId;
    }

    public final void registrar() {
        definirDados();
        validar();
        aplicarSaldo();
        for (Conta contaAfetada : contasAfetadas()) {
            contaAfetada.getExtrato().aplicarExtrato(this);
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
                System.out.println("O valor deve ser maior que zero.");
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

    protected abstract void validar();

    protected abstract void aplicarSaldo();

    public abstract void cancelar();

    public abstract boolean editar(double novoValor);

    protected Conta[] contasAfetadas() {
        return new Conta[] { conta };
    }

    protected final Conta getConta() {
        return conta;
    }

    public final int getId() {
        return id;
    }

    public final LocalDate getData() {
        return data;
    }

    public final int getMes() {
        return data == null ? 0 : data.getMonthValue();
    }

    public final int getAno() {
        return data == null ? 0 : data.getYear();
    }

    public final double getValor() {
        return valor;
    }

    protected void setValor(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
        this.valor = valor;
    }

    public final String getDescricao() {
        return descricao;
    }

    public final Categoria getCategoria() {
        return categoria;
    }

    public final Conta getContaRelacionada() {
        return conta;
    }

    public final String getTipo() {
        return tipo;
    }
}
