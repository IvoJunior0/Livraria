import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {
    public static Scanner scanner = new Scanner(System.in);
    public static boolean logado = false;

    public static void main(String[] args) throws Exception {
        /*
         * Como não é possível cadastrar cursos, livros e exemplares no sistema,
         * as listas de livros e exemplares cadastrados serão Lists, e não ArrayLists.
         * 
         * Como é possível cadastrar vários usuários, a lista de usuários cadastrados
         * é um ArrayList.
         */
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
                        new Usuario("Ivo Dos Santos Soares Junior", cursos.get(0), "12345678"))); // Os codigos dos
                                                                                                  // usuários serão
                                                                                                  // usados como
                                                                                                  // indexes.
        // Referência ao objeto das credenciais que o usuário escolher na hora do login.
        // A variável logado serve como um checkout rápido para saber se o ponteiro
        // aponta para algum objeto Usuário.
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
        // Usado para verficações e coleta de dados rápidas em O(1).
        // Key: código do usuário.
        // Value: objeto do empréstimo.
        Map<Integer, Emprestimo> emprestimos = new HashMap<>();

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
        while (opcaoMenu != 10) {
            System.out.println("\n1. Fazer cadastro");
            System.out.println("2. Login");
            System.out.println("3. Alterar dados do usuário");
            System.out.println("4. Fazer empréstimo");
            System.out.println("5. Dados detalhados do empréstimo");
            System.out.println("6. Fazer devolução");
            System.out.println("7. Logout");
            System.out.println("8. Perfil do usuário");
            System.out.println("9. Mostrar empréstimos ativos");
            System.out.println("10. Sair do sistema");

            System.out.println("Qual operação você deseja fazer?");
            opcaoMenu = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer.

            switch (opcaoMenu) {
                // Cadastro de usuário
                case 1:
                    // Variáveis usadas para passar como parametros no cadastro do usuário.
                    Usuario user = new Usuario();
                    // Inputs do usuário.
                    String usuarioNome, usuarioSenha;
                    char confimarDados = ' ';
                    Curso cursoEscolhido;

                    // Só sairá desse loop se os dados forem totalmente confirmados
                    // ou se usuário não quiser cadastrar outros dados após cancelar o cadastro.
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
                        System.out.printf("- Curso: %s (%s)\n", cursoEscolhido.getNome(),
                                cursoEscolhido.getModalidade());
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

                    // Somente se os dados forem confirmados. Se não, ele só volta pro menu.
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
                        System.out.println("\nVocê já está logado. Deseja sair de sua conta? (S/n)");
                        char sairOpcao = ' ';
                        sairOpcao = lerRespostaSimNao(sairOpcao);

                        // Se ele quiser continuar logado.
                        if (sairOpcao == 'n') {
                            break;
                        }
                        logado = false;

                        System.out.println("\nLogout feito com sucesso!");
                        System.out.println("Deseja fazer login em outra conta? (S/n)");
                        sairOpcao = lerRespostaSimNao(sairOpcao);

                        // Se quiser entrar em outra conta.
                        if (sairOpcao == 'n') {
                            break;
                        }

                        scanner.nextLine(); // Limpando buffer.
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
                                System.out.printf("Bem-vindo, %s.\n", usuarioLogado.getPrimeiroNome());
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

                    boolean dadosConfirmados = false;
                    int opcaoAlterar = 0;

                    // Dados passíveis de alterações diretamente pelo usuário.
                    // Matricula e código serão lidadas pelo sistema.
                    System.out.println("\n-> Dados alteraveis:");
                    System.out.println("1. Nome");
                    System.out.println("2. Senha");
                    System.out.println("3. Curso");
                    System.out.println("4. Voltar ao menu");
                    System.out.println("Deseja alterar que dado do usuário?");
                    opcaoAlterar = validarOpcao(4);

                    switch (opcaoAlterar) {
                        case 1: // Nome
                            dadosConfirmados = usuarioLogado.alterarNome();
                            break;
                        case 2: // Senha
                            dadosConfirmados = usuarioLogado.alternarSenha();
                            break;
                        case 3: // Curso
                            imprimirListaCursos(cursos);
                            dadosConfirmados = usuarioLogado.alterarCurso(cursos);
                            break;
                        case 4: // Voltar ao menu.
                            break;
                        default:
                            System.out.println("\n-> Opção inválida");
                            break;
                    }

                    // Evitar que ele imprima os dados.
                    if (!dadosConfirmados) {
                        break;
                    }

                    System.out.println("\nDados atualizados:");
                    imprimirDadosUsuario(usuarioLogado);
                    break;

                // Fazer empréstimo.
                case 4:
                    if (!logado) {
                        System.out.println("\n-> Você precisa estar logado para acessar essa funcionalidade.");
                        break;
                    }

                    // Só é permitido um empréstimo por usuário.
                    if (usuarioLogado.fezEmprestimo(emprestimos)) {
                        System.out.println("\nEncontramos um emprestimo em seu nome.");
                        System.out.println("Só é permitido um emprestimo de exemplar por usuário.");
                        System.out.println("Por favor, espere o prazo acabar ou faça a devolução do exemplar.\n");
                    }

                    char escolherLivroOpcao = ' ';

                    List<String[]> linhasTabelaLivros = new ArrayList<>();
                    linhasTabelaLivros.add(
                            new String[] { "Código", "Título (Autor)", "Edição (Ano)", "Nº de Páginas", "Estoque" }); // Primeira
                                                                                                                      // linha
                                                                                                                      // da
                                                                                                                      // tabela.

                    // Iteração para armazenar o valor da linha de cada item da lista livros.
                    for (Livro livro : livros) { // As outras linhas da tabela.
                        linhasTabelaLivros.add(new String[] {
                                String.valueOf(livro.getCodigo()),
                                livro.getTitulo() + " (" + livro.getAutor() + ")",
                                livro.getEdicao() + " (" + livro.getAno() + ")",
                                livro.getNumeroPaginas() + " Páginas",
                                "Disponíveis: " + livro.getEstoque()
                        });
                    }

                    imprimirTabela(linhasTabelaLivros);

                    List<String[]> linhasTabelaExemplares = new ArrayList<>();
                    linhasTabelaExemplares.add(new String[] { "Código", "Título", "Status" });

                    for (Exemplar exemplar : exemplares) {
                        linhasTabelaExemplares.add(new String[] {
                                String.valueOf(exemplar.getCodigo()),
                                exemplar.getLivro().getTitulo(),
                                exemplar.getStatus()
                        });
                    }

                    imprimirTabela(linhasTabelaExemplares);

                    Exemplar exemplarRequerido; // Referência ao exemplar escolhido, não uma cópia dele.
                    // Repetir até a pessoa escolher um livro disponível.
                    do {
                        escolherLivroOpcao = 'n'; // Por padrão a respota vai ser não para escolher outro livro em todo
                                                  // novo loop.

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
                    Emprestimo novoEmprestimo = new Emprestimo(usuarioLogado, exemplarRequerido);
                    emprestimos.put(usuarioLogado.getCodigo(), novoEmprestimo);
                    break;

                // Dados do empréstimo.
                case 5:
                    if (!logado) {
                        System.out.println("\n-> Você precisa estar logado para acessar essa funcionalidade.");
                        break;
                    }

                    if (!usuarioLogado.fezEmprestimo(emprestimos)) {
                        System.out.println("\nVocê não tem emprestimos cadastrados em seu nome.");
                        break;
                    }

                    Emprestimo emprestimoUsuario = emprestimos.get(usuarioLogado.getCodigo());

                    List<String[]> tabelaEmprestimo = new ArrayList<>();
                    tabelaEmprestimo
                            .add(new String[] { "Aluno", "Exemplar", "Data do empréstimo", "Data de devolução" });
                    tabelaEmprestimo.add(new String[] {
                            emprestimoUsuario.getAluno().getNome(),
                            emprestimoUsuario.getExemplar().getLivro().getTitulo(),
                            formatarData(emprestimoUsuario.getDataEmprestimo()),
                            formatarData(emprestimoUsuario.getDataDevolucao())
                    });

                    System.out.println("\nDados do emprestimo:");
                    imprimirTabela(tabelaEmprestimo);
                    break;

                // Fazer devolução.
                case 6:
                    if (!logado) {
                        System.out.println("\n-> Você precisa estar logado para acessar essa funcionalidade.");
                        break;
                    }

                    if (!usuarioLogado.fezEmprestimo(emprestimos)) {
                        System.out.println("\nVocê não tem emprestimos cadastrados em seu nome.");
                        break;
                    }
                    break;

                // Logout
                case 7:
                    if (!logado) {
                        System.out.println("\n-> Você precisa estar logado para acessar essa funcionalidade.");
                        break;
                    }
                    char opcaoLogout = ' ';

                    System.out.printf("\nVocê tem certeza que deseja sair de sua conta, %s? (S/n)\n",
                            usuarioLogado.getPrimeiroNome());
                    opcaoLogout = lerRespostaSimNao(opcaoLogout);

                    if (opcaoLogout == 'S') {
                        System.out.println("\n-> Logout feito com sucesso!");
                        usuarioLogado = null;
                        logado = false;
                    }

                    break;

                // Perfil do usuário
                case 8:
                    if (!logado) {
                        System.out.println("\n-> Você precisa estar logado para acessar essa funcionalidade.");
                        break;
                    }

                    System.out.printf("\n=== PERFIL DE %s ===\n", usuarioLogado.getPrimeiroNome().toUpperCase());
                    System.out.printf("Nome completo: %s\n", usuarioLogado.getNome());
                    System.out.printf("Matricula: %s\n", usuarioLogado.getMatricula());
                    System.out.printf("Curso: %s (%s)\n", usuarioLogado.getCurso().getNome(),
                            usuarioLogado.getCurso().getModalidade());

                    if (usuarioLogado.fezEmprestimo(emprestimos)) {
                        Livro livroEmprestimo = emprestimos.get(usuarioLogado.getCodigo()).getExemplar().getLivro();

                        System.out.printf("Exemplar emprestado: %s (Código: %d)\n", livroEmprestimo.getTitulo(),
                                livroEmprestimo.getCodigo());
                    }

                    break;

                // Mostrar empréstimos ativos
                case 9:
                    // Caso não tenha nenhum emprestimo para não imprimir só a primeira linha dos
                    // atributos do emprestimo.
                    if (emprestimos.size() == 0) {
                        System.out.println("\nNenhum emprestimo foi cadastrado por nenhum usuário.");
                        break;
                    }

                    List<String[]> linhasTabelaEmprestimos = new ArrayList<>();
                    linhasTabelaEmprestimos.add(
                            new String[] { "Código", "Aluno", "Exemplar", "Data do empréstimo", "Data de devolução" });

                    for (Emprestimo emprestimo : emprestimos.values()) {
                        linhasTabelaEmprestimos.add(new String[] {
                                String.valueOf(emprestimo.getCodigo()),
                                emprestimo.getAluno().getNome(),
                                emprestimo.getExemplar().getLivro().getTitulo(),
                                formatarData(emprestimo.getDataEmprestimo()),
                                formatarData(emprestimo.getDataDevolucao())
                        });
                    }

                    imprimirTabela(linhasTabelaEmprestimos);
                    break;

                // Sair do sistema.
                case 10:
                    System.out.println("\nObrigado e volte sempre!");
                    break;

                default:
                    System.out.println("\n-> Opção inválida");
                    break;
            }
        }
    }

    public static void imprimirTabela(List<String[]> colunas) {
        if (colunas.isEmpty()) {
            return;
        }

        int quantidadeColunas = colunas.get(0).length; // o index 0 é referente ao valor tamanho mínimo das colunas.
        int[] tamanhoColunas = new int[quantidadeColunas]; // Armazena o tamanho a ser impresso de cada coluna.

        // Calcular o tamanho máximo por coluna.
        for (String[] coluna : colunas) {
            for (int i = 0; i < quantidadeColunas; i++) {
                tamanhoColunas[i] = Math.max(tamanhoColunas[i], coluna[i].length());
            }
        }

        // Imprimir linha horizontal.
        StringBuilder horizontal = new StringBuilder("+");
        for (int tamanho : tamanhoColunas)
            horizontal.append("-".repeat(tamanho + 2)).append("+");
        String linhaHorizontal = horizontal.toString();

        // Linha cabeçalho
        System.out.println();
        System.out.println(linhaHorizontal);

        // Imprimir cada linha.
        for (int r = 0; r < colunas.size(); r++) {
            String[] linha = colunas.get(r);
            System.out.print("|");
            for (int i = 0; i < quantidadeColunas; i++) {
                System.out.printf(" %-" + tamanhoColunas[i] + "s |", linha[i]);
            }
            System.out.println();
            System.out.println(linhaHorizontal); // Linha que fica entre as linhas.
        }
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
                novoTexto.append(
                        palavra.substring(0, 1).toUpperCase()
                                + palavra.substring(1));
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
     * Método de validação de opção.
     * Entra em loop enquanto a opção escolhida for negativa ou maior que o número
     * de opções.
     * 
     * @param numeroOpcoes Número de opções disponíveis.
     * @return int - Opção válida.
     */
    public static int validarOpcao(int numeroOpcoes) {
        int opcao;
        do {
            opcao = scanner.nextInt();
            if (opcao < 1 || opcao > numeroOpcoes) {
                System.out.println("\n-> Opção inválida!");
                System.out.println("Por favor, digite escolha outra:");
            }
        } while (opcao < 1 || opcao > numeroOpcoes);
        return opcao;
    }

    /**
     * Método de validação de senhas.
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
                System.out.println("\n-> Opção inválida!");
                System.out.println("Por favor, escolha uma que esteja dentro da lista:");
            }
        } while (opcao < 1 || opcao > lista.size());

        return lista.get(opcao - 1);
    }

    /**
     * Lê e valida uma resposta de sim ou não.
     * 
     * @param opcao Opção digitada pelo usuário.
     * @return char - Respota final;
     */
    public static char lerRespostaSimNao(char opcao) {
        do {
            opcao = scanner.next().charAt(0);
            if (opcao != 'S' && opcao != 'n') {
                System.out.println("-> Opção inválida. Digita novamente.");
            }
        } while (opcao != 'S' && opcao != 'n');
        return opcao;
    }

    /**
     * Método de formatação de datas no padrão dia - mês - ano.
     * Trabalha com datas sem horários.
     * 
     * @param data Data a ser formatada.
     * @return String - Data formatada (padrão dd/mm/yyyy).
     */
    public static String formatarData(LocalDate data) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/uuuu");

        return data.format(formatter);
    }
}
