package rpg;

public class Guerreiro extends Personagem {
    private int forca;

    public Guerreiro(String nome, int vida, int ataque, int defesa, int forca) {
        super(nome, vida, ataque, defesa);
        this.forca = forca;
    }

    public int getForca() {
        return forca;
    }

    @Override
    public void atacar(Personagem alvo) {
        int rolagem = Dado.rolar(20);
        int danoBase = ataque + forca + Dado.rolar(6);

        if (rolagem == 20) {
            danoBase *= 2;
            System.out.println(nome + " acerta um CRÍTICO!");
        } else if (rolagem == 1) {
            danoBase = 0;
            System.out.println(nome + " errou o ataque!");
        }

        int danoFinal = danoBase - alvo.getDefesa();
        if (danoFinal < 0) danoFinal = 0;

        alvo.receberDano(danoFinal);
        System.out.println(nome + " ataca " + alvo.getNome() +
                " causando " + danoFinal + " de dano! (Vida restante do alvo: " + alvo.getVida() + ")");
    }
}
