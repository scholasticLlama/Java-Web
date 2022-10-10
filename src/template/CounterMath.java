package template;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CounterMath implements Callable<Integer> {
    private File dir;
    private String word;

    public CounterMath(File dir, String word) {
        this.dir = dir;
        this.word = word;
    }

    public boolean search(File ff){
        try (Scanner sc = new Scanner(new FileInputStream(ff))) {
            boolean flag = false;
            while ( !flag && sc.hasNextLine()) {

                String str = sc.nextLine();
                if (str.contains(word))
                    flag = true;

            }
            return flag;
        } catch (IOException e) {
            return false;
        }
    }


    @Override
    public Integer call() throws Exception {
        int count = 0;
        try {
            File[] files = dir.listFiles();
            List<Future<Integer>> result = new ArrayList<>();
            for (File ff : files)
                if (ff.isDirectory()) {
                    CounterMath counter =

                            new CounterMath(ff, word);

                    FutureTask<Integer> task =

                            new FutureTask<Integer>(counter);

                    result.add(task);
                    new Thread(task).start();
                } else if ( search(ff) )
                    count++;

            for (Future<Integer> rez : result)
                count += rez.get();

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }
}
