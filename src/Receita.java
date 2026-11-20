public class Receita extends Transacao {
    public Receita(Conta conta) {
        super(conta, "Receita");
    }

    @Override
    protected void validar() {
    }

    @Override
    protected void aplicarSaldo() {
        creditar(puxarValor());
    }


    

    @Override
    public void cancelar() {
        if (puxarConta().mostrarSaldo() - puxarValor() < 0) {
            throw new IllegalStateException("o saldo ficaria negativo.");
        }
        debitar(puxarValor());
        puxarConta().puxarExtrato().DeletarExtrato(puxarId());
    }

    @Override
    public boolean editar(double novoValor) {
        if (novoValor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }

        double diferenca = novoValor - puxarValor();

        if (puxarConta().mostrarSaldo() + diferenca < 0) {
            throw new IllegalStateException("o saldo ficaria negativo.");
        }

        if (diferenca > 0) {
            creditar(diferenca);
        } else if (diferenca < 0) {
            debitar(-diferenca);
        }

        aplicarValor(novoValor);
        return true;
    }
}