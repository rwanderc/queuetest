package queuetest;

import java.util.Date;

/**
 *
 * @author Roberto Wander
 */
public class Monitor extends Thread {

    private final TimeStats prodStats;
    private final TimeStats consStats;
    private final long cycle = 60000;

    public Monitor(final TimeStats consStats, final TimeStats prodStats) {
        super("Monitor");
        this.prodStats = prodStats;
        this.consStats = consStats;
    }

    @Override
    public void run() {

        System.out.println("Started at " + new Date());
        System.out.println("DATE\tnPROD\tPROD\tnCONS\tCONS");

        while (true) {

            long now = System.currentTimeMillis();
            long currMinStart = now - (now % 60000);
            long nextMinStart = currMinStart + 60000;
            long distCurrNext = nextMinStart - now;

            try {
                Thread.sleep(distCurrNext);
            } catch (InterruptedException ex) {
            }

            System.out.println(
                    new Date(currMinStart) + "\t"
                    + prodStats.getCounts(currMinStart) + "\t"
                    + prodStats.getAverages(currMinStart) + "\t"
                    + consStats.getCounts(currMinStart) + "\t"
                    + consStats.getAverages(currMinStart));

        }

    }

}
