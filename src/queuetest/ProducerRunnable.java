package queuetest;

import java.util.Queue;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Roberto Wander
 */
public class ProducerRunnable implements Runnable {

    private final int N = 1 * 1000;
    private final Semaphore semaphore;
    private final Queue<Entity> queue;
    private final TimeStats prodStats;

    public ProducerRunnable(Semaphore semaphore, Queue<Entity> queue, TimeStats prodStats) {
        this.semaphore = semaphore;
        this.queue = queue;
        this.prodStats = prodStats;
    }

    @Override
    public void run() {

        long time;

        try {

            time = System.nanoTime();

            for (int i = 0; i < N; i++) {
                queue.offer(new Entity());
            }

            time = -time + System.nanoTime();

            prodStats.add(N, time);

        } catch (Throwable ex) {
        } finally {

            semaphore.release();

        }
    }

}
