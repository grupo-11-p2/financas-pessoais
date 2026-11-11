public class Despesa {
    private int valor;
    private Conta conta;

    public Despesa(int valor, Conta conta) {
        this.valor = valor;
        this.conta = conta;
    }

    public void aplicar() {
        this.conta.aplicarSaldo(valor);
    }
}