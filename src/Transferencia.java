public class Transferencia extends Transacao {
    private final String nomeContaDestino;

    public Transferencia(Conta contaOrigem, Conta contaDestino) {
        super(contaOrigem, "Transferencia");
        if (contaDestino == null) {
            throw new IllegalArgumentException("A conta de destino não pode ser nula.");
        }
        if (contaOrigem == contaDestino) {
            throw new IllegalArgumentException("A origem e o destino devem ser contas diferentes.");
        }
        nomeContaDestino = contaDestino.mostrarNome();
    }

    @Override
    protected void validar(Conta conta) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta de origem não pode ser nula.");
        }
        if (puxarValor() > conta.mostrarSaldo()) {
            throw new ExcecoesFinanceiras.SaldoInsuficienteException("Saldo insuficiente para realizar a transferência.");
        }
    }

    @Override
    protected void aplicarSaldo(Conta conta) {
        throw new IllegalStateException("É necessário informar a conta de destino.");
    }

    @Override
    protected void validar(Conta conta, Conta contaDestino) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta de origem não pode ser nula.");
        }
        if (contaDestino == null) {
            throw new IllegalArgumentException("A conta de destino não pode ser nula.");
        }
        if (puxarValor() > conta.mostrarSaldo()) {
            throw new ExcecoesFinanceiras.SaldoInsuficienteException("Saldo insuficiente para realizar a transferência.");
        }
    }

    @Override
    protected void aplicarSaldo(Conta conta, Conta contaDestino) {
        if (conta == null || contaDestino == null) {
            throw new IllegalArgumentException("A conta de origem e a conta de destino não podem ser nulas.");
        }
        debitar(conta, puxarValor());
        creditar(contaDestino, puxarValor());
    }

    @Override
    protected Conta[] contasAfetadas(Conta contaAfetada) {
        throw new IllegalStateException("É necessário informar a conta de destino.");
    }

    @Override
    protected Conta[] contasAfetadas(Conta contaAfetada, Conta contaDestino) {
        if (contaAfetada == null || contaDestino == null) {
            throw new IllegalArgumentException("As contas da transferência não podem ser nulas.");
        }
        return new Conta[] { contaAfetada, contaDestino };
    }

    public String puxarNomeContaDestino() {
        return nomeContaDestino;
    }

    @Override
    public void cancelar(Conta conta) {
        throw new IllegalStateException("É necessário informar a conta de destino.");
    }

    @Override
    public void cancelar(Conta conta, Conta contaDestino) {
        if (conta == null || contaDestino == null) {
            throw new IllegalArgumentException("A conta de origem e a conta de destino não podem ser nulas.");
        }
        if (contaDestino.mostrarSaldo() - puxarValor() < 0) {
            throw new ExcecoesFinanceiras.SaldoInsuficienteException("a conta de destino não tem saldo para devolver.");
        }
        debitar(contaDestino, puxarValor());
        creditar(conta, puxarValor());
        for (Conta contaAfetada : contasAfetadas(conta, contaDestino)) {
            contaAfetada.puxarExtrato().deletarExtrato(puxarId());
        }
    }

    @Override
    public boolean editar(Conta conta, double novoValor) {
        throw new IllegalStateException("É necessário informar a conta de destino.");
    }

    @Override
    public boolean editar(Conta conta, Conta contaDestino, double novoValor) {
        if (conta == null) {
            throw new IllegalArgumentException("A conta de origem não pode ser nula.");
        }
        if (contaDestino == null) {
            throw new IllegalArgumentException("A conta de destino não pode ser nula.");
        }
        if (!Double.isFinite(novoValor) || novoValor <= 0) {
            throw new ExcecoesFinanceiras.ValorInvalidoException("O valor deve ser maior que zero.");
        }

        double diferenca = novoValor - puxarValor();

        if (conta.mostrarSaldo() - diferenca < 0) {
            throw new ExcecoesFinanceiras.SaldoInsuficienteException("saldo insuficiente na conta de origem.");
        }
        if (contaDestino.mostrarSaldo() + diferenca < 0) {
            throw new ExcecoesFinanceiras.SaldoInsuficienteException("a conta de destino não tem saldo para devolver.");
        }

        if (diferenca > 0) {
            debitar(conta, diferenca);
            creditar(contaDestino, diferenca);
        } else if (diferenca < 0) {
            creditar(conta, -diferenca);
            debitar(contaDestino, -diferenca);
        }

        aplicarValor(novoValor);
        return true;
    }
}