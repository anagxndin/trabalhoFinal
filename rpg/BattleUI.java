package rpg;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class BattleUI {
    private JFrame frame;
    private JTextField nomeField;
    private JComboBox<String> classeBox;
    private JLabel lblJogador, lblInimigo;
    private JButton btnAtacar, btnBolaDeFogo, btnUsarPocao, btnSalvar;
    private JTextArea areaLog;

    private Batalha batalha;
    private RepositorioPersonagem repoP = new ArquivoRepositorioPersonagem();
    private RepositorioBatalha repoB = new ArquivoRepositorioBatalha();

    public void exibir() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("RPG - Batalha");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 500);
            frame.setLayout(new BorderLayout());

            JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
            nomeField = new JTextField(12);
            classeBox = new JComboBox<>(new String[]{"Guerreiro", "Mago"});
            JButton btnIniciar = new JButton("Iniciar");
            topo.add(new JLabel("Nome:"));
            topo.add(nomeField);
            topo.add(new JLabel("Classe:"));
            topo.add(classeBox);
            topo.add(btnIniciar);

            JPanel centro = new JPanel(new BorderLayout());
            JPanel status = new JPanel(new GridLayout(2, 1));
            lblJogador = new JLabel("Jogador: -");
            lblInimigo = new JLabel("Inimigo: -");
            status.add(lblJogador);
            status.add(lblInimigo);

            JPanel acoes = new JPanel(new FlowLayout(FlowLayout.LEFT));
            btnAtacar = new JButton("Atacar");
            btnBolaDeFogo = new JButton("Bola de Fogo");
            btnUsarPocao = new JButton("Usar Poção");
            btnSalvar = new JButton("Salvar Log");
            btnAtacar.setEnabled(false);
            btnBolaDeFogo.setEnabled(false);
            btnUsarPocao.setEnabled(false);
            btnSalvar.setEnabled(false);
            acoes.add(btnAtacar);
            acoes.add(btnBolaDeFogo);
            acoes.add(btnUsarPocao);
            acoes.add(btnSalvar);

            areaLog = new JTextArea();
            areaLog.setEditable(false);
            JScrollPane scroll = new JScrollPane(areaLog);

            centro.add(status, BorderLayout.NORTH);
            centro.add(acoes, BorderLayout.CENTER);
            centro.add(scroll, BorderLayout.SOUTH);

            frame.add(topo, BorderLayout.NORTH);
            frame.add(centro, BorderLayout.CENTER);

            // handlers
            btnIniciar.addActionListener(e -> iniciarBatalha());
            btnAtacar.addActionListener(e -> acaoJogador("Atacar"));
            btnBolaDeFogo.addActionListener(e -> acaoJogador("Bola de Fogo"));
            btnUsarPocao.addActionListener(ignored -> acaoJogador("Usar Poção"));
            btnSalvar.addActionListener(e -> salvarLog());

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private void iniciarBatalha() {
        String nome = Optional.ofNullable(nomeField.getText()).orElse("Herói").trim();
        String classe = (String) classeBox.getSelectedItem();

        Personagem jogador;
        if ("Guerreiro".equals(classe)) jogador = new Guerreiro(nome, 100, 10, 5, 6);
        else jogador = new Mago(nome, 80, 8, 3, 30, 12);

        Personagem inimigo = new Monstro("Goblin", 70, 7, 2);

        batalha = new Batalha(jogador, inimigo);
        atualizarStatus();
        atualizarLog();

        btnAtacar.setEnabled(true);
        btnBolaDeFogo.setEnabled(jogador instanceof Mago);
        btnUsarPocao.setEnabled(true);
        btnSalvar.setEnabled(true);
        jogador.adicionarItem(new Item("Poção de Vida", 30));

        try { repoP.salvar(jogador); } catch (Exception ex) { append("Falha ao salvar personagem: " + ex.getMessage()); }
    }

    private void acaoJogador(String nomeAcao) {
        if (batalha == null) return;
        Personagem jogador = batalha.getJogador();
        Personagem inimigo = batalha.getInimigo();

        Acao escolhida = null;
        for (Acao a : jogador.getAcoes()) {
            if (nomeAcao.equalsIgnoreCase(a.getNome())) { escolhida = a; break; }
        }
        if (escolhida == null) { append("Ação não disponível."); return; }

        batalha.jogadorAcao(escolhida);
        atualizarStatus();
        atualizarLog();

        if (batalha.terminou()) {
            btnAtacar.setEnabled(false);
            btnBolaDeFogo.setEnabled(false);
            append("Combate encerrado.");
        }
    }

    private void salvarLog() {
        if (batalha == null) return;
        try {
            repoB.salvarLog(batalha.getLog());
            append("Log salvo em batalhas.log");
        } catch (Exception e) {
            append("Falha ao salvar log: " + e.getMessage());
        }
    }

    private void atualizarStatus() {
        lblJogador.setText(String.format("Jogador: %s - HP: %d", batalha.getJogador().getNome(), batalha.getJogador().getVida()));
        lblInimigo.setText(String.format("Inimigo: %s - HP: %d", batalha.getInimigo().getNome(), batalha.getInimigo().getVida()));
    }

    private void atualizarLog() {
        areaLog.setText("");
        for (String l : batalha.getLog()) areaLog.append(l + "\n");
    }

    private void append(String s) { areaLog.append(s + "\n"); }
}
