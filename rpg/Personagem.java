package rpg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class Personagem implements Serializable {
    protected String nome;
    protected int vida;
    protected int ataque;
    protected int defesa;
    protected List<Item> inventario = new ArrayList<>();

    public Personagem(String nome, int vida, int ataque, int defesa) {
        this.nome = nome;
        this.vida = vida;
        this.ataque = ataque;
        this.defesa = defesa;
    }

    public abstract void atacar(Personagem alvo) throws ManaInsuficienteException, AtaqueInvalidoException;

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida < 0) this.vida = 0;
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void adicionarItem(Item item) {
        inventario.add(item);
    }

    public String getNome() { return nome; }
    public int getVida() { return vida; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public List<Item> getInventario() { return inventario; }
}
