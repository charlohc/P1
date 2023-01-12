import java.util.ArrayList;
import java.util.Scanner;

public class P1 extends Thread{
    int workPerThread = 0;
    ArrayList<Integer> primeNumbers = new ArrayList<>();

    public void workDividedOnThreads(int startNumber, int endNumber, int threadNumber) {
        workPerThread = (endNumber - startNumber) / threadNumber;
        System.out.println("work per thread: " + workPerThread);
    }

    public void createThreads(int startNumber, int endNumber, int threadNumber) {
        for (int i = 0; i < threadNumber; i++) {
            P1 thread = new P1();
            thread.start();
            System.out.println("thread " + i + " created");

            endNumber = startNumber + workPerThread;
            for (int j = startNumber; j <= endNumber; j++) {
                System.out.println("start " + startNumber + " end " + (startNumber + workPerThread));
                if (isPrime(j)) {
                    primeNumbers.add(i);
                    System.out.println("added prime " + j);
                }
            }
            startNumber = startNumber + workPerThread;
            endNumber = endNumber + workPerThread;
            System.out.println("new start " + startNumber);
            System.out.println("new end " + endNumber);
        }

    }

    public void findPrime(int startNumber, int endNumber) {
        for (int j = startNumber; j <= endNumber; j++) {
            if (isPrime(j)) {
                primeNumbers.add(j);
                System.out.println("added prime " + j);
            }
        }
    }

    private boolean isPrime(int n) {
        // 0 and 1 are neither prime nor composite numbers
        if (n == 0 || n == 1) {
            return false;
        }
        // 2 is a prime number
        if (n == 2) {
            return true;
        }
        // every composite number has a prime factor
        // less than or equal to its square root.
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        P1 main = new P1();
        Scanner in = new Scanner(System.in);
        System.out.println("Type in the start number of the interval");
        int start = in.nextInt();

        System.out.println("Type in the end number of the interval");
        int end = in.nextInt();

        System.out.println("Type in the number of threads");
        int numThreads = in.nextInt();

        main.workDividedOnThreads(start,end,numThreads);
        main.createThreads(start,end,numThreads);

    }
}
