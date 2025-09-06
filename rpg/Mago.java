package rpg;

public class Mago extends Personagem {
    private int mana;
    private int poderMagico;

    public Mago(String nome, int vida, int ataque, int defesa, int mana, int poderMagico) {
        super(nome, vida, ataque, defesa);
        this.mana = mana;
        this.poderMagico = poderMagico;
    }

    @Override
    public void atacar(Personagem alvo) throws ManaInsuficienteException {
        if (mana < 5) throw new ManaInsuficienteException("Mana insuficiente para atacar!");
        int dano = ataque + poderMagico + Dado.rolar(8) - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
        mana -= 5;
        System.out.println(nome + " lança magia em " + alvo.getNome() + " causando " + dano + " de dano! (Mana: " + mana + ")");
    }

    public void bolaDeFogo(Personagem alvo) throws ManaInsuficienteException {
        if (mana < 10) throw new ManaInsuficienteException("Mana insuficiente para Bola de Fogo!");
        int dano = poderMagico + Dado.rolar(12) - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
        mana -= 10;
        System.out.println(nome + " lança Bola de Fogo em " + alvo.getNome() + " causando " + dano + " de dano! (Mana: " + mana + ")");
    }

    public int getMana() { return mana; }
    public int getPoderMagico() { return poderMagico; }
}
