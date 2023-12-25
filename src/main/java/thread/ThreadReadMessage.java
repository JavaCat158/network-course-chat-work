package thread;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ThreadReadMessage extends Thread {
    private int count = -1;

    @Override
    public void run() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/client_logger.log"))
        ) {
            int innerCount = 0;
            String line;
            while (!isInterrupted()) {
                while ((line = bufferedReader.readLine()) != null) {
                    innerCount++;
                    if (count > -1) {
                        if (innerCount > count) {
                            System.out.println(line);
                        }
                    } else {
                        System.out.println(line);
                    }
                }
                count = innerCount;
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            return;
        }
    }
}
