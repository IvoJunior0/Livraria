import java.util.ArrayList;

public class Usuario {
    private static int quantidadeUsuarios;
    // Código serve para identificação do sistema pro sistema.
    // O atributo matricula é o codigo visível disponível ao usuário.
    private int codigo;
    private String nome;
    private String matricula;
    private String senha;
    private Curso curso;

    public Usuario() {
        // Por padrão, usuário não cadastrados receberão código igual a -1.
        this.codigo = -1;
    }

    public void cadastrarUsuario(String nome, Curso curso, String senha) {
        this.codigo = ++Usuario.quantidadeUsuarios;
        this.nome = nome;
        this.senha = senha;
        this.curso = curso;
        this.matricula = gerarMatricula();
    }
    /*
     * Por questões de segurança, existe essa sobrecarga.
     * Usada durante a alteração de dados que não sejam a senha. (evitar que exista um getSenha())
     */
    public void cadastrarUsuario(String nome, Curso curso) {
        this.codigo = ++Usuario.quantidadeUsuarios;
        this.nome = nome;
        this.curso = curso;
        this.matricula = gerarMatricula();
    }

    /**
     * Método gerador de matriculas de alunos.
     * A matricula é a junção entre o curso do aluno e o codigo formatado dele.
     * O código é formatado em um string de 4 digitos decimais.
     * 
     * Exemplo:
     * O primeiro aluno é matriculado em informática e terá código do curso INF e código 1.
     * INF + 1 = INF0001
     * 
     * @return String - Matricula formatada do aluno.
     */
    private String gerarMatricula() {
        String matriculaCurso = this.curso.getCodigo();
        String matriculaID = String.format("%04d", this.codigo);
        return matriculaCurso + matriculaID;
    }

    /**
     * 
     * @param senhaDigitada
     * @return boolean - Se a senha digitada é igual a senha do usuário.
     */
    public boolean verificaSenha(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }

    public int getCodigo() {
        return this.codigo;
    }
    public String getNome() {
        return this.nome;
    }
    public String getMatricula() {
        return this.matricula;
    }
    public Curso getCurso() {
        return this.curso;
    }
}
