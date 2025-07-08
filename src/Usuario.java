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
        this.codigo = -1; // Por padrão, usuário não cadastrados receberão código igual a -1.
    }

    /**
     * Construtor de Usuario com parâmetros.
     * Usado para instância de contas estáticas do sistema.
     * @param nome
     * @param curso
     * @param senha
     */
    public Usuario(String nome, Curso curso, String senha) {
        this.codigo = ++Usuario.quantidadeUsuarios;
        this.nome = nome;
        this.senha = senha;
        this.curso = curso;
        this.matricula = gerarMatricula();
    }

    /**
     * Função de cadastro de usuário.
     * Usada para cadastro, implementação de novos dados e atualização de atributos.
     * 
     * @param nome
     * @param curso
     * @param senha
     */
    public void cadastrarUsuario(String nome, Curso curso, String senha) {
        this.codigo = ++Usuario.quantidadeUsuarios;
        this.nome = nome;
        this.senha = senha;
        this.curso = curso;
        this.matricula = gerarMatricula();
    }

     /**
      * Por questões de segurança, existe essa sobrecarga.
      * Usada durante a alteração de dados que não sejam a senha. (evitar que exista um getSenha())
      * 
      * @param nome Nome do usuário.
      * @param curso Curso do usuáro.
      */
    public void cadastrarUsuario(String nome, Curso curso) {
        this.codigo = ++Usuario.quantidadeUsuarios;
        this.nome = nome;
        this.curso = curso;
        this.matricula = gerarMatricula();
    }

    /**
     * Método gerador de matriculas de alunos.
     * A matricula é a junção entre o código do curso do aluno e o codigo do aluno.
     * O código do aluno é formatado em um string de 4 digitos decimais.
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
     * Função que verificar se a senha digita é igual a senha do usuário.
     * Garantir que o cliente não tenha acesso direto a senha do usuário, mas possa saber se a senha digitada está correta.
     * 
     * @param senhaDigitada Senha para verificação.
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
