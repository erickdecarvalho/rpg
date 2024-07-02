package jogo;

import batalha.Batalha;
import personagens.*;
import personagens.extras.Efeito;
import personagens.extras.Equipamento;
import personagens.extras.Fraqueza;
import personagens.extras.Habilidade;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Jogo {
    private ArrayList<Personagem> personagens;
    private ArrayList<Habilidade> habilidades;
    private ArrayList<Fraqueza> fraquezas;
    private ArrayList<Equipamento> equipamentos;
    Scanner scanner = new Scanner(System.in);

    public Jogo() {
        this.personagens = new ArrayList<>();
        this.habilidades = new ArrayList<>();
        this.fraquezas = new ArrayList<>();
        this.equipamentos = new ArrayList<>();
    }

    public void iniciarBatalha(Integer idJogador, Integer idInimigo) {
        Personagem jogador = buscarPersonagemPorId(idJogador);
        Personagem inimigo = (Inimigo) buscarPersonagemPorId(idInimigo);
        Integer pontosVidaAntesBatalha = inimigo.getPontosVida();

        Batalha batalha = new Batalha();
        Scanner scanner = new Scanner(System.in);

        Integer rodada = 1;
        while (inimigo.getPontosVida() > 0 && jogador.getPontosVida() > 0) {
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-= RODADA " + rodada + " -=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");

            if (!jogador.getEfeitos().isEmpty()) {
                System.out.println("O jogador " + jogador.getNome() + " está sob os seguintes efeitos:");
                for (Efeito efeito : jogador.getEfeitos()) {
                    System.out.println("- " + efeito.getTipo() + " (Duração restante: " + efeito.getDuracao() + " turnos)");
                }
            }

            System.out.println("\nTurno do jogador " + jogador.getNome());

            System.out.println("Escolha a ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Usar magia");
            System.out.println("3 - Defender");
            System.out.println("4 - Fugir");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    batalha.realizarAtaqueFisico(jogador, inimigo);
                    break;
                case 2:
                    if (jogador.getHabilidades().isEmpty()) {
                        System.out.println("O jogador não possui habilidades para usar magia. Atacará fisicamente.");
                        batalha.realizarAtaqueFisico(jogador, inimigo);
                    } else {
                        System.out.println("Escolha a habilidade para usar:");
                        for (int i = 0; i < jogador.getHabilidades().size(); i++) {
                            System.out.println((i + 1) + " - " + jogador.getHabilidades().get(i).getNome());
                        }
                        int idHabilidade = scanner.nextInt();
                        scanner.nextLine();
                        Habilidade habilidade = buscarHabilidadePorId(idHabilidade);
                        if (habilidade != null) {
                            batalha.realizarAtaqueMagico(jogador, inimigo, habilidade);
                        } else {
                            System.out.println("Habilidade não encontrada!");
                        }
                    }
                    break;
                case 3:
                    jogador.defender();
                    break;
                case 4:
                    System.out.println("Você fugiu da batalha!");
                    return;
                default:
                    System.out.println("Ação inválida!");
                    break;
            }


            if (inimigo.getPontosVida() <= 0) {
                System.out.println("O inimigo " + inimigo.getNome() + " morreu!");
                if (inimigo instanceof Inimigo) {
                    Inimigo inimigoDerrotado = (Inimigo) inimigo;
                    jogador.adicionarExperiencia(inimigoDerrotado.getRecompensaXp());
                }
                break;
            }

            System.out.println("\nTurno do inimigo " + inimigo.getNome());

            int escolhaInimigo = (int) (Math.random() * 4) + 1;

            switch (escolhaInimigo) {
                case 1:
                    batalha.realizarAtaqueFisico(inimigo, jogador);
                    Iterator<Efeito> iterator = jogador.getEfeitos().iterator();
                    while (iterator.hasNext()) {
                        Efeito efeito = iterator.next();
                        if (efeito.getTipo().equals("Dormindo")) {
                            iterator.remove();
                            System.out.println("O jogador acordou!");
                        }
                    }
                    break;
                case 2:
                    if (!inimigo.getHabilidades().isEmpty()) {
                        int indiceHabilidade = (int) (Math.random() * inimigo.getHabilidades().size());
                        Habilidade habilidade = inimigo.getHabilidades().get(indiceHabilidade);
                        batalha.realizarAtaqueMagico(inimigo, jogador, habilidade);
                    } else {
                        batalha.realizarAtaqueFisico(inimigo, jogador);
                    }
                    break;
                case 3:
                    int escolhaEfeito = (int) (Math.random() * 3) + 1;

                    if (escolhaEfeito == 1) {
                        System.out.println("ATENÇÃO: O jogador foi envenenado!");
                        ((Inimigo) inimigo).envenenar(jogador, 5);
                    } else if (escolhaEfeito == 2) {
                        System.out.println("ATENÇÃO: O jogador foi atordoado!");
                        ((Inimigo) inimigo).atordoar(jogador);
                    } else if (escolhaEfeito == 3) {
                        System.out.println("ATENÇÃO: O jogador está dormindo!");
                        ((Inimigo) inimigo).provocarSono(jogador);
                    } else {
                        System.out.println("ATENÇÃO: O jogador foi queimado!");
                        ((Inimigo) inimigo).queimar(jogador, 10);
                    }
                    break;
                default:
                    System.out.println("Ação inválida do inimigo!");
                    break;
            }

            jogador.atualizaEfeitos();
            if (jogador.getPontosVida() <= 0) {
                System.out.println("O jogador " + jogador.getNome() + " morreu!");
                break;
            }

            rodada++;
        }
        inimigo.setPontosVida(pontosVidaAntesBatalha);

        System.out.println("Fim da batalha!");
    }

    public void adicionarArmadura(Scanner scanner) {
        System.out.println("\n=== Cadastrar Equipamento ===");

        System.out.println("1 - Armadura / 2 - Arma:");

        if (scanner.nextInt() == 1) {
            scanner.nextLine();
            System.out.println("Nome da Armadura: ");
            String nome = scanner.nextLine();

            System.out.println("Aumento de Defesa da Armadura: ");
            int aumentoDefesa = scanner.nextInt();
            scanner.nextLine();

            Equipamento armadura = new Equipamento(nome, Equipamento.TipoEquipamento.ARMADURA, aumentoDefesa);
            equipamentos.add(armadura);
            System.out.println("Armadura adicionada com sucesso!");
        }   else {
            scanner.nextLine();
            System.out.println("Nome da Arma: ");
            String nome = scanner.nextLine();

            System.out.println("Aumento do Ataque da Armadura: ");
            int aumentoDeAtaque = scanner.nextInt();
            scanner.nextLine();

            Equipamento arma = new Equipamento(nome, Equipamento.TipoEquipamento.ARMA, aumentoDeAtaque);
            equipamentos.add(arma);
            System.out.println("arma adicionada com sucesso!");
        }
    }

    public void adicionarEquipamentoAJogador(Integer idJogador, Integer idEquipamento) {
        Personagem jogador = buscarPersonagemPorId(idJogador);
        Equipamento equipamento = buscarEquipamentoPorId(idEquipamento);

        if (equipamento.getTipo() == Equipamento.TipoEquipamento.ARMA && jogador.temEquipamentoEquipadoDoTipo(Equipamento.TipoEquipamento.ARMA)) {
            jogador.desequiparEquipamento(jogador.getArmaEquipada());
        } else if (equipamento.getTipo() == Equipamento.TipoEquipamento.ARMADURA && jogador.temEquipamentoEquipadoDoTipo(Equipamento.TipoEquipamento.ARMADURA)) {
            jogador.desequiparEquipamento(jogador.getArmaduraEquipada());
        }

        jogador.equiparEquipamento(equipamento);
        System.out.println("Equipamento " + equipamento.getNome() + " adicionada com sucesso!");
    }

    public void listarEquipamentos() {
        System.out.println("Lista de Equipamentos:");
        for (Equipamento equipamento : equipamentos) {
            System.out.println(equipamento);
        }
    }

    public void criarPersonagem(Integer classe, Integer id, String nome, Integer pontosVida, Integer forca, Integer defesa, Integer nivel, Integer resistencia, Integer atributoExtra) {
        switch (classe) {
            case 1:
                Guerreiro guerreiro = new Guerreiro(id, nome, pontosVida, forca, defesa, nivel, resistencia, atributoExtra);
                personagens.add(guerreiro);
                break;
            case 2:
                Mago mago = new Mago(id, nome, pontosVida, forca, defesa, nivel, resistencia, atributoExtra);
                personagens.add(mago);
                break;
            case 3:
                Arqueiro arqueiro = new Arqueiro(id, nome, pontosVida, forca, defesa, nivel, resistencia, atributoExtra);
                personagens.add(arqueiro);
                break;
            default:
                System.out.println("Tipo de personagem inválido!");
                break;
        }
    }

    public void adicionarHabilidadeAJogador(Integer idJogador, Integer idHabilidade) {
        Personagem jogador = buscarPersonagemPorId(idJogador);
        Habilidade habilidade = buscarHabilidadePorId(idHabilidade);

        jogador.adicionarHabilidade(habilidade);
        System.out.println("Habilidade adicionada com sucesso!");
    }

    public void adicionarFraquezaAoJogador(Integer idJogador, Integer idFraqueza) {
        Personagem jogador = buscarPersonagemPorId(idJogador);
        Fraqueza fraqueza = buscarFraquezaPorId(idFraqueza);

        jogador.adicionarFraqueza(fraqueza);
        System.out.println("Fraqueza adicionada com sucesso!");
    }

    public void adicionarHabilidade(Scanner scanner) {
        System.out.println("\n=== Adicionar Habilidade ===");

        System.out.println("ID da Habilidade: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Tipo da Habilidade: ");
        String tipoHabilidade = scanner.nextLine();

        System.out.println("Descrição da Habilidade: ");
        String nomeHabilidade = scanner.nextLine();

        System.out.println("Dano Base da Habilidade: ");
        int danoBase = scanner.nextInt();

        Habilidade habilidade = new Habilidade(id, nomeHabilidade, tipoHabilidade, danoBase);
        habilidades.add(habilidade);
    }

    public void adicionarFraqueza(Scanner scanner) {
        System.out.println("\n=== Adicionar Fraqueza ===");

        System.out.println("ID da Fraqueza: ");
        Integer id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Tipo da Fraqueza: ");
        String tipoHabilidade = scanner.nextLine();

        System.out.println("Descrição da Fraqueza: ");
        String nomeHabilidade = scanner.nextLine();

        System.out.println("Dano Base da Fraqueza: ");
        int danoBase = scanner.nextInt();

        Fraqueza fraqueza = new Fraqueza(id, nomeHabilidade, tipoHabilidade, danoBase);
        fraquezas.add(fraqueza);
    }

    public void criarInimigo(Integer id, String nome, Integer pontosVida, Integer forca, Integer defesa, Integer nivel, Integer resistencia,  TipoInimigo tipoInimigo, Integer recompensaXp) {
        Inimigo inimigo = new Inimigo(id, nome, pontosVida, forca, defesa, nivel, resistencia, tipoInimigo, recompensaXp);
        personagens.add(inimigo);
    }


    private Personagem buscarPersonagemPorId(Integer id) {
        for (Personagem jogador : personagens) {
            if (jogador.getId().equals(id)) {
                return jogador;
            }
        }
        return null;
    }

    private Equipamento buscarEquipamentoPorId(Integer id) {
        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getId().equals(id)) {
                return equipamento;
            }
        }
        return null;
    }

    private Habilidade buscarHabilidadePorId(Integer id) {
        for (Habilidade habilidade : habilidades) {
            if (habilidade.getId().equals(id)) {
                return habilidade;
            }
        }
        return null;
    }

    private Fraqueza buscarFraquezaPorId(Integer id) {
        for (Fraqueza fraqueza : fraquezas) {
            if (fraqueza.getId().equals(id)) {
                return fraqueza;
            }
        }
        return null;
    }

    public void listarPersonagens() {
        System.out.println("Lista de Personagens:");
        for (Personagem personagem : personagens) {
            System.out.println(personagem);
        }
    }

    public void listarHabilidades() {
        System.out.println("Lista de Habilidades:");
        for (Habilidade habilidade : habilidades) {
            System.out.println(habilidade);
        }
    }

    public void listarFraquezas() {
        System.out.println("Lista de Fraquezas:");
        for (Fraqueza fraqueza : fraquezas) {
            System.out.println(fraqueza);
        }
    }

}
