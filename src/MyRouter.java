import java.util.Map;
import java.util.Random;

public class MyRouter implements Runnable {
    private Map<Integer, Integer> sizeToFreq;

    public MyRouter(Map<Integer, Integer> sizeToFreq) {
        this.sizeToFreq = sizeToFreq;
    }

    public static String generateRoute(String letters, int length) {
        Random random = new Random();
        StringBuilder route = new StringBuilder();
        for (int i = 0; i < length; i++) {
            route.append(letters.charAt(random.nextInt(letters.length())));
        }
        return route.toString();
    }

    @Override
    public void run() {
        String route = generateRoute("RLRFR", 100);
        int numberOfOccurrences = (int) route.chars().filter(ch -> ch == 'R').count();
        System.out.println("Количество вхождений: " + numberOfOccurrences);
        synchronized (sizeToFreq) {
            int frequency = 1;
            if (sizeToFreq.containsKey(numberOfOccurrences)) {
                frequency = sizeToFreq.get(numberOfOccurrences) + 1;
            }
            sizeToFreq.put(numberOfOccurrences, frequency);
        }
    }
}
