import java.util.Objects;

/**
 * UWW CS424 | Spring 2016 | Program 1
 * This class implements the Collatz Conjecture, wherein
 * any positive integer becomes 1 when the following algorithm
 * is repeatedly applied: n = n/2 if n is even, n = 3(n) + 1 if odd.
 *
 * @author Connor P Grady (1765561)
 * @version 1.0
 */
public class Collatz {
    private int n;

    /**
     * Entry point of the Collatz program. Initializes and executes
     * the program.
     * @param args Specify value of n, parsable to positive Integer.
     */
    public static void main(String[] args) {
        // Get the value of n from the args
        String nStr = Objects.requireNonNull(args)[0];

        // Instantiate the Collatz object with the value of n
        Collatz collatz = new Collatz(Integer.parseInt(nStr));

        // Execute the Collatz program
        collatz.run();
    }

    /**
     * Initializes a new instance of Collatz with the specified starting Integer.
     * @param n The Integer with which to run the Collatz Conjecture.
     */
    public Collatz(int n) {
        this.n = n;
    }

    /**
     * Executes the Collatz Conjecture, outputting results to the Console.
     */
    public void run() {
        // Make sure n is a positive integer
        if (n < 1) throw new IllegalArgumentException("n out of range, must be greater than 0");

        // Write starting value of n
        System.out.println(n);

        // We subject n to the algorithm until it reaches a value of 1
        while (n != 1) {
            // Subject n to the algorithm
            // If n is even: n/2
            // If n is odd: 3(n) + 1
            n = ((n % 2) == 0) ? (n / 2) : ((n * 3) + 1);

            // Write the current value of n
            System.out.println(n);
        }
    }
}
