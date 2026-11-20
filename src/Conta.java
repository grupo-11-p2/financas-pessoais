public class Conta {
    private String nome;
    private double saldo = 0.0;
    private final Extrato extrato = new Extrato();
    private boolean dadosmudados = false;

    public void aplicarNome(String nome) {
        this.nome = nome;
        this.dadosmudados = true;
    }

    public String mostrarNome() {
        return nome;
    }
 
    public double mostrarSaldo() {
        return saldo;
    }

    public void aplicarSaldo(double valor) {
        this.saldo = valor;
        this.dadosmudados = true;
    }

    public Extrato puxarExtrato() {
        return extrato;
    }

    public boolean mostrarDados() {
        return dadosmudados;
    }
}