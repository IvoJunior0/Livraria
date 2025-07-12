public class Exemplar {
    private static int quantidadeTotalExemplares = 0;
    private int codigo;
    private String status;
    private Livro livro;

    public Exemplar(Livro livro) {
        this.codigo = ++quantidadeTotalExemplares;
        this.status = "Livre";
        this.livro = livro;
        // Toda vez que um exemplar é criado, o estoque de livros aumenta.
        // Forma de deixar o valor do estoque dinâmico.
        this.livro.atualizarEstoque(1);
    }

    /**
     * Método de atualização dos atributos no momento do emprestimo.
     * 
     * Retira um do estoque disponível do livro no sistema.
     * Atualiza o status do exemplar para indisponível.
     */
    public void emprestar() {
        this.getLivro().atualizarEstoque(-1);
        this.status = "Emprestado";
    }

    public Livro getLivro() {
        return this.livro;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getStatus() {
        return this.status;
    }
}
