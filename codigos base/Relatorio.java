import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Relatorio {
    private int mes;
    private int ano;
    private Categoria categoria;

    private final Extrato extrato;

    public Relatorio(Extrato extrato) {
        if (extrato == null) {
            throw new IllegalArgumentException("O extrato não pode ser nulo.");
        }
        this.extrato = extrato;
    }

    public double mostrarSaldoTotal() {
        double total = 0.0;

        for (Transacao transacao : extrato.getListaTransacao()) {
            total += transacao.getValor();
        }

        return total;
    }

    public Extrato mostrarExtratoTotal(int mes, int ano) {
        validarMes(mes);
        Extrato resultado = new Extrato();

        for (Transacao transacao : extrato.getListaTransacao()) {
            if (transacao.getMes() == mes && transacao.getAno() == ano) {
                resultado.aplicarExtrato(transacao);
            }
        }

        return resultado;
    }

    public Extrato mostrarExtratoTotal(int mes,int ano, Categoria categoria) 
    {
        validarMes(mes);
        Extrato resultado = new Extrato();

        for (Transacao transacao : extrato.getListaTransacao()) {
            if (transacao.getMes() == mes
                    && transacao.getAno() == ano
                    && Objects.equals(transacao.getCategoria(), categoria)) {
                resultado.aplicarExtrato(transacao);
            }
        }

        return resultado;
    }

    public List<Categoria> puxarTodasCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        for (Transacao transacao : extrato.getListaTransacao()) {
            Categoria categoriaTransacao = transacao.getCategoria();

            if (!categorias.contains(categoriaTransacao)) {
                categorias.add(categoriaTransacao);
            }
        }

        return categorias;
    }

    public void selecionarMes(int mes) {
        validarMes(mes);
        this.mes = mes;
    }

    public void selecionarAno(int ano) {
        this.ano = ano;
    }

    public void selecionarCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    private void validarMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("O mês deve estar entre 1 e 12.");
        }
    }
}