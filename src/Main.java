import java.util.*;

public class Main {
    public static final Map<Integer, Integer> sizeToFreq = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        final int numberOfThreads = 1000;

        List<Thread> threads = new ArrayList<>();
        System.out.println("Запускается " + numberOfThreads + " потоков...");
        for (int i = 0; i < numberOfThreads; i++) {
            Thread thread = new Thread(new MyRouter(sizeToFreq));
            threads.add(thread);
            thread.start();
        }
        System.out.println("Ожидаю завершения потоков...");
        for (Thread thread : threads) {
            thread.join();
        }

        // Определение максимального количества повторений
        Optional<Map.Entry<Integer, Integer>> maxEntry = sizeToFreq.entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue));
        int maxCount = maxEntry.get().getValue();
        // Выдача одной или более частот с найденным выше максимальным количеством повторений
        System.out.print("Самые частые количества повторений (встретились " + maxCount + " раз):");
        for (Map.Entry<Integer, Integer> freq: sizeToFreq.entrySet()) {
            if (freq.getValue() == maxCount) {
                System.out.print("  " + freq.getKey());
            }
        }
        System.out.println("\nДругие размеры:");
        for (Map.Entry<Integer, Integer> freq: sizeToFreq.entrySet()) {
            if (freq.getValue() < maxCount) {
                System.out.println(String.format("%s (%s раз)", freq.getKey(), freq.getValue()));
            }
        }
    }
}
