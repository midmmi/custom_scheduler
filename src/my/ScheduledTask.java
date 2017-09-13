package my;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.Callable;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * A composite object for time and task
 * Created by M4 on 12/09/2017.
 */
public class ScheduledTask<E> implements Delayed, Callable<E> {

    private final Callable<E> callable;
    private final long millis;

    public ScheduledTask(LocalDateTime dateTime, Callable<E> callable) {
        this.millis = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        this.callable = callable;
    }

    @Override
    public E call() throws Exception {
        return callable.call();
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = millis - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Long l = getMillis() - ((ScheduledTask)o).getMillis();
        if (l > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        else if (l < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        else return l.intValue();
    }

    public long getMillis() {
        return millis;
    }
}
