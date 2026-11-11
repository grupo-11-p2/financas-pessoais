import java.time.LocalDate;

public abstract class Transacao {

    private static int ContadorId = 0;

    private int id;
<<<<<<< HEAD
    private LocalDate data;
=======
    private int data;
>>>>>>> de67a68c795babd8250031e137b4d8cdc5d8af76
    private Double valor;
    private String descricao;
    private Categoria categoria;
    private Conta contaRelacionada;
    private String tipo;
    private String nomecategoria;

   public void aplicar(Conta conta) {}

   public void gerarId(){
    if (id != 0) {
        id = ContadorId + 1;
    }else{
        System.out.println("Aviso: Ja existe um id gerado");
    }
   }

   public void colocarCategoria(){
    if (nomecategoria != null){
        categoria = new Categoria(nomecategoria);
        this.nomecategoria = Leitor.lerTexto("Digite a categoria desejada: ");
    }
   }

   public void colocarValor(){
    if (valor != null){
        valor = Leitor.lerDouble("Digite o valor: ");
    }
   }

<<<<<<< HEAD
   public void colocarData(){
    if (data != null){
        data = LocalDate.parse(Leitor.lerTexto("Digite a data (AAAA-MM-DD): "));
=======
    public void colocarData(){
    if (data == 0){
        data = Leitor.lerInteiro("Digite a data: ");
>>>>>>> de67a68c795babd8250031e137b4d8cdc5d8af76
    }
   }

   public void colocarDescricao(){
    if (descricao != null){
        descricao = Leitor.lerTexto("Digite a descrição desejada: ");
    }
   }
}
