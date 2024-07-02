package personagens;

public class Guerreiro extends Personagem {
    private Integer danoCritico;

    public Guerreiro(Integer id, String nome, Integer pontosVida, Integer forca, Integer defesa, Integer nivel, Integer resistencia, Integer danoCritico) {
        super(id, nome, pontosVida, forca, defesa, nivel, resistencia);
        this.danoCritico = danoCritico;
    }

    public Integer getDanoCritico() {
        return danoCritico;
    }

    public void setDanoCritico(Integer danoCritico) {
        this.danoCritico = danoCritico;
    }

    @Override
    public String toString() {
        return "Guerreiro - ID: " + getId() +
                ", Nível: " + getNivel() +
                ", Nome: " + getNome() +
                ", Pontos de Vida: " + getPontosVida() +
                ", Força: " + getForca() +
                ", Defesa: " + getDefesa() +
                ", Dano Crítico: " + getDanoCritico() +
                ", XP: " + getExperiencia() +
                ", habilidades=" + getHabilidades() +
                ", armaEquipada=" + (getArmaEquipada() != null ? getArmaEquipada().getNome() : "nenhuma") +
                ", armaduraEquipada=" + (getArmaduraEquipada() != null ? getArmaduraEquipada().getNome() : "nenhuma") +
                ", fraquezas=" + getFraquezas();
    }
}
