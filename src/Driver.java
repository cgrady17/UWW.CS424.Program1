import java.io.*;
import java.nio.file.Files;
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
    public static void main(String[] args) throws IOException, InterruptedException {
        final int n = 35;

        // Write welcome and interrogative
        System.out.print("UWW CS424 | Spring 2016 | Program 1 | Version 1.0\nPlease enter a positive integer: ");

        // Instantiate a Scanner on the System's input
        Scanner scanner = new Scanner(System.in);

        // Get the value of the next Integer from the Scanner
        int n2 = scanner.nextInt();
        // Close the Scanner
        scanner.close();

        // Ensure that the Collatz class is in the current working directory
        // This is really only for my development through IntelliJ, where they
        // are in separate directories
        ensureCollatz();

        System.out.println("Creating the child Process...");

        // Instantiate the ProcessBuilder with the commands to run the Collatz java class
        // with the argument being the value of the User's input
        // I call InheritIO so that it uses the same IO as the current (parent) process
        ProcessBuilder pBuilder = new ProcessBuilder("java", "Collatz", String.valueOf(n2)).inheritIO();

        System.out.println("Starting the child Process...");

        // Start the new child Process
        Process childP = pBuilder.start();

        System.out.println("Child Process is running...");

        // Wait for the Process to complete, getting the result code
        int resultCode = childP.waitFor();

        System.out.println("Child Process has finished execution.");

        // Exit if the child Process' result code is not 0 (success)
        if (resultCode != 0) {
            System.out.println("Error in execution. Exiting...");
            return;
        }

        System.out.println("\nCollatz's Conjecture has been proven. We're done!");
    }

    /**
     * Ensures that Collatz.class exists in the current working directory.
     * Attempts to copy the file to the current working directory if possible.
     *
     * @throws IOException Thrown if the file does not exist on copying.
     */
    private static void ensureCollatz() throws IOException {
        File collatzFile = new File("Collatz.class");
        if (collatzFile.exists()) return;

        File outputFile = new File("out/production/UWW.CS424.Program1/Collatz.class");
        if (outputFile.exists()) Files.copy(outputFile.toPath(), collatzFile.toPath());
    }

    /**
     * Returns a single String representing the value of an InputStream.
     *
     * I originally had this before the Child Process used the same IO
     * of the Parent. It's potentially useful, though, so I haven't
     * removed it.
     *
     * @param inputStream The InputStream to convert to a String.
     * @return String representing the value of the InputStream.
     * @throws IOException Thrown when reading the InputStream.
     */
    private static String output(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append(System.getProperty("line.separator"));
            }
        }

        return stringBuilder.toString();
    }
}
