import jogo.Jogo;
import personagens.TipoInimigo;

import java.util.Scanner;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {
        Jogo jogo = new Jogo();
        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 11:
                    criarPersonagem(jogo, scanner);
                    break;
                case 21:
                    criarInimigo(jogo, scanner);
                    break;
                case 31:
                    cadastrarBatalha(jogo, scanner);
                    break;
                case 12:
                    jogo.listarPersonagens();
                    break;
                case 41:
                    jogo.adicionarHabilidade(scanner);
                    break;
                case 51:
                    jogo.adicionarArmadura(scanner);
                    break;
                case 52:
                    jogo.listarEquipamentos();
                    break;
                case 14:
                    adicionarHabilidadeAJogador(jogo, scanner);
                    break;
                case 61:
                    jogo.adicionarFraqueza(scanner);
                    break;
                case 62:
                    jogo.listarFraquezas();
                    break;
                case 16:
                    adicionarFraquezaAJogador(jogo, scanner);
                    break;
                case 13:
                    adicionarEquipamentoAJogador(jogo, scanner);
                    break;
                case 42:
                    jogo.listarHabilidades();
                    break;
                case 0:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu ===");
        System.out.println("1. Gerenciar Personagens:");
        System.out.println("   11 - Criar Personagem");
        System.out.println("   12 - Listar Personagens");
        System.out.println("   13 - Adicionar Equipamento a um Personagem");
        System.out.println("   14 - Adicionar Habilidade a um Personagem");
        System.out.println("   15 - Adicionar Fraqueza a um Personagem");

        System.out.println("2. Gerenciar Inimigos:");
        System.out.println("   21 - Criar Inimigo");

        System.out.println("3. Gerenciar Batalhas:");
        System.out.println("   31 - Cadastrar Batalha");

        System.out.println("4. Gerenciar Habilidades:");
        System.out.println("   41 - Cadastrar Habilidade");
        System.out.println("   42 - Listar Habilidades Disponíveis");

        System.out.println("5. Gerenciar Equipamentos:");
        System.out.println("   51 - Cadastrar Equipamento");
        System.out.println("   52 - Listar Armaduras");

        System.out.println("6. Gerenciar Fraquezas:");
        System.out.println("   61 - Cadastrar Fraqueza");
        System.out.println("   62 - Listar Fraqueza");

        System.out.println("0 - Sair");
    }


    private static void criarPersonagem(Jogo jogo, Scanner scanner) {
        System.out.println("\n=== Criar Personagem ===");
        System.out.println("=== SELECIONE A CLASSE ===\n1 - Guerreiro\n2 - Mago\n3 - Arqueiro");
        int classe = scanner.nextInt();

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Pontos de Vida: ");
        int pontosVida = scanner.nextInt();

        System.out.println("Força: ");
        int forca = scanner.nextInt();

        System.out.println("Defesa: ");
        int defesa = scanner.nextInt();

        System.out.println("Nível: ");
        int nivel = scanner.nextInt();

        System.out.println("Resistência: ");
        int resistencia = scanner.nextInt();

        switch (classe) {
            case 1:
                System.out.println("Dano Crítico: ");
                int danoCritico = scanner.nextInt();
                jogo.criarPersonagem(1, id, nome, pontosVida, forca, defesa, nivel, resistencia, danoCritico);
                break;
            case 2:
                System.out.println("Pontos Magia: ");
                int pontosMagia = scanner.nextInt();
                jogo.criarPersonagem(2, id, nome, pontosVida, forca, defesa, nivel, resistencia, pontosMagia);
                break;
            case 3:
                System.out.println("Destreza: ");
                int destreza = scanner.nextInt();
                jogo.criarPersonagem(3, id, nome, pontosVida, forca, defesa, nivel, resistencia, destreza);
                break;
        }


        System.out.println("Personagem criado com sucesso!");
    }

    private static void adicionarHabilidadeAJogador(Jogo jogo, Scanner scanner) {
        System.out.println("\n=== Selecione o ID do personagem ===");
        jogo.listarPersonagens();
        System.out.println("ID: ");
        int id = scanner.nextInt();

        System.out.println("\n=== Selecione o ID da habilidade ===");
        jogo.listarHabilidades();
        System.out.println("ID: ");
        int idHabilidade = scanner.nextInt();

        jogo.adicionarHabilidadeAJogador(id, idHabilidade);

    }

    private static void adicionarEquipamentoAJogador(Jogo jogo, Scanner scanner) {
        System.out.println("\n=== Selecione o ID do personagem ===");
        jogo.listarPersonagens();
        System.out.println("ID: ");
        int id = scanner.nextInt();

        System.out.println("\n=== Selecione o ID do Equipamento ===");
        jogo.listarEquipamentos();
        System.out.println("ID: ");
        int idEquipamento = scanner.nextInt();

        jogo.adicionarEquipamentoAJogador(id, idEquipamento);

    }

    private static void adicionarFraquezaAJogador(Jogo jogo, Scanner scanner) {
        System.out.println("\n=== Selecione o ID do personagem ===");
        jogo.listarPersonagens();
        System.out.println("ID: ");
        int id = scanner.nextInt();

        System.out.println("\n=== Selecione o ID da Fraqueza ===");
        jogo.listarFraquezas();
        System.out.println("ID: ");
        int idFraqueza = scanner.nextInt();

        jogo.adicionarFraquezaAoJogador(id, idFraqueza);

    }

    private static void criarInimigo(Jogo jogo, Scanner scanner) {
        System.out.println("\n=== Criar Inimigo ===");
        System.out.println("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("Pontos de Vida: ");
        int pontosVida = scanner.nextInt();

        System.out.println("Força: ");
        int forca = scanner.nextInt();

        System.out.println("Defesa: ");
        int defesa = scanner.nextInt();

        System.out.println("Tipo de Inimigo (1 - Chefe, 2 - Monstro): ");
        int tipoInimigoValor = scanner.nextInt();
        scanner.nextLine();

        TipoInimigo tipoInimigo;
        switch (tipoInimigoValor) {
            case 1:
                tipoInimigo = TipoInimigo.CHEFE;
                break;
            case 2:
                tipoInimigo = TipoInimigo.MONSTRO;
                break;
            default:
                tipoInimigo = TipoInimigo.MONSTRO;
                break;
        }


        System.out.println("Nível: ");
        int nivel = scanner.nextInt();

        System.out.println("Resistência: ");
        int resistencia = scanner.nextInt();

        System.out.println("Recompensa de XP: ");
        int recompensaXp = scanner.nextInt();

        jogo.criarInimigo(id, nome, pontosVida, forca, defesa, nivel, resistencia, tipoInimigo, recompensaXp);
        System.out.println("Inimigo criado com sucesso!");
    }

    private static void cadastrarBatalha(Jogo jogo, Scanner scanner) {
        System.out.println("\n=== Cadastrar Batalha ===");
        System.out.println("ID do Jogador: ");
        int idJogador = scanner.nextInt();

        System.out.println("ID do Inimigo: ");
        int idInimigo = scanner.nextInt();

        jogo.iniciarBatalha(idJogador, idInimigo);
        System.out.println("Batalha iniciada!");
    }
}
