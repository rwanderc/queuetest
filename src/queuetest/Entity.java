package queuetest;

import java.util.Random;

/**
 *
 * @author Roberto Wander
 */
public class Entity {

    public Double[] data;

    public Entity() {

        Random r = new Random();
        int n = r.nextInt(101);
        data = new Double[n];
        for (int i = 0; i < n; i++) {
            data[i] = r.nextDouble();
        }

    }

}
