package com.gojek;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static com.gojek.utils.Const.*;

/**
 * The program implements a ticketing system of a parking lot.
 * @author liyan
 * @since 2019-09-28
 *
 */
public class Main {
    private static final String CREATE_SLOT = "create_parking_lot";
    private static final String PARK = "park";
    private static final String LEAVE = "leave";
    private static final String STATUS = "status";
    private static final String REG_NUM_WITH_COLOR = "registration_numbers_for_cars_with_colour";
    private static final String SLOT_NUM_WITH_COLOR = "slot_numbers_for_cars_with_colour";
    private static final String SLOT_NUM_WITH_REG_NUM = "slot_number_for_registration_number";
    private static final String EXIT = "exit";
    private ParkingControllerInterface parkSlot;

    private Main(){
        parkSlot = new ParkingController();
    }

    private void run(String[] args) throws IOException{
        if (args.length > 0) {
            readInputfile(args[0]);
        } else {
            readFromSTDI();
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        try {
            main.run(args);
        }catch (IOException ex){
            System.out.println("Error getting input file: " + ex);
        }
    }

    /**
     * read the commands from standard input
     */
    private void readFromSTDI() {
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
    private void readInputfile(String fileName) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        lines.forEach(this::processLine);
    }

    /**
     * Process each line of command string and print out the output
     * @param line: input line. contains command and input parameters
     */
    private void processLine(String line) {
        String[] cmdLine = line.split(" ");
        String out;
        switch (cmdLine[0]){
            case CREATE_SLOT:
                out = parkSlot.createNumOfSlot(Integer.parseInt(cmdLine[1]));
                break;
            case PARK:
                out = parkSlot.parkSlot(cmdLine[1], cmdLine[2]);
                break;
            case LEAVE:
                out = parkSlot.leaveSlot(Integer.parseInt(cmdLine[1]));
                break;
            case STATUS:
                out = parkSlot.getStatus();
                break;
            case REG_NUM_WITH_COLOR:
                String[] regNums = parkSlot.getRegNumWithColor(cmdLine[1]);
                if (regNums.length == 0)
                    out = MSG_NOT_FOUND;
                else
                    out = String.join(", ", regNums);
                break;
            case SLOT_NUM_WITH_COLOR:
                int[] slotNum = parkSlot.getSlotNumWithColor(cmdLine[1]);
                if (slotNum.length == 0)
                    out = MSG_NOT_FOUND;
                else
                    out = Arrays.stream(slotNum).mapToObj(String::valueOf).collect(Collectors.joining(", "));
                break;
            case SLOT_NUM_WITH_REG_NUM:
                out = parkSlot.getSlotNumByRegNum(cmdLine[1]);
                break;
            case EXIT:
                System.exit(0);
            default:
                out = MSG_UNKOWN_CMD;


        }
        System.out.println(out);
    }
}
