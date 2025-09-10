package rpg;

public interface Acao {
    String getNome();

    boolean podeExecutar(Personagem self);

    void executar(Personagem self, Personagem alvo) throws Exception;
}
