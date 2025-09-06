package rpg;

public class Guerreiro extends Personagem {
    private int forca;

    public Guerreiro(String nome, int vida, int ataque, int defesa, int forca) {
        super(nome, vida, ataque, defesa);
        this.forca = forca;
    }

    @Override
    public void atacar(Personagem alvo) {
        int dano = ataque + forca + Dado.rolar(6) - alvo.getDefesa();
        if (dano < 0) dano = 0;
        alvo.receberDano(dano);
        System.out.println(nome + " ataca " + alvo.getNome() + " causando " + dano + " de dano!");
    }

    public int getForca() { return forca; }
}
