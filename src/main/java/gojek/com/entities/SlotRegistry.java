package gojek.com.entities;

public class SlotRegistry {


    private String regNum;
    private String color;
    private int slotNum;

    public SlotRegistry(String regNum, String color, int slot){
        this.regNum = regNum;
        this.color = color;
        this.slotNum = slot;
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSlotNum() {
        return slotNum;
    }

    public void setSlotNum(int slotNum) {
        this.slotNum = slotNum;
    }
}
