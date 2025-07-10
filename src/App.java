import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean logado = false;

    public static void main(String[] args) throws Exception {
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
                new Curso("Biologia", "Licenciatura"));

        ArrayList<Usuario> usuariosCadastrados = new ArrayList<>(
                Arrays.asList(
                        // Usuário estático para testes e afins.
                        new Usuario("Ivo dos Santos Soares Junior", cursos.get(0), "12345678"))); // Os codigos dos
                                                                                                  // usuários serão
                                                                                                  // usados como
                                                                                                  // indexes.
        Usuario usuarioLogado = new Usuario();

        List<Livro> livros = Arrays.asList(
                new Livro("Capitães da areia", "Jorge Amado", "Edição de bolso", 2009, 280),
                new Livro("A hora da estrela", "Clarice Lispector", "Edição comemorativa", 2020, 88));

        List<Exemplar> exemplares = Arrays.asList(
            new Exemplar(livros.get(0)), // Exemplar de "Capitaes da areia".
            new Exemplar(livros.get(0)), 
            new Exemplar(livros.get(1)) // Exemplar de "A hora da estrela".
        );

        // HashMap da lista de emprestimos relacionados aos usuários.
        // Usado para verficações e coleta de dados rápidas (em O(1)).
        Map<Usuario, Emprestimo> emprestimos = new HashMap<>();

        int opcaoMenu = 0;

        // Tela de Bem-vindos.
        System.out.println("=========================================================================================");
        System.out.println(" _______   _                ____                    _      ______                       ");
        System.out.println("|__   __| | |              |  _ \\                  | |    |  ____|                      ");
        System.out.println("   | |    | |__     ___    | |_) |   ___     ___   | | __ | |__      __ _    ___    ___ ");
        System.out.println(
                "   | |    | '_ \\   / _ \\   |  _ <   / _ \\   / _ \\  | |/ / |  __|    / _` |  / __|  / _ \\");
        System.out.println("   | |    | | | | |  __/   | |_) | | (_) | | (_) | |   <  | |      | (_| | | (__  |  __/");
        System.out.println(
                "   |_|    |_| |_|  \\___|   |____/   \\___/   \\___/  |_|\\_\\ |_|       \\__,_|  \\___|  \\___|");
        System.out.println("=========================================================================================");
        System.out.println("Por Ivo Jr.");

        // Programa principal.
        while (opcaoMenu != 7) {
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
                    // Variáveis usadas para passar como parametros no cadastro do usuário.
                    Usuario user = new Usuario();
                    String usuarioNome, usuarioSenha;
                    char confimarDados = ' ';
                    Curso cursoEscolhido;

                    while (true) {
                        System.out.println("\nNome do usuário:");
                        usuarioNome = scanner.nextLine();
                        usuarioNome = capitalizarTexto(usuarioNome);

                        System.out.println("\nSenha do usuário:");
                        usuarioSenha = validarSenha(8);

                        imprimirListaCursos(cursos);
                        System.out.println("Selecione seu curso:");
                        cursoEscolhido = validarOpcaoLista(cursos);

                        System.out.println("\nUsuário a ser cadastrado:");
                        System.out.printf("- Nome: %s\n", usuarioNome);
                        System.out.printf("- Senha: %s\n", usuarioSenha);
                        System.out.printf("- Curso: %s (%s)\n", cursoEscolhido.getNome(), cursoEscolhido.getModalidade());
                        System.out.println("\nConfirmar dados: (S/n)");
                        confimarDados = lerRespostaSimNao(confimarDados);

                        // Se os dados estiverem corretos.
                        if (confimarDados == 'S') {
                            break;
                        }

                        System.out.println("Deseja cadastrar outros dados? (S/n)");
                        confimarDados = lerRespostaSimNao(confimarDados);

                        // Se não quiser cadastrar outros dados.
                        if (confimarDados == 'n') {
                            break;
                        }
                    }

                    if (confimarDados == 'S') {                        
                        user.cadastrarUsuario(usuarioNome, cursoEscolhido, usuarioSenha);
    
                        System.out.printf("Usuário %s cadastrado com sucesso!\n", user.getNome());
                        imprimirDadosUsuario(user);
                        usuariosCadastrados.add(user);
                    }
                    break;

                // Login
                case 2:
                    if (logado) {
                        System.out.println("Você já está logado. Deseja sair de sua conta? (S/n)");
                        char sairOpcao = ' ';
                        sairOpcao = lerRespostaSimNao(sairOpcao);

                        // Até chegar ele terá digitado ou 'S' ou 'n', obrigatoriamente.
                        if (sairOpcao == 'n') {
                            break;
                        }
                        logado = false;
                    }

                    // Usuário continuará deslogado enquanto as credenciais estiverem erradas
                    while (!logado) {
                        String matriculaLogin, senhaLogin;

                        System.out.println("\nMatricula:");
                        matriculaLogin = scanner.nextLine();
                        System.out.println("\nSenha:");
                        senhaLogin = scanner.nextLine();

                        // Busca por credienciais na lista de usuários do sistema.
                        for (Usuario usuario : usuariosCadastrados) {
                            if (usuario.getMatricula().equals(matriculaLogin) && usuario.verificaSenha(senhaLogin)) {
                                usuarioLogado = usuario;
                                logado = true;

                                System.out.println("\nLogin feito com sucesso!");
                                // Sepração em array do nome usando espaços como parâmetros.
                                // [0] se refere ao primeiro nome do usuário.
                                String primeiroNome = usuarioLogado.getNome().split("[\\s]")[0];
                                System.out.printf("Bem-vindo, %s.\n", primeiroNome);
                                break;
                            }
                        }

                        // Ele só entra nessa condição se não tiver encontrado nenhum usuário antes.
                        if (!logado) {
                            System.out.println("\nUsuário não encontrado:");
                            System.out.println("Senha ou matriculas incorretas.");
                        }
                    }
                    break;

                // Alterar dados do usuário.
                case 3:
                    if (!logado) {
                        System.out.println("\nVocê precisa estar logado para acessar essa funcionalidade.");
                        break;
                    }
                    String novoNome = usuarioLogado.getNome(), novaSenha = "";
                    Curso novoCurso = usuarioLogado.getCurso();

                    // Dados passíveis de alterações diretamente pelo usuário.
                    // Matricula e código serão lidadas pelo sistema.
                    System.out.println("\n-> Dados alteraveis:");
                    System.out.println("1. Nome");
                    System.out.println("2. Senha");
                    System.out.println("3. Curso");
                    System.out.println("Deseja alterar que dado do usuário?");
                    // TODO: sistema de opção inválida.
                    int opcaoAlterar = scanner.nextInt();

                    switch (opcaoAlterar) {
                        case 1: // Nome
                            System.out.println("\nEscolha um novo nome:");
                            novoNome = scanner.nextLine();

                            usuarioLogado.cadastrarUsuario(novoNome, novoCurso);
                            break;
                        case 2: // Senha
                            System.out.println("\nNova senha:");
                            novaSenha = validarSenha(8);

                            usuarioLogado.cadastrarUsuario(novoNome, novoCurso, novaSenha);
                            break;
                        case 3: // Curso
                            imprimirListaCursos(cursos);
                            System.out.println("Selecione seu curso:");
                            novoCurso = validarOpcaoLista(cursos);

                            usuarioLogado.cadastrarUsuario(novoNome, novoCurso);
                            break;
                        default:
                            break;
                    }

                    System.out.println("\nDados atualizados:");
                    imprimirDadosUsuario(usuarioLogado);
                    break;

                // Fazer emprestimo.
                case 4:
                    // if (!logado) {
                    // System.out.println("\nVocê precisa estar logado para acessar essa
                    // funcionalidade.");
                    // break;
                    // }
                    int opcaoLivro;
                    char escolherLivroOpcao = ' ';

                    // Tabela de livros cadastrados no sistema.
                    // Código - Tìtulo (Autor) - Edição - Número de páginas - Estoque
                    // Calculo do tamanho mínimo de cada coluna da tabela.
                    int codColunaTamanho = "Código".length();
                    int tituloAutorColunaTamanho = "Título (Autor)".length();
                    int edicaoAnoColunaTamanho = "Edição (Ano)".length();
                    int paginasColunaTamanho = "Nº de Páginas".length();
                    int estoqueColunaTamanho = "Estoque".length();

                    /*
                     * Checando se o tamanho do valor dos atributos são maiores que o tamanho mínimo de cada coluna.
                     * Se for, o novo valores é atribuido ao tamanho da coluna.
                     * O método max é usada para saber qual valor é maior dentre o tamanho mínimo e o valor do atributo.
                     */
                    for (Livro livro : livros) {
                        codColunaTamanho = Math.max(codColunaTamanho, String.valueOf(livro.getCodigo()).length());
                        tituloAutorColunaTamanho = Math.max(tituloAutorColunaTamanho,
                                (livro.getTitulo() + " (" + livro.getAutor() + ")").length());
                        edicaoAnoColunaTamanho = Math.max(edicaoAnoColunaTamanho,
                                (livro.getEdicao() + " (" + livro.getAno() + ")").length());
                        paginasColunaTamanho = Math.max(paginasColunaTamanho,
                                (livro.getNumeroPaginas() + " Páginas").length());
                        estoqueColunaTamanho = Math.max(estoqueColunaTamanho,
                                ("Disponíveis: " + livro.getEstoque()).length());
                    }

                    // A linha horizontal é algo semelhante a isso:
                    // +----+-----+------+------+
                    String linhaHorizontal = "+" +
                            "-".repeat(codColunaTamanho + 2) + "+" +
                            "-".repeat(tituloAutorColunaTamanho + 2) + "+" +
                            "-".repeat(edicaoAnoColunaTamanho + 2) + "+" +
                            "-".repeat(paginasColunaTamanho + 2) + "+" +
                            "-".repeat(estoqueColunaTamanho + 2) + "+"; // + 2 são as margens horizontais das colunas.

                    // Imprimir o cabeçalho da tabela de livros.
                    System.out.println("\nLivros disponíveis:");
                    System.out.println('\n' + linhaHorizontal); // +----+-----+------+------+
                    System.out.printf(
                            "| %-" + codColunaTamanho + "s | %-" + tituloAutorColunaTamanho + "s | %-"
                                    + edicaoAnoColunaTamanho + "s | %-" + paginasColunaTamanho + "s | %-"
                                    + estoqueColunaTamanho + "s |\n",
                            "Código", "Título (Autor)", "Edição (Ano)", "Nº de Páginas", "Estoque");
                    System.out.println(linhaHorizontal); // +----+-----+------+------+

                    // Imprimir as linhas dos livros.
                    for (Livro livro : livros) {
                        System.out.printf(
                                "| %-" + codColunaTamanho + "s | %-" + tituloAutorColunaTamanho + "s | %-"
                                        + edicaoAnoColunaTamanho + "s | %-" + paginasColunaTamanho + "s | %-"
                                        + estoqueColunaTamanho + "s |\n",
                                livro.getCodigo(),
                                livro.getTitulo() + " (" + livro.getAutor() + ")",
                                livro.getEdicao() + " (" + livro.getAno() + ")",
                                livro.getNumeroPaginas() + " Páginas",
                                "Disponíveis: " + livro.getEstoque());
                    }
                    System.out.println(linhaHorizontal); // Linha final. +----+-----+------+------+

                    // Lista de exemplares.
                    // Código - Tìtulo do exemplar - Status
                    int colunaTituloTamanho = "Título".length();
                    int colunaStatusTamanho = "Status".length();

                    for (Exemplar exemplar : exemplares) {
                        colunaTituloTamanho = Math.max(colunaTituloTamanho, String.valueOf(exemplar.getLivro().getTitulo()).length());
                        colunaStatusTamanho = Math.max(colunaStatusTamanho, exemplar.getStatus().length());
                    }

                    // Atualizando o layout da linha horizontal para lista de exemplares.
                    linhaHorizontal = "+" + 
                                "-".repeat(codColunaTamanho + 2) + "+" +
                                "-".repeat(colunaTituloTamanho + 2) + "+" +
                                "-".repeat(colunaStatusTamanho + 2) + "+";
                    
                    // Imprimir cabeçalho da lista de exemplares.
                    System.out.println("\nExemplares:");
                    System.out.println('\n' + linhaHorizontal); // +----+-----+------+------+
                    System.out.printf(
                            "| %-" + codColunaTamanho + "s | %-" + colunaTituloTamanho + "s | %-"
                                    + colunaStatusTamanho + "s |\n",
                            "Código", "Título", "Status");
                    System.out.println(linhaHorizontal); // +----+-----+------+------+

                    // Imprimir as linhas dos exemplares.
                    for (Exemplar exemplar : exemplares) {
                        System.out.printf(
                                "| %-" + codColunaTamanho + "s | %-" + colunaTituloTamanho + "s | %-"
                                        + colunaStatusTamanho + "s |\n",
                                exemplar.getCodigo(),
                                exemplar.getLivro().getTitulo(),
                                exemplar.getStatus());
                    }
                    System.out.println(linhaHorizontal); // Linha final. +----+-----+------+------+
                    Exemplar exemplarRequerido = null; // Referência ao exemplar escolhido, não uma cópia dele.

                    // Repetir até a pessoa escolher um livro disponível.
                    do {
                        System.out.println("Qual exemplar você deseja? (Digite o código)");
                        exemplarRequerido = validarOpcaoLista(exemplares);
                        
                        // Checar se tá disponível.
                        if (exemplarRequerido.getStatus() != "Livre") {
                            System.out.println("\nLivro indisponível. Deseja escolher outro? (S/n)");
                            escolherLivroOpcao = lerRespostaSimNao(escolherLivroOpcao);
                        }
                        
                        if (escolherLivroOpcao == 'n') {
                            break;
                        }
                    } while (escolherLivroOpcao == 'S');

                    exemplarRequerido.emprestar();
                    Emprestimo emprestimo = new Emprestimo(usuarioLogado, exemplarRequerido);
                    break;

                // Dados do emprestimo.
                case 5:
                    if (!logado) {
                        System.out.println("\nVocê precisa estar logado para acessar essa funcionalidade.");
                        break;
                    }
                    break;

                // Fazer devolução.
                case 6:
                    if (!logado) {
                        System.out.println("\nVocê precisa estar logado para acessar essa funcionalidade.");
                        break;
                    }
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        }

        // Ao sair do projeto.
        System.out.println("Obrigado e volte sempre!");
    }

    /**
     * Imprimir uma lista dos atributos (visíveis do usuário).
     * Senha e código não são mostrados por (obviamente) questões de seguranção.
     * 
     * @param usuario Usuários para ter os dados impressos.
     */
    public static void imprimirDadosUsuario(Usuario usuario) {
        System.out.printf("\nNome: %s\n", usuario.getNome());
        System.out.printf("Matricula: %s\n", usuario.getMatricula());
        System.out.printf("Curso: %s (%s)\n", usuario.getCurso().getNome(), usuario.getCurso().getModalidade());
    }

    /**
     * Método de capitalização de textos.
     * 
     * O texto é dividido em palavras com base em espaços usando regex.
     * Em seguida, cada palavra tem seu primeiro caractere convertido para maiúsculo e é
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
                novoTexto.append(
                    palavra.substring(0, 1).toUpperCase()
                    + palavra.substring(1)
                );
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
     * Imprimindo as opções de cursos de forma dinâmica.
     * Formatação padrão alinhado a esquerda.
     * 
     * 1º coluna: Posição do curso na lista. (2 digitos)
     * 2º coluna: Nome do curso. (24 digitos)
     * 3º coluna: Modalidade do curso. (28 digitos)
     * 
     * @param lista Lista de cursos disponíveis.
     */
    public static void imprimirListaCursos(List<Curso> lista) {
        System.out.println("\n+----+--------------------------+------------------------------+");
        System.out.println("| #  |         CURSO            |          MODALIDADE          |");
        System.out.println("+----+--------------------------+------------------------------+");
        for (int i = 0; i < lista.size(); i++) {
            Curso curso = lista.get(i);
            System.out.printf("| %-2d | %-24s | %-28s |\n", i + 1, curso.getNome(), curso.getModalidade());
        }
        System.out.println("+----+--------------------------+------------------------------+");
    }

    /**
     * Função de validação de senhas.
     * Entra em loop enquanto a senha não tiver um tamanho mínimo aceito.
     * 
     * @param tamanhoMinimoSenha Numero minimo de caracteres que a senha deve ter.
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
     * Método de validação de opções baseadas em List e ArrayLists genéricas.
     * Entra em loop enquanto a opção escolhida não estiver dentro da lista.
     *
     * @param <T>   Curso, Exemplar e Usuário
     * @param lista Lista de opções disponíveis.
     * @return T - item selecionado da lista.
     */
    public static <T> T validarOpcaoLista(List<T> lista) {
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
     * Lê e valida uma resposta de sim ou não.
     * 
     * @return char - Respota final;
     */
    public static char lerRespostaSimNao(char opcao) {
        do {
            opcao = scanner.next().charAt(0);
            if (opcao != 'S' && opcao != 'n') {
                System.out.println("Opção inválida. Digita novamente.");
            }
        } while (opcao != 'S' && opcao != 'n');
        return opcao;
    }
}
