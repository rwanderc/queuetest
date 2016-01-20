package queuetest;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Roberto Wander
 */
public class Producer extends Thread {

    private final static int MIN_QUEUE = 1000;
    private final static int SLEEP = 5;

    private final Queue<Entity> queue;
    private final ExecutorService service;
    private final Semaphore semaphore;
    private final TimeStats prodStats;

    public Producer(final Queue<Entity> queue, final TimeStats prodStats) {

        super("Producer");
        this.queue = queue;
        this.service = Executors.newFixedThreadPool(1);
        this.semaphore = new Semaphore(1);
        this.prodStats = prodStats;

    }

    @Override
    public void run() {

        while (true) {

            while (queue.size() > MIN_QUEUE) {
                try {
                    Thread.sleep(SLEEP);
                } catch (Throwable ex) {
                }
            }

            semaphore.acquireUninterruptibly();
            service.execute(new ProducerRunnable(semaphore, queue, prodStats));

        }

    }

}
