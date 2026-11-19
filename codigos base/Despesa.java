public class Despesa extends Transacao {
    public Despesa(Conta conta) {
        super(conta, "Despesa");
    }

    @Override
    protected void validar() {
        if (puxarValor() > puxarConta().mostrarSaldo()) {
            throw new IllegalStateException("Saldo insuficiente para realizar a despesa.");
        }
    }

    @Override
    protected void aplicarSaldo() {
        debitar(puxarValor());
    }

    @Override
    public void cancelar() {
        creditar(puxarValor());
        puxarConta().puxarExtrato().DeletarExtrato(puxarId());
    }

    @Override
    public boolean editar(double novoValor) {
        if (!Double.isFinite(novoValor) || novoValor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        double diferenca = novoValor - puxarValor(); // positivo = a despesa aumentou

        if (puxarConta().mostrarSaldo() - diferenca < 0) {
            throw new IllegalStateException("saldo insuficiente para esse valor.");
        }

        if (diferenca > 0) {
            debitar(diferenca);
        } else if (diferenca < 0) {
            creditar(-diferenca);
        }

        aplicarValor(novoValor);
        return true;
    }
}