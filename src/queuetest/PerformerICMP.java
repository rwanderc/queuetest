package queuetest;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author Roberto Wander
 */
public class PerformerICMP {

    private static final String COMMAND = "/bin/ping -n -c1 -2 localhost";

    public int ping() {
        
        String command = COMMAND;
        Process p = null;

        try {

            p = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line;
            do {
                line = reader.readLine();
                if (line != null && line.contains("time=") && line.contains(" ms")) {
                    int ind1 = line.indexOf("time=") + 5;
                    int ind2 = line.indexOf(" ms");
                    float resp = Float.valueOf(line.substring(ind1, ind2));
                    return Math.round(resp);
                }
            } while (line != null);

            return -1;

        } catch (Throwable ex) {

            return -2;

        } finally {

            try {
                if (p != null) {
                    p.destroy();
                }
            } catch (Throwable ex) {
            }

        }
    }

}
