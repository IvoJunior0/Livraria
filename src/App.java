import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean logado = false;
    public static void main(String[] args) throws Exception {
        // Objetos principais do sistema.

        // Os codigo serão usados como indexes dessa ArrayList.
        ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
        Usuario usuarioLogado = new Usuario();
        Usuario user = new Usuario();
        List<Curso> cursos = Arrays.asList(
            new Curso("Informática", "Técnico Integrado"),
            new Curso("Meio Ambiente", "Técnico Integrado"),
            new Curso("Eletromecânica", "Técnico Integrado"),
            new Curso("Alimentos", "Técnico Integrado"),
            new Curso("Automação Industrial", "Técnico Integrado"),
            new Curso("Florestas", "Técnico Integrado"),
            new Curso("Administração", "Técnico Integrado PROEJA"),
            new Curso("Metalurgia", "Técnico Subsequente"),
            new Curso("Química", "Licenciatura"),
            new Curso("Matemática", "Licenciatura"),
            new Curso("Biologia", "Licenciatura")
        );
        Livro livro1 = new Livro(
                         "Capitães da areia", 
                          "Jorge Amado", 
                         "Edição de bolso", 
                            2009, 
                  280);
        Livro livro2 = new Livro(
                         "A hora da estrela", 
                          "Clarice Lispector", 
                         "Edição comemorativa", 
                            2020, 
                  88);
        Exemplar exemplar1 = new Exemplar(livro1); // Exemplar de "Capitaes da areia".
        Exemplar exemplar2 = new Exemplar(livro2); // Exemplar de "A hora da estrela".

        int opcaoMenu = 0;

        System.out.println("=========================================================================================");
        System.out.println(" _______   _                ____                    _      ______                       ");
        System.out.println("|__   __| | |              |  _ \\                  | |    |  ____|                      ");
        System.out.println("   | |    | |__     ___    | |_) |   ___     ___   | | __ | |__      __ _    ___    ___ ");
        System.out.println("   | |    | '_ \\   / _ \\   |  _ <   / _ \\   / _ \\  | |/ / |  __|    / _` |  / __|  / _ \\");
        System.out.println("   | |    | | | | |  __/   | |_) | | (_) | | (_) | |   <  | |      | (_| | | (__  |  __/");
        System.out.println("   |_|    |_| |_|  \\___|   |____/   \\___/   \\___/  |_|\\_\\ |_|       \\__,_|  \\___|  \\___|");
        System.out.println("=========================================================================================");
        System.out.println("A melhor livraria online de Açailândia.");

        // Programa principal.
        while (opcaoMenu != 5) {
            System.out.println("\n1. Fazer cadastro");
            System.out.println("2. Login");
            System.out.println("3. Alterar dados do usuário");
            System.out.println("4. Fazer emprestimo");
            System.out.println("5. Dados do emprestimo");
            System.out.println("6. Fazer devolução");
            System.out.println("7. Sair do sistema");

            opcaoMenu = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer.

            switch (opcaoMenu) {
                // Cadastro de usuário
                case 1:
                    if (user.getCodigo() != -1) {
                        System.out.println("\nUsuário já foi cadastrado.");
                        break;
                    }

                    // Variáveis usadas para passar como parametros no cadastro do usuário.
                    String usuarioNome, usuarioSenha;
                    Curso cursoEscolhido;

                    System.out.println("\nNome do usuário:");
                    usuarioNome = scanner.nextLine();
                    System.out.println("\nSenha do usuário:");
                    usuarioSenha = validarSenha(8);

                    imprimirListaCursos(cursos);
                    System.out.println("Selecione seu curso:");
                    cursoEscolhido = validarCurso(cursos);

                    user.cadastrarUsuario(usuarioNome, cursoEscolhido, usuarioSenha);

                    System.out.printf("Usuário %s cadastrado com sucesso!\n", user.getNome());
                    imprimirDadosUsuario(user);
                    usuariosCadastrados.add(user);
                    break;

                // Login
                case 2:
                    if (logado) {
                        System.out.println("Você já está logado. Deseja sair de sua conta? (S/n)");
                    }
                    String nomeLogin, senhaLogin;

                    System.out.println("Nome:");
                    nomeLogin = scanner.nextLine();
                    System.out.println("Senha:");
                    senhaLogin = validarSenha(8);

                    for (Usuario usuario : usuariosCadastrados) {
                        if (usuario.getNome().equals(nomeLogin) && usuario.verificaSenha(senhaLogin)) {
                            usuarioLogado = usuario;
                            logado = true;
                            break;
                        }
                    }

                    if (!logado) {
                        
                    }
                    break;

                // Alterar dados do usuário.
                case 3:
                    if (user.getCodigo() == -1) {
                        System.out.println("\nUsuário não foi cadastrado.");
                        break;
                    }
                    String novoNome = user.getNome(), novaSenha = "";
                    Curso novoCurso = user.getCurso();

                    // Dados passíveis de alterações diretamente pelo usuário.
                    // Matricula e código serão lidadas pelo sistema.
                    System.out.println("\n-> Dados alteraveis");
                    System.out.println("1. Nome");
                    System.out.println("2. Senha");
                    System.out.println("3. Curso");
                    System.out.println("Deseja alterar que dado do usuário?");
                    int opcaoAlterar = scanner.nextInt();

                    switch (opcaoAlterar) {
                        case 1: // Nome
                            System.out.println("\nEscolha um novo nome:");
                            novoNome = scanner.nextLine();

                            user.cadastrarUsuario(novoNome, novoCurso);
                            break;
                        case 2: // Senha
                            System.out.println("\nNova senha:");
                            novaSenha = validarSenha(8);

                            user.cadastrarUsuario(novoNome, novoCurso, novaSenha);
                            break;
                        case 3: // Curso
                            imprimirListaCursos(cursos);
                            System.out.println("Selecione seu curso:");
                            novoCurso = validarCurso(cursos);

                            user.cadastrarUsuario(novoNome, novoCurso);
                            break;
                        default:
                            break;
                    }
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

        // Ao sair do projeto.
        System.out.println("\nObrigado e volte sempre!");
    }

    public static void imprimirDadosUsuario(Usuario usuario) {
        System.out.printf("\nNome: %s\n", usuario.getNome());
        System.out.printf("Matricula: %s\n", usuario.getMatricula());
        System.out.printf("Curso: %s (%s)\n", usuario.getCurso().getNome(), usuario.getCurso().getModalidade());
        System.out.println("Codigo: " + usuario.getCodigo());
    }

    /**
     * Imprimindo as opções de cursos de forma dinâmica.
     * Formatação padrão alinhado a esquerda.
     * 
     * 1º coluna: Posição do curso na lista. (2 digitos)
     * 2º coluna: Nome do curso. (24 digitos)
     * 3º coluna: Modalidade do curso. (28 digitos)
     * 
     * @param lista List<Curso> - Lista de cursos disponíveis. 
     */
    public static void imprimirListaCursos(List<Curso> lista) {
        System.out.println("\n+----+--------------------------+------------------------------+");
        System.out.println("| #  |         CURSO            |          MODALIDADE          |");
        System.out.println("+----+--------------------------+------------------------------+");
        for (int i = 0; i < lista.size(); i++) {
            Curso curso = lista.get(i);
            System.out.printf("| %-2d | %-24s | %-28s |\n", i+1, curso.getNome(), curso.getModalidade());
        }
        System.out.println("+----+--------------------------+------------------------------+");
    }

    /**
     * Função de validação de senhas.
     * Entra em loop enquanto a senha não tiver um tamanho mínimo aceito.
     * 
     * @param tamanhoMinimoSenha - Numero minimo de caracteres que a senha deve ter.
     * @return String - Senha do usuário.
     */
    public static String validarSenha(int tamanhoMinimoSenha) {
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
     * Função de validação da opção do curso.
     * Entra em loop enquanto a opção de curso não estiver dentro da lista de cursos.
     * 
     * @param listaCursos List<Curso> - Lista de cursos disponíveis. 
     * @return Curso - Curso do usuário.
     */
    public static Curso validarCurso(List<Curso> listaCursos) {
        int cursoOpcao;
        do {
            cursoOpcao = scanner.nextInt();
            // Para opções inválidas.
            if (cursoOpcao < 1 || cursoOpcao > listaCursos.size()) {
                System.out.println("\nOpção fora da lista!");
                System.out.println("Por favor, escolha uma que esteja dentro da lista:");
            }
        } while(cursoOpcao < 1 || cursoOpcao > listaCursos.size());

        return listaCursos.get(cursoOpcao - 1); // cursoOpcao - 1 se refere ao index na lista de cursos.
    }
}
