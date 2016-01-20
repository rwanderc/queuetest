package queuetest;

import java.util.HashMap;
import lombok.Data;

/**
 *
 * @author Roberto Wander
 */
@Data
public class TimeStats {

    private final HashMap<Long, Long> counts;
    private final HashMap<Long, Double> averages;

    public TimeStats(final long nanoCycle) {
        this.counts = new HashMap<>();
        this.averages = new HashMap<>();
    }

    public HashMap<Long, Long> getCounts() {
        return counts;
    }

    public Long getCounts(Long time) {
        if (time == null) {
            return null;
        }
        return counts.get(time - (time % 60000));
    }

    public HashMap<Long, Double> getAverages() {
        return averages;
    }

    public Double getAverages(Long time) {
        if (time == null) {
            return null;
        }
        return averages.get(time - (time % 60000));
    }

    public synchronized void add(long count, double totalTime) {

        if (count == 0) {
            return;
        }

        Double avg = null;
        Long n = null;

        long now = System.currentTimeMillis();
        long time = now - (now % 60000);

        if (counts.get(time) == null && averages.get(time) == null) {

            averages.put(time, totalTime / count);
            counts.put(time, count);

        } else {

            avg = averages.get(time);
            n = counts.get(time);

            averages.put(time, (double) ((avg * n) + (totalTime / count)) / (n + count));
            counts.put(time, n + count);

        }

    }

}
