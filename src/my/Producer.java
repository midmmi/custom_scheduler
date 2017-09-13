package my;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * Generates pairs of {@code}LocalDateTime and {@code}Callable.
 *
 * Created by M4 on 12/09/2017.
 */
public class Producer implements Runnable {
    private final Scheduler<String> scheduler;
    private final LocalDateTime start = LocalDateTime.now().plus(10, ChronoUnit.SECONDS);

    public Producer(Scheduler<String> scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++) {
            scheduler.submit(getLocalDateTime(), getCallable());
        }
    }

    private LocalDateTime getLocalDateTime() {
        //All tasks should start at the same time
        return start;
    }

    private Callable<String> getCallable() {
        return () -> {
            String s = UUID.randomUUID().toString();
            System.out.println(s);
            Thread.sleep(100);
            return s;
        };
    }
}
