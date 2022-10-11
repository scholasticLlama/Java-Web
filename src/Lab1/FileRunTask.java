package Lab1;

import java.io.File;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class FileRunTask implements Runnable {
    private final BlockingQueue <File> que;
    private final File startDir;
    public static final File EXIT = new File("");
    public FileRunTask(BlockingQueue <File> que,  File startDir) {
        this.que = que;
        this.startDir = startDir;
    }
    public void run() {
        try {
            runDir(startDir);
            que.put(EXIT);
        } catch (InterruptedException ignored) { }

    }

    public void runDir(File dir) throws InterruptedException {
        File[] files = dir.listFiles();
        assert files != null;
        for (File ff: files)
            if (ff.isDirectory())
                runDir(ff);
            else
                que.put(ff);

    }
}
