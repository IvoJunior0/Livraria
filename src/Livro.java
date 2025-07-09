public class Livro {
    private static int quantidadeTotalLivros = 0;
    private int codigo;
    private String titulo;
    private String autor;
    private String edicao;
    private int ano;
    private int numeroPaginas;
    private int estoque;

    /**
     * Construtor da classe Livro.
     * 
     * @param titulo Título do livro.
     * @param autor Autor do livro.
     * @param edicao Edição do livro.
     * @param ano Ano de publicação da edição.
     * @param numeroPaginas Número de páginas totais da edição.
     * @param estoque Estoque de exemplares disponíveis.
     */
    public Livro(String titulo, String autor, String edicao, int ano, int numeroPaginas, int estoque) {
        this.codigo = ++quantidadeTotalLivros;
        this.titulo = titulo;
        this.autor = autor;
        this.edicao = edicao;
        this.ano = ano;
        this.numeroPaginas = numeroPaginas;
        this.estoque = estoque;
    }

    public static int getQuantidadeTotalLivros() {
        return Livro.quantidadeTotalLivros;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public String getAutor() {
        return this.autor;
    }

    public String getEdicao() {
        return this.edicao;
    }

    public int getAno() {
        return this.ano;
    }

    public int getNumeroPaginas() {
        return this.numeroPaginas;
    }

    public int getEstoque() {
        return this.estoque;
    }
}
