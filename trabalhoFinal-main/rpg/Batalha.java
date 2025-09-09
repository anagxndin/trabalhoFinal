package rpg;

import java.util.ArrayList;
import java.util.List;

public class Batalha {
    private final Personagem jogador;
    private final Personagem inimigo;
    private final List<String> log = new ArrayList<>();

    public Batalha(Personagem jogador, Personagem inimigo) {
        this.jogador = jogador;
        this.inimigo = inimigo;
        log("Início: " + jogador.getNome() + " vs " + inimigo.getNome());
    }

    public Personagem getJogador() {
        return jogador;
    }

    public Personagem getInimigo() {
        return inimigo;
    }

    public List<String> getLog() {
        return new ArrayList<>(log);
    }

    private void log(String s) {
        System.out.println(s);
        log.add(s);
    }

    public boolean terminou() {
        return !jogador.estaVivo() || !inimigo.estaVivo();
    }

    public void jogadorAcao(Acao acao) {
        if (terminou())
            return;
        if (acao == null || !acao.podeExecutar(jogador)) {
            log("Ação inválida. Turno perdido.");
            return;
        }
        try {
            acao.executar(jogador, inimigo);
            log(jogador.getNome() + " usou " + acao.getNome() + ".");
        } catch (Exception e) {
            log("Falha na ação: " + e.getMessage());
        }
        if (!inimigo.estaVivo()) {
            log("Vitória de " + jogador.getNome() + "!");
            return;
        }
        turnoInimigo();
    }

    public void turnoInimigo() {
        if (terminou())
            return;
        // inimigo sempre usa "Atacar"
        Acao atacar = null;
        for (Acao a : inimigo.getAcoes()) {
            if ("Atacar".equalsIgnoreCase(a.getNome())) {
                atacar = a;
                break;
            }
        }
        if (atacar == null) {
            log(inimigo.getNome() + " não tem ação Atacar.");
            return;
        }
        try {
            atacar.executar(inimigo, jogador);
            log(inimigo.getNome() + " usou Atacar.");
        } catch (Exception e) {
            log(inimigo.getNome() + " falhou: " + e.getMessage());
        }
        if (!jogador.estaVivo()) {
            log("Derrota de " + jogador.getNome() + ".");
        }
    }
}