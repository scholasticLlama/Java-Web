package Lab1;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Main {
    private static String enterDir(Scanner sc) {
        String dir = sc.next();
        if ((new File(dir)).exists())
            return dir;
        else {
            System.out.print("That directory is incorrect. Enter directory -> ");
            return enterDir(sc);
        }

    }

    private static double enterNUmber(Scanner sc) {
        String number = sc.next();
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e){
            System.out.print("Wrong input. Enter double -> ");
            return enterNUmber(sc);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter directory -> ");
        String dir = enterDir(sc);
        System.out.print("Enter minValue -> ");
        double minValue = enterNUmber(sc);
        System.out.print("Enter maxValue -> ");
        double maxValue = enterNUmber(sc);

        if (maxValue < minValue) {
            double tempValue = minValue;
            minValue = maxValue;
            maxValue = tempValue;
        }

        BlockingQueue<File> que = new ArrayBlockingQueue<File> (10);
        FileRunTask running = new FileRunTask(que, new File(dir));

        new Thread(running).start();
        for (int i=0; i<50; i++)
            new Thread(new SearchTask(que, minValue, maxValue)).start();

    }
}
