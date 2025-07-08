import java.time.LocalDate;

public class Emprestimo {
    private static int totalDiasEmprestimo = 15;
    private Usuario aluno;
    private Exemplar exemplar;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;

    public Emprestimo(Usuario aluno, Exemplar exemplar, LocalDate dataEmprestimo) {
        this.aluno = aluno;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataEmprestimo.plusDays(totalDiasEmprestimo);
    }

    // TODO: isso aqui.
    public void devolverExemplar() {

    }
}
