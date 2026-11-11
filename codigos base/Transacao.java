import java.time.LocalDate;
import java.util.Scanner;

public abstract class Transacao {

    private static int ContadorId = 0;

    private int id;
    private LocalDate data;
    private Double valor;
    private String descricao;
    private Categoria categoria;

    private Conta contaRelacionada;
    private String tipo;

   public void aplicar(Conta conta) {}

   public void gerarId(){
    if (id != 0) {
        id = ContadorId + 1;
    }else{
        System.out.println("Aviso: Ja existe um id gerado");
    }
   }

   public void colocarDescricao(){
    if (descricao != null){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a descrição desejada: ");
        descricao = scanner.nextLine();

        scanner.close();
        //Precisa de ajuste posteriormente
    }
        
   }
}
