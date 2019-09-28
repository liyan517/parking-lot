package gojek.com;


import org.junit.Before;
import org.junit.Test;

import static gojek.com.utils.Const.*;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ParkingControllerTest {
    private ParkingController parkingController;
    @Before
    public void setup(){
        parkingController = new ParkingController();
    }

    @Test
    public void createNumOfSlot_empty() {
        parkingController.createNumOfSlot(2);
        assertEquals(2, parkingController.availableSlot.size());
        assertEquals(2, parkingController.curSlot.size());
        assert parkingController.availableSlot.peek() != null;
        assertEquals(1, parkingController.availableSlot.peek().getSlotNum());

    }

    @Test
    public void leaveSlot_leaveAvailableSlot() {
        parkingController.createNumOfSlot(2);
        String out = parkingController.leaveSlot(1);
        assertEquals(String.format(TEMP_LEAVE_SLOT, 1), out);
    }

    @Test
    public void leaveSlot_leaveMultipleTimes(){
        parkingController.createNumOfSlot(2);
        parkingController.parkSlot("reg1", "white");
        parkingController.parkSlot("reg2", "white");
        String out = parkingController.leaveSlot(1);
        assertEquals(String.format(TEMP_LEAVE_SLOT, 1), out);
        String park1 = parkingController.parkSlot("reg3", "black");
        assertEquals(String.format(TEMP_ALLOCATE_SLOT, 1) ,park1);
        String out2 = parkingController.leaveSlot(2);
        assertEquals(String.format(TEMP_LEAVE_SLOT, 2), out2);
        assertEquals(1, parkingController.availableSlot.size());
        assertEquals(1, parkingController.allOccupiedSlot.size());
    }

    @Test
    public void getStatus() {
        parkingController.createNumOfSlot(2);
        parkingController.parkSlot("reg1", "white");
        parkingController.parkSlot("reg2", "white");
        String statusStr = parkingController.getStatus();
        System.out.println(statusStr);
    }

    @Test
    public void parkSlot_basic() {
        parkingController.createNumOfSlot(2);
        parkingController.parkSlot("reg1", "white");
        assertEquals(1, parkingController.availableSlot.size());
        assertEquals(1, parkingController.allOccupiedSlot.size());
    }

    @Test
    public void parkSlot_noSlot() {
        String out = parkingController.parkSlot("reg1", "white");
        assertEquals(MSG_PARK_FULL, out);
    }

    @Test
    public void parkSlot_more_slot() {
        parkingController.createNumOfSlot(2);
        parkingController.parkSlot("reg1", "white");
        parkingController.createNumOfSlot(2);
        assert parkingController.availableSlot.size() == 3;
        String out = parkingController.parkSlot("reg1", "white");
        assertEquals(String.format(TEMP_ALLOCATE_SLOT, 2),out);

    }

    @Test
    public void getRegNumWithColor_basic() {
        parkingController.createNumOfSlot(2);
        parkingController.parkSlot("reg1", "white");
        parkingController.parkSlot("reg2", "black");
        String[] regNum = parkingController.getRegNumWithColor("white");
        assertEquals(1, regNum.length);
        assertEquals("reg1", regNum[0]);
    }

    @Test
    public void getSlotNumWithColor_basic() {
        parkingController.createNumOfSlot(2);
        parkingController.parkSlot("reg1", "white");
        parkingController.parkSlot("reg2", "black");
        int[] slotNumWithColor = parkingController.getSlotNumWithColor("white");
        assertEquals(1, slotNumWithColor.length);
        assertEquals(1, slotNumWithColor[0]);
    }

    @Test
    public void getSlotNumWithColor_twoValue() {
        parkingController.createNumOfSlot(4);
        parkingController.parkSlot("reg1", "black");
        parkingController.parkSlot("reg2", "white");
        parkingController.parkSlot("reg3", "green");
        parkingController.parkSlot("reg4", "white");
        int[] slotNumWithColor = parkingController.getSlotNumWithColor("white");
        assertEquals(2, slotNumWithColor.length);
        int[] result = {2,4};
        assertArrayEquals(result, slotNumWithColor);
    }


    @Test
    public void getSlotNumByRegNum_basic() {
        parkingController.createNumOfSlot(2);
        parkingController.parkSlot("reg1", "white");
        parkingController.parkSlot("reg2", "black");
        String slotNum = parkingController.getSlotNumByRegNum("reg1");
        assertEquals("1", slotNum);
    }
}