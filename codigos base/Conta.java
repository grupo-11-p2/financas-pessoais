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

    public Extrato puxarExtrato() {
        return extrato;
    }
}