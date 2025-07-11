import java.util.ArrayList;
import java.util.Map;

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
     * 
     * @param nome Nome a ser cadastrado.
     * @param curso Curso a ser cadastrado.
     * @param senha Senha a ser cadastrada.
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
     * @param nome Nome a ser cadastrado.
     * @param curso Curso a ser cadastrado.
     * @param senha Senha a ser cadastrada.
     */
    public void cadastrarUsuario(String nome, Curso curso, String senha) {
        this.codigo = ++Usuario.quantidadeUsuarios;
        this.nome = nome;
        this.senha = senha;
        this.curso = curso;
        this.matricula = gerarMatricula();
    }

    /**
     * Método gerador de matriculas de alunos.
     * 
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
        return "2025" + matriculaCurso + matriculaID;
    }

    /**
     * Método que verifica se a senha digita é igual a senha do usuário.
     * Garantir que o cliente não tenha acesso direto a senha do usuário, mas possa saber se a senha digitada está correta.
     * 
     * @param senhaDigitada Senha para verificação.
     * @return boolean - Se a senha digitada é igual a senha do usuário.
     */
    public boolean verificaSenha(String senhaDigitada) {
        return this.senha.equals(senhaDigitada);
    }

    /**
     * Método de checagem se o usuário tem algum emprestimo cadastro no sistema.
     * Usa o código do usuário como chave do HashMap.
     * 
     * @param emprestimosLista Lista de emprestimos do sistema.
     * @return boolean - Se tem ou não tem emprestimo cadastrado.
     */
    public boolean fezEmprestimo(Map<Integer, Emprestimo> emprestimosLista) {
        if (emprestimosLista.get(this.getCodigo()) == null) {
            return false;
        }
        return true;
    }

    /**
     * Método de alteração de dados do usuário baseado na escolha em menus.
     * 
     * @param dadoEscolhido Código da opção do dado escolhido.
     * @param novoDado Novo valor a ser alterado.
     */
    public void alterarDados(int dadoEscolhido, Object novoDado) {
        switch (dadoEscolhido) {
            // Nome
            case 1:
                this.nome = novoDado.toString();
                break;
            // Senha
            case 2:
                this.senha = novoDado.toString();
                break;
            // Curso
            case 3:
                this.curso = (Curso) novoDado;
                break;
            
            default:
                break;
        }
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
