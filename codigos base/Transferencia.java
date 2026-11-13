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

    @Override
    public void cancelar() {
        if (contaDestino.mostrarSaldo() - getValor() < 0) {
            throw new IllegalStateException("a conta de destino não tem saldo para devolver.");
        }
        contaDestino.debitar(getValor());
        getConta().creditar(getValor());
        for (Conta c : contasAfetadas()) {
            c.getExtrato().DeletarExtrato(getId());
        }
    }

    @Override
    public boolean editar(double novoValor) {
        if (!Double.isFinite(novoValor) || novoValor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        double diferenca = novoValor - getValor();

        if (getConta().mostrarSaldo() - diferenca < 0) {
            throw new IllegalStateException("saldo insuficiente na conta de origem.");
        }
        if (contaDestino.mostrarSaldo() + diferenca < 0) {
            throw new IllegalStateException("a conta de destino não tem saldo para devolver.");
        }

        if (diferenca > 0) {
            getConta().debitar(diferenca);
            contaDestino.creditar(diferenca);
        } else if (diferenca < 0) {
            getConta().creditar(-diferenca);
            contaDestino.debitar(-diferenca);
        }

        setValor(novoValor);
        return true;
    }
}