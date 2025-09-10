package rpg;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoRepositorioPersonagem implements RepositorioPersonagem {
    private final File arquivo = new File("personagens.dat");

    @Override
    public void salvar(Personagem p) throws Exception {
        // estratégia simples: carrega lista, adiciona, regrava
        List<Personagem> todos = listarTodos();
        todos.add(p);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
            oos.writeObject(todos);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Personagem> listarTodos() throws Exception {
        if (!arquivo.exists())
            return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            Object obj = ois.readObject();
            if (obj instanceof List)
                return (List<Personagem>) obj;
        }
        return new ArrayList<>();
    }
}
