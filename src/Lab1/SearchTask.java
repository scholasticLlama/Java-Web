package Lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;

public class SearchTask implements Runnable {
    private BlockingQueue <File> que;
    private double minValue;
    private double maxValue;
    SearchTask(BlockingQueue <File> que, double minValue, double maxValue) {
        this.que = que;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
    public void search(File ff) throws IOException {
        Scanner sc = new Scanner(new FileInputStream(ff));
        while (sc.hasNextLine()) {
            String inputString = sc.nextLine();
            String[] strings = inputString.split(" ");
            for (String string : strings) {
                try{
                    if (Double.parseDouble(string) >= minValue && Double.parseDouble(string) <= maxValue)
                        System.out.println(ff.getPath()

                                + " -> " + inputString);

                } catch (NumberFormatException e) {}

            }
        }
        sc.close();
    }

    public void run() {
        try {

            while (true) {

                File ff = que.take();
                if (ff == FileRunTask.EXIT) {

                    que.put(ff);
                    break;

                } else
                    if (ff.getName().endsWith(".cs"))
                        search(ff);

            }

        } catch (IOException e) { e.printStackTrace();
        } catch (InterruptedException e) { }
    }
}
