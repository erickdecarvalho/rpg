package personagens.extras;

public class Fraqueza extends Habilidade {
    public Fraqueza(Integer id, String nome, String tipo, int danoBase) {
        super(id, nome, tipo, danoBase);
    }

    @Override
    public String toString() {
        return "Fraqueza{" +
                "id=" + getId() + '\'' +
                "nome='" + getNome() + '\'' +
                ", tipo='" + getTipo() + '\'' +
                ", danoBase=" + getDanoBase() +
                '}';
    }
}
