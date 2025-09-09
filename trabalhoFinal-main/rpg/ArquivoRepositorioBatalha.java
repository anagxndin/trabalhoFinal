package rpg;

import java.io.*;
import java.util.List;

public class ArquivoRepositorioBatalha implements RepositorioBatalha {
    private final File arquivo = new File("batalhas.log");

    @Override
    public void salvarLog(List<String> log) throws Exception {
        try (PrintWriter pw = new PrintWriter(new FileWriter(arquivo, true))) {
            pw.println("=== Nova Batalha ===");
            for (String linha : log)
                pw.println(linha);
            pw.println();
        }
    }
}