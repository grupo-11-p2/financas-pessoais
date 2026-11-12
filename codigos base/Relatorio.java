import java.util.ArrayList;
import java.util.List;

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

    public double calcularTotalPorTipo(Extrato transacoes, String tipo) {
        if (transacoes == null) {
            throw new IllegalArgumentException("O extrato não pode ser nulo.");
        }
        if (tipo == null || tipo.trim().isEmpty()) {
            throw new IllegalArgumentException("O tipo da transação não pode ser vazio.");
        }

        double total = 0.0;
        for (Transacao transacao : transacoes.getListaTransacao()) {
            if (tipo.equalsIgnoreCase(transacao.getTipo())) {
                total += transacao.getValor();
            }
        }
        return total;
    }

    public void gerarRelatorio(String nomeConta) {
        int mes = lerMes();
        int ano = lerAno();
        selecionarMes(mes);
        selecionarAno(ano);

        Categoria categoriaSelecionada = selecionarCategoria(puxarTodasCategorias(mes, ano));
        Extrato transacoes = categoriaSelecionada == null
                ? mostrarExtratoTotal(mes, ano)
                : mostrarExtratoTotal(mes, ano, categoriaSelecionada);

        System.out.printf("%nRelatório de %02d/%d - %s%n", mes, ano, nomeConta);
        if (categoriaSelecionada != null) {
            System.out.println("Categoria: " + categoriaSelecionada.getNome());
        }
        transacoes.exibirTransacoes();
        System.out.printf("Total de receitas: R$ %.2f%n",
                calcularTotalPorTipo(transacoes, "Receita"));
        System.out.printf("Total de despesas: R$ %.2f%n",
                calcularTotalPorTipo(transacoes, "Despesa"));
        System.out.printf("Total de transferências: R$ %.2f%n",
                calcularTotalPorTipo(transacoes, "Transferencia"));
    }

    public Extrato mostrarExtratoTotal(int mes, int ano) {
        validarMes(mes);
        validarAno(ano);
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
        validarAno(ano);
        Extrato resultado = new Extrato();

        for (Transacao transacao : extrato.getListaTransacao()) {
            if (transacao.getMes() == mes
                    && transacao.getAno() == ano
                    && categoriasIguais(transacao.getCategoria(), categoria)) {
                resultado.aplicarExtrato(transacao);
            }
        }

        return resultado;
    }

    public List<Categoria> puxarTodasCategorias() {
        return puxarTodasCategorias(extrato.getListaTransacao());
    }

    public List<Categoria> puxarTodasCategorias(int mes, int ano) {
        Extrato transacoes = mostrarExtratoTotal(mes, ano);
        return puxarTodasCategorias(transacoes.getListaTransacao());
    }

    private List<Categoria> puxarTodasCategorias(List<Transacao> transacoes) {
        List<Categoria> categorias = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            Categoria categoriaTransacao = transacao.getCategoria();

            if (categoriaTransacao != null && !contemCategoria(categorias, categoriaTransacao)) {
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
        validarAno(ano);
        this.ano = ano;
    }

    public void selecionarCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    private int lerMes() {
        while (true) {
            int mes = Leitor.lerInteiro("Mês (1-12): ");
            if (mes >= 1 && mes <= 12) {
                return mes;
            }
            System.out.println("O mês deve estar entre 1 e 12.");
        }
    }

    private int lerAno() {
        while (true) {
            int ano = Leitor.lerInteiro("Ano: ");
            if (ano > 0) {
                return ano;
            }
            System.out.println("O ano deve ser maior que zero.");
        }
    }

    private Categoria selecionarCategoria(List<Categoria> categorias) {
        if (categorias.isEmpty()) {
            return null;
        }

        System.out.println("Categoria para filtrar (0 - Todas):");
        for (int i = 0; i < categorias.size(); i++) {
            System.out.printf("%d - %s%n", i + 1, categorias.get(i).getNome());
        }

        while (true) {
            int escolha = Leitor.lerInteiro("Opção: ");
            if (escolha == 0) {
                return null;
            }
            if (escolha >= 1 && escolha <= categorias.size()) {
                Categoria selecionada = categorias.get(escolha - 1);
                selecionarCategoria(selecionada);
                return selecionada;
            }
            System.out.println("Opção inválida!");
        }
    }

    private void validarMes(int mes) {
        if (mes < 1 || mes > 12) {
            throw new IllegalArgumentException("O mês deve estar entre 1 e 12.");
        }
    }

    private void validarAno(int ano) {
        if (ano < 1) {
            throw new IllegalArgumentException("O ano deve ser maior que zero.");
        }
    }

    private boolean contemCategoria(List<Categoria> categorias, Categoria categoria) {
        for (Categoria existente : categorias) {
            if (categoriasIguais(existente, categoria)) {
                return true;
            }
        }
        return false;
    }

    private boolean categoriasIguais(Categoria primeira, Categoria segunda) {
        if (primeira == segunda) {
            return true;
        }
        if (primeira == null || segunda == null) {
            return false;
        }
        String nomePrimeira = primeira.getNome();
        String nomeSegunda = segunda.getNome();
        return nomePrimeira == null
                ? nomeSegunda == null
                : nomeSegunda != null && nomePrimeira.equalsIgnoreCase(nomeSegunda);
    }
}