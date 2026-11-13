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

    @Override
    public void cancelar() {
        if (getConta().mostrarSaldo() - getValor() < 0) {
            throw new IllegalStateException("o saldo ficaria negativo.");
        }
        getConta().debitar(getValor());
        getConta().getExtrato().DeletarExtrato(getId());
    }

    @Override
    public boolean editar(double novoValor) {
        if (novoValor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        double diferenca = novoValor - getValor();

        if (getConta().mostrarSaldo() + diferenca < 0) {
            throw new IllegalStateException("o saldo ficaria negativo.");
        }

        if (diferenca > 0) {
            getConta().creditar(diferenca);
        } else if (diferenca < 0) {
            getConta().debitar(-diferenca);
        }

        setValor(novoValor);
        return true;
    }
}