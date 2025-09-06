package rpg;

import java.util.Scanner;

public class Combate {

    public static void lutar(Personagem jogador, Personagem inimigo) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nInício do combate: " + jogador.getNome() + " vs " + inimigo.getNome());

        while (jogador.estaVivo() && inimigo.estaVivo()) {

        
            System.out.println("\n--- Turno de " + jogador.getNome() + " ---");
            System.out.println("1 - Atacar");
            if (jogador instanceof Mago) System.out.println("2 - Bola de Fogo");
            System.out.print("Escolha sua ação: ");
            int escolha = sc.nextInt();

            try {
                switch (escolha) {
                    case 1 -> jogador.atacar(inimigo);
                    case 2 -> {
                        if (jogador instanceof Mago)
                            ((Mago) jogador).bolaDeFogo(inimigo);
                        else
                            throw new AtaqueInvalidoException("Ação inválida para essa classe!");
                    }
                    default -> throw new AtaqueInvalidoException("Opção inválida! Você perdeu o turno.");
                }
            } catch (ManaInsuficienteException | AtaqueInvalidoException e) {
                System.out.println(e.getMessage());
            }

            // Verifica se o inimigo morreu
            if (!inimigo.estaVivo()) break;

            // --- TURNO DO INIMIGO (ataque automático) ---
            System.out.println("\n--- Turno de " + inimigo.getNome() + " ---");
            try {
                inimigo.atacar(jogador);
            } catch (ManaInsuficienteException | AtaqueInvalidoException e) {
                System.out.println(inimigo.getNome() + " não pôde atacar: " + e.getMessage());
            }
        }

        // --- RESULTADO FINAL ---
        if (jogador.estaVivo()) {
            System.out.println("\nParabéns, " + jogador.getNome() + "! Você venceu o combate!");
        } else {
            System.out.println("\nQue pena, " + inimigo.getNome() + " venceu. Tente novamente!");
        }
    }
}
