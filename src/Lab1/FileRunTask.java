package Lab1;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class FileRunTask implements Runnable {
    private BlockingQueue <File> que;
    private File startDir;
    public static final File EXIT = new File("");
    public FileRunTask(BlockingQueue <File> que,

                       File startDir) {

        this.que = que;
        this.startDir = startDir;
    }
    public void run() {

        try {
            runDir(startDir);
            que.put(EXIT);
        } catch (NullPointerException e) {
            System.out.println("That directory doesn't exist");
            System.exit(0);
        } catch (InterruptedException e) { }

    }

    public void runDir(File dir) throws InterruptedException {
        File[] files = dir.listFiles();
        for (File ff: files)
            if (ff.isDirectory())
                runDir(ff);
            else
                que.put(ff);

    }
}
