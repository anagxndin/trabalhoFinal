package rpg;

import java.util.Random;

public class Dado {
    private static Random random = new Random();

    public static int rolar(int lados) {
        return random.nextInt(lados) + 1;
    }
}
