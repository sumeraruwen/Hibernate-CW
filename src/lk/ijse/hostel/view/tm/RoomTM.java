package lk.ijse.hostel.view.tm;

import java.math.BigDecimal;

public class RoomTM {
    private String code;
    private String type;
    private BigDecimal keyMoney;
    private int qty;

    public RoomTM(String code, String type, BigDecimal keyMoney, int qty) {
        this.code = code;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }

    public RoomTM() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(BigDecimal keyMoney) {
        this.keyMoney = keyMoney;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "RoomTM{" +
                "code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", keyMoney=" + keyMoney +
                ", qty=" + qty +
                '}';
    }
}
