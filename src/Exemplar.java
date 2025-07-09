public class Exemplar {
    private int codigo;
    private String status;
    private Livro livro;

    public Exemplar(Livro livro) {
        this.status = "Livre";
        this.livro = livro;
    }

    public Livro getLivro() {
        return this.livro;
    }
}
