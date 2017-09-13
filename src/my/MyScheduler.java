package my;

import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

/**
 * Default implementation of {@code}Scheduler
 * Created by M4 on 12/09/2017.
 */
public class MyScheduler<E> implements Scheduler<E> {
    private final BlockingQueue<ScheduledTask<E>> queue;

    public MyScheduler(BlockingQueue<ScheduledTask<E>> queue) {
        this.queue = queue;
    }

    @Override
    public void submit(LocalDateTime dateTime, Callable<E> callable) {
        ScheduledTask<E> task = new ScheduledTask<>(dateTime, callable);
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
