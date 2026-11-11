public class Receita {
    private int valor;
    private Conta conta;

    public Receita(int valor, Conta conta) {
        this.valor = valor;
        this.conta = conta;
    }

    public void aplicar() {
        this.conta.aplicarSaldo(valor);
    }
}