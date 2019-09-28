package gojek.com.entities;

public class Slot {
    private int slotNum;
    private Status status;

    public Slot(int slotNum){
        this.slotNum = slotNum;
        this.status = Status.AVAI;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
