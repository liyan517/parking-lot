package gojek.com;


import org.junit.Before;
import org.junit.Test;

public class ParkingControllerTest {
    private ParkingController parkingController;
    @Before
    public void setup(){
        parkingController = new ParkingController();
    }

    @Test
    public void createNumOfSlot_empty() {
        parkingController.createNumOfSlot(2);
        assert parkingController.availableSlot.size() == 2;
        assert parkingController.curSlot.size() == 2;
        assert parkingController.availableSlot.peek().getSlotNum() == 1;

    }

    @Test
    public void leaveSlot() {
    }

    @Test
    public void getStatus() {
    }

    @Test
    public void parkSlot() {
    }

    @Test
    public void getRegNumWithColor() {
    }

    @Test
    public void getSlotNumWithColor() {
    }

    @Test
    public void getSlotNumByRegNum() {
    }
}