package queuetest;

import java.util.Queue;

/**
 *
 * @author Roberto Wander
 */
public class ConsumerRunnable implements Runnable {

    private static final long SLEEP = 1;

    private final Queue<Entity> queue;
    private final TimeStats consStats;

    public ConsumerRunnable(final Queue<Entity> queue, final TimeStats consStats) {
        this.queue = queue;
        this.consStats = consStats;
    }

    @Override
    public void run() {

        Entity entity = null;
        long time;

        while (true) {

            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException ex) {
            }

            do {

                time = System.nanoTime();

                entity = queue.poll();
                new PerformerICMP().ping();

                time = -time + System.nanoTime();
                consStats.add(1, time);

            } while (entity != null);

        }

    }

}
