import java.util.Scanner;

/**
 * UWW CS424 | Spring 2016 | Problem 1
 * This program is an example of a multi-process (two, in this case)
 * program. In this program, the Driver operates as the Parent process
 * and the Collatz class operates on a child process.
 *
 * @author Connor P Grady (1765561)
 * @version 1.0
 */
public class Driver {

    /**
     * Program entry-point. Initiates the Collatz program in
     * a child process.
     *
     * @param args CLI arguments appended to the application's execution.
     *             Should be none in this case.
     */
    public static void main(String[] args) {
        final int n = 35;

        System.out.print("UWW CS424 | Spring 2016 | Program 1 | Version 1.0\nPlease enter a positive integer: ");

        Scanner scanner = new Scanner(System.in);

        int n2 = scanner.nextInt();

        scanner.close();

        Collatz collatz = new Collatz(n2);
        collatz.run();

        System.out.println("\nThanks and goodbye.");
    }
}
