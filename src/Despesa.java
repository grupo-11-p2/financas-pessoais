public class Despesa extends Transacao {
    public Despesa(Conta conta) {
        super(conta, "Despesa");
    }

    @Override
    protected void validar(Conta conta) {
        if (puxarValor() > conta.mostrarSaldo()) {
            throw new ExcecoesFinanceiras.SaldoInsuficienteException("Saldo insuficiente para realizar a despesa.");
        }
    }

    @Override
    protected void aplicarSaldo(Conta conta) {
        debitar(conta, puxarValor());
    }

    @Override
    public void cancelar(Conta conta) {
        creditar(conta, puxarValor());
        conta.puxarExtrato().deletarExtrato(puxarId());
    }

    @Override
    public boolean editar(Conta conta, double novoValor) {
        if (!Double.isFinite(novoValor) || novoValor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        double diferenca = novoValor - puxarValor(); 

        if (conta.mostrarSaldo() - diferenca < 0) {
            throw new IllegalStateException("saldo insuficiente para esse valor.");
        }

        if (diferenca > 0) {
            debitar(conta, diferenca);
        } else if (diferenca < 0) {
            creditar(conta, -diferenca);
        }

        aplicarValor(novoValor);
        return true;
    }
}