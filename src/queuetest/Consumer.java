package queuetest;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Roberto Wander
 */
public class Consumer extends Thread {

    private static final int THREAD_POOL_SIZE = 100;

    private final TimeStats consStats;

    private final Queue<Entity> queue;
    private final ExecutorService service;

    public Consumer(Queue<Entity> queue, TimeStats consStats) {
        super("Consumer");
        this.service = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        this.queue = queue;
        this.consStats = consStats;
    }

    @Override
    public void run() {

        for (int i = 0; i < THREAD_POOL_SIZE; i++) {
            service.execute(new ConsumerRunnable(queue, consStats));
        }

    }

}
