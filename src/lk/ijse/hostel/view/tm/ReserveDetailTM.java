package lk.ijse.hostel.view.tm;

import java.math.BigDecimal;

public class ReserveDetailTM {
     private String resId;
     private String id;
     private String code;
     private int qty;
     private BigDecimal keyMoney;
     private String status;

    public ReserveDetailTM(String resId, String id, String code, int qty, BigDecimal keyMoney, String status) {
        this.resId = resId;
        this.id = id;
        this.code = code;
        this.qty = qty;
        this.keyMoney = keyMoney;
        this.status = status;
    }

    public ReserveDetailTM() {
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getKeyMoney() {
        return keyMoney;
    }

    public void setKeyMoney(BigDecimal keyMoney) {
        this.keyMoney = keyMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ReserveDetailTM{" +
                "resId='" + resId + '\'' +
                ", id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", qty=" + qty +
                ", keyMoney=" + keyMoney +
                ", status='" + status + '\'' +
                '}';
    }
}
