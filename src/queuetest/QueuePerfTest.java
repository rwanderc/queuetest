package queuetest;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author Roberto Wander
 */
public class QueuePerfTest {

    private final static long CYCLE = 60 * 1000 * 1000 * 1000;

    private final Queue<Entity> queue;

    private final TimeStats prodStats;
    private final TimeStats consStats;

    private final Monitor monitor;
    private final Producer prod;
    private final Consumer cons;

    private QueuePerfTest() {

        this.prodStats = new TimeStats(CYCLE);
        this.consStats = new TimeStats(CYCLE);

        this.queue = new ConcurrentLinkedQueue<>();

        this.monitor = new Monitor(prodStats, consStats);
        this.prod = new Producer(queue, prodStats);
        this.cons = new Consumer(queue, consStats);

    }

    public void start() {

        monitor.start();

        prod.start();
        cons.start();

    }

    public static void main(String[] args) {
        QueuePerfTest perfTest = new QueuePerfTest();
        perfTest.start();

//        TimeStats s = new TimeStats(CYCLE);
//        s.add(4);
//        s.add(4);
//        s.add(4);
//        System.out.println(s.getAverages(System.nanoTime()));
    }

}
