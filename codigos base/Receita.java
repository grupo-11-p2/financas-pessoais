public class Receita extends Transacao {
    public Receita(Conta conta) {
        super(conta, "Receita");
    }

    @Override
    protected void validar() {
    }

    @Override
    protected void aplicarSaldo() {
        getConta().creditar(getValor());
    }
}