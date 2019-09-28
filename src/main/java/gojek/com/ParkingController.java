package gojek.com;

import gojek.com.entities.Slot;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ParkingController implements ParkingControllerInterface{
    List<Slot> curSlot;
    /**
     * priority queue to keep track of available slots
     * the slot with smallest slot number will be on top priority
     */
    PriorityQueue<Slot> availableSlot;

    ParkingController(){
        curSlot = new ArrayList<>();
        availableSlot = new PriorityQueue<>(Comparator.comparingInt(Slot::getSlotNum));
    }

    public String createNumOfSlot(int num) {
        int maxNum = curSlot.size();
        while (num-- > 0){
            Slot newSlot = new Slot(++maxNum);
            curSlot.add(newSlot);
            availableSlot.offer(newSlot);
        }
        return String.format("Created a parking lot with %d slots", num);
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
