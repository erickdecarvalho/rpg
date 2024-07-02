package batalha;

import personagens.extras.Habilidade;
import personagens.Personagem;

public class Batalha {

    public Batalha() {
    }


    public void realizarAtaqueFisico(Personagem atacante, Personagem alvo) {
        System.out.println("    - Vida do alvo " + alvo.getNome() + " antes do ataque físico: " + alvo.getPontosVida());

        int dano = atacante.calcularDanoFisico(alvo);
        alvo.setPontosVida(alvo.getPontosVida() - dano);

        System.out.println(atacante.getNome() + " atacou " + alvo.getNome() + " causando " + dano + " pontos de dano.");
        System.out.println("    - Vida do alvo " + alvo.getNome() + " depois do ataque físico: " + alvo.getPontosVida());
    }

    public void realizarAtaqueMagico(Personagem atacante, Personagem alvo, Habilidade habilidade) {
        System.out.println("Vida do alvo " + alvo.getNome() + " antes do ataque mágico: " + alvo.getPontosVida());

        int dano = atacante.calcularDanoMagico(habilidade, alvo);
        alvo.setPontosVida(alvo.getPontosVida() - dano);

        System.out.println(atacante.getNome() + " usou " + habilidade.getNome() + " em " + alvo.getNome() + " causando " + dano + " pontos de dano.");
        System.out.println("Vida do alvo " + alvo.getNome() + " depois do ataque mágico: " + alvo.getPontosVida());
    }

}
