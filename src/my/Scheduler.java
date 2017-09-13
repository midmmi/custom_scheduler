package my;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;

/**
 * Front-end interface
 * Created by M4 on 12/09/2017.
 */
public interface Scheduler<E> {

    /**
     * Interface for adding tasks to the system
     * @param dateTime of task execution
     * @param callable task
     */
    void submit(LocalDateTime dateTime, Callable<E> callable);
}
