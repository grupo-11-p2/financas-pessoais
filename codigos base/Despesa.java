public class Despesa extends Transacao {
    public Despesa(Conta conta) {
        super(conta, "Despesa");
    }

    @Override
    protected void validar() {
        if (getValor() > getConta().mostrarSaldo()) {
            throw new IllegalStateException("Saldo insuficiente para realizar a despesa.");
        }
    }

    @Override
    protected void aplicarSaldo() {
        getConta().debitar(getValor());
    }

    @Override
    public void cancelar() {
        getConta().creditar(getValor());
        getConta().getExtrato().DeletarExtrato(getId());
    }

    @Override
    public boolean editar(double novoValor) {
        if (!Double.isFinite(novoValor) || novoValor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        double diferenca = novoValor - getValor(); // positivo = a despesa aumentou

        if (getConta().mostrarSaldo() - diferenca < 0) {
            throw new IllegalStateException("saldo insuficiente para esse valor.");
        }

        if (diferenca > 0) {
            getConta().debitar(diferenca);
        } else if (diferenca < 0) {
            getConta().creditar(-diferenca);
        }

        setValor(novoValor);
        return true;
    }
}