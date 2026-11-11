public class Transferencia {
    private int valor;
    private Conta conta;

    public Transferencia(int valor, Conta conta) {
        this.valor = valor;
        this.conta = conta;
    }

    public void aplicar() {
        this.conta.aplicarSaldo(valor);
    }
}