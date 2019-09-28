package gojek.com;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * The program implements a ticketing system of a parking lot.
 * @author liyan
 * @since 2019-09-28
 *
 */
public class Main {


    public static void main(String[] args) {
        try {
            if (args.length > 0) {
                readInputfile(args[0]);
            } else {
                readFromSTDI();
            }
        }catch (IOException ex){
            System.out.println("Error getting input file: " + ex);
        }

    }

    /**
     * read the commands from standard input
     */
    private static void readFromSTDI() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            processLine(line);
        }
    }

    /**
     * read the commands from an input file
     * @param fileName: the input file name.
     * @throws IOException: exception when reading files
     */
    private static void readInputfile(String fileName) throws IOException {
        Files.readAllLines(Paths.get(fileName));
    }

    /**
     * Process each line of command string and print out the output
     * @param line: input line. contains command and input parameters
     */
    private static void processLine(String line) {

    }
}
