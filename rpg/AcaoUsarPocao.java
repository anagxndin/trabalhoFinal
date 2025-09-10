package rpg;

public class AcaoUsarPocao implements Acao, java.io.Serializable {
    @Override public String getNome() { return "Usar Poção"; }

    @Override
    public boolean podeExecutar(Personagem self) {
        return self != null && self.getInventario().listar().stream()
                .anyMatch(i -> "Poção de Vida".equalsIgnoreCase(i.getNome()));
    }

    @Override
    public void executar(Personagem self, Personagem alvo) throws Exception {
        var itens = self.getInventario().listar();
        for (Item it : itens) {
            if ("Poção de Vida".equalsIgnoreCase(it.getNome())) {
                self.curar(it.getEfeito());
                self.getInventario().remover(it);
                System.out.println(self.getNome() + " usou " + it.getNome() + " (+"
                        + it.getEfeito() + " de vida).");
                return;
            }
        }
        throw new AtaqueInvalidoException("Nenhuma poção disponível.");
    }
}