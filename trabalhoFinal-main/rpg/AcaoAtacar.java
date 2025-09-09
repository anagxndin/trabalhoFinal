package rpg;

public class AcaoAtacar implements Acao {
    @Override
    public String getNome() {
        return "Atacar";
    }

    @Override
    public boolean podeExecutar(Personagem self) {
        return self != null &&
                self.estaVivo();
    }

    @Override
    public void executar(Personagem self, Personagem alvo) throws Exception {
        if (self == null || alvo == null)
            throw new AtaqueInvalidoException("Alvo inválido.");
        self.atacar(alvo); // delega para a implementação concreta (Guerreiro/Mago/Monstro)
    }
}