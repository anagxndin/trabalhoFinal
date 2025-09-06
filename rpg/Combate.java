package rpg;

import java.util.Scanner;

public class Combate {
    public static void lutar(Personagem jogador, Personagem inimigo) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nInício do combate entre " + jogador.getNome() + " e " + inimigo.getNome());

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            // TURNO DO JOGADOR
            System.out.println("\n--- Turno de " + jogador.getNome() + " ---");
            System.out.println("1 - Atacar");
            if (jogador instanceof Mago) {
                System.out.println("2 - Bola de Fogo");
            }
            System.out.print("Escolha sua ação: ");
            int escolha = sc.nextInt();

            switch (escolha) {
                case 1:
                    jogador.atacar(inimigo);
                    break;
                case 2:
                    if (jogador instanceof Mago) {
                        ((Mago) jogador).bolaDeFogo(inimigo);
                    } else {
                        System.out.println("Ação inválida! Você perdeu o turno.");
                    }
                    break;
                default:
                    System.out.println("Ação inválida! Você perdeu o turno.");
            }

            if (!inimigo.estaVivo()) break;

            System.out.println("\n--- Turno de " + inimigo.getNome() + " ---");
            inimigo.atacar(jogador);
        }

        // Mensagem final
        if (jogador.estaVivo()) {
            System.out.println("\nParabéns, " + jogador.getNome() + "! Você venceu o combate!");
        } else {
            System.out.println("\nQue pena... " + inimigo.getNome() + " venceu. Tente novamente!");
        }
    }
}
