public class Receita extends Transacao {
    public Receita(Conta conta) {
        super(conta, "Receita");
    }

    @Override
    protected void validar(Conta conta) {
    }

    @Override
    protected void aplicarSaldo(Conta conta) {
        creditar(conta, puxarValor());
    }


    

    @Override
    public void cancelar(Conta conta) {
        if (conta.mostrarSaldo() - puxarValor() < 0) {
            throw new IllegalStateException("o saldo ficaria negativo.");
        }
        debitar(conta, puxarValor());
        conta.puxarExtrato().deletarExtrato(puxarId());
    }

    @Override
    public boolean editar(Conta conta, double novoValor) {
        if (novoValor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        double diferenca = novoValor - puxarValor();

        if (conta.mostrarSaldo() + diferenca < 0) {
            throw new IllegalStateException("o saldo ficaria negativo.");
        }

        if (diferenca > 0) {
            creditar(conta, diferenca);
        } else if (diferenca < 0) {
            debitar(conta, -diferenca);
        }

        aplicarValor(novoValor);
        return true;
    }
}