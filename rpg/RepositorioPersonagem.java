package rpg;

import java.util.List;

public interface RepositorioPersonagem {
    void salvar(Personagem p) throws Exception;

    List<Personagem> listarTodos() throws Exception;
}
