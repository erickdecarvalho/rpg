package personagens;

import personagens.extras.Efeito;

public class Inimigo extends Personagem {
    private TipoInimigo tipoInimigo;
    private Integer recompensaXp;

    public Inimigo(Integer id, String nome, Integer pontosVida, Integer forca, Integer defesa, Integer nivel, Integer resistencia, TipoInimigo tipoInimigo, Integer recompensaXp) {
        super(id, nome, pontosVida, forca, defesa, nivel, resistencia);
        this.tipoInimigo = tipoInimigo;
        this.recompensaXp = recompensaXp;
    }

    public TipoInimigo getTipoInimigo() {
        return tipoInimigo;
    }

    public void setTipoInimigo(TipoInimigo tipoInimigo) {
        this.tipoInimigo = tipoInimigo;
    }

    public Integer getRecompensaXp() {
        return recompensaXp;
    }

    public void setRecompensaXp(Integer recompensaXp) {
        this.recompensaXp = recompensaXp;
    }

    public void envenenar(Personagem jogador, int danoPorTurno) {
        jogador.aplicaEfeito(new Efeito("Envenenado", 3, danoPorTurno));
    }

    public void atordoar(Personagem jogador) {
        jogador.aplicaEfeito(new Efeito("Atordoado", 1, 0));
    }

    public void queimar(Personagem jogador, int danoPorTurno) {
        jogador.aplicaEfeito(new Efeito("Queimado", 3, danoPorTurno));
    }

    public void provocarSono(Personagem jogador) {
        jogador.aplicaEfeito(new Efeito("Dormindo", 1000, 0));
    }

    @Override
    public String toString() {
        return "INIMIGO - ID: " + getId() +
                ", Nível: " + getNivel() +
                ", Nome: " + getNome() +
                ", Pontos de Vida: " + getPontosVida() +
                ", Força: " + getForca() +
                ", Defesa: " + getDefesa() +
                ", Recompensa XP: " + getRecompensaXp() +
                ", habilidades=" + getHabilidades() +
                ", fraquezas=" + getFraquezas();
    }
}
