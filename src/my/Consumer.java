package my;

import java.util.concurrent.*;

/**
 * Consumer of tasks. Simply executes {@code}Callable in a new thread.
 *
 * Created by M4 on 12/09/2017.
 */
public class Consumer<E extends Callable, K> implements Runnable {
    private final BlockingQueue<E> queue;
    private final ExecutorService executorService;

    public Consumer(BlockingQueue<E> queue, ExecutorService executorService) {
        this.queue = queue;
        this.executorService = executorService;
    }

    @Override
    public void run() {
        try {
            while (!executorService.isShutdown()) {
                Callable<K> callable = queue.take();
                Future<K> future = executorService.submit(callable);
                future.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
