package gojek.com;

import gojek.com.entities.Slot;
import gojek.com.entities.SlotRegistry;
import gojek.com.entities.Status;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class ParkingController implements ParkingControllerInterface{
    List<Slot> curSlot;
    /**
     * priority queue to keep track of available slots
     * the slot with smallest slot number will be on top priority
     */
    PriorityQueue<Slot> availableSlot;
    List<SlotRegistry> allOccupiedSlot;

    ParkingController(){
        curSlot = new ArrayList<>();
        availableSlot = new PriorityQueue<>(Comparator.comparingInt(Slot::getSlotNum));
        allOccupiedSlot = new ArrayList<>();
    }

    public String createNumOfSlot(int num) {
        int maxNum = curSlot.size();
        for (int i = 0; i < num; i++) {
            Slot newSlot = new Slot(++maxNum);
            curSlot.add(newSlot);
            setSlotAvailable(newSlot);
        }
        return String.format("Created a parking lot with %d slots", num);
    }

    public String leaveSlot(int slotNum) {
        List<SlotRegistry> slotRegistries = allOccupiedSlot.stream().filter(i -> i.getSlotNum() == slotNum).collect(Collectors.toList());
        assert slotRegistries.size() < 2: "Corrupted data: one slot has multiple cars";
        if(slotRegistries.size() == 1){
            SlotRegistry slotRegistry = slotRegistries.get(0);
            setSlotAvailable(slotRegistry.getSlot());
            allOccupiedSlot.remove(slotRegistry);
        }

        return String.format("Slot number %d is free", slotNum);
    }

    private void setSlotAvailable(Slot slot) {
        slot.setStatus(Status.AVAI);
        availableSlot.offer(slot);
    }

    public String getStatus() {
        return null;
    }

    public String parkSlot(String regNum, String color) {
        if (availableSlot.isEmpty()){
            return "Sorry, parking lot is full";
        } else {
            Slot top = availableSlot.poll();
            top.setStatus(Status.OCCUPIED);
            SlotRegistry registry = new SlotRegistry(regNum, color, top.getSlotNum(), top);
            allOccupiedSlot.add(registry);
            return String.format("Allocated slot number: %d", top.getSlotNum());
        }
    }

    public String[] getRegNumWithColor(String color) {
        return allOccupiedSlot.stream().filter(i -> color.equals(i.getColor()))
                .map(SlotRegistry::getRegNum).toArray(String[]::new);
    }

    public int[] getSlotNumWithColor(String color) {
        return allOccupiedSlot.stream().filter(i -> color.equals(i.getColor()))
                .mapToInt(SlotRegistry::getSlotNum).toArray();
    }

    public int getSlotNumByRegNum(String regNum) {
        int[] slotNums = allOccupiedSlot.stream().filter(i -> regNum.equals(i.getRegNum()))
                .mapToInt(SlotRegistry::getSlotNum).toArray();
        assert slotNums.length < 2 : "Corrupted data: Multiple slot with same registration number";
        return slotNums[0];
    }
}
