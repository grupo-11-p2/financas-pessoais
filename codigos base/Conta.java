public class Conta {
    private String nome;
    private double saldo = 0.0;
    private final Extrato extrato = new Extrato();

    public void aplicarNome(String nome) {
        this.nome = nome;
    }

    public String mostrarNome() {
        return nome;
    }

    public double mostrarSaldo() {
        return saldo;
    }

    public void aplicarSaldo(double valor) {
        this.saldo = valor;
    }

    public void creditar(double valor) {
        validarValorPositivo(valor);
        saldo += valor;
    }

    public void debitar(double valor) {
        validarValorPositivo(valor);
        if (valor > saldo) {
            throw new IllegalStateException("Saldo insuficiente.");
        }
        saldo -= valor;
    }

    public Extrato getExtrato() {
        return extrato;
    }

    private void validarValorPositivo(double valor) {
        if (!Double.isFinite(valor) || valor <= 0) {
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
        }
    }
}