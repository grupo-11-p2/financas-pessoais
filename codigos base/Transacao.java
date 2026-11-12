import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class Transacao {

    private static int ContadorId = 0;

    private int id;
    private LocalDate data;
    private Double valor;
    private String descricao;
    private Categoria categoria;
    private Conta contaRelacionada;
    private String tipo;
    private String nomecategoria;

   public int gerarId() {
        if (id != 0) {
            System.out.println("Aviso: já existe um id gerado");
            return id;
        }
        ContadorId++;
        id = ContadorId;
        return id;
    }

    public void colocarData() {
        while (true) {
            try {
                data = LocalDate.parse(Leitor.lerTexto("Digite a data (AAAA-MM-DD): "));
                return;
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato AAAA-MM-DD.");
            }
        }
    }

    public void colocarValor() {
        do {
            valor = Leitor.lerDouble("Digite o valor: ");
            if (valor <= 0) {
                System.out.println("O valor deve ser maior que zero.");
            }
        } while (valor <= 0);
    }

    public void colocarDescricao() {
      //  descricao = Leitor.lerTextoLivre("Digite a descrição desejada: ");
    }

    public void colocarCategoria() {
        String nomeCategoria = Leitor.lerTexto("Digite a categoria desejada: ");
    //    categoria = new Categoria(nomeCategoria);
    }

    //jogar alguns metodos para leitor, refatorar leitor

    public void DefinirDados() {
        gerarId();
        System.out.println("Id definido é: " + id);

        colocarData();
        System.out.println("Data definida é: " + data);

        colocarValor();

        colocarDescricao();
        colocarCategoria();
    }

    public double getValor() {
        return valor;
    }

    public void aplicar(Conta conta) {
    }
}
