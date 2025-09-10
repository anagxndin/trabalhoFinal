package rpg;

public class AcaoBolaDeFogo implements Acao {
    @Override
    public String getNome() {
        return "Bola de Fogo";
    }

    @Override
    public boolean podeExecutar(Personagem self) {
        return self instanceof Mago;
    }

    @Override
    public void executar(Personagem self, Personagem alvo) throws Exception {
        if (!(self instanceof Mago))
            throw new AtaqueInvalidoException("Ação exclusiva de Mago.");
        ((Mago) self).bolaDeFogo(alvo);
    }
}
