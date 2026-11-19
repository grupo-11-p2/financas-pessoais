public class Categoria {
    private String nome;

    public void definirNome(String nome) {
        this.nome = nome;
    }

    public String puxarNome() {
        return nome;
    }
}