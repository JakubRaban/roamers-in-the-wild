package pl.jakubraban.evolutionsimulator.randomness;

import java.util.*;

public class RandomnessHandler {

    private static Random random = new Random();

    public static int randomIntFromRange(int min, int max) {
        if(max < min) throw new IllegalArgumentException("Minimum in range cannot be greater than maximum");
        return random.nextInt(max - min + 1) + min;
    }

    public static <T> T randomElementFromList(List<T> list) {
        return randomNElementsFromList(list, 1).get(0);
    }

    public static <T> List<T> randomNElementsFromList(List<T> list, final int N) {
        if(N <= 0) throw new IllegalArgumentException();
        if(list.size() <= N) return list;
        Collections.shuffle(list);
        return list.subList(0, N);
    }

    public static <T> T randomElementByProbability(Map<T, Probability> probabilityMap) {
        if(probabilityMap.values().stream().mapToInt(Probability::getProbabilityInPercent).sum() != 100)
            throw new IllegalArgumentException("Bad sum of probabilities");
        List<T> probabilityInducedList = new LinkedList<>();
        for(Map.Entry<T, Probability> entry: probabilityMap.entrySet()) {
            int probabilityInPercent = entry.getValue().getProbabilityInPercent();
            for(int i = 0; i < probabilityInPercent; i++) probabilityInducedList.add(entry.getKey());
        }
        return randomElementFromList(probabilityInducedList);
    }

    public static <T> T randomElementByRelativeProbability(Map<T, Integer> probabilityMap) {
        List<T> repeatingElementsByProbability = new LinkedList<>();
        for(Map.Entry<T, Integer> entry : probabilityMap.entrySet()) {
            for(int i = 1; i <= entry.getValue(); i++) repeatingElementsByProbability.add(entry.getKey());
        }
        return randomElementFromList(repeatingElementsByProbability);
    }

    public static String randomName(int length) {
        if(length <= 0) throw new IllegalArgumentException("Negative or zero length");
        List<Character> allowedCharacters = Arrays.asList('0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f');
        StringBuilder nameBuilder = new StringBuilder();
        for(int i = 0; i < length; i++) nameBuilder.append(randomElementFromList(allowedCharacters));
        return nameBuilder.toString();
    }

}
