public class Transferencia extends Transacao {
    private final Conta contaDestino;

    public Transferencia(Conta contaOrigem, Conta contaDestino) {
        super(contaOrigem, "Transferencia");
        if (contaDestino == null) {
            throw new IllegalArgumentException("A conta de destino não pode ser nula.");
        }
        if (contaOrigem == contaDestino) {
            throw new IllegalArgumentException("A origem e o destino devem ser contas diferentes.");
        }
        this.contaDestino = contaDestino;
    }

    @Override
    protected void validar() {
        if (getValor() > getConta().mostrarSaldo()) {
            throw new IllegalStateException("Saldo insuficiente para realizar a transferência.");
        }
    }

    @Override
    protected void aplicarSaldo() {
        getConta().debitar(getValor());
        contaDestino.creditar(getValor());
    }

    @Override
    protected Conta[] contasAfetadas() {
        return new Conta[] { getConta(), contaDestino };
    }

    public Conta getContaDestino() {
        return contaDestino;
    }
}