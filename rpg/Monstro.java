package rpg;

public class Monstro extends Personagem {
    public Monstro(String nome, int vida, int ataque, int defesa) {
        super(nome, vida, ataque, defesa);
        this.acoes.add(new AcaoAtacar());
    }

@Override
public void atacar(Personagem alvo) {
    int dano = ataque + Dado.rolar(6) - alvo.getDefesa();
    if (dano < 0) dano = 0;
    alvo.receberDano(dano);
    System.out.println(nome + " golpeia " + alvo.getNome() + " causando " + dano + " de dano!");
}
}