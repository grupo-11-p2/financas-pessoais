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
}