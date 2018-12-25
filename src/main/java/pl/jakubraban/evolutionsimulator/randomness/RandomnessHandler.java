package pl.jakubraban.evolutionsimulator.randomness;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomnessHandler {

    private static Random random = new Random();

    public static int randomIntFromRange(int min, int max) {
        if(max < min) throw new IllegalArgumentException("Minimum in range cannot be greater than maximum");
        return random.nextInt(max - min + 1) + min;
    }

    public static <T> T randomElementFromList(List<T> list) {
        int drawnIndex = randomIntFromRange(0, list.size() - 1);
        return list.get(drawnIndex);
    }

    public static String randomName(int length) {
        if(length <= 0) throw new IllegalArgumentException("Negative or zero length");
        List<Character> allowedCharacters = Arrays.asList('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f');
        StringBuilder nameBuilder = new StringBuilder();
        for(int i = 0; i < length; i++) nameBuilder.append(randomElementFromList(allowedCharacters));
        return nameBuilder.toString();
    }

}
