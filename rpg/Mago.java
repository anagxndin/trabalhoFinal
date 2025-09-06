package rpg;

public class Mago extends Personagem {
    protected int mana;
    protected int poderMagico;

    public Mago(String nome, int vida, int ataque, int defesa, int mana, int poderMagico) {
        super(nome, vida, ataque, defesa);
        this.mana = mana;
        this.poderMagico = poderMagico;
    }

    public boolean temMana() {
        return mana >= 5;
    }

    public int getMana() {
        return mana;
    }

    public int getPoderMagico() {
        return poderMagico;
    }

    @Override
    public void atacar(Personagem alvo) {
        if (mana >= 5) {
            int rolagem = Dado.rolar(20);
            int danoBase = ataque + poderMagico + Dado.rolar(8);

            if (rolagem == 20) {
                danoBase *= 2;
                System.out.println(nome + " lança magia CRÍTICA!");
            } else if (rolagem == 1) {
                danoBase = 0;
                System.out.println(nome + " falhou ao conjurar magia!");
            }

            int danoFinal = danoBase - alvo.getDefesa();
            if (danoFinal < 0) danoFinal = 0;

            alvo.receberDano(danoFinal);
            mana -= 5;

            System.out.println(nome + " ataca " + alvo.getNome() +
                    " causando " + danoFinal + " de dano! (Vida do alvo: " + alvo.getVida() +
                    ", Mana restante: " + mana + ")");
        } else {
            System.out.println(nome + " está sem mana e não pode atacar!");
        }
    }

    // Habilidade especial
    public void bolaDeFogo(Personagem alvo) {
        if (mana >= 10) {
            int dano = poderMagico + Dado.rolar(12);
            alvo.receberDano(dano);
            mana -= 10;
            System.out.println(nome + " lança BOLA DE FOGO em " + alvo.getNome() +
                    " causando " + dano + " de dano! (Mana restante: " + mana + ")");
        } else {
            System.out.println(nome + " tentou lançar Bola de Fogo mas não tem mana!");
        }
    }
}
