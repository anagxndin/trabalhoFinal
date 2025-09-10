package rpg;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Inventario implements Serializable {
    private final int capacidade;
    private final List<Item> itens = new ArrayList<>();

    public Inventario(int capacidade) {
        this.capacidade = capacidade;
    }

    public void adicionar(Item item) throws InventarioCheioException {
        if (itens.size() >= capacidade)
            throw new InventarioCheioException("Inventário cheio!");
        itens.add(item);
    }

    public boolean remover(Item item) {
        return itens.remove(item);
    }

    public List<Item> listar() {
        return new ArrayList<>(itens);
    }

    public int getCapacidade() {
        return capacidade;
    }
}