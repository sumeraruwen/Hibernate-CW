package lk.ijse.hostel.dto;

import lombok.*;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class RoomDTO {
    private String code;
    private String type;
    private BigDecimal keyMoney;
    private int qty;


}
