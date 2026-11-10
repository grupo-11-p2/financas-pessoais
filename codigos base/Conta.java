import java.util.ArrayList;
import java.util.List;

public class Conta {
    private String nome;
    private double saldo = 0.0;
    private Extrato extrato;

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
}