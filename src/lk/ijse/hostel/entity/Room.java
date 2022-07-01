package lk.ijse.hostel.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
@Entity
public class Room {
    @Id
    private String code;
    private String type;
    private BigDecimal keyMoney;
    private int qty;

   /* @Transient
    @OneToMany(cascade = CascadeType.ALL)
    private List<Student> studentList ;
*/
   /* @ManyToMany(mappedBy = "rooms")
    private List<Student> studentList = new ArrayList<>();*/

    @OneToMany(mappedBy = "room",cascade = CascadeType.ALL)
    List<ReserveDetail> room;

    public Room(String code, String type, BigDecimal keyMoney, int qty) {
        this.code = code;
        this.type = type;
        this.keyMoney = keyMoney;
        this.qty = qty;
    }
}
