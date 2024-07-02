package personagens.extras;

public class Equipamento {
    public enum TipoEquipamento {
        ARMA, ARMADURA
    }
    private static int proximoId = 1;

    private Integer id;
    private String nome;
    private TipoEquipamento tipo;
    private int aumentoAtributo;

    public Equipamento(String nome, TipoEquipamento tipo, int aumentoAtributo) {
        this.id = proximoId++;
        this.nome = nome;
        this.tipo = tipo;
        this.aumentoAtributo = aumentoAtributo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public TipoEquipamento getTipo() {
        return tipo;
    }

    public int getAumentoAtributo() {
        return aumentoAtributo;
    }

    @Override
    public String toString() {
        return "Equipamento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", tipo=" + tipo +
                ", aumentoAtributo=" + aumentoAtributo +
                '}';
    }
}
