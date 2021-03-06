import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;

public class Application {
    private final static int THREAD_POOL_SIZE = 4;
    private static Map<String, Integer> hashMap = null;
    private static Map<String, Integer> hashTable = null;
    private static Map<String, Integer> synchronizedMap = null;
    private static Map<String, Integer> concurrentHashMap = null;

    public static void main(String[] args) throws InterruptedException {
        hashMap = new HashMap<>();
        testMap(hashMap);

        hashTable = new Hashtable<>();
        testMap(hashTable);

        synchronizedMap = Collections.synchronizedMap(new HashMap<>());
        testMap(synchronizedMap);

        concurrentHashMap = new ConcurrentHashMap<>();
        testMap(concurrentHashMap);
    }

    private static void testMap(final Map<String, Integer> testMap) throws InterruptedException {

        System.out.println("Тест для: " + testMap.getClass());
        long averageTime = 0;
        for (int stage = 0; stage < 5; stage++) {

            long startTime = System.nanoTime();
            ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);

            for (int j = 0; j < THREAD_POOL_SIZE; j++) {
                executorService.execute (() -> {
                    for (int element = 0; element < 500000; element++) {
                        Integer randomNumber = (int) Math.ceil(Math.random() * 550000);

                        testMap.put(String.valueOf(randomNumber), randomNumber);
                        /*System.out.println(testMap.get(String.valueOf(randomNumber)));*/
                    }
                });
            }
            executorService.shutdown();
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);

            long entTime = System.nanoTime();
            long totalTime = (entTime - startTime) / 1000000L;
            averageTime += totalTime;
            System.out.println(totalTime + " мс");
        }
        System.out.println("Для " + testMap.getClass() + " среднее время " + averageTime / 5 + " мс\n");
    }
}