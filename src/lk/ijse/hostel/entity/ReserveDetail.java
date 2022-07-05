package lk.ijse.hostel.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class ReserveDetail {
    @Id
    private String resId;
    private String id;
    private String code;
    private int qty;
    private BigDecimal keyMoney;
    private String status;

   /* @OneToMany(mappedBy = "code", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<ReserveDetail> reserveDetails;*/

    @ManyToOne
    private Student student;

    @ManyToOne
    private Room room;

    public ReserveDetail(String resId, String id, String code, int qty, BigDecimal keyMoney, String status) {
        this.resId = resId;
        this.id = id;
        this.code = code;
        this.qty = qty;
        this.keyMoney = keyMoney;
        this.status = status;
    }
}
