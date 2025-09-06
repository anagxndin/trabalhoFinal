package rpg;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Escolha do nome
        System.out.print("Digite o nome do seu personagem: ");
        String nomeJogador = sc.nextLine();

        // Escolha da classe
        System.out.println("Escolha a classe do seu personagem:");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.print("Digite a opção: ");
        int classe = sc.nextInt();

        Personagem jogador;

        if (classe == 1) {
            jogador = new Guerreiro(nomeJogador, 100, 10, 5, 6);
            System.out.println("Você escolheu Guerreiro!");
        } else if (classe == 2) {
            jogador = new Mago(nomeJogador, 80, 8, 3, 30, 12);
            System.out.println("Você escolheu Mago!");
        } else {
            System.out.println("Opção inválida. Criando Guerreiro por padrão.");
            jogador = new Guerreiro(nomeJogador, 100, 10, 5, 6);
        }

        // Mensagem de boas-vindas
        System.out.println("\nBem-vindo, " + jogador.getNome() + "!");
        System.out.println("O jogo já vai começar...");
        System.out.println("Você possui as seguintes habilidades iniciais:");

        if (jogador instanceof Guerreiro) {
            Guerreiro g = (Guerreiro) jogador;
            System.out.println("Tipo: Guerreiro");
            System.out.println("Vida: " + g.getVida());
            System.out.println("Ataque: " + g.getAtaque());
            System.out.println("Defesa: " + g.getDefesa());
            System.out.println("Força: " + g.getForca());
        } else if (jogador instanceof Mago) {
            Mago m = (Mago) jogador;
            System.out.println("Tipo: Mago");
            System.out.println("Vida: " + m.getVida());
            System.out.println("Ataque: " + m.getAtaque());
            System.out.println("Defesa: " + m.getDefesa());
            System.out.println("Poder Mágico: " + m.poderMagico);
            System.out.println("Mana: " + m.mana);
        }
        System.out.println("--------------------------------------\n");

    
        Personagem inimigo = new Mago("Gnarly", 80, 8, 3, 30, 12);
        String tipoInimigo = (inimigo instanceof Guerreiro) ? "Guerreiro" : "Mago";
        System.out.println("O inimigo é: " + inimigo.getNome() + " (" + tipoInimigo + ")\n");

        //determina quem começa
        int iniJogador = Dado.rolar(20);
        int iniInimigo = Dado.rolar(20);
        System.out.println(jogador.getNome() + " tirou " + iniJogador + " de iniciativa.");
        System.out.println(inimigo.getNome() + " tirou " + iniInimigo + " de iniciativa.");

        if (iniInimigo > iniJogador) {
            Personagem temp = jogador;
            jogador = inimigo;
            inimigo = temp;
            System.out.println(inimigo.getNome() + " ganhou a iniciativa e começa!");
        } else {
            System.out.println(jogador.getNome() + " ganhou a iniciativa e começa!");
        }

        Combate.lutar(jogador, inimigo);

        sc.close();
    }
}
