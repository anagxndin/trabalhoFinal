package rpg;

import java.io.Serializable;

public class Item implements Serializable {
    private String nome;
    private int efeito; // vida ou mana

    public Item(String nome, int efeito) {
        this.nome = nome;
        this.efeito = efeito;
    }

    public String getNome() { return nome; }
    public int getEfeito() { return efeito; }
}
