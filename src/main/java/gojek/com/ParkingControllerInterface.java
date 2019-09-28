package gojek.com;

public interface ParkingControllerInterface {
    /**
     * Handle command create_parking_lot
     * take in the number of slots to be created
     * create new available slots.
     * @param num: number of new slots
     * @return: status message of the create command
     */
    String createNumOfSlot(int num);

    /**
     * Handle command leave
     * called when a car exit the parking lot(by slotNum)
     * @param slotNum: the slot id
     * @return: status message of the leave command
     */
    String leaveSlot(int slotNum);

    /**
     * get the current available slots of the parking lots
     * @return: output string
     */
    String getStatus();

    /**
     * called when a car entered a parking lot
     * @param regNum: registration number
     * @param color: color of a car
     * @return: parking message
     */
    String parkSlot(String regNum, String color);

    /**
     * get the current list of registered number in the parking
     * lot that occupied by cards with color
     * @param color: car color
     * @return: list of registration number
     */
    String[] getRegNumWithColor(String color);

    /**
     * get the slot number that occupied by cars with color
     * @param color: car color
     * @return: list of slot numbers
     */
    int[] getSlotNumWithColor(String color);

    /**
     * get the parking slot number with registration number
     * @param regNum : registration number
     * @return: the parking slot numnber
     */
    String getSlotNumByRegNum(String regNum);
}
