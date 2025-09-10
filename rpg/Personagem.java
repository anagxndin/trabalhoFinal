package rpg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Personagem implements Serializable {
    protected String nome;
    protected int vida;
    protected int ataque;
    protected int defesa;
    protected Inventario inventario = new Inventario(20);
    protected List<Acao> acoes = new ArrayList<>();

    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public abstract void atacar(Personagem alvo) throws ManaInsuficienteException,
            AtaqueInvalidoException;

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0)
            this.vida = 0;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void adicionarItem(Item item) {
        try {
            inventario.adicionar(item);
        } catch (InventarioCheioException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public List<Acao> getAcoes() {
        return acoes;
    }
    public void curar(int qtd) { this.vida += qtd; 
    }
}
