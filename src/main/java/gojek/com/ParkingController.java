package gojek.com;

public class ParkingController implements ParkingControllerInterface{

    public String createNumOfSlot(int num) {
        return null;
    }

    public String leaveSlot(int slotNum) {
        return null;
    }

    public String getStatus() {
        return null;
    }

    public String parkSlot(String regNum, String color) {
        return null;
    }

    public String[] getRegNumWithColor(String color) {
        return new String[0];
    }

    public int[] getSlotNumWithColor(String color) {
        return new int[0];
    }

    public int getSlotNumByRegNum(String regNum) {
        return 0;
    }
}
