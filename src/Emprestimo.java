import java.time.LocalDate;

public class Emprestimo {
    private static int totalDiasEmprestimo = 15;
    private static int totalEmprestimos = 0;
    private int codigo;
    private Usuario aluno;
    private Exemplar exemplar;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    /**
     * Construtor da classe Emprestimo.
     * 
     * A data do emprestimo é calculada automaticamente,
     * assim como a data da devolução do exemplar.
     * 
     * @param aluno    Objeto do usuário.
     * @param exemplar Objeto do exemplar.
     */
    public Emprestimo(Usuario aluno, Exemplar exemplar) {
        this.codigo = ++totalEmprestimos;
        this.aluno = aluno;
        this.exemplar = exemplar;
        this.dataEmprestimo = LocalDate.now();
        this.dataDevolucao = dataEmprestimo.plusDays(totalDiasEmprestimo);
    }

    // TODO: isso aqui.
    public void devolverExemplar() {

    }

    public static int getTotalDiasEmprestimo() {
        return Emprestimo.totalDiasEmprestimo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public Usuario getAluno() {
        return this.aluno;
    }

    public Exemplar getExemplar() {
        return this.exemplar;
    }

    public LocalDate getDataEmprestimo() {
        return this.dataEmprestimo;
    }

    public LocalDate getDataDevolucao() {
        return this.dataDevolucao;
    }
}
