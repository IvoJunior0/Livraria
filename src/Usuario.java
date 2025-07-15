import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
     * @param nome  Nome a ser cadastrado.
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
     * @param nome  Nome a ser cadastrado.
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
     * O primeiro aluno é matriculado em informática e terá código do curso INF e
     * código 1.
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
     * Garantir que o cliente não tenha acesso direto a senha do usuário, mas possa
     * saber se a senha digitada está correta.
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
        if (emprestimosLista.get(this.codigo) == null) {
            return false;
        }
        return true;
    }

    /**
     * Método de alteração do nome do usuário.
     * 
     * @return boolean - Se o novo nome foi ou não cadastrado.
     */
    public boolean alterarNome() {
        Scanner scanner = new Scanner(System.in);
        String novoNome = "";
        char opcaoNovoNome = 'n';
        boolean nomeValido = false;

        while (!nomeValido) {
            System.out.println("\nNovo nome do usuário:");
            novoNome = scanner.nextLine();
            novoNome = capitalizarTexto(novoNome);

            if (novoNome.equals(this.nome)) {
                System.out.println("\nVocê digitou mesmo nome de usuário que está cadastrado na sua conta.");
                System.out.println("Deseja digitar outro nome? (S/n)");
                opcaoNovoNome = lerRespostaSimNao(opcaoNovoNome);

                if (opcaoNovoNome == 'S') {
                    continue;
                } else {
                    break;
                }
            }

            nomeValido = true;
        }

        this.nome = novoNome;
        return nomeValido;
    }

    /**
     * Método de alteração de senha.
     * 
     * Lê a nova senha a confimação dela.
     * Entra em loop até a nova senha e a senha de confirmação forem iguais.
     * Dá a opção de voltar ao menu se o usuário não quiser mais trocar a senha.
     * 
     * @return boolean - Se a senha for alteradaou não.
     */
    public boolean alternarSenha() {
        Scanner scanner = new Scanner(System.in);
        String novaSenha = "", senhaConfirmacao;
        boolean senhaValidada = false;
        char tentarNovamente = ' ';

        while (!senhaValidada || tentarNovamente == 'S') {

            System.out.println("\nNova senha:");
            novaSenha = validarSenha(8);

            System.out.println("\nConfirmar senha:");
            senhaConfirmacao = validarSenha(8);

            if (!novaSenha.equals(senhaConfirmacao)) {
                senhaValidada = false;

                System.out.println("\nSenha não foi confirmada. Houve erro de digitação.");
                System.out.println("Deseja tentar novamente? (S/n)");

                tentarNovamente = lerRespostaSimNao(tentarNovamente);
            } else {
                tentarNovamente = 'n';
                senhaValidada = true;
                break;
            }

            // scanner.nextLine(); // Limpando buffer.

            // Se ele não quiser digitar outra senha.
            if (tentarNovamente == 'n') {
                return false;
            }
        }

        this.senha = novaSenha;
        return true;
    }

    /**
     * Método de alteração do curso do usuário.
     * Seleção de novo cursos baseados na lista de cursos disponíveis.
     * 
     * @param listaCursos Lista de cursos disponíveis.
     * @return boolean - Se o curso foi alterado ou não.
     */
    public boolean alterarCurso(List<Curso> listaCursos) {
        Scanner scanner = new Scanner(System.in);
        Curso novoCurso = null;
        char opcaoCurso = 'n';
        boolean cursoValidado = false;

        while (!cursoValidado) {
            System.out.println("Selecione seu curso:");
            novoCurso = validarOpcaoLista(listaCursos);

            if (novoCurso == this.curso) {
                System.out.println("\nVocê escolheu o mesmo curso que está cadastrado na sua conta.");
                System.out.println("Deseja escolher outro curso? (S/n)");
                opcaoCurso = lerRespostaSimNao(opcaoCurso);

                if (opcaoCurso == 'S') {
                    continue;
                } else {
                    break;
                }
            }

            cursoValidado = true;
        }

        this.curso = novoCurso;
        this.matricula = gerarMatricula(); // Quando o curso é alterado, a matricula do usuário também deve mudar.
        return cursoValidado;
    }

    /**
     * Método de capitalização de textos.
     * 
     * O texto é dividido em palavras com base em espaços usando regex.
     * Em seguida, cada palavra tem seu primeiro caractere convertido para maiúsculo
     * e é
     * concatenada ao resultado final.
     * 
     * Exemplo: "joão gabriel" torna-se "João Gabriel".
     * 
     * @param texto Texto a ser capitalizado.
     * @return String - Texto capitalizado.
     */
    public static String capitalizarTexto(String texto) {
        StringBuilder novoTexto = new StringBuilder(); // Evitar concatenação.
        String[] palavras = texto.trim().split("\\s+");

        for (int i = 0; i < palavras.length; i++) {
            String palavra = palavras[i];
            if (!palavra.isEmpty()) {
                novoTexto.append(palavra.substring(0, 1).toUpperCase() + palavra.substring(1));
                if (i < palavras.length - 1) {
                    // Evitar que adicione espaço na última palavra
                    // para que não fique "João Gabriel "
                    novoTexto.append(" ");
                }
            }
        }

        return novoTexto.toString();
    }

    /**
     * Método de validação de opções baseadas em List e ArrayLists genéricas.
     * Entra em loop enquanto a opção escolhida não estiver dentro da lista.
     *
     * @param <T>   Curso, Exemplar e Usuário
     * @param lista Lista de opções disponíveis.
     * @return T - item selecionado da lista.
     */
    public static <T> T validarOpcaoLista(List<T> lista) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        do {
            opcao = scanner.nextInt();
            if (opcao < 1 || opcao > lista.size()) {
                System.out.println("\nOpção inválida!");
                System.out.println("Por favor, escolha uma que esteja dentro da lista:");
            }
        } while (opcao < 1 || opcao > lista.size());

        return lista.get(opcao - 1);
    }

    /**
     * Método de validação de senhas.
     * Entra em loop enquanto a senha não tiver um tamanho mínimo aceito.
     * 
     * @param tamanhoMinimoSenha Numero minimo de caracteres que a senha deve ter.
     * @return String - Senha do usuário.
     */
    public static String validarSenha(int tamanhoMinimoSenha) {
        Scanner scanner = new Scanner(System.in);
        String senha;
        do {
            senha = scanner.nextLine();
            if (senha.length() < tamanhoMinimoSenha) {
                System.out.printf("Por favor, insira uma senha com %d digitos ou mais:\n", tamanhoMinimoSenha);
            }
        } while (senha.length() < tamanhoMinimoSenha);
        return senha;
    }

    /**
     * Lê e valida uma resposta de sim ou não.
     * 
     * @param opcao Opção digitada pelo usuário.
     * @return char - Respota final;
     */
    public static char lerRespostaSimNao(char opcao) {
        Scanner scanner = new Scanner(System.in);
        do {
            opcao = scanner.next().charAt(0);
            if (opcao != 'S' && opcao != 'n') {
                System.out.println("Opção inválida. Digita novamente.");
            }
        } while (opcao != 'S' && opcao != 'n');
        return opcao;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public String getNome() {
        return this.nome;
    }

    /**
     * Getter do primeiro nome do usuário.
     * 
     * Sepração em array do nome completo com regex sendo espaços os parâmetros.
     * [0] se refere ao primeiro nome do usuário.
     * 
     * @return String - Primeiro nome do usuário.
     */
    public String getPrimeiroNome() {
        return this.nome.split("[\\s]")[0];
    }

    public String getMatricula() {
        return this.matricula;
    }

    public Curso getCurso() {
        return this.curso;
    }
}
