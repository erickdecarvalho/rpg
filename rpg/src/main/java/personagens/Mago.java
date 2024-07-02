package personagens;

public class Mago extends Personagem {
    private Integer inteligencia;

    public Mago(Integer id, String nome, Integer pontosVida, Integer forca, Integer defesa, Integer nivel, Integer resistencia, Integer inteligencia) {
        super(id, nome, pontosVida, forca, defesa, nivel, resistencia);
        this.inteligencia = inteligencia;
    }

    public Integer getInteligencia() {
        return inteligencia;
    }

    public void setInteligencia(Integer inteligencia) {
        this.inteligencia = inteligencia;
    }

    @Override
    public String toString() {
        return "Mago - ID: " + getId() +
                ", Nível: " + getNivel() +
                ", Nome: " + getNome() +
                ", Pontos de Vida: " + getPontosVida() +
                ", Força: " + getForca() +
                ", Defesa: " + getDefesa() +
                ", Inteligência: " + getInteligencia() +
                ", XP: " + getExperiencia() +
                ", habilidades=" + getHabilidades() +
                ", fraquezas=" + getFraquezas();
    }
}
