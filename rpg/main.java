package rpg;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do seu personagem: ");
        String nome = sc.nextLine();

        System.out.println("Escolha a classe:\n1 - Guerreiro\n2 - Mago");
        int opcao = sc.nextInt();

        Personagem jogador;
        if (opcao == 1) jogador = new Guerreiro(nome, 100, 10, 5, 6);
        else jogador = new Mago(nome, 80, 8, 3, 30, 12);

        System.out.println("Bem-vindo, " + jogador.getNome() + "! O jogo vai começar.");

        // Criar inimigo
        Personagem inimigo = new Mago("Gnarly", 80, 8, 3, 30, 12);
        System.out.println("Inimigo: " + inimigo.getNome() + " (Mago)");

        Combate.lutar(jogador, inimigo);
        sc.close();
    }
}
