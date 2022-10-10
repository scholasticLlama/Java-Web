package template;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter directiry -> ");
        String dir = sc.next();
        System.out.print("Enter keyword -> ");
        String word = sc.next();
        CounterMath counter = new CounterMath(new File(dir), word);
        FutureTask<Integer> task = new FutureTask<Integer>(counter);
        new Thread(task).start();
        try {
            System.out.println(task.get() + " files.");
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
