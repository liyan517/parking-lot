package com.gojek;

import com.gojek.entities.Slot;
import com.gojek.entities.SlotRegistry;
import com.gojek.utils.Const;
import com.gojek.entities.Status;
import sun.jvm.hotspot.utilities.Assert;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * Implement the parking lot logic
 * @author liyan
 */
public class ParkingController implements ParkingControllerInterface{
    List<Slot> curSlot;
    /**
     * priority queue to keep track of available slots
     * the slot with smallest slot number will be on top priority
     */
    PriorityQueue<Slot> availableSlot;

    /**
     * slots that are currently registered
     */
    List<SlotRegistry> allOccupiedSlot;

    ParkingController(){
        curSlot = new ArrayList<>();
        availableSlot = new PriorityQueue<>(Comparator.comparingInt(Slot::getSlotNum));
        allOccupiedSlot = new ArrayList<>();
    }

    /**
     * @param num: number of new slots
     * @return: creation message
     */
    public String createNumOfSlot(int num) {
        int maxNum = curSlot.size();
        for (int i = 0; i < num; i++) {
            Slot newSlot = new Slot(++maxNum);
            curSlot.add(newSlot);
            setSlotAvailable(newSlot);
        }
        return String.format(Const.TEMP_CREATE_SLOT, num);
    }

    /**
     * @param slotNum: the slot id
     * @return: leave slot message
     */
    public String leaveSlot(int slotNum) {
        // get the current registration information with slot number
        List<SlotRegistry> slotRegistries = allOccupiedSlot.stream()
                .filter(i -> i.getSlotNum() == slotNum).collect(Collectors.toList());
        Assert.that(slotRegistries.size() < 2, "Corrupted data: one slot has multiple cars");
        if(slotRegistries.size() == 1){
            //free the slot
            SlotRegistry slotRegistry = slotRegistries.get(0);
            setSlotAvailable(slotRegistry.getSlot());
            allOccupiedSlot.remove(slotRegistry);
        }

        return String.format(Const.TEMP_LEAVE_SLOT, slotNum);
    }

    /**
     * Set the input slot available and add the available slot queue
     * @param slot: the slot to be set as available
     */
    private void setSlotAvailable(Slot slot) {
        slot.setStatus(Status.AVAI);
        availableSlot.offer(slot);
    }

    public String getStatus() {
        String header = "Slot No.    Registration No    Colour" + System.lineSeparator();
        allOccupiedSlot.sort(Comparator.comparingInt(SlotRegistry::getSlotNum));
        String res = allOccupiedSlot.stream().map(Object::toString).collect(Collectors.joining(System.lineSeparator()));
        return header + res;
    }

    /**
     * park car with color and a registration number.
     * assume registration number is unique.
     * @param regNum: registration number
     * @param color:  color of a car
     * @return
     */
    public String parkSlot(String regNum, String color) {
        if (availableSlot.isEmpty()){
            return Const.MSG_PARK_FULL;
        } else {
            // validate if the registration number is unique
            if (!Const.MSG_NOT_FOUND.equals(this.getSlotNumByRegNum(regNum)))
                return String.format(Const.TEMP_REGNUM_EXIST, regNum);
            else {
                Slot top = availableSlot.poll();
                top.setStatus(Status.OCCUPIED);
                SlotRegistry registry = new SlotRegistry(regNum, color, top.getSlotNum(), top);
                allOccupiedSlot.add(registry);
                return String.format(Const.TEMP_ALLOCATE_SLOT, top.getSlotNum());
            }
        }
    }

    /**
     * Get the list of registration numbers that currently registered by car with given color
     * @param color: car color
     * @return: list of registration numbers
     */
    public String[] getRegNumWithColor(String color) {
        return allOccupiedSlot.stream().filter(i -> color.equals(i.getColor()))
                .map(SlotRegistry::getRegNum).toArray(String[]::new);
    }

    /**
     * Get the list of slot numbers that currently registered by car with given color
     * @param color: car color
     * @return: list of slot numbers
     */
    public int[] getSlotNumWithColor(String color) {
        return allOccupiedSlot.stream().filter(i -> color.equals(i.getColor()))
                .mapToInt(SlotRegistry::getSlotNum).toArray();
    }

    /**
     * Get the only slot that currently registered with given registration number
     * @param regNum : registration number
     * @return: return registration number or "Not found" message
     */
    public String getSlotNumByRegNum(String regNum) {
        int[] slotNums = allOccupiedSlot.stream().filter(i -> regNum.equals(i.getRegNum()))
                .mapToInt(SlotRegistry::getSlotNum).toArray();
        Assert.that(slotNums.length < 2, "Corrupted data: Multiple slot with same registration number");
        if (slotNums.length == 1){
            return String.valueOf(slotNums[0]);
        }else {
            return Const.MSG_NOT_FOUND;
        }
    }
}
