import java.util.ArrayList;
import java.util.Scanner;

public class Finding_primes_with_threads extends Thread{
    double workPerThread = 0;
    ArrayList<Integer> primeNumbers = new ArrayList<>();

    public void workDividedOnThreads(double startNumber, double endNumber, int threadNumber) {
        workPerThread = (endNumber - startNumber) / threadNumber;
    }

    public void createThreads(double startNumber, double endNumber, int threadNumber) {
        for (int i = 1; i <= threadNumber; i++) {
            Finding_primes_with_threads thread = new Finding_primes_with_threads();
            thread.start();

            for (int j = (int) startNumber; j <= endNumber; j++) {
                if (isPrime(j)) {
                    if (!primeNumbers.contains(j)) {
                        primeNumbers.add(j);
                    }
                }
            }
            startNumber = (startNumber + workPerThread);
            endNumber = (endNumber + workPerThread);
        }

    }

    private boolean isPrime(int n) {
        if (n == 0 || n == 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Finding_primes_with_threads main = new Finding_primes_with_threads();
        Scanner in = new Scanner(System.in);
        System.out.println("Type in the start number of the interval");
        int start = in.nextInt();

        System.out.println("Type in the end number of the interval");
        int end = in.nextInt();

        System.out.println("Type in the number of threads");
        int numThreads = in.nextInt();

        main.workDividedOnThreads(start,end,numThreads);
        main.createThreads(start, (start + main.workPerThread),numThreads);

        System.out.println(main.primeNumbers);
        System.out.println("Created " + numThreads + " threads from the interval " + start + " - " +
                end + ", each thread has a workload of " + main.workPerThread + " number(s)");
    }
}
