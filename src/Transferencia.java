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
        if (puxarValor() > puxarConta().mostrarSaldo()) {
            throw new ExcecoesFinanceiras.SaldoInsuficienteException("Saldo insuficiente para realizar a transferência.");
        }
    }

    @Override
    protected void aplicarSaldo() {
        debitar(puxarValor());
        creditar(contaDestino, puxarValor());
    }

    @Override
    protected Conta[] contasAfetadas() {
        return new Conta[] { puxarConta(), contaDestino };
    }

    public Conta puxarContaDestino() {
        return contaDestino;
    }

    @Override
    public void cancelar() {
        if (contaDestino.mostrarSaldo() - puxarValor() < 0) {
            throw new IllegalStateException("a conta de destino não tem saldo para devolver.");
        }
        debitar(contaDestino, puxarValor());
        creditar(puxarValor());
        for (Conta conta : contasAfetadas()) {
            conta.puxarExtrato().DeletarExtrato(puxarId());
        }
    }

    @Override
    public boolean editar(double novoValor) {
        if (!Double.isFinite(novoValor) || novoValor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        double diferenca = novoValor - puxarValor();

        if (puxarConta().mostrarSaldo() - diferenca < 0) {
            throw new IllegalStateException("saldo insuficiente na conta de origem.");
        }
        if (contaDestino.mostrarSaldo() + diferenca < 0) {
            throw new IllegalStateException("a conta de destino não tem saldo para devolver.");
        }

        if (diferenca > 0) {
            debitar(diferenca);
            creditar(contaDestino, diferenca);
        } else if (diferenca < 0) {
            creditar(-diferenca);
            debitar(contaDestino, -diferenca);
        }

        aplicarValor(novoValor);
        return true;
    }
}