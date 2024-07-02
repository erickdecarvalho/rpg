package personagens.extras;

public class Efeito {
    private String tipo;
    private int duracao;
    private int danoPorTurno;

    public Efeito(String tipo, int duracao, int danoPorTurno) {
        this.tipo = tipo;
        this.duracao = duracao;
        this.danoPorTurno = danoPorTurno;
    }

    public String getTipo() {
        return tipo;
    }

    public int getDuracao() {
        return duracao;
    }

    public int getDanoPorTurno() {
        return danoPorTurno;
    }

    public void passaTurno() {
        duracao--;
    }
}