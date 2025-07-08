public class Curso {
    private String codigo;
    private String nome;
    private String modalidade;

    public Curso(String nome, String modalidade) {
        this.nome = nome;
        this.modalidade = modalidade;
        this.codigo = gerarCodigo(nome);
    }

    /**
     * Função que gera o código do curso baseado no nome dele.
     * 1º -> Ela usa como base as três primeiras letras do nome e deixa todas em maiúsculo por padrão.
     * 
     * 2º -> Se o nome do curso tiver mais de uma palavra:
     *       - Usa as duas primeiras letras do primeiro termo.
     *       - Junta com com a primeira letra do segundo.
     * 
     * Exemplo:
     * nomeCurso = "Informática";
     * this.codigo = "INF";
     * nomeCurso = "Meio Ambiente";
     * this.codigo = "MEA";
     * 
     * @param nomeCurso Nome do curso.
     * 
     * @return String - Codigo do curso formatado.
     */
    private String gerarCodigo(String nomeCurso) {
        // Se for somente uma palavra.
        if (!nomeCurso.contains(" ")) {
            return nomeCurso.substring(0, 3).toUpperCase();
        }
        int espacoIndex = nomeCurso.indexOf(" ");
        String primeroTermo = nomeCurso.substring(0, 2).toUpperCase();
        String segundoTermo = nomeCurso.substring(espacoIndex+1, espacoIndex+2).toUpperCase();
        return primeroTermo + segundoTermo;
    }

    public String getCodigo() {
        return this.codigo;
    }
    public String getNome() {
        return this.nome;
    }
    public String getModalidade() {
        return this.modalidade;
    }
}
