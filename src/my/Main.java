package my;

import java.util.concurrent.*;

/**
 * Main class showing example.
 *
 *
 * Created by M4 on 12/09/2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<ScheduledTask<String>> queue = new DelayQueue<>();
        Scheduler<String> scheduler = new MyScheduler<>(queue);

        //Having two consumers there should be at least three thread
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Consumer<ScheduledTask<String>, String> consumer1 = new Consumer<>(queue, executorService);
        Consumer<ScheduledTask<String>, String> consumer2 = new Consumer<>(queue, executorService);

        executorService.execute(consumer1);
        executorService.execute(consumer2);

        Producer producer1 = new Producer(scheduler);
        Producer producer2 = new Producer(scheduler);

        executorService.execute(producer1);
        executorService.execute(producer2);

        Thread.sleep(20000);

        executorService.shutdown();
        try {
            // Wait a while for existing tasks to terminate
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!executorService.awaitTermination(60, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            executorService.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
}
