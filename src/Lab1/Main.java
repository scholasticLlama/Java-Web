package Lab1;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main {
    public static void main(String[] args) {

        try{
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter directiry -> ");
            String dir = sc.next();
            System.out.print("Enter minValue -> ");
            double minValue = sc.nextDouble();
            System.out.print("Enter maxValue -> ");
            double maxValue = sc.nextDouble();

            BlockingQueue<File> que = new ArrayBlockingQueue<File> (10);
            FileRunTask running = new FileRunTask(que, new File(dir));

            new Thread(running).start();
            for (int i=0; i<50; i++)
                new Thread(new SearchTask(que, minValue, maxValue)).start();

        } catch (InputMismatchException e) {
            System.out.println("Wrong input");
            System.exit(0);
        }


    }
}
