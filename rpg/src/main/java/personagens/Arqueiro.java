package personagens;

public class Arqueiro extends Personagem {
    private Integer pontaria;

    public Arqueiro(Integer id, String nome, Integer pontosVida, Integer forca, Integer defesa, Integer nivel, Integer resistencia, Integer pontaria) {
        super(id, nome, pontosVida, forca, defesa, nivel, resistencia);
        this.pontaria = pontaria;
    }

    public Integer getPontaria() {
        return pontaria;
    }

    public void setPontaria(Integer pontaria) {
        this.pontaria = pontaria;
    }

    @Override
    public String toString() {
        return "Mago - ID: " + getId() +
                ", Nível: " + getNivel() +
                ", Nome: " + getNome() +
                ", Pontos de Vida: " + getPontosVida() +
                ", Força: " + getForca() +
                ", Defesa: " + getDefesa() +
                ", Pontaria: " + getPontaria() +
                ", XP: " + getExperiencia() +
                ", habilidades=" + getHabilidades() +
                ", fraquezas=" + getFraquezas();
    }
}
