package personagens;

import personagens.extras.Efeito;
import personagens.extras.Equipamento;
import personagens.extras.Fraqueza;
import personagens.extras.Habilidade;

import java.util.ArrayList;
import java.util.Iterator;

public class Personagem {
    private Integer id;
    private String nome;
    private Integer pontosVida;
    private Integer forca;
    private Integer defesa;
    private Integer nivel;
    private Integer resistencia;
    private ArrayList<Habilidade> habilidades;
    private ArrayList<Fraqueza> fraquezas;
    private Integer experiencia;
    private Equipamento armaEquipada;
    private Equipamento armaduraEquipada;
    private ArrayList<Efeito> efeitos;

    public Personagem() {
        this.habilidades = new ArrayList<>();
    }

    public Personagem(Integer id, String nome, Integer pontosVida, Integer forca, Integer defesa, Integer nivel, Integer resistencia) {
        this.id = id;
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.forca = forca;
        this.defesa = defesa;
        this.nivel = nivel;
        this.resistencia = resistencia;
        this.habilidades = new ArrayList<>();
        this.fraquezas = new ArrayList<>();
        this.experiencia = 0;
        this.efeitos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPontosVida() {
        return pontosVida;
    }

    public void setPontosVida(Integer pontosVida) {
        this.pontosVida = pontosVida;
    }

    public Integer getForca() {
        return forca;
    }

    public void setForca(Integer forca) {
        this.forca = forca;
    }

    public Integer getDefesa() {
        return defesa;
    }

    public void setDefesa(Integer defesa) {
        this.defesa = defesa;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHabilidades(ArrayList<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public ArrayList<Fraqueza> getFraquezas() {
        return fraquezas;
    }

    public void setFraquezas(ArrayList<Fraqueza> fraquezas) {
        this.fraquezas = fraquezas;
    }

    public ArrayList<Habilidade> getHabilidades() {
        return habilidades;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Integer getResistencia() {
        return resistencia;
    }

    public void setResistencia(Integer resistencia) {
        this.resistencia = resistencia;
    }

    public Equipamento getArmaEquipada() {
        return armaEquipada;
    }

    public void setArmaEquipada(Equipamento armaEquipada) {
        this.armaEquipada = armaEquipada;
    }

    public Equipamento getArmaduraEquipada() {
        return armaduraEquipada;
    }

    public void setArmaduraEquipada(Equipamento armaduraEquipada) {
        this.armaduraEquipada = armaduraEquipada;
    }

    public void adicionarHabilidade(Habilidade habilidade) {
        habilidades.add(habilidade);
    }


    public void adicionarFraqueza(Fraqueza fraqueza) {
        fraquezas.add(fraqueza);
    }

    public void criaEfeito(Efeito efeito) {
        efeitos.add(efeito);
    }

    public ArrayList<Efeito> getEfeitos() {
        return efeitos;
    }

    public void setEfeitos(ArrayList<Efeito> efeitos) {
        this.efeitos = efeitos;
    }

    public void aplicaEfeito(Efeito efeito) {
        boolean efeitoExistente = false;
        Iterator<Efeito> iterator = efeitos.iterator();
        while (iterator.hasNext()) {
            Efeito e = iterator.next();
            if (e.getTipo().equals(efeito.getTipo())) {
                efeitoExistente = true;
                break;
            }
        }

        if (!efeitoExistente) {
            efeitos.add(efeito);
        }
    }

    public void atualizaEfeitos() {
        Iterator<Efeito> iterator = efeitos.iterator();
        while (iterator.hasNext()) {
            Efeito efeito = iterator.next();
            int dano = efeito.getDanoPorTurno();
            pontosVida -= dano;

            System.out.println("    + O efeito " + efeito.getTipo() + " causou " + dano + " pontos de dano.");
            System.out.println("    + Vida do alvo " + getNome() + " depois do efeito: " + pontosVida);

            efeito.passaTurno();
            if (efeito.getDuracao() <= 0) {
                iterator.remove();
            }
        }
    }

    public void defender() {
        System.out.println("O jogador " + this.nome + " está se defendendo!");
        this.defesa *= 2;
    }

    public int calcularDanoFisico(Personagem alvo) {
        int danoBase = this.forca * this.nivel;

        int danoFinal = danoBase - alvo.getDefesa();

        if (danoFinal < 0) {
            danoFinal = 1;
        }

        double variacao = Math.random() * 0.2 - 0.1;
        danoFinal = (int) (danoFinal * (1 + variacao));

        return danoFinal;
    }

    public int calcularDanoMagico(Habilidade habilidade, Personagem alvo) {
        int danoBase = habilidade.getDanoBase() * this.resistencia * this.nivel;

        for (Fraqueza fraqueza : alvo.getFraquezas()) {
            if (fraqueza.getTipo().equals(habilidade.getTipo())) {
                danoBase = (int) (danoBase * 1.5);
            }
        }

        for (Habilidade habilidadeAlvo : alvo.getHabilidades()) {
            if (habilidadeAlvo.getTipo().equals(habilidade.getTipo())) {
                danoBase = (int) (danoBase * 0.5);
            }
        }

        double variacao = Math.random() * 0.2 - 0.1;
        danoBase = (int) (danoBase * (1 + variacao));

        return danoBase;
    }

    public boolean temEquipamentoEquipadoDoTipo(Equipamento.TipoEquipamento tipo) {
        if (tipo == Equipamento.TipoEquipamento.ARMA) {
            return this.armaEquipada != null;
        } else if (tipo == Equipamento.TipoEquipamento.ARMADURA) {
            return this.armaduraEquipada != null;
        }
        return false;
    }

    public void equiparEquipamento(Equipamento equipamento) {
        if (equipamento.getTipo() == Equipamento.TipoEquipamento.ARMA) {
            this.armaEquipada = equipamento;
            this.forca += equipamento.getAumentoAtributo();
        } else if (equipamento.getTipo() == Equipamento.TipoEquipamento.ARMADURA) {
            this.armaduraEquipada = equipamento;
            this.defesa += equipamento.getAumentoAtributo();
        }
    }

    public void desequiparEquipamento(Equipamento equipamento) {
        if (equipamento.getTipo() == Equipamento.TipoEquipamento.ARMA) {
            if (this.armaEquipada != null && this.armaEquipada.getId() == equipamento.getId()) {
                this.armaEquipada = null;
                this.forca -= equipamento.getAumentoAtributo();
            }
        } else if (equipamento.getTipo() == Equipamento.TipoEquipamento.ARMADURA) {
            if (this.armaduraEquipada != null && this.armaduraEquipada.getId() == equipamento.getId()) {
                this.armaduraEquipada = null;
                this.defesa -= equipamento.getAumentoAtributo();
            }
        }
    }

    public void adicionarExperiencia(Integer xp) {
        this.experiencia += xp;
        verificarSubidaNivel();
    }

    private void verificarSubidaNivel() {
        if (experiencia >= 100) {
            subirNivel();
        }
    }

    private void subirNivel() {
        nivel++;
        forca += 5;
        defesa += 3;

        if (armaEquipada != null) {
            forca += armaEquipada.getAumentoAtributo();
        }
        if (armaduraEquipada != null) {
            defesa += armaduraEquipada.getAumentoAtributo();
        }

        experiencia = 0;
    }

    @Override
    public String toString() {
        return "Personagem{" +
                "id=" + id +
                ", nível='" + nivel + '\'' +
                ", nome='" + nome + '\'' +
                ", pontosVida=" + pontosVida +
                ", forca=" + forca +
                ", defesa=" + defesa +
                ", habilidades=" + habilidades +
                ", fraquezas=" + fraquezas +
                '}';
    }
}
