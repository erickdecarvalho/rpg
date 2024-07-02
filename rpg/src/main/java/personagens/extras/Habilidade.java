package personagens.extras;

public class Habilidade {
    private Integer id;
    private String nome;
    private String tipo;
    private int danoBase;

    public Habilidade(Integer id, String nome, String tipo, int danoBase) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.danoBase = danoBase;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getDanoBase() {
        return danoBase;
    }

    public void setDanoBase(int danoBase) {
        this.danoBase = danoBase;
    }

    @Override
    public String toString() {
        return "Habilidade{" +
                "id=" + id + '\'' +
                "nome='" + nome + '\'' +
                ", tipo='" + tipo + '\'' +
                ", danoBase=" + danoBase +
                '}';
    }
}
